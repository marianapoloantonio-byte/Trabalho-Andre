package Controller;

import Model.Jogos;
import java.util.List;

public class AdministradorController {

    private JogoController jogoController;

    public AdministradorController() {
        this.jogoController = new JogoController();
    }

    public void cadastrarJogo(int id, String titulo, String genero, double preco) {
        jogoController.cadastrarJogo(id, titulo, genero, preco);
    }

    public void removerJogo(int id) {
        jogoController.deletarJogo(id);
    }

    public List<Jogos> listarJogos() {
        return jogoController.listarJogos();
    }

    public Jogos buscarJogo(int id) {
        return jogoController.buscarPorId(id);
    }

    public boolean atualizarJogo(int id, String novoTitulo, String novoGenero, double novoPreco) {
        return jogoController.atualizarJogo(id, novoTitulo, novoGenero, novoPreco);
    }
}