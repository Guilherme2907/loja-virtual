/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Cliente;
import com.guilherme.lojavirtual.repositories.ClienteRepository;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import com.guilherme.lojavirtual.services.util.SenhaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guilherme
 */
@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundErrorCustom("Email não encontrado");
        }

        String newPass = SenhaUtil.createNewPass();
        cliente.setSenha(encoder.encode(newPass));
        clienteRepository.save(cliente);
        emailService.sendNewEmail(cliente, newPass);
    }
}
