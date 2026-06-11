package Model.Pagamento;

import Interfaces.Pagamento;
import java.io.Serializable;

public class PagamentoBoleto implements Pagamento, Serializable {
    @Override
    public void realizarPagamento(double valor) {
        System.out.println("🧾 Gerando boleto no valor de R$ " + valor + "...");
        System.out.println("✅ Boleto gerado com sucesso!");
    }
}