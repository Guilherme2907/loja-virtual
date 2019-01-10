package com.guilherme.lojavirtual.resources;

import com.guilherme.lojavirtual.domain.Categoria;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Guilherme
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @GetMapping
    public List<Categoria> list(){
        Categoria c1 = new Categoria(1,"Informática");
        Categoria c2 = new Categoria(2,"Escritório");
        List<Categoria> categorias = Arrays.asList(c1,c2);
        return categorias;
    }
}
