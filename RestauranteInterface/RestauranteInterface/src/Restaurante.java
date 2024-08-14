import java.util.ArrayList;

public class Restaurante {
    private ArrayList<Pedido> pedidos;
    private ArrayList<Mesa> mesas;

    public Restaurante() {
        pedidos = new ArrayList<>();
        mesas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
             mesas.add(new Mesa(i));
        }
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void reservarMesa(int numeroMesa) {
        Mesa mesa = getMesa(numeroMesa);
        if (mesa != null) {
            if (!mesa.isReservada()) {
                mesa.setReservada(true);
                System.out.println("Mesa reservada com sucesso! Número: " + numeroMesa);
            } else {
                System.out.println("Mesa número " + numeroMesa + " já está reservada.");
            }
        } else {
            System.out.println("Mesa número " + numeroMesa + " não encontrada.");
        }
    }

    public void liberarMesa(int numeroMesa) {
        Mesa mesa = getMesa(numeroMesa);
        if (mesa != null) {
            if (mesa.isReservada()) {
                mesa.setReservada(false);
                System.out.println("Mesa liberada! Número: " + numeroMesa);
            } else {
                System.out.println("Mesa número " + numeroMesa + " não está reservada.");
            }
        } else {
            System.out.println("Mesa número " + numeroMesa + " não encontrada.");
        }
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void removePedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public void buscaPedido(Pedido pedido) {
        boolean encontrado = false;
        for (Pedido p : pedidos) {
            if (p.getNumero() == pedido.getNumero()) {
                System.out.println("Encontrado! " + p);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Pedido não encontrado!");
        }
    }

    public void exibirPedidos() {
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }

    public Mesa getMesa(int numeroMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getNumero() == numeroMesa) {
                return mesa;
            }
        }
        return null;
    }
}
