import java.util.ArrayList;

public class Pedido {
    private final int numero;
    private ArrayList<ItemPedido> itens;
    private final double taxaEntrega;

    public Pedido(int numero) {
        this.numero = numero;
        this.itens = new ArrayList<>();
        this.taxaEntrega = 5.0;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public void removerItem(ItemPedido item) {
        itens.remove(item);
    }

    public double calcularTotalItens() {
        double total = 0.0;
        for (ItemPedido item : itens) {
            total += item.getPrecoTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido Número: ").append(numero).append("\n");
        for (ItemPedido item : itens) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }

}