/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Cidade;
import com.guilherme.lojavirtual.dto.CidadeDTO;
import com.guilherme.lojavirtual.repositories.CidadeRepository;
import com.guilherme.lojavirtual.repositories.EstadoRepository;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guilherme
 */
@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Set<CidadeDTO> findAllByEstado(Integer id) {

        Set<Cidade> cidades = cidadeRepository.findAllByEstadoId(id);

        if (cidades.isEmpty()) {
            throw new ObjectNotFoundErrorCustom("Estado não encontrado");
        }
        return cidades.stream().map(c -> new CidadeDTO(c)).collect(Collectors.toSet());
    }

}
