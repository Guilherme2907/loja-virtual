/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.resources;

import com.guilherme.lojavirtual.domain.Pedido;
import com.guilherme.lojavirtual.services.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Guilherme
 */

@RestController
@RequestMapping("/pedidos")
public class PedidoResources {
    
    @Autowired
    private PedidosService service;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidos(@PathVariable("id") Integer id){
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok(pedido);
    }
}
