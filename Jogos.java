package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jogos implements Serializable {
    private int id;
    private String titulo;
    private String genero;
    private double preco;
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Jogos(int id, String titulo, String genero, double preco) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.preco = preco;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Jogo [ID: " + id + " | Título: " + titulo + " | Gênero: " + genero + " | Preço: R$ " + preco + "]";
    }
}