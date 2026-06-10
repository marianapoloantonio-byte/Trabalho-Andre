package View;

import Controller.AvaliacaoController;
import Controller.JogoController;
import Model.Avaliacao;
import Model.Jogos;
import Model.Usuario;

import java.util.Scanner;

public class AvaliacaoView {

    private Scanner scanner = new Scanner(System.in);

    private AvaliacaoController avaliacaoController = new AvaliacaoController();
    private JogoController jogoController = new JogoController();
    private JogosView jogosView = new JogosView();

    public void avaliarJogo(Usuario usuario) {

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

        // ============= ATENCAO ==============

        avaliacaoController.avaliarJogo(usuario, jogoEscolhido, nota, comentario);


        System.out.println("\n✅ Avaliação registrada!");

        //Adiciona a quantidade de estrelas da avaliacao
        String estrelas = "";
        for(int i = 0; i < nota; i++) {
            estrelas += "⭐";
        }
        System.out.println("⭐ Nota: " + estrelas);

        System.out.println("💬 Comentário: " + comentario);
    }



    public void visualizarAvaliacoes() {

        jogosView.exibirCatalogo(jogoController.listarJogos());

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
