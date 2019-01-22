/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.repositories;

import com.guilherme.lojavirtual.domain.Cidade;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Guilherme
 */
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query("SELECT c FROM Cidade c where c.estado.id = :id")
    Set<Cidade> findAllByEstadoId(@Param("id") Integer id);
}
