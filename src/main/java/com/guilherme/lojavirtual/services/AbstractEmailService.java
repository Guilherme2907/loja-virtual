/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Cliente;
import com.guilherme.lojavirtual.domain.Pedido;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author Guilherme
 */
public abstract class AbstractEmailService implements EmailService {
    
    @Value("${default.sender}")
    private String sender;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(sm);
    }
    
    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pedido.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado,Código: " + pedido.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());
        return sm;
    }
    
    protected String htmlFromTemplatePedido(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return templateEngine.process("email/confirmacaoPedido", context);
    }
    
    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
        try {
            MimeMessage mm = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(mm);
        } catch (MessagingException ex) {
            sendOrderConfirmationEmail(pedido);
        }
    }
    
    protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm);
        mmh.setTo(pedido.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido confirmado,Código: " + pedido.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(pedido), true);
        return mm;
    }
    
    @Override
    public void sendNewPassEmail(Cliente cliente, String newPass) {
        SimpleMailMessage sm = prepareSimpleMailMessagePassword(cliente, newPass);
        sendEmail(sm);
    }
    
    private SimpleMailMessage prepareSimpleMailMessagePassword(Cliente cliente, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(cliente.getEmail());
        sm.setSubject("Confirmação de nova senha");
        sm.setFrom(sender);
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }
    
}
