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
public enum TipoCliente {

    PESSOAJURIDICA(1, "Pessoa Física"),
    PESSOAFISICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descricao;

    private TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(int cod) {
        for (TipoCliente tipo : TipoCliente.values()) {
            if (tipo.getCodigo() == cod) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido");
    }
}
