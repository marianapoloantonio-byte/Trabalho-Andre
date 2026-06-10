package Model;

public class Administrador extends Pessoa{
    public Administrador(int id,String nome, String email){
        super(id, nome,email);
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Administrador: " + getNome());
    }
}
