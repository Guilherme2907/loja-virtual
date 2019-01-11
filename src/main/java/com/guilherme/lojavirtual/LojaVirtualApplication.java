package com.guilherme.lojavirtual;

import com.guilherme.lojavirtual.domain.Categoria;
import com.guilherme.lojavirtual.domain.Cidade;
import com.guilherme.lojavirtual.domain.Cliente;
import com.guilherme.lojavirtual.domain.Endereco;
import com.guilherme.lojavirtual.domain.Estado;
import com.guilherme.lojavirtual.domain.Produto;
import com.guilherme.lojavirtual.domain.enums.TipoCliente;
import com.guilherme.lojavirtual.repositories.CategoriaRepository;
import com.guilherme.lojavirtual.repositories.CidadeRepository;
import com.guilherme.lojavirtual.repositories.ClienteRepository;
import com.guilherme.lojavirtual.repositories.EnderecoRepository;
import com.guilherme.lojavirtual.repositories.EstadoRepository;
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
    
    @Autowired
    CidadeRepository cidadeRepository;
    
    @Autowired
    EstadoRepository estadoRepository;
    
    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired
    EnderecoRepository enderecoRepository;

    public static void main(String[] args) {
        SpringApplication.run(LojaVirtualApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria("Informática");
        Categoria cat2 = new Categoria("Escritório");
        
        Produto p1 = new Produto("Computador", 2000.00);
        Produto p2 = new Produto("Impressora", 800.00);
        Produto p3 = new Produto("Mouse", 80.00);
        
        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));
        
        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));
        
        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
        
        Estado est1 = new Estado("Minas Gerais");
        Estado est2 = new Estado("São Paulo");
        
        Cidade c1 = new Cidade("Uberlândia", est1);
        Cidade c2 = new Cidade("São Paulo", est2);
        Cidade c3 = new Cidade("Campinas", est2);
        
        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2,c3));
        
        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
        
        Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "397924848", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("23078956","23075892"));
        Endereco e1 = new Endereco("Rua Flores", "300", "Apto 203", "jardim", "38220834", c1, cli1);
        Endereco e2 = new Endereco("Avenida Matos", "105", "Sala 800", "centro", "78954899", c1, cli1);
        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
        
        
        clienteRepository.save(cli1);
        enderecoRepository.saveAll(Arrays.asList(e1,e2));
        
        
    }

}
