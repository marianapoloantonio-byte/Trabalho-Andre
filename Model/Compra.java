package Model;

import Interfaces.Pagamento;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

// -- Serializable serve para salvar objetos
public class Compra implements Serializable {

    private Usuario usuario;
    private List<Jogos> jogos;
    private Pagamento pagamento;
    private LocalDate dataCompra;

    // -- Construtor
    public Compra(Usuario usuario, List<Jogos> jogos, Pagamento pagamento){
        this.usuario = usuario;
        this.jogos = jogos;
        this.pagamento = pagamento;
        this.dataCompra = LocalDate.now();
    }

    //Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public List<Jogos> getJogos() {
        return jogos;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

}
