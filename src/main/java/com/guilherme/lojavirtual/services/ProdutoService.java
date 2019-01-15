/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Categoria;
import com.guilherme.lojavirtual.domain.Produto;
import com.guilherme.lojavirtual.repositories.CategoriaRepository;
import com.guilherme.lojavirtual.repositories.ProdutoRepository;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guilherme
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtorepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto findById(Integer id) {
        return produtorepository.findById(id).orElseThrow(() -> new ObjectNotFoundErrorCustom("Objeto não encontrado para o Id: "
                + id + ",Tipo: " + Produto.class.getSimpleName()));
    }

    public Page<Produto> searchProdutos(String nome, List<Integer> categoriasIds, Integer page, Integer elementsPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, elementsPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(categoriasIds);
        return produtorepository.searchProdutos(nome, categorias, pageRequest);
    }

}
