package View;

import Model.Jogos;
import java.util.List;

public class JogosView {

    public void exibirCatalogo(List<Jogos> jogos) {

        System.out.println("\n----------- CATÁLOGO DE JOGOS -----------");

        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return;
        }

        for (Jogos j : jogos) {
            System.out.println(
                    "ID: " + j.getId()
                            + " | " + j.getTitulo()
                            + " [" + j.getGenero() + "]"
                            + " - R$ " + j.getPreco()
            );
        }
    }
}
