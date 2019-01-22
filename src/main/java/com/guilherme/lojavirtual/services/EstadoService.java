/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Estado;
import com.guilherme.lojavirtual.dto.EstadoDTO;
import com.guilherme.lojavirtual.repositories.EstadoRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guilherme
 */

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Set<EstadoDTO> findAll() {
        Set<Estado> estados = estadoRepository.findAllByOrderByNome();
        return estados.stream().map(e -> new EstadoDTO(e)).collect(Collectors.toSet());
    }

}
