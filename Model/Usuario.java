package Model;

import Interfaces.Autenticavel;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa implements Autenticavel {
    private String senha;
    private List<Jogos> biblioteca;

    public Usuario(int id, String nome, String email, String senha) {
        super(id, nome, email);
        this.senha = senha;
        this.biblioteca = new ArrayList<>();
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Usuário: " + nome + " | Email: " + email + " | ID: " + id);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void adicionarJogo(Jogos jogo) {
        biblioteca.add(jogo);
    }

    public List<Jogos> getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(List<Jogos> biblioteca) {
        this.biblioteca = biblioteca;
    }

    // Valida se o email E a senha estão corretos
    @Override
    public boolean login(String email, String senha) {
        return this.email.equalsIgnoreCase(email) && this.senha.equals(senha);
    }
}