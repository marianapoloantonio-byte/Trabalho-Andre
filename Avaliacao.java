package Model;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    private Usuario usuario;
    private Jogos jogo;
    private int nota;
    private String comentario;

    public Avaliacao(Usuario usuario, Jogos jogo, int nota, String comentario) {
        this.usuario = usuario;
        this.jogo = jogo;
        this.nota = nota;
        this.comentario = comentario;
    }

    public String getEstrelas() {
        String estrelas = "";
        for (int i = 0; i < nota; i++) {
            estrelas += "⭐";
        }
        return estrelas;
    }

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