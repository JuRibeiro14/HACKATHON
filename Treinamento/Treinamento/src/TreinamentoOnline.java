public class TreinamentoOnline extends Treinamento {
    private final String link;

    public TreinamentoOnline(int id, Instrutor instrutor, String linguagemEnsinada, String link) {
        super(id, instrutor, linguagemEnsinada, "Online");
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getDescricao() {
        return "Treinamento Online: " + super.getLinguagemEnsinada() + " no link: " + link;
    }
}
