/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.domain;

import javax.persistence.Entity;

/**
 *
 * @author Guilherme
 */

@Entity
public class Categoria extends AbstractEntity<Integer> {

    private String nome;

    public Categoria() {
        super();
    }

    public Categoria(Integer id,String nome) {
        super(id);
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
