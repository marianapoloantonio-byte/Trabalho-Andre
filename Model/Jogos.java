package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jogos  implements Serializable {
    private int id;
    private String titulo;
    private String genero;
    private double preco;

    // -- Cria um array list para avaliações
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Jogos(int id, String titulo, String genero, double preco) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.preco = preco;
    }

    // -- Adiciona uma avaliacao na lista
    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }


    //gets
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public double getPreco() {
        return preco;
    }
    //sets
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString(){
        return "jogo{" + "id" + id + ",titulo" + titulo +" ,genero" + genero +
                "preço" +preco + '}';
    }
}
