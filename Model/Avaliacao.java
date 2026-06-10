package Model;

import java.io.Serializable;

public class Avaliacao implements Serializable {

    private Usuario usuario;
    private Jogos jogo;
    private int nota;
    private String comentario;

    // -- Construtor
    public Avaliacao(Usuario usuario, Jogos jogo, int nota, String comentario) {
        this.usuario = usuario;
        this.jogo = jogo;
        this.nota = nota;
        this.comentario = comentario;
    }

    //Sistema para colocar as estrelas da avaliação conforme o numero
    public String getEstrelas() {

        String estrelas = "";

        for(int i = 0; i < nota; i++) {
            estrelas += "⭐";
        }

        return estrelas;
    }

    // -- Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public Jogos getJogo() {
        return jogo;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }


}
