package Controller;

import Interfaces.Pagamento;
import Model.Jogos;
import Model.Pagamento.PagamentoBoleto;
import Model.Pagamento.PagamentoCartao;
import Model.Pagamento.PagamentoPix;
import Model.Usuario;
import Model.Compra;
import Service.BibliotecaService;
import Service.LogService;
import Service.ArquivoService;

import java.util.ArrayList;
import java.util.List;

public class CompraController {

    private BibliotecaService bibliotecaService = new BibliotecaService();
    private List<Compra> compras = new ArrayList<>();

    public boolean processarCompra(Usuario usuario, Jogos jogo, int opcaoPagamento) {

        if(usuario == null || jogo == null)
            return false;

        Pagamento pagamento;

        switch(opcaoPagamento){
            case 1 -> pagamento = new PagamentoCartao();
            case 2 -> pagamento = new PagamentoPix();
            case 3 -> pagamento = new PagamentoBoleto();
            default -> {
                return false;
            }
        }

        pagamento.realizarPagamento(jogo.getPreco());

        boolean adicionou = bibliotecaService.adicionarJogo(usuario, jogo);

        if(!adicionou)
            return false;

        List<Jogos> jogosComprados = new ArrayList<>();
        jogosComprados.add(jogo);

        Compra compra = new Compra(usuario, jogosComprados, pagamento);
        compras.add(compra);

        UsuarioController usuarioController = new UsuarioController();
        ArquivoService.salvar("usuarios.dat", usuarioController.listarUsuarios());

        LogService.registrar("Compra realizada: " + usuario.getNome() + " comprou " + jogo.getTitulo());
        System.out.println("✅ Compra de '" + jogo.getTitulo() + "' finalizada com sucesso!");
        return true;
    }
}