package dcomp.lpweb.vendas.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "pagamento_boleto")
public final class PagamentoBoleto extends Pagamento {

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @PastOrPresent
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
