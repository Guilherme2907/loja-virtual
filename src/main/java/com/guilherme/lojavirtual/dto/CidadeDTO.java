/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.dto;

import com.guilherme.lojavirtual.domain.Cidade;
import java.io.Serializable;

/**
 *
 * @author Guilherme
 */
public class CidadeDTO implements Serializable{
    private Integer id;
    
    private String nome;

    public CidadeDTO() {
    }

    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }  
}
