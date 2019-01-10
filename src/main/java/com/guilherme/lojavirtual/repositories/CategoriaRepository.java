/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.repositories;

import com.guilherme.lojavirtual.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Guilherme
 */
public interface CategoriaRepository  extends  JpaRepository<Categoria, Integer>{
    
}
