/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Guilherme
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTUtil jWTUtil;

    private UserDetailsService userDatailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jWTUtil, UserDetailsService userDatailsService) {
        super(authenticationManager);
        this.jWTUtil = jWTUtil;
        this.userDatailsService = userDatailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth = getAuthenticationToken(token.substring(7));
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        if (jWTUtil.isTokenValid(token)) {
            String userName = jWTUtil.getUsername(token);
            UserDetails user = userDatailsService.loadUserByUsername(userName);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }

}
