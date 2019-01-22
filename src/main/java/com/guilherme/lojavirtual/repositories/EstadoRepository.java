/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.repositories;

import com.guilherme.lojavirtual.domain.Estado;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Guilherme
 */
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Query("select e from Estado e order by e.nome")
    Set<Estado> findAllByOrderByNome(); 
    
}
