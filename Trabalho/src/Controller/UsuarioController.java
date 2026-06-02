package Controller;

import Model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        usuarios.add(new Usuario(1, "Yasmin", "yas@email.com"));
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
        System.out.println("✨ Usuário " + nome + " cadastrado com sucesso!");
    }
}