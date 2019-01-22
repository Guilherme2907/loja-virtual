/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.dto;

import com.guilherme.lojavirtual.domain.Estado;
import java.io.Serializable;

/**
 *
 * @author Guilherme
 */
public class EstadoDTO implements Serializable {

    private String nome;

    public EstadoDTO() {
    }

    public EstadoDTO(Estado estado) {
        this.nome = estado.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
