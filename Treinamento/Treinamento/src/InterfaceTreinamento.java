import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;

public class InterfaceTreinamento {

    ConnectionDB connectionDB = new ConnectionDB();
    Connection conn = connectionDB.getConnection();

    private JFrame frame;
    private JTextField nomeAlunoField;
    private JTextField notaAlunoField;
    private JTextField horasField;
    private JTextArea mensagensArea;
    private DefaultListModel<String> alunoListModel;
    private JList<String> alunoList;
    private JComboBox<String> modalidadeComboBox;
    private JComboBox<String> cursoComboBox;
    private List<Aluno> alunos;
    private TreinamentoPresencial treinamentoPresencial;
    private TreinamentoOnline treinamentoOnline1;
    private TreinamentoOnline treinamentoOnline2;
    private Instrutor instrutorPresencial;
    private Instrutor instrutorOnline1;
    private Instrutor instrutorOnline2;
    private Map<String, Instrutor> cursoParaInstrutor;
    private Map<String, Treinamento> cursoParaTreinamento;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                InterfaceTreinamento window = new InterfaceTreinamento();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public InterfaceTreinamento() {
        alunos = new ArrayList<>();
        instrutorPresencial = new Instrutor("Alessandro", true, "Java");
        instrutorOnline1 = new Instrutor("Gilmar", true,"C#");
        instrutorOnline2 = new Instrutor("Fernanda", true,"C++");

        treinamentoPresencial = new TreinamentoPresencial(1, instrutorPresencial, "Java", "Sala 101");
        treinamentoOnline1 = new TreinamentoOnline(2, instrutorOnline1, "C#", "http://www.exemplo.com/treinamento_online_c#");
        treinamentoOnline2 = new TreinamentoOnline(3, instrutorOnline2, "C++", "http://www.exemplo.com/treinamento_online_c++");

        cursoParaInstrutor = new HashMap<>();
        cursoParaInstrutor.put("Java (Presencial)", instrutorPresencial);
        cursoParaInstrutor.put("C# (Online)", instrutorOnline1);
        cursoParaInstrutor.put("C++ (Online)", instrutorOnline2);

        cursoParaTreinamento = new HashMap<>();
        cursoParaTreinamento.put("Java (Presencial)", treinamentoPresencial);
        cursoParaTreinamento.put("C# (Online)", treinamentoOnline1);
        cursoParaTreinamento.put("C++ (Online)", treinamentoOnline2);

        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(16, 28, 82));
        frame.setBounds(100, 100, 700, 750); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        ImageIcon originalImage = new ImageIcon(getClass().getResource("Logo.png"));
        Image scaledImage = originalImage.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon resizedImage = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(resizedImage);
        imageLabel.setBounds(10, 17, 150, 106);
        frame.getContentPane().add(imageLabel);
        
        JLabel lblNome = new JLabel("Nome do Aluno:");
        lblNome.setForeground(new Color(255, 255, 255));
        lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNome.setBounds(43, 133, 126, 25);
        frame.getContentPane().add(lblNome);
        
        nomeAlunoField = new JTextField();
        nomeAlunoField.setBounds(158, 134, 200, 25);
        frame.getContentPane().add(nomeAlunoField);
        nomeAlunoField.setColumns(10);
        
        JLabel lblNota = new JLabel("Nota do Aluno:");
        lblNota.setForeground(new Color(255, 255, 255));
        lblNota.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNota.setBounds(43, 168, 126, 25);
        frame.getContentPane().add(lblNota);
        
        notaAlunoField = new JTextField();
        notaAlunoField.setBounds(158, 168, 200, 25);
        frame.getContentPane().add(notaAlunoField);
        notaAlunoField.setColumns(10);
        
        JLabel lblHoras = new JLabel("Carga Horária:");
        lblHoras.setForeground(new Color(255, 255, 255));
        lblHoras.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblHoras.setBounds(43, 202, 126, 25);
        frame.getContentPane().add(lblHoras);
        
        horasField = new JTextField();
        horasField.setBounds(158, 203, 200, 25);
        frame.getContentPane().add(horasField);
        horasField.setColumns(5);

        JLabel lblModalidade = new JLabel("Modalidade:");
        lblModalidade.setForeground(new Color(255, 255, 255));
        lblModalidade.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblModalidade.setBounds(43, 237, 126, 25);
        frame.getContentPane().add(lblModalidade);

        modalidadeComboBox = new JComboBox<>(new String[]{"Presencial", "Online"});
        modalidadeComboBox.setBounds(158, 238, 200, 25);
        frame.getContentPane().add(modalidadeComboBox);

        JLabel lblCurso = new JLabel("Curso:");
        lblCurso.setForeground(new Color(255, 255, 255));
        lblCurso.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCurso.setBounds(43, 272, 126, 25);
        frame.getContentPane().add(lblCurso);

        cursoComboBox = new JComboBox<>(new String[]{
            "Java (Presencial)",
            "C# (Online)",
            "C++ (Online)"
        });
        cursoComboBox.setBounds(158, 273, 200, 25);
        frame.getContentPane().add(cursoComboBox);
        
        JButton btnAdicionar = new JButton("Adicionar Aluno");
        btnAdicionar.setForeground(new Color(92, 0, 185));
        btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAdicionar.setBounds(444, 133, 205, 25);
        frame.getContentPane().add(btnAdicionar);
        
        JButton btnRemover = new JButton("Remover Aluno");
        btnRemover.setForeground(new Color(92, 0, 185));
        btnRemover.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnRemover.setBounds(444, 166, 205, 25);
        frame.getContentPane().add(btnRemover);
        
        JButton btnVerificarDisponibilidade = new JButton("Disponibilidade/Instrutor");
        btnVerificarDisponibilidade.setForeground(new Color(92, 0, 185));
        btnVerificarDisponibilidade.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnVerificarDisponibilidade.setBounds(444, 238, 205, 24);
        frame.getContentPane().add(btnVerificarDisponibilidade);
        
        JButton btnCalcularMedia = new JButton("Calcular Média");
        btnCalcularMedia.setForeground(new Color(92, 0, 185));
        btnCalcularMedia.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCalcularMedia.setBounds(389, 509, 190, 24);
        frame.getContentPane().add(btnCalcularMedia);
        
        JButton btnVerificarTreinamento = new JButton("Verificar Treinamento");
        btnVerificarTreinamento.setForeground(new Color(92, 0, 185));
        btnVerificarTreinamento.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnVerificarTreinamento.setBounds(444, 201, 205, 25);
        frame.getContentPane().add(btnVerificarTreinamento);
        
        JButton btnGerarLinkOnline = new JButton("Gerar Link Online");
        btnGerarLinkOnline.setForeground(new Color(92, 0, 185));
        btnGerarLinkOnline.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnGerarLinkOnline.setBounds(119, 509, 190, 25);
        frame.getContentPane().add(btnGerarLinkOnline);
        
        alunoListModel = new DefaultListModel<>();
        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setBounds(43, 333, 606, 113);

        frame.getContentPane().add(scrollPane);
        alunoList = new JList<>(alunoListModel);
        scrollPane.setViewportView(alunoList);

        frame.setVisible(true);
        
        mensagensArea = new JTextArea();
        mensagensArea.setEditable(false);
        mensagensArea.setBounds(43, 555, 606, 106);
        frame.getContentPane().add(mensagensArea);
        
        JLabel lblNewLabel = new JLabel("Para adicionar alunos, removê-los, verificar treinamentos e a disponibilidade");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(158, 35, 488, 13);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("de instrutores, preencha os campos com o nome do aluno, nota, carga horária, ");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setBounds(158, 55, 488, 13);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("modalidade e curso.");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2.setBounds(158, 78, 175, 13);
        frame.getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Para calcular a média dos alunos ou gerar o link para a aula online, utilize os botões abaixo:");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_3.setBounds(73, 470, 577, 13);
        frame.getContentPane().add(lblNewLabel_3);
        
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeAlunoField.getText();
                double nota = Double.parseDouble(notaAlunoField.getText());
                int horas = Integer.parseInt(horasField.getText());
                String modalidade = (String) modalidadeComboBox.getSelectedItem();
                String curso = (String) cursoComboBox.getSelectedItem();
                
                if (nome.isEmpty() || horas <= 0) {
                    mensagensArea.setText("Por favor, preencha todos os campos corretamente.");
                    return;
                }

                if (horas > 80) {
                    mensagensArea.setText("A carga horária não pode exceder 80 horas.");
                    return;
                }

                if (cursoParaTreinamento.containsKey(curso)) {
                    Instrutor instrutor = cursoParaInstrutor.get(curso);
                    
                    if (instrutor != null) {
                        Aluno aluno = new Aluno(nome, nota, horas, modalidade, curso, instrutor);
                        alunos.add(aluno);
                        aluno.salvarNoBanco(conn);
                        alunoListModel.addElement(aluno.toString());
                    } else {
                        mensagensArea.setText("Instrutor não encontrado para o curso selecionado.");
                    }
                } else {
                    mensagensArea.setText("Curso não encontrado.");
                }
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeAlunoField.getText();
                double nota = Double.parseDouble(notaAlunoField.getText());
                boolean alunoRemovido = false;
                
                for (int i = 0; i < alunos.size(); i++) {
                    Aluno aluno = alunos.get(i);
                    if (aluno.getNome().equals(nome) && aluno.getNota() == nota) {
                        alunos.remove(i);
                        alunoListModel.remove(i);
                        alunoRemovido = true;
                        break;
                    }
                }

                if (alunoRemovido) {
                } else {
                    mensagensArea.setText("Aluno não encontrado.");
                }
            }
        });
        
        btnVerificarDisponibilidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String modalidade = (String) modalidadeComboBox.getSelectedItem();
                Instrutor instrutor = null;

                if (modalidade.equals("Presencial")) {
                    instrutor = instrutorPresencial;
                } else if (modalidade.equals("Online")) {
                    instrutor = instrutorOnline1; 
                    instrutor = instrutorOnline2;
                }

                if (instrutor != null) {
                    mensagensArea.setText("Instrutor disponível: " + instrutor.getNome() + ", Linguagem: " + instrutor.getLinguagem());
                } else {
                    mensagensArea.setText("Instrutor não encontrado.");
                }
            }
        });

        btnCalcularMedia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double somaNotas = 0;
                int contador = 0;

                for (Aluno aluno : alunos) {
                    somaNotas += aluno.getNota();
                    contador++;
                }

                double media = contador > 0 ? somaNotas / contador : 0;
                mensagensArea.setText("Média das notas dos alunos: " + media);
            }
        });

        btnVerificarTreinamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String curso = (String) cursoComboBox.getSelectedItem();
                if (cursoParaTreinamento.containsKey(curso)) {
                    Treinamento treinamento = cursoParaTreinamento.get(curso);
                    mensagensArea.setText("Treinamento encontrado: " + treinamento.getDescricao());
                } else {
                    mensagensArea.setText("Treinamento não encontrado.");
                }
            }
        });

        btnGerarLinkOnline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String curso = (String) cursoComboBox.getSelectedItem();
                if (curso.contains("Online")) {
                    TreinamentoOnline treinamentoOnline = (TreinamentoOnline) cursoParaTreinamento.get(curso);
                    if (treinamentoOnline != null) {
                        mensagensArea.setText("Link do treinamento: " + treinamentoOnline.getLink());
                    } else {
                        mensagensArea.setText("Link não encontrado.");
                    }
                } else {
                    mensagensArea.setText("O curso selecionado não é online.");
                }
            }
        });
    }
}
