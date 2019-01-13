package com.guilherme.lojavirtual.resources;

import com.guilherme.lojavirtual.domain.Categoria;
import com.guilherme.lojavirtual.services.CategoriaService;
import com.guilherme.lojavirtual.services.exception.DataIntegrityViolationExceptionCustom;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<Categoria> findById(@PathVariable("id") Integer id) {
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Categoria categoria) {
        Categoria categoriaSaved = service.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoriaSaved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        service.update(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
