package Model.Pagamento;

import Interfaces.Pagamento;

import java.io.Serializable;

public class PagamentoPix implements Pagamento, Serializable {

    @Override
    public void realizarPagamento(double valor) {
        System.out.println("📱 Processando pagamento de R$ " + valor + " via PIX...");
        System.out.println("✅ Pagamento realizado com sucesso!");
    }

}
