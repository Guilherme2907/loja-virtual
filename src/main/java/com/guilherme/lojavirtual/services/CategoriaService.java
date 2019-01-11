/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Categoria;
import com.guilherme.lojavirtual.repositories.CategoriaRepository;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guilherme
 */
@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    public Categoria findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundErrorCustom("Objeto não encontrado para o Id: "
                + id + ",Tipo: " + Categoria.class.getSimpleName()));
    }
}
