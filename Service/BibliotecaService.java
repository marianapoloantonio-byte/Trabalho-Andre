package Service;

import Model.Jogos;
import Model.Usuario;

import java.util.List;

// -- Serve para criar uma regra de negocio
public class BibliotecaService {

    // -- Nao deixa adicionar um jogo ja existente na biblioteca
    public boolean adicionarJogo(Usuario usuario, Jogos jogo) {

        if(usuario.getBiblioteca().contains(jogo)) {
            System.out.println("❌ Você já possui este jogo.");
            return false;
        }

        usuario.adicionarJogo(jogo);

        return true;
    }


    // -- Adiciona o jogo
    public void adicionarJogo(Usuario usuario, List<Jogos> jogos) {

        for(Jogos jogo : jogos) {
            adicionarJogo(usuario, jogo);
        }

    }


}
