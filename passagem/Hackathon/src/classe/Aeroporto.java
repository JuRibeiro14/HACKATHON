package classe;

import java.util.ArrayList;

public class Aeroporto {
    private ArrayList<Voo> voos;

    public Aeroporto() {
        voos = new ArrayList<>();
    }

    public ArrayList<Voo> getVoos() {
        return voos;
    }

    public void adicionarVoo(Voo voo) {
        if (buscarVoo(voo.getNumeroVoo()) == null) {
            voos.add(voo);
        } else {
            System.out.println("O voo com o número " + voo.getNumeroVoo() + " já existe.");
        }
    }
    
    public void removerVoo(String numeroVoo) {
        voos.removeIf(voo -> voo.getNumeroVoo().equals(numeroVoo));
    }

    public Voo buscarVoo(String numeroVoo) {
        for (Voo voo : voos) {
            if (voo.getNumeroVoo().equals(numeroVoo)) {
                return voo;
            }
        }
        return null;
    }

    public void exibirTodosVoos() {
        if (voos.isEmpty()) {
            System.out.println("Nenhum voo disponível.");
            return;
        }
        for (Voo voo : voos) {
            System.out.println(voo);
            System.out.println("--------");
        }
    }

    public int calcularOcupacaoTotal() {
        int ocupacaoTotal = 0;
        for (Voo voo : voos) {
            ocupacaoTotal += voo.getAssentosDisponiveis();
        }
        return ocupacaoTotal;
    }
}
