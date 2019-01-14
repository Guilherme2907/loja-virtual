/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.dto;

import com.guilherme.lojavirtual.domain.Cliente;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Guilherme
 */
public class ClienteDTO implements Serializable{
    
    private Integer id;
    
    @NotEmpty(message = "Nome obrigatório")
    @Length(min = 5,max = 20,message = "Nome deve conter no mínimo {min} e no máximo {max}")
    private String nome;
    
    @NotEmpty(message = "Email obrigatório")
    @Email(message = "Email inválido")
    private String email;

    
    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 
}
