/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author Guilherme
 */
public interface EmailService {
    
    void sendOrderConfirmationEmail(Pedido pedido);
    
    void sendEmail(SimpleMailMessage sm);
}
