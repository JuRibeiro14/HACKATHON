import java.util.ArrayList;
import java.util.List;

public class Treinamento {
    private final int id;
    private final Instrutor instrutor;
    private final String linguagemEnsinada;
    private final List<Aluno> alunos;
    private int cargaHoraria;
    private final String tipoTreinamento;

    public Treinamento(int id, Instrutor instrutor, String linguagemEnsinada, String tipoTreinamento) {
        this.id = id;
        this.instrutor = instrutor;
        this.linguagemEnsinada = linguagemEnsinada;
        this.alunos = new ArrayList<>();
        this.cargaHoraria = 0;
        this.tipoTreinamento = tipoTreinamento;
    }
    
    public List<Aluno> getAlunos() {
        return alunos;
    }
    
    public int getId() {
        return id;
    }

    public String getLinguagemEnsinada() {
        return linguagemEnsinada;
    }
    
    public String getTipoTreinamento() {
        return tipoTreinamento;    
    }
    
    public boolean removerAluno(String nome, double notaFinal) {
        return alunos.removeIf(aluno -> aluno.getNome().equals(nome) && aluno.getNota() == notaFinal);
    }
    
    public Instrutor getInstrutor() {
        return instrutor;
    }
    
    public void verificarDisponibilidade() {
        System.out.println("Disponibilidade: " + instrutor.isDisponivel());
    }

    public boolean definirCargaHoraria(int horas) {
        if (horas <= 80) {
            this.cargaHoraria = horas;
            return true;
        } 
        return false;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public boolean verificarCargaHoraria(Aluno aluno) {
        final int LIMITE_CARGA_HORARIA = 80;
        return aluno.getCargaHoraria() > LIMITE_CARGA_HORARIA;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        aluno.adicionarTreinamento(this);
        System.out.println("Aluno adicionado com sucesso!");
    }

    public String getDescricao() {
        return "ID: " + id + ", Instrutor: " + instrutor.getNome() + ", Linguagem: " + linguagemEnsinada;
    } 
    
    public String verificarUltimoTreinamento(String nome) {
        Aluno aluno = buscarAlunoPorNome(nome); 
        if (aluno == null) {
            return "Aluno não encontrado.";
        }

        List<Treinamento> historico = aluno.getHistoricoTreinamento();
        if (historico.isEmpty()) {
            return "O aluno não possui histórico de treinamentos.";
        }

        Treinamento ultimoTreinamento = historico.get(historico.size() - 1);
        if (ultimoTreinamento.getCargaHoraria() > 80) {
            return "Carga horária do último treinamento do aluno: " + ultimoTreinamento.getCargaHoraria() + "h. Aluno excedeu o limite de carga horária de 80 horas.";
        } else {
            return "Carga horária do último treinamento do aluno: " + ultimoTreinamento.getCargaHoraria() + "h. Aluno não excedeu o limite de 80 horas.";
        }
    }

    public Aluno buscarAlunoPorNome(String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(nome)) {
                return aluno;
            }
        }
        return null; 
    }
    
    public double calcularMedia() {
        double soma = 0;
        for (Aluno aluno : alunos) {
            soma += aluno.getNota();
        }
        return alunos.size() > 0 ? soma / alunos.size() : 0;
    }
}
