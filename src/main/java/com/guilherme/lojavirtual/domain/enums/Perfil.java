/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.domain.enums;

import java.util.Arrays;

/**
 *
 * @author Guilherme
 */
public enum Perfil {

    CLIENTE(1, "ROLE_CLIENTE"), ADMIN(2, "ROLE_ADMIN");

    private Integer cod;
    private String descricao;

    private Perfil(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Perfil p : Perfil.values()) {
            if (p.getCod() == cod) {
                return p;
            }
        }
        return null;
    }
}
