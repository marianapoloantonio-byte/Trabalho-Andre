package Controller;

import Model.Jogos;
import Model.Usuario;

public class CompraController {
    
    public boolean processarCompra(Usuario usuario, Jogos jogo, int opcaoPagamento) {
        if (usuario == null || jogo == null) return false;

        // Impede comprar o mesmo jogo duas vezes 
        if (usuario.getBiblioteca().contains(jogo)) {
            System.out.println("Você já possui este jogo na sua biblioteca!");
            return false;
        }

        // Simula a validação das interfaces de pagamento 
        String formaPagamento = switch (opcaoPagamento) {
            case 1 -> "Cartão de Crédito";
            case 2 -> "Pix";
            case 3 -> "Boleto Bancário";
            default -> "Desconhecido";
        };

        System.out.println("💳 Processando pagamento de R$ " + jogo.getPreco() + " via " + formaPagamento + "...");
        
        // Adiciona o jogo na biblioteca do usuário
        usuario.adicionarJogo(jogo);
        System.out.println("Sucesso! '" + jogo.getTitulo() + "' foi adicionado à sua biblioteca.");
        return true;
    }
}