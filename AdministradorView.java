package View;

import Controller.AdministradorController;
import Model.Jogos;
import java.util.Scanner;

public class AdministradorView {

    private Scanner scanner = new Scanner(System.in);
    private AdministradorController administradorController = new AdministradorController();

    public void exibirMenuAdministrador() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1. Cadastrar Novo Jogo");
            System.out.println("2. Listar Todos os Jogos");
            System.out.println("3. Remover Jogo");
            System.out.println("4. Atualizar Dados de um Jogo"); // <-- NOVO NO MENU
            System.out.println("0. Voltar");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarJogo();
                case 2 -> listarJogos();
                case 3 -> removerJogo();
                case 4 -> atualizarJogo();
                case 0 -> System.out.println("Saindo do modo administrador...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarJogo() {

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        System.out.print("Preço: ");
        String precoInput = scanner.nextLine();

        double preco = Double.parseDouble(precoInput.trim().replace(",", "."));

        administradorController.cadastrarJogo(id, titulo, genero, preco);
    }

    private void listarJogos() {

        if (administradorController.listarJogos().isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return;
        }

        System.out.println("\n=== CATÁLOGO DE JOGOS ===");

        for (Jogos j : administradorController.listarJogos()) {
            System.out.println(
                    "ID: " + j.getId()
                            + " | " + j.getTitulo()
                            + " | " + j.getGenero()
                            + " | R$ " + j.getPreco()
            );
        }
    }

    private void removerJogo() {

        System.out.print("Digite o ID do jogo: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        administradorController.removerJogo(id);
    }

    private void atualizarJogo() {
        System.out.print("Digite o ID do jogo que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Novo Título: ");
        String novoTitulo = scanner.nextLine();

        System.out.print("Novo Gênero: ");
        String novoGenero = scanner.nextLine();

        System.out.print("Novo Preço: ");
        String precoInput = scanner.nextLine();
        double novoPreco = Double.parseDouble(precoInput.trim().replace(",", "."));

        boolean sucesso = administradorController.atualizarJogo(id, novoTitulo, novoGenero, novoPreco);

        if (sucesso) {
            System.out.println("✅ Jogo atualizado com sucesso!");
        } else {
            System.out.println("❌ Jogo não encontrado com o ID fornecido.");
        }
    }
}