package Controller;

import Model.Avaliacao;
import Model.Jogos;
import Model.Usuario;

public class AvaliacaoController {

    public boolean avaliarJogo(Usuario usuario, Jogos jogo, int nota, String comentario) {

        if(nota < 1 || nota > 5) {
            return false;
        }

        Avaliacao avaliacao = new Avaliacao(usuario, jogo, nota, comentario);

        jogo.adicionarAvaliacao(avaliacao);

        return true;
    }
}
