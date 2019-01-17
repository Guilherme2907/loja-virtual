/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.config;

import com.guilherme.lojavirtual.services.DBService;
import com.guilherme.lojavirtual.services.EmailService;
import com.guilherme.lojavirtual.services.SmtpEmailService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author Guilherme
 */
@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dBService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDevDataBase() throws ParseException {
        if (!strategy.equals("create")) {
            return false;
        }
        dBService.instantiateTestDataBase();
        return true;
    }
    
    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
