package Controller;

import Model.Usuario;
import Service.LogService;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {

        usuarios.add(new Usuario(1, "Yasmin", "yas@gmail.com"));
        usuarios.add(new Usuario(2, "Alana", "alana@gmail.com"));
        usuarios.add(new Usuario(3, "Mariana", "mari@gmail.com"));
    }

    public Usuario login(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public void cadastrarUsuario(int id, String nome, String email) {
        Usuario novo = new Usuario(id, nome, email);
        usuarios.add(novo);
        LogService.registrar("Usuário cadastrado: " + nome);
        System.out.println("✨ Usuário " + nome + " cadastrado com sucesso!");
    }



}
