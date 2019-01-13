package com.guilherme.lojavirtual.resources;

import com.guilherme.lojavirtual.domain.Categoria;
import com.guilherme.lojavirtual.dto.CategoriaDTO;
import com.guilherme.lojavirtual.services.CategoriaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> categorias = service.findAll();
        List<CategoriaDTO> categoriasDto = categorias.stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
        return ResponseEntity.ok(categoriasDto);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findAllPage(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "elementsPerPage", defaultValue = "24") int elementsPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

        Page<Categoria> categoriaPage = service.findAllPage(page, elementsPerPage, direction, orderBy);
        Page<CategoriaDTO> categoriaDtoPage = categoriaPage.map(cat -> new CategoriaDTO(cat));
        return ResponseEntity.ok(categoriaDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable("id") Integer id) {
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoriaDTO categoriaDto) {
        Categoria categoria = service.toCategoria(categoriaDto);
        categoria = service.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id,@Valid @RequestBody CategoriaDTO categoriaDto) {
        Categoria categoria = service.toCategoria(categoriaDto);
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
