/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.repositories;

import com.guilherme.lojavirtual.domain.Cliente;
import com.guilherme.lojavirtual.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Guilherme
 */
public interface PedidoRepository  extends  JpaRepository<Pedido, Integer>{
    
    Page<Pedido> findByCliente(Cliente cliente,Pageable pageable); 
}
