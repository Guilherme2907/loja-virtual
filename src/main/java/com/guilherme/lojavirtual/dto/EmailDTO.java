/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;

/**
 *
 * @author Guilherme
 */
public class EmailDTO implements Serializable {

    @Email(message = "Email inválido")
    private String email;

    public EmailDTO() {
    }

    public EmailDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
