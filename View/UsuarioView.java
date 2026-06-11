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
                System.out.print("Digite um ID numérico para sua conta: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Digite seu Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Digite seu Email: ");
                String novoEmail = scanner.nextLine();
                System.out.print("Digite sua Senha: ");
                String novaSenha = scanner.nextLine();

                usuarioController.cadastrarUsuario(id, nome, novoEmail, novaSenha);
            }
        }
    }

    private void abrirMenuLogado(Usuario usuarioLogado) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- BEM-VINDO, " + usuarioLogado.getNome().toUpperCase() + " ---");
            System.out.println("1. Visualizar Catálogo de Jogos");
            System.out.println("2. Comprar um Jogo");
            System.out.println("3. Visualizar Minha Biblioteca");
            System.out.println("4. Avaliar um Jogo");
            System.out.println("5. Ver Avaliações de um Jogo");
            System.out.println("6. Alterar Meus Dados Pessoais"); // <-- NOVO
            System.out.println("7. Excluir Minha Conta");          // <-- NOVO
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
                case 6 -> { // <-- CASO NOVO
                    System.out.print("Novo Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nova Senha: ");
                    String senha = scanner.nextLine();
                    usuarioController.atualizarUsuario(usuarioLogado.getId(), nome, email, senha);
                    System.out.println("✅ Dados atualizados com sucesso!");
                }
                case 7 -> { // <-- CASO NOVO
                    System.out.print("⚠️ Tem certeza que deseja apagar a sua conta permanentemente? (S/N): ");
                    String conf = scanner.nextLine();
                    if(conf.equalsIgnoreCase("S")) {
                        usuarioController.deletarUsuario(usuarioLogado.getId());
                        System.out.println("❌ Conta excluída.");
                        opcao = 0;
                    }
                }
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