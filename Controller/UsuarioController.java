package Controller;

import Model.Usuario;
import Service.ArquivoService;
import Service.LogService;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static final String ARQUIVO_USUARIOS = "usuarios.dat";

    @SuppressWarnings("unchecked")
    public UsuarioController() {
        if (usuarios.isEmpty()) {
            List<Usuario> carregados = (List<Usuario>) ArquivoService.carregar(ARQUIVO_USUARIOS);
            if (carregados != null) {
                usuarios = carregados;
            } else {
                usuarios.add(new Usuario(1, "Yasmin", "yas@gmail.com", "123"));
                usuarios.add(new Usuario(2, "Alana", "alana@gmail.com", "123"));
                usuarios.add(new Usuario(3, "Mariana", "mari@gmail.com", "123"));
                ArquivoService.salvar(ARQUIVO_USUARIOS, usuarios);
            }
        }
    }

    public Usuario login(String email, String senha) {
        for (Usuario u : usuarios) {
            if (u.login(email, senha)) {
                return u;
            }
        }
        return null;
    }

    public void cadastrarUsuario(int id, String nome, String email, String senha) {
        Usuario novo = new Usuario(id, nome, email, senha);
        usuarios.add(novo);

        ArquivoService.salvar(ARQUIVO_USUARIOS, usuarios);
        LogService.registrar("Usuário cadastrado: " + nome);
        System.out.println("✨ Usuário " + nome + " cadastrado com sucesso!");
    }

    public boolean deletarUsuario(int id) {
        boolean removido = usuarios.removeIf(u -> u.getId() == id);
        if (removido) {
            ArquivoService.salvar(ARQUIVO_USUARIOS, usuarios);
            LogService.registrar("Usuário deletado ID: " + id);
            return true;
        }
        return false;
    }

    public boolean atualizarUsuario(int id, String novoNome, String novoEmail, String novaSenha) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                u.setNome(novoNome);
                u.setEmail(novoEmail);
                u.setSenha(novaSenha);

                ArquivoService.salvar(ARQUIVO_USUARIOS, usuarios);
                LogService.registrar("Usuário atualizado ID " + id + ": " + novoNome);
                return true;
            }
        }
        return false;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}