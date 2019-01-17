/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.config;

import com.guilherme.lojavirtual.services.DBService;
import com.guilherme.lojavirtual.services.EmailService;
import com.guilherme.lojavirtual.services.MockEmailService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author Guilherme
 */
@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dBService;

    @Bean
    public boolean instantiateTestDataBase() throws ParseException {
        dBService.instantiateTestDataBase();
        return true;
    }
    
    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
