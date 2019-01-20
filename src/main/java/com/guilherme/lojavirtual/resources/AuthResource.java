/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.resources;

import com.guilherme.lojavirtual.dto.EmailDTO;
import com.guilherme.lojavirtual.security.JWTUtil;
import com.guilherme.lojavirtual.security.UserDetailsApp;
import com.guilherme.lojavirtual.services.AuthService;
import com.guilherme.lojavirtual.services.UserService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Guilherme
 */
@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jWTUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserDetailsApp user = userService.authenticated();
        String token = jWTUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
        authService.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }
}
