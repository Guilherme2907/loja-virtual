/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.domain;

import com.guilherme.lojavirtual.domain.enums.EstadoPagamento;
import javax.persistence.Entity;


/**
 *
 * @author Guilherme
 */

@Entity
public class PagamentoComCartao extends Pagamento {

    private int numeroParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(int numeroParcelas, EstadoPagamento estadoPagamento, Pedido pedido) {
        super(estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

}
