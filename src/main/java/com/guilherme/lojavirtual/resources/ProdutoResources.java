/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.resources;

import com.guilherme.lojavirtual.domain.Produto;
import com.guilherme.lojavirtual.dto.ProdutoDTO;
import com.guilherme.lojavirtual.resources.utils.URL;
import com.guilherme.lojavirtual.services.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Guilherme
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoResources {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable("id") Integer id) {
        Produto produto = service.findById(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> searchProdutos(@RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "elementsPerPage", defaultValue = "24") int elementsPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

        
        nome = URL.decodeParam(nome);
        List<Integer> categoriasIds = URL.decodeIntList(categorias);

        Page<Produto> produtos = service.searchProdutos(nome, categoriasIds, page, elementsPerPage, direction, orderBy);
        Page<ProdutoDTO> produtosDto = produtos.map(produto -> new ProdutoDTO(produto));
        return ResponseEntity.ok(produtosDto);
    }
}
