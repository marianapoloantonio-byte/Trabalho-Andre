package Model;

import java.io.Serializable;

    public abstract class Pessoa implements Serializable{

        protected int id;
        protected String nome;
        protected String email;


        public Pessoa (int id, String nome, String email){
            this.id = id;
            this.nome = nome;
            this.email = email;
        }

        //abstrato
        public abstract void exibirPerfil();

        public int getIdade() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getEmail() {
            return email;
        }

        public void setIdade(int id) {
            this.id = id;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setEmail(String email) {
            this.email = email;
        }


    }

