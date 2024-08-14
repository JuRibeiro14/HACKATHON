import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InterfaceRestaurante {

    private JFrame frame;
    private JTextField textFieldNome;
    private JTextField textFieldQuantidade;
    private JTextField textFieldPreco;
    private JTextField textFieldNumeroPedido;
    private JTextField textFieldNumeroMesa;
    private JTextArea textAreaPedidos;
    private Restaurante restaurante;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {



            public void run() {
                try {
                    InterfaceRestaurante window = new InterfaceRestaurante();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InterfaceRestaurante() {
        restaurante = new Restaurante();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 13));
        frame.setBackground(new Color(108, 75, 34));
        frame.getContentPane().setBackground(new Color(108, 75, 34));
        frame.setBounds(100, 100, 800, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        ImageIcon originalImage = new ImageIcon(getClass().getResource("Logo.png"));
        Image scaledImage = originalImage.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        ImageIcon resizedImage = new ImageIcon(scaledImage);
        JLabel label = new JLabel(resizedImage);
        label.setBounds(10, 42, 227, 300);
        frame.getContentPane().add(label);

        JLabel lblNumeroPedido = new JLabel("Número do Pedido:");
        lblNumeroPedido.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNumeroPedido.setForeground(new Color(218, 198, 171));
        lblNumeroPedido.setBounds(248, 76, 120, 14);
        frame.getContentPane().add(lblNumeroPedido);

        textFieldNumeroPedido = new JTextField();
        textFieldNumeroPedido.setBackground(new Color(218, 198, 171));
        textFieldNumeroPedido.setBounds(378, 75, 80, 20);
        frame.getContentPane().add(textFieldNumeroPedido);
        textFieldNumeroPedido.setColumns(10);

        JLabel lblNumeroMesa = new JLabel("Número da Mesa:");
        lblNumeroMesa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNumeroMesa.setForeground(new Color(218, 198, 171));
        lblNumeroMesa.setBounds(248, 197, 120, 14);
        frame.getContentPane().add(lblNumeroMesa);

        textFieldNumeroMesa = new JTextField();
        textFieldNumeroMesa.setBackground(new Color(218, 198, 171));
        textFieldNumeroMesa.setBounds(378, 196, 80, 20);
        frame.getContentPane().add(textFieldNumeroMesa);
        textFieldNumeroMesa.setColumns(10);

        JLabel lblNome = new JLabel("Nome do Produto:");
        lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNome.setForeground(new Color(218, 198, 171));
        lblNome.setBounds(247, 118, 120, 14);
        frame.getContentPane().add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setBackground(new Color(218, 198, 171));
        textFieldNome.setBounds(378, 117, 80, 20);
        frame.getContentPane().add(textFieldNome);
        textFieldNome.setColumns(10);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblQuantidade.setForeground(new Color(218, 198, 171));
        lblQuantidade.setBounds(475, 118, 80, 14);
        frame.getContentPane().add(lblQuantidade);

        textFieldQuantidade = new JTextField();
        textFieldQuantidade.setBackground(new Color(218, 198, 171));
        textFieldQuantidade.setBounds(558, 117, 56, 20);
        frame.getContentPane().add(textFieldQuantidade);
        textFieldQuantidade.setColumns(10);

        JLabel lblPreco = new JLabel("Preço/uni:");
        lblPreco.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPreco.setForeground(new Color(218, 198, 171));
        lblPreco.setBounds(485, 76, 80, 14);
        frame.getContentPane().add(lblPreco);

        textFieldPreco = new JTextField();
        textFieldPreco.setBackground(new Color(218, 198, 171));
        textFieldPreco.setBounds(558, 75, 56, 20);
        frame.getContentPane().add(textFieldPreco);
        textFieldPreco.setColumns(10);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(248, 259, 528, 100); // Aumenta a largura e a altura
        frame.getContentPane().add(scrollPane);

        JButton btnAdicionarItem = new JButton("Adicionar Item");
        btnAdicionarItem.setBackground(new Color(255, 255, 255));
        btnAdicionarItem.setForeground(new Color(104, 73, 45));
        btnAdicionarItem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdicionarItem.setBounds(648, 67, 128, 25);
        frame.getContentPane().add(btnAdicionarItem);

        JButton btnRemoverItem = new JButton("Remover Item");
        btnRemoverItem.setBackground(new Color(255, 255, 255));
        btnRemoverItem.setForeground(new Color(104, 73, 45));
        btnRemoverItem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnRemoverItem.setBounds(648, 118, 128, 25);
        frame.getContentPane().add(btnRemoverItem);

        JButton btnReservarMesa = new JButton("Reservar Mesa");
        btnReservarMesa.setBackground(new Color(255, 255, 255));
        btnReservarMesa.setForeground(new Color(104, 73, 45));
        btnReservarMesa.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReservarMesa.setBounds(475, 192, 139, 25);
        frame.getContentPane().add(btnReservarMesa);

        btnReservarMesa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reservarMesa();
            }
        });

        JButton btnCalcularTotal = new JButton("Calcular Total");
        btnCalcularTotal.setBackground(new Color(255, 255, 255));
        btnCalcularTotal.setForeground(new Color(104, 73, 45));
        btnCalcularTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCalcularTotal.setBounds(648, 153, 128, 25);
        frame.getContentPane().add(btnCalcularTotal);



        textAreaPedidos = new JTextArea();
        scrollPane.setViewportView(textAreaPedidos);
        textAreaPedidos.setBackground(new Color(218, 198, 171));
        textAreaPedidos.setEditable(false);

        JLabel lblNewLabel = new JLabel("Para adicionar, remover ou calcular o total do pedido preencha os campos com");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(245, 24, 531, 13);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("o n° do pedido, nome do produto, preço e quantidade:");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_1.setBounds(247, 47, 384, 13);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Ou");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_2.setBounds(709, 95, 45, 13);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Informações do Pedido:");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_3.setBounds(248, 235, 191, 13);
        frame.getContentPane().add(lblNewLabel_3);

        btnAdicionarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });

        btnRemoverItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerItem();
            }
        });

        btnCalcularTotal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularTotal();
            }
        });
    }
    ConnectionDB conexaoDB = new ConnectionDB();
    Connection conn = conexaoDB.getConnection();

    private void adicionarItem() {
        try {


            int numeroPedido = Integer.parseInt(textFieldNumeroPedido.getText());
            String nome = textFieldNome.getText();
            int quantidade = Integer.parseInt(textFieldQuantidade.getText());
            double preco = Double.parseDouble(textFieldPreco.getText());

            ItemPedido item = new ItemPedido(nome, quantidade, preco);
            Pedido pedido = buscarOuCriarPedido(numeroPedido);
            PedidoDAO pedidoDAO = new PedidoDAO(conn);
            pedido.adicionarItem(item);
            pedidoDAO.salvarPedido(pedido);
            atualizarPedidos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Dados inválidos. Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void removerItem() {
        try {
            int numeroPedido = Integer.parseInt(textFieldNumeroPedido.getText());
            String nome = textFieldNome.getText();
            int quantidade = Integer.parseInt(textFieldQuantidade.getText());
            double preco = Double.parseDouble(textFieldPreco.getText());

            ItemPedido item = new ItemPedido(nome, quantidade, preco);
            Pedido pedido = buscarOuCriarPedido(numeroPedido);
            pedido.removerItem(item);
            atualizarPedidos();
            JOptionPane.showMessageDialog(frame, "Item removido do pedido número " + numeroPedido, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Dados inválidos. Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calcularTotal() {
        try {
            int numeroPedido = Integer.parseInt(textFieldNumeroPedido.getText());
            Pedido pedido = buscarOuCriarPedido(numeroPedido);
            if (pedido != null) {
                double totalPedido = pedido.calcularTotalItens();  // Método para calcular apenas o total dos itens
                double taxaEntrega = pedido.getTaxaEntrega(); // Método para obter a taxa de entrega
                double totalFinal = totalPedido + taxaEntrega; // Calcula o total final

                String mensagem = String.format("Total do Pedido número %d:\n" +
                                "Valor dos itens: R$ %.2f\n" +
                                "Taxa de entrega: R$ %.2f\n" +
                                "Valor total: R$ %.2f",
                        numeroPedido, totalPedido, taxaEntrega, totalFinal);

                JOptionPane.showMessageDialog(frame, mensagem, "Total", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Pedido não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Número do pedido inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Pedido buscarOuCriarPedido(int numero) {
        for (Pedido p : restaurante.getPedidos()) {
            if (p.getNumero() == numero) {
                return p;
            }
        }
        Pedido novoPedido = new Pedido(numero);
        restaurante.addPedido(novoPedido);
        return novoPedido;
    }

    private void atualizarPedidos() {
        textAreaPedidos.setText("");
        for (Pedido p : restaurante.getPedidos()) {
            textAreaPedidos.append(p.toString() + "\n");
        }
    }

    private void reservarMesa() {
        try {
            int numeroMesa = Integer.parseInt(textFieldNumeroMesa.getText());
            Mesa mesa = restaurante.getMesa(numeroMesa);

            if (mesa != null) {
                if (!mesa.isReservada()) {
                    mesa.setReservada(true);
                    JOptionPane.showMessageDialog(frame, "Mesa número " + numeroMesa + " reservada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Mesa número " + numeroMesa + " já está reservada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Mesa número " + numeroMesa + " não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Número da mesa inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}



