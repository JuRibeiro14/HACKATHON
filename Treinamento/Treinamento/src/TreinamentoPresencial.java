public class TreinamentoPresencial extends Treinamento {
    private final String local;

    public TreinamentoPresencial(int id, Instrutor instrutor, String linguagemEnsinada, String local) {
        super(id, instrutor, linguagemEnsinada, "Presencial");
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public String getDescricao() {
        return "Treinamento Presencial: " + super.getLinguagemEnsinada() + " em " + local;
    }
}
