package com.guilherme.lojavirtual.resources;

import com.guilherme.lojavirtual.domain.Categoria;
import com.guilherme.lojavirtual.services.CategoriaService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Guilherme
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
    
    @Autowired
    CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> list(@PathVariable("id") Integer id){
        Categoria categoria = service.findById(id);
       
        return ResponseEntity.ok(categoria);
    }
}
