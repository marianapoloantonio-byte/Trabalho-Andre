package View;

import Controller.CompraController;
import Controller.JogoController;
import Controller.UsuarioController;
import Model.Jogos;
import Model.Usuario;
import java.util.Scanner;

public class MenuView {
    private Scanner scanner = new Scanner(System.in);
    private UsuarioController usuarioController = new UsuarioController();
    private JogoController jogoController = new JogoController();
    private CompraController compraController = new CompraController();

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
                case 2 -> menuAdministrador();
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
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarCatalogo();
                case 2 -> comprarJogo(usuarioLogado);
                case 3 -> visualizarBiblioteca(usuarioLogado);
                case 0 -> System.out.println("Deslogando usuário...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // --- MENU DO ADMINISTRADOR ---
    private void menuAdministrador() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n🛠️ === MENU ADMINISTRADOR (CRUD JOGOS) ===");
            System.out.println("1. Cadastrar Novo Jogo");
            System.out.println("2. Listar Todos os Jogos");
            System.out.println("3. Remover Jogo do Catálogo");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("ID do Jogo: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Gênero: ");
                    String genero = scanner.nextLine();
                    System.out.print("Preço: R$ ");
                    double preco = scanner.nextDouble();
                    jogoController.cadastrarJogo(id, titulo, genero, preco);
                }
                case 2 -> listarCatalogo();
                case 3 -> {
                    System.out.print("Digite o ID do jogo que deseja remover: ");
                    int idDeletar = scanner.nextInt();
                    jogoController.deletarJogo(idDeletar);
                }
                case 0 -> System.out.println("Saindo do modo administrador...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // --- MÉTODOS AUXILIARES DE FLUXO ---
    private void listarCatalogo() {
        System.out.println("\n ----------- CATÁLOGO DE JOGOS DISPONÍVEIS -----------");
        if (jogoController.listarJogos().isEmpty()) {
            System.out.println("Nenhum jogo cadastrado no momento.");
        } else {
            for (Jogos j : jogoController.listarJogos()) {
                System.out.println("ID: " + j.getId() + " | " + j.getTitulo() + " [" + j.getGenero() + "] - R$ " + j.getPreco());
            }
        }
    }

    private void comprarJogo(Usuario usuario) {
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