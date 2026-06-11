package View;

import Controller.CompraController;
import Controller.JogoController;
import Model.Jogos;
import Model.Usuario;

import java.util.Scanner;

public class CompraView {

    private Scanner scanner = new Scanner(System.in);

    private CompraController compraController = new CompraController();
    private JogoController jogoController = new JogoController();
    private JogosView jogosView = new JogosView();

    private void listarCatalogo() {
        jogosView.exibirCatalogo(jogoController.listarJogos());
    }

    public void comprarJogo(Usuario usuario) {

        listarCatalogo();

        System.out.print("\nDigite o ID do jogo que deseja comprar: ");
        int idJogo = scanner.nextInt();

        Jogos jogoEscolhido = jogoController.buscarPorId(idJogo);

        if (jogoEscolhido == null) {
            System.out.println("Jogo não encontrado!");
            return;
        }

        System.out.println("\nFormas de Pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Pix");
        System.out.println("3. Boleto");
        System.out.print("Escolha a opção de pagamento: ");
        int formaPagamento = scanner.nextInt();

        compraController.processarCompra(usuario, jogoEscolhido, formaPagamento);
    }

}
