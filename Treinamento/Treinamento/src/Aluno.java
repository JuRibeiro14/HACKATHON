import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private final String nome;
    private final double notaFinal;
    private final List<Treinamento> historicoTreinamento;
    private int cargaHoraria;
    private String modalidade;
    private String curso;
    private Instrutor instrutor;

    public Aluno(String nome, double notaFinal, int cargaHoraria, String modalidade, String curso, Instrutor instrutor) {
        this.nome = nome;
        this.notaFinal = notaFinal;
        this.cargaHoraria = cargaHoraria;
        this.historicoTreinamento = new ArrayList<>();
        this.modalidade = modalidade;
        this.curso = curso;
        this.instrutor = instrutor;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return notaFinal;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }
    
    public String getModalidade() {
    	return modalidade;
    }
    
    public String getCurso(){
    	return curso;
    }
    
    public Instrutor getInstrutor(){
    	return instrutor;
    }

    public List<Treinamento> getHistoricoTreinamento() {
        return historicoTreinamento;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void adicionarTreinamento(Treinamento treinamento) {
        historicoTreinamento.add(treinamento);
    }

    public void salvarNoBanco(Connection conn) {
        String sql = "INSERT INTO Aluno (nome, notaFinal, cargaHoraria, modalidade, curso, instrutor_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, notaFinal);
            stmt.setInt(3, cargaHoraria);
            stmt.setString(4, modalidade);
            stmt.setString(5, curso);
            stmt.setInt(6, instrutor.getId(conn, instrutor)); // Método para obter o ID do instrutor
            stmt.executeUpdate();
            System.out.println("Aluno salvo com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o aluno: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Aluno |" +
                " Nome: '" + nome + '\'' +
                ", Nota Final: " + notaFinal +
                ", Historico de Treinamento: " + historicoTreinamento +
                ", Carga Horária: " + cargaHoraria + "h" +
                ", Curso: " + curso +
                ", Instrutor: " + instrutor;
    }
}
