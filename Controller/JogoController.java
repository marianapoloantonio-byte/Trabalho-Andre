package Controller;

import Model.Jogos;
import Service.ArquivoService;
import Service.LogService;

import java.util.ArrayList;
import java.util.List;

public class JogoController {
    private static List<Jogos> bancoDeJogos = new ArrayList<>();
    private static final String ARQUIVO_JOGOS = "jogos.dat";

    @SuppressWarnings("unchecked")
    public JogoController() {
        if (bancoDeJogos.isEmpty()) {
            List<Jogos> carregados = (List<Jogos>) ArquivoService.carregar(ARQUIVO_JOGOS);
            if (carregados != null) {
                bancoDeJogos = carregados;
            } else {
                bancoDeJogos.add(new Jogos(1, "Resident Evil 4", "Terror", 169.00));
                bancoDeJogos.add(new Jogos(2, "Hollow Knight", "Metroidvania", 27.99));
                ArquivoService.salvar(ARQUIVO_JOGOS, bancoDeJogos);
            }
        }
    }

    public void cadastrarJogo(int id, String titulo, String genero, double preco) {
        Jogos novoJogo = new Jogos(id, titulo, genero, preco);
        bancoDeJogos.add(novoJogo);

        ArquivoService.salvar(ARQUIVO_JOGOS, bancoDeJogos);
        LogService.registrar("Jogo cadastrado: " + titulo);
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
        boolean removido = bancoDeJogos.removeIf(j -> j.getId() == id);
        if (removido) {
            ArquivoService.salvar(ARQUIVO_JOGOS, bancoDeJogos);
            LogService.registrar("Jogo deletado ID: " + id);
            System.out.println("✅ Operação concluída. Jogo removido.");
        } else {
            System.out.println("❌ Jogo não encontrado.");
        }
    }

    public boolean atualizarJogo(int id, String novoTitulo, String novoGenero, double novoPreco) {
        Jogos jogo = buscarPorId(id);
        if (jogo != null) {
            jogo.setTitulo(novoTitulo);
            jogo.setGenero(novoGenero);
            jogo.setPreco(novoPreco);

            ArquivoService.salvar(ARQUIVO_JOGOS, bancoDeJogos);
            LogService.registrar("Jogo atualizado ID " + id + ": " + novoTitulo);
            return true;
        }
        return false;
    }
}