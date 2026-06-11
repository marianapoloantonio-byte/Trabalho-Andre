package View;

import java.util.Scanner;

public class MenuView {
    private Scanner scanner = new Scanner(System.in);
    private AdministradorView administradorView = new AdministradorView();
    private UsuarioView usuarioView = new UsuarioView();

    public void exibirMenuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--------BEM-VINDO À LOJA DE JOGOS--------");
            System.out.println("1. Entrar como Usuário");
            System.out.println("2. Entrar como Administrador");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> usuarioView.menuUsuario();
                case 2 -> administradorView.exibirMenuAdministrador();
                case 0 -> System.out.println("Saindo do sistema... Até logo!");
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}