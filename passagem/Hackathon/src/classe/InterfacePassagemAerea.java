package classe;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import java.sql.Connection;

public class InterfacePassagemAerea {

    ConnectionDB connectionDB = new ConnectionDB();
    Connection conn = connectionDB.getConnection();

    private JFrame frame;
    private Aeroporto aeroporto;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                InterfacePassagemAerea window = new InterfacePassagemAerea();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public InterfacePassagemAerea() {
        aeroporto = new Aeroporto(); 
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBackground(new Color(159, 205, 247));
        frame.getContentPane().setBackground(new Color(159, 205, 247));
        frame.setBounds(100, 100, 600, 800); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    
        ImageIcon originalImage = new ImageIcon(getClass().getResource("Logo.png"));
        Image scaledImage = originalImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedImage = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(resizedImage);
        imageLabel.setBounds(216, 21, 139, 133);
        frame.getContentPane().add(imageLabel);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(197, 224, 250));
        panel.setBounds(36, 170, 516, 507); 
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JTextField numeroVooField = new JTextField();
        numeroVooField.setBounds(22, 76, 150, 19);
        panel.add(numeroVooField);
        
        JTextField origemField = new JTextField();
        origemField.setBounds(22, 119, 150, 19);
        panel.add(origemField);
        
        JTextField destinoField = new JTextField();
        destinoField.setBounds(22, 161, 150, 19);
        panel.add(destinoField);
        
        JTextField assentosField = new JTextField();
        assentosField.setBounds(22, 202, 150, 19);
        panel.add(assentosField);
        
        JButton adicionarButton = new JButton("Adicionar Voo");
        adicionarButton.setForeground(new Color(0, 128, 255));
        adicionarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        adicionarButton.setBackground(new Color(255, 255, 255));
        adicionarButton.setBounds(22, 231, 150, 25);
        panel.add(adicionarButton);
        
        JButton removerButton = new JButton("Remover Voo");
        removerButton.setForeground(new Color(0, 128, 255));
        removerButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        removerButton.setBackground(new Color(255, 255, 255));
        removerButton.setBounds(22, 266, 150, 25);
        panel.add(removerButton);
        
        JTextArea displayArea = new JTextArea();
        displayArea.setBounds(211, 76, 279, 215);
        panel.add(displayArea);
        
        JTextField numeroVooReservaField = new JTextField();
        numeroVooReservaField.setBounds(22, 354, 150, 19);
        panel.add(numeroVooReservaField);
        
        JTextField quantidadeAssentosField = new JTextField();
        quantidadeAssentosField.setBounds(22, 397, 150, 19);
        panel.add(quantidadeAssentosField);
        
        JButton reservarButton = new JButton("Reservar Assento");
        reservarButton.setBackground(new Color(255, 255, 255));
        reservarButton.setForeground(new Color(0, 128, 255));
        reservarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        reservarButton.setBounds(22, 426, 150, 25);
        panel.add(reservarButton);
        
        JButton pagamentoButton = new JButton("Realizar Pagamento");
        pagamentoButton.setBackground(new Color(255, 255, 255));
        pagamentoButton.setForeground(new Color(0, 128, 255));
        pagamentoButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        pagamentoButton.setBounds(257, 426, 183, 25);
        panel.add(pagamentoButton);
        
        JTextField tipoViagemField = new JTextField();
        tipoViagemField.setBounds(257, 354, 183, 19);
        panel.add(tipoViagemField);
        
        JTextField pontosField = new JTextField();
        pontosField.setBounds(257, 397, 183, 19);
        panel.add(pontosField);
        
        adicionarButton.addActionListener(e -> {
            String numeroVoo = numeroVooField.getText();
            String origem = origemField.getText();
            String destino = destinoField.getText();
            int assentos = Integer.parseInt(assentosField.getText());

            Voo voo = new Voo(numeroVoo, origem, destino, assentos);
            aeroporto.adicionarVoo(voo);
            voo.salvarNoBanco(conn);
            
            displayArea.append("Voo adicionado: " + voo.toString() + "\n");
        });
        
        JButton exibirTodosButton = new JButton("Exibir Todos os Voos");
        exibirTodosButton.setForeground(new Color(0, 128, 255));
        exibirTodosButton.setBackground(new Color(255, 255, 255));
        exibirTodosButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        exibirTodosButton.setBounds(22, 461, 150, 25);
        panel.add(exibirTodosButton);

        exibirTodosButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Voo voo : aeroporto.getVoos()) {
                sb.append(voo.toString()).append("\n--------\n");
            }
            displayArea.setText(sb.toString());
        });
        
        removerButton.addActionListener(e -> {
            String numeroVoo = numeroVooField.getText();
            aeroporto.removerVoo(numeroVoo);

            StringBuilder sb = new StringBuilder();
            for (Voo voo : aeroporto.getVoos()) {
                sb.append(voo.toString()).append("\n--------\n");
            }
            displayArea.setText(sb.toString());
        });

        reservarButton.addActionListener(e -> {
            String numeroVoo = numeroVooReservaField.getText();
            int quantidadeAssentos = Integer.parseInt(quantidadeAssentosField.getText());
            
            Voo voo = aeroporto.buscarVoo(numeroVoo);
            if (voo != null) {
                if (voo.realizarReserva(quantidadeAssentos)) {
                    displayArea.setText("Reserva realizada para o voo: " + numeroVoo);
                } else {
                    displayArea.setText("Não há assentos suficientes disponíveis.");
                }
            } else {
                displayArea.setText("Voo não encontrado.");
            }
        });

        pagamentoButton.addActionListener(e -> {
            String tipoViagem = tipoViagemField.getText();
            boolean pontosTuristicos = "sim".equalsIgnoreCase(pontosField.getText());

            String numeroVoo = numeroVooField.getText();
            Voo voo = aeroporto.buscarVoo(numeroVoo);

            if (voo != null) {
                double valorBase = 40.0;
                double valorTotal = voo.realizarPagamento(tipoViagem, pontosTuristicos);
                
                StringBuilder mensagemDetalhada = new StringBuilder();
                mensagemDetalhada.append("Detalhes do Pagamento:\n")
                                 .append("Tipo de Viagem: ").append(tipoViagem).append("\n")
                                 .append("Valor Base: R$").append(valorBase).append("\n")
                                 .append("Inclui Pontos Turísticos: ").append(pontosTuristicos ? "Sim" + " Taxa:R$50,00" : "Não").append("\n")
                                 .append("Valor Total: R$").append(valorTotal).append("\n");

                displayArea.setText(mensagemDetalhada.toString());
            } 
        });

        JLabel lblNumeroVoo = new JLabel("Número do Voo:");
        lblNumeroVoo.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNumeroVoo.setBounds(22, 63, 106, 13);
        panel.add(lblNumeroVoo);
        
        JLabel lblOrigem = new JLabel("Origem do Voo:");
        lblOrigem.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblOrigem.setBounds(22, 105, 106, 13);
        panel.add(lblOrigem);
        
        JLabel lblDestino = new JLabel("Destino do Voo:");
        lblDestino.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDestino.setBounds(22, 148, 106, 13);
        panel.add(lblDestino);
        
        JLabel lblAssentos = new JLabel("Assentos Disponíveis:");
        lblAssentos.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblAssentos.setBounds(22, 190, 138, 13);
        panel.add(lblAssentos);

        JLabel lblNumeroVooReserva = new JLabel("N° do Voo para Reserva:");
        lblNumeroVooReserva.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNumeroVooReserva.setBounds(22, 339, 200, 13);
        panel.add(lblNumeroVooReserva);

        JLabel lblQuantidadeAssentos = new JLabel("Quantidade de Assentos:");
        lblQuantidadeAssentos.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblQuantidadeAssentos.setBounds(22, 383, 160, 13);
        panel.add(lblQuantidadeAssentos);

        JLabel lblTipoViagem = new JLabel("Tipo de Viagem (ida/ida e volta):");
        lblTipoViagem.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTipoViagem.setBounds(257, 339, 200, 13);
        panel.add(lblTipoViagem);

        JLabel lblPontos = new JLabel("Pontos Turísticos (sim/não):");
        lblPontos.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblPontos.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblPontos.setBounds(257, 383, 200, 13);
        panel.add(lblPontos);
        
        JLabel lblNewLabel = new JLabel("Para adicionar ou remover um voo preencha os campos com o n° do voo, origem,");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(10, 10, 506, 13);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("destino e assentos disponíveis:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setBounds(10, 29, 200, 13);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("informações sobre os voos:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2.setBounds(212, 62, 200, 13);
        panel.add(lblNewLabel_2);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 128, 255));
        separator.setBounds(0, 306, 516, 2);
        panel.add(separator);
    }
}
