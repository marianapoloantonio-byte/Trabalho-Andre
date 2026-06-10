package View;

import Controller.CompraController;
import Controller.JogoController;
import Controller.UsuarioController;
import Model.Avaliacao;
import Model.Jogos;
import Model.Usuario;
import java.util.Scanner;



public class MenuView {
    private Scanner scanner = new Scanner(System.in);
    private UsuarioController usuarioController = new UsuarioController();
    private JogoController jogoController = new JogoController();
    private CompraController compraController = new CompraController();
    private AdministradorView administradorView = new AdministradorView();
    private JogosView jogosView = new JogosView();

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
                case 2 -> comprarJogo(usuarioLogado);
                case 3 -> visualizarBiblioteca(usuarioLogado);
                case 4 -> avaliarJogo(usuarioLogado);
                case 5 -> visualizarAvaliacoes();
                case 0 -> System.out.println("Deslogando usuário...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }



    // --- MÉTODOS AUXILIARES DE FLUXO ---
    private void listarCatalogo() {
        jogosView.exibirCatalogo(jogoController.listarJogos());
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

    // --

    // ============= METODOS PARA AVALIACOES ===================
    private void avaliarJogo(Usuario usuario) {

        // -- Exibe mensagem se nao tiver jogo na biblioteca para avaliar
        if(usuario.getBiblioteca().isEmpty()) {
            System.out.println("❌ Você não possui jogos para avaliar.");
            return;
        }

        System.out.println("\n🎮 SEUS JOGOS:");

        for(Jogos j : usuario.getBiblioteca()) {
            System.out.println("ID: " + j.getId() + " | " + j.getTitulo());
        }

        System.out.print("\nDigite o ID do jogo: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Jogos jogoEscolhido = null;

        for(Jogos j : usuario.getBiblioteca()) {

            if(j.getId() == id) {
                jogoEscolhido = j;
                break;
            }

        }

        if(jogoEscolhido == null) {
            System.out.println("❌ Jogo não encontrado.");
            return;
        }

        // -- Solicita a nota do jogo
        System.out.print("Nota (1 a 5): ");
        int nota = scanner.nextInt();
        scanner.nextLine();

        if(nota < 1 || nota > 5) {
            System.out.println("❌ Nota inválida.");
            return;
        }

        System.out.print("Comentário: ");
        String comentario = scanner.nextLine();

        Avaliacao avaliacao = new Avaliacao(usuario, jogoEscolhido, nota, comentario);

        jogoEscolhido.adicionarAvaliacao(avaliacao);

        System.out.println("\n✅ Avaliação registrada!");
        System.out.println("⭐ Nota: " + avaliacao.getEstrelas());
        System.out.println("💬 Comentário: " + comentario);
    }


    private void visualizarAvaliacoes() {

        listarCatalogo();

        System.out.print("\nDigite o ID do jogo: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Jogos jogo = jogoController.buscarPorId(id);

        if(jogo == null) {
            System.out.println("❌ Jogo não encontrado.");
            return;
        }

        System.out.println("\n============================");
        System.out.println("🎮 " + jogo.getTitulo());
        System.out.println("==============================");

        if(jogo.getAvaliacoes().isEmpty()) {
            System.out.println("Ainda não existem avaliações para este jogo.");
            return;
        }

        for(Avaliacao avaliacao : jogo.getAvaliacoes()) {

            System.out.println("\n👤 Usuário: " + avaliacao.getUsuario().getNome());

            System.out.println("⭐ Avaliação: " + avaliacao.getEstrelas());

            System.out.println("💬 Comentário: " + avaliacao.getComentario());

            System.out.println("----------------------------");
        }
    }



}
