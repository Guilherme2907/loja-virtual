/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.repositories;

import com.guilherme.lojavirtual.domain.Categoria;
import com.guilherme.lojavirtual.domain.Produto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Guilherme
 */
public interface ProdutoRepository  extends  JpaRepository<Produto, Integer>{

    @Query("SELECT DISTINCT p from Produto p INNER JOIN p.categorias cat WHERE p.nome LIKE %:nome% AND cat IN :categorias")
    public Page<Produto> searchProdutos(@Param("nome")String nome,@Param("categorias")List<Categoria> categorias,Pageable pageRequest);

}
