package Controller;

import Model.Jogos;
import java.util.ArrayList;
import java.util.List;

public class JogoController {
    private List<Jogos> bancoDeJogos = new ArrayList<>();

    public JogoController() {
        // Alguns jogos iniciais para teste
        bancoDeJogos.add(new Jogos(1, "Resident Evil 4", "Terror", 169.00));
        bancoDeJogos.add(new Jogos(2, "Hollow Knight", "Metroidvania", 27.99));
    }

    // CRUD para o menu do Administrador
    public void cadastrarJogo(int id, String titulo, String genero, double preco) {
        Jogos novoJogo = new Jogos(id, titulo, genero, preco);
        bancoDeJogos.add(novoJogo);
        System.out.println("Jogo '" + titulo + "' cadastrado com sucesso!");
    }

    public List<Jogos> listarJogos() {
        return bancoDeJogos;
    }

    public Jogos buscarPorId(int id) {
        for (Jogos j : bancoDeJogos) {
            if (j.getId() == id) return j;
        }
        return null;
    }

    public void deletarJogo(int id) {
        Jogos jogo = buscarPorId(id);
        if (jogo != null) {
            bancoDeJogos.remove(jogo);
            System.out.println("Jogo removido do catálogo.");
        } else {
            System.out.println("Jogo não encontrado.");
        }
    }
}