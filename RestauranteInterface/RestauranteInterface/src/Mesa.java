public class Mesa {
    private final int numero;
    private boolean reservada;

    public Mesa(int numero) {
        this.numero = numero;
        this.reservada = false;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isReservada() {
        return reservada;
    }

    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    @Override
    public String toString() {
        return "Mesa " + numero + (reservada ? " (Reservada)" : " (Disponível)");
    }
}

