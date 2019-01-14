/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Categoria;
import com.guilherme.lojavirtual.dto.CategoriaDTO;
import com.guilherme.lojavirtual.repositories.CategoriaRepository;
import com.guilherme.lojavirtual.services.exception.DataIntegrityViolationExceptionCustom;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Categoria save(Categoria categoria) {
        categoria.setId(null);
        return repository.save(categoria);
    }

     public Categoria update(Categoria categoria) {
        Categoria newCategoria = findById(categoria.getId());
        newCategoria = updateData(newCategoria,categoria);
        return repository.save(newCategoria);
    }

    public void deleteById(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionCustom("Não é possível deletar categorias que possuem produtos cadastrados");
        }
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Page<Categoria> findAllPage(int page, int elementsPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, elementsPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Categoria toCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria(categoriaDTO.getId(),categoriaDTO.getNome());
        return categoria;
    }
    
     public Categoria updateData(Categoria newCategoria,Categoria categoria){
        newCategoria.setNome(categoria.getNome());
        return newCategoria;
    }
}
