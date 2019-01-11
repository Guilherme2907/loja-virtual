package com.guilherme.lojavirtual;

import com.guilherme.lojavirtual.domain.Categoria;
import com.guilherme.lojavirtual.domain.Produto;
import com.guilherme.lojavirtual.repositories.CategoriaRepository;
import com.guilherme.lojavirtual.repositories.ProdutoRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LojaVirtualApplication implements CommandLineRunner {

    @Autowired
    CategoriaRepository categoriaRepository;
    
    @Autowired
    ProdutoRepository produtoRepository;

    public static void main(String[] args) {
        SpringApplication.run(LojaVirtualApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria c1 = new Categoria("Informática");
        Categoria c2 = new Categoria("Escritório");
        
        Produto p1 = new Produto("Computador", 2000.00);
        Produto p2 = new Produto("Impressora", 800.00);
        Produto p3 = new Produto("Mouse", 80.00);
        
        c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        c2.getProdutos().addAll(Arrays.asList(p2));
        
        p1.getCategorias().addAll(Arrays.asList(c1));
        p2.getCategorias().addAll(Arrays.asList(c1,c2));
        p3.getCategorias().addAll(Arrays.asList(c1));
        
        categoriaRepository.saveAll(Arrays.asList(c1,c2));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
    }

}
