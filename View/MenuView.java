package View;

import Controller.JogoController;
import Controller.UsuarioController;
import Model.Jogos;
import Model.Usuario;
import java.util.Scanner;



public class MenuView {
    private Scanner scanner = new Scanner(System.in);
    private UsuarioController usuarioController = new UsuarioController();
    private JogoController jogoController = new JogoController();
    private AdministradorView administradorView = new AdministradorView();
    private JogosView jogosView = new JogosView();
    private AvaliacaoView avaliacaoView = new AvaliacaoView();
    private CompraView compraView = new CompraView();


    public void exibirMenuPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--------BEM-VINDO À LOJA DE JOGOS--------");
            System.out.println("1. Entrar como Usuário");
            System.out.println("2. Entrar como Administrador");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1 -> menuUsuario();
                case 2 -> administradorView.exibirMenuAdministrador();
                case 0 -> System.out.println("Saindo... ");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // --- MENU DO USUÁRIO ---
    private void menuUsuario() {
        System.out.print("\nDigite seu email de login: ");
        String email = scanner.nextLine();
        Usuario usuarioLogado = usuarioController.login(email);

        if (usuarioLogado == null) {
            System.out.println(" Usuário não encontrado! Deseja criar uma conta? (S/N)");
            String resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("S")) {
                System.out.print("Digite um ID (número): ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Digite seu nome: ");
                String nome = scanner.nextLine();
                usuarioController.cadastrarUsuario(id, nome, email);
                usuarioLogado = usuarioController.login(email);
            } else {
                return;
            }
        }

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nOlá, " + usuarioLogado.getNome() + "! O que deseja fazer?");
            System.out.println("1. Ver Catálogo de Jogos");
            System.out.println("2. Comprar um Jogo");
            System.out.println("3. Visualizar Minha Biblioteca");
            System.out.println("4. Avaliar um Jogo");
            System.out.println("5. Ver Avaliações de um jogo");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarCatalogo();
                case 2 -> compraView.comprarJogo(usuarioLogado);
                case 3 -> visualizarBiblioteca(usuarioLogado);
                case 4 -> avaliacaoView.avaliarJogo(usuarioLogado);
                case 5 -> avaliacaoView.visualizarAvaliacoes();
                case 0 -> System.out.println("Deslogando usuário...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }



    // --- MÉTODOS AUXILIARES DE FLUXO ---
    private void listarCatalogo() {
        jogosView.exibirCatalogo(jogoController.listarJogos());
    }

    private void visualizarBiblioteca(Usuario usuario) {
        System.out.println("\n ----------- MINHA BIBLIOTECA -----------");
        if (usuario.getBiblioteca().isEmpty()) {
            System.out.println("Sua biblioteca está vazia.");
        } else {
            for (Jogos j : usuario.getBiblioteca()) {
                System.out.println("• " + j.getTitulo() + " (" + j.getGenero() + ")");
            }
        }
    }
    
}
