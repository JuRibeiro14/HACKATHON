package classe.main;

import classe.Aeroporto;
import classe.Voo;

public class Main {
    public static void main(String[] args){
        Aeroporto aeroporto = new Aeroporto();

        Voo voo1 = new Voo("1234", "São Paulo", "Rio de Janeiro", 50);
        Voo voo2 = new Voo("5678", "Salvador", "Fortaleza", 30);
        Voo voo3 = new Voo("91011", "Belo Horizonte", "Recife", 20);

        aeroporto.adicionarVoo(voo1);
        aeroporto.adicionarVoo(voo2);
        aeroporto.adicionarVoo(voo3);

        System.out.println("Voos disponíveis:");
        aeroporto.exibirTodosVoos();

        System.out.println("\nRealizando reserva no voo 1234:");
        if (voo1.realizarReserva(2)) {
            System.out.println("Reserva realizada com sucesso.");
        } else {
            System.out.println("Não há assentos suficientes.");
        }

        System.out.println("\nVerificando disponibilidade no voo 1234:");
        System.out.println("Assentos disponíveis: " + voo1.getAssentosDisponiveis());

        System.out.println("\nRealizando pagamento para voo 1234 (ida e volta, com pontos turísticos):");
        voo1.realizarPagamento("ida e volta", true);

        System.out.println("\nDetalhes da passagem para o voo 1234:");
        System.out.println(voo1);

        System.out.println("\nBuscando e removendo o voo 5678:");
        Voo vooBuscado = aeroporto.buscarVoo("5678");
        if (vooBuscado != null) {
            aeroporto.removerVoo("5678");
            System.out.println("Voo encontrado e removido.");
        } else {
            System.out.println("Voo não encontrado.");
        }

        System.out.println("\nVoos disponíveis após remoção:");
        aeroporto.exibirTodosVoos();
    }
}
