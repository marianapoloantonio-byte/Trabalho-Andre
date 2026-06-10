package Model;

import Interfaces.Autenticavel;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa implements Autenticavel {
    private List<Jogos> biblioteca;

    public Usuario(int id,String nome, String email){
        super (id,nome,email);
        this.biblioteca = new ArrayList<>();
    }

    @Override
    public void exibirPerfil(){
        System.out.println("usuario"+ nome);
    }


    // Adicionar jogo na biblioteca
    public void adicionarJogo(Jogos jogo) {
        biblioteca.add(jogo);
    }
    public List<Jogos> getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(List<Jogos> biblioteca) {
        this.biblioteca = biblioteca;
    }

    // --- Verifica se o email esta cadastrado
    @Override
    public boolean login(String email) {
        return this.email.equalsIgnoreCase(email);
    }
}
