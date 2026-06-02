package Model;

import java.io.Serializable;

public abstract class Pessoa implements Serializable{

    protected int idade;
    protected String nome;
    protected String email;


    public Pessoa (int idade, String nome, String email){
        this.idade = idade;
        this.nome = nome;
        this.email = email;
    }

    //abstrato
    public abstract void exibirPerfil();

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
