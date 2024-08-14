import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoDAO {

    private Connection conn;

    public PedidoDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvarPedido(Pedido pedido) throws SQLException {
        String sqlPedido = "INSERT INTO Pedido(numero, taxaEntrega) VALUES (?, ?)";
        String sqlItem = "INSERT INTO ItemPedido(pedido_id, numero_pedido, nomeDoPrato, quantidade, precoUnitario) VALUES (?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPedido = conn.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmtPedido.setInt(1, pedido.getNumero());
                pstmtPedido.setDouble(2, pedido.getTaxaEntrega());
                int affectedRows = pstmtPedido.executeUpdate();

                if (affectedRows > 0) {
                    try (var rs = pstmtPedido.getGeneratedKeys()) {
                        if (rs.next()) {
                            int pedidoId = rs.getInt(1);

                            try (PreparedStatement pstmtItem = conn.prepareStatement(sqlItem)) {
                                for (ItemPedido item : pedido.getItens()) {
                                    pstmtItem.setInt(1, pedidoId);
                                    pstmtItem.setInt(2, pedido.getNumero());
                                    pstmtItem.setString(3, item.getNomeDoPrato());
                                    pstmtItem.setInt(4, item.getQuantidade());
                                    pstmtItem.setDouble(5, item.getPrecoUnitario());
                                    pstmtItem.executeUpdate();
                                }
                            }
                        }
                    }
                }
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
