/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.lojavirtual.dto.CredenciaisDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Guilherme
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private AuthenticationManager authenticationManager;
    
    private JWTUtil jWTUtil;
    
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jWTUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jWTUtil = jWTUtil;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            CredenciaisDTO cred = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getSenha(), new ArrayList<>());
            Authentication auth = authenticationManager.authenticate(user);
            return auth;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        String username = ((UserDetailsApp) auth.getPrincipal()).getUsername();
        String token = jWTUtil.generateToken(username);
        res.addHeader("Authorization", "Bearer " + token);
    }
    
    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
        
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            
            response.setStatus(401);
            
            response.setContentType("application/json");
            
            response.getWriter().append(json());            
        }
        
        private String json() {
            
            long date = new Date().getTime();
            
            return "{\"timestamp\": " + date + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Não autorizado\", "
                    + "\"message\": \"Email ou senha inválidos\", "
                    + "\"path\": \"/login\"}";
        }
    }
}
