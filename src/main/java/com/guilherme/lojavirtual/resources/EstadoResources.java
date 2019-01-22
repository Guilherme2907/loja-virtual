/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.resources;

import com.guilherme.lojavirtual.dto.CidadeDTO;
import com.guilherme.lojavirtual.dto.EstadoDTO;
import com.guilherme.lojavirtual.services.CidadeService;
import com.guilherme.lojavirtual.services.EstadoService;
import java.util.Set;
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
@RequestMapping("/estados")
public class EstadoResources {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<Set<EstadoDTO>> findAll() {
        Set<EstadoDTO> estados = estadoService.findAll();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}/cidades")
    public ResponseEntity<Set<CidadeDTO>> findCidadesByEstado(@PathVariable("id") Integer id) {
        Set<CidadeDTO> cidades = cidadeService.findAllByEstado(id);
        return ResponseEntity.ok(cidades);
    }
}
