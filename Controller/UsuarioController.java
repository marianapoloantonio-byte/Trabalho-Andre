package Controller;

import Model.Usuario;
import Service.LogService;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        usuarios.add(new Usuario(1, "Yasmin", "yas@gmail.com", "123"));
        usuarios.add(new Usuario(2, "Alana", "alana@gmail.com", "123"));
        usuarios.add(new Usuario(3, "Mariana", "mari@gmail.com", "123"));
    }

    // Agora o método login recebe e-mail e senha para validar
    public Usuario login(String email, String senha) {
        for (Usuario u : usuarios) {
            if (u.login(email, senha)) {
                return u;
            }
        }
        return null;
    }

    // O método de cadastro agora recebe e armazena a senha nova
    public void cadastrarUsuario(int id, String nome, String email, String senha) {
        Usuario novo = new Usuario(id, nome, email, senha);
        usuarios.add(novo);

        LogService.registrar("Usuário cadastrado: " + nome);
        System.out.println("✨ Usuário " + nome + " cadastrado com sucesso!");
    }
}