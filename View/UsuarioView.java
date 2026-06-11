package View;

import Controller.UsuarioController;
import Model.Jogos;
import Model.Usuario;
import java.util.Scanner;

public class UsuarioView {
    private Scanner scanner = new Scanner(System.in);
    private UsuarioController usuarioController = new UsuarioController();
    private CompraView compraView = new CompraView();
    private AvaliacaoView avaliacaoView = new AvaliacaoView();
    private JogosView jogosView = new JogosView();
    private Controller.JogoController jogoController = new Controller.JogoController();

    public void menuUsuario() {
        System.out.println("\n--- LOGIN DE USUÁRIO ---");
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        Usuario usuarioLogado = usuarioController.login(email, senha);

        if (usuarioLogado != null) {
            abrirMenuLogado(usuarioLogado);
        } else {
            System.out.println("\n❌ Email ou senha incorretos (ou utilizador não existe)!");
            System.out.println("Deseja criar uma conta nova?");
            System.out.println("1. Sim");
            System.out.println("2. Não");
            System.out.print("Opção: ");
            int op = scanner.nextInt();
            scanner.nextLine();

            if (op == 1) {
                System.out.print("Digite o ID para sua conta: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do teclado
                System.out.print("Digite seu nome: ");
                String nome = scanner.nextLine();
                System.out.print("Digite seu email: ");
                String novoEmail = scanner.nextLine();
                System.out.print("Digite uma senha para sua conta: ");
                String novaSenha = scanner.nextLine();

                usuarioController.cadastrarUsuario(id, nome, novoEmail, novaSenha);

                Usuario novoUsuario = usuarioController.login(novoEmail, novaSenha);
                if (novoUsuario != null) {
                    System.out.println("\n🔓 Conta criada e logada com sucesso!");
                    abrirMenuLogado(novoUsuario);
                }
            }
        }
    }

    private void abrirMenuLogado(Usuario usuarioLogado) {
        System.out.println("\n🔓 Login realizado com sucesso! Bem-vindo, " + usuarioLogado.getNome() + "!");
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU USUÁRIO (" + usuarioLogado.getNome() + ") ---");
            System.out.println("1. Visualizar Catálogo de Jogos");
            System.out.println("2. Comprar um Jogo");
            System.out.println("3. Visualizar Minha Biblioteca");
            System.out.println("4. Avaliar um Jogo");
            System.out.println("5. Ver Avaliações de um Jogo");
            System.out.println("0. Deslogar");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> jogosView.exibirCatalogo(jogoController.listarJogos());
                case 2 -> compraView.comprarJogo(usuarioLogado);
                case 3 -> visualizarBiblioteca(usuarioLogado);
                case 4 -> avaliacaoView.avaliarJogo(usuarioLogado);
                case 5 -> avaliacaoView.visualizarAvaliacoes();
                case 0 -> System.out.println("Deslogando usuário...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void visualizarBiblioteca(Usuario usuario) {
        System.out.println("\n ----------- MINHA BIBLIOTECA -----------");
        if (usuario.getBiblioteca().isEmpty()) {
            System.out.println("Sua biblioteca está vazia.");
        } else {
            for (Jogos j : usuario.getBiblioteca()) {
                System.out.println("ID: " + j.getId() + " | " + j.getTitulo() + " [" + j.getGenero() + "]");
            }
        }
    }
}