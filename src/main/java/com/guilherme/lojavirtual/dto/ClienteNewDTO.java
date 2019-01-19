/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.dto;

import com.guilherme.lojavirtual.services.validation.ClienteInsert;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Guilherme
 */
@ClienteInsert
public class ClienteNewDTO {

    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 5, max = 20, message = "Nome deve conter no mínimo {min} e no máximo {max}")
    private String nome;
    
    @NotEmpty(message = "Campo obrigatório")
    private String senha;

    @NotEmpty(message = "Email obrigatório")
    @Email(message = "Email inválido")
    private String email;
    private String cpfOuCnpj;

    
    private Integer tipoCliente;

    @NotEmpty(message = "Campo obrigatório")
    private String logradouro;
    @NotEmpty(message = "Campo obrigatório")
    private String numero;
    private String complemento;
    private String bairro;
    @NotEmpty(message = "Campo obrigatório")
    private String cep;

    private Integer cidadeId;

    @NotEmpty(message = "Campo obrigatório")
    private String telefone1;
    private String telefone2;

    public ClienteNewDTO() {
    }

    public ClienteNewDTO(String nome, String email, String cpfOuCnpj, Integer tipoCliente, String logradouro, String numero, String complemento, 
            String bairro, String cep, Integer cidadeId, String telefone1, String telefone2,String senha) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = tipoCliente;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidadeId = cidadeId;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }
}
