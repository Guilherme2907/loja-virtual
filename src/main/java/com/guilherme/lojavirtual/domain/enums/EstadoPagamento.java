/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.domain.enums;

/**
 *
 * @author Guilherme
 */
public enum EstadoPagamento {
    
    PENDENTE(1,"Pendente"),QUITADO(2,"Quitado"),CANCELADO(3,"Cancelado");
    
    private int codigo;
    private String descricao;

    private EstadoPagamento(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    public static EstadoPagamento toEnum(int cod){
        for(EstadoPagamento estadoPagamento : EstadoPagamento.values()){
            if(estadoPagamento.getCodigo() == cod){
                return estadoPagamento;
            }
        }
        throw new IllegalArgumentException("Código inválido");
    }
    
}
