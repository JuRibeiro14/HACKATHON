public class Main {
    public static void main(String[] args) {
        Instrutor instrutor1 = new Instrutor("Alessandro", true, "Java");
        Instrutor instrutor2 = new Instrutor("Fernanda", true, "C++");
        Instrutor instrutorOnline = new Instrutor("Gilmar", true, "C#");

        TreinamentoOnline treinamentoOnline = new TreinamentoOnline(1, instrutorOnline, "C#", "http://www.exemplo.com/treinamento_online");
        TreinamentoOnline online1 = new TreinamentoOnline(3, instrutor2, "C++", "http://linkfake.com");
        TreinamentoPresencial presencial1 = new TreinamentoPresencial(4, instrutor1, "Java", "Praia Grande");

        Aluno aluno1 = new Aluno("Matheus", 8, 40, "presencial", "C++", instrutor2);
        Aluno aluno2 = new Aluno("Livia", 10,30, "online", "Java", instrutor1);

        System.out.println(treinamentoOnline.getDescricao());

        System.out.println(online1.getDescricao());
        System.out.println(presencial1.getDescricao());

        treinamentoOnline.definirCargaHoraria(50);
        treinamentoOnline.adicionarAluno(aluno1);
        treinamentoOnline.adicionarAluno(aluno2);
        treinamentoOnline.calcularMedia();

        presencial1.definirCargaHoraria(40);
        presencial1.adicionarAluno(aluno1);
        presencial1.adicionarAluno(aluno2);
        presencial1.calcularMedia();

        treinamentoOnline.verificarUltimoTreinamento("Matheus");
    }
}
