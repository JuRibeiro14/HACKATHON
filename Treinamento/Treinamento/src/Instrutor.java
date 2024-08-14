import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Instrutor {

    ConnectionDB connectionDB = new ConnectionDB();
    Connection conn = connectionDB.getConnection();

    private String nome;
    private boolean disponivel;
    private String linguagem;

    public Instrutor(String nome, boolean disponivel, String linguagem) {
        this.nome = nome;
        this.disponivel = disponivel;
        this.linguagem = linguagem;
    }

    public int getId(Connection conn, Instrutor instrutor){
        String SQL = "SELECT id FROM instrutor WHERE nome = ?";
        int id = -1; // Valor padrão para indicar que o ID não foi encontrado

        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, nome);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o ID do instrutor: " + e.getMessage());
        }

        return id;
    }
    public boolean isDisponivel() {
        return disponivel;
    }

    public String getNome() {
        return nome;
    }
   
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getLinguagem() {
    	return linguagem;
    }
    
    @Override
    public String toString() {
        return nome +
                '.';
    }
}
