package com.guilherme.lojavirtual;

import com.guilherme.lojavirtual.domain.Categoria;
import com.guilherme.lojavirtual.domain.Cidade;
import com.guilherme.lojavirtual.domain.Cliente;
import com.guilherme.lojavirtual.domain.Endereco;
import com.guilherme.lojavirtual.domain.Estado;
import com.guilherme.lojavirtual.domain.ItemPedido;
import com.guilherme.lojavirtual.domain.Pagamento;
import com.guilherme.lojavirtual.domain.PagamentoComBoleto;
import com.guilherme.lojavirtual.domain.PagamentoComCartao;
import com.guilherme.lojavirtual.domain.Pedido;
import com.guilherme.lojavirtual.domain.Produto;
import com.guilherme.lojavirtual.domain.enums.EstadoPagamento;
import com.guilherme.lojavirtual.domain.enums.TipoCliente;
import com.guilherme.lojavirtual.repositories.CategoriaRepository;
import com.guilherme.lojavirtual.repositories.CidadeRepository;
import com.guilherme.lojavirtual.repositories.ClienteRepository;
import com.guilherme.lojavirtual.repositories.EnderecoRepository;
import com.guilherme.lojavirtual.repositories.EstadoRepository;
import com.guilherme.lojavirtual.repositories.ItemPedidoRepository;
import com.guilherme.lojavirtual.repositories.PagamentoRepository;
import com.guilherme.lojavirtual.repositories.PedidoRepository;
import com.guilherme.lojavirtual.repositories.ProdutoRepository;
import java.text.SimpleDateFormat;
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
    
    @Autowired
    PedidoRepository pedidoRepository;
    
    @Autowired
    PagamentoRepository pagamentoRepository;
    
    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(LojaVirtualApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null,"Informática");
        Categoria cat2 = new Categoria(null,"Escritório");

        Produto p1 = new Produto(null,"Computador", 2000.00);
        Produto p2 = new Produto(null,"Impressora", 800.00);
        Produto p3 = new Produto(null,"Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null,"Minas Gerais");
        Estado est2 = new Estado(null,"São Paulo");

        Cidade c1 = new Cidade(null,"Uberlândia", est1);
        Cidade c2 = new Cidade(null,"São Paulo", est2);
        Cidade c3 = new Cidade(null,"Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null,"Maria Silva", "maria@gmail.com", "397924848", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("23078956", "23075892"));
        Endereco e1 = new Endereco(null,"Rua Flores", "300", "Apto 203", "jardim", "38220834", c1, cli1);
        Endereco e2 = new Endereco(null,"Avenida Matos", "105", "Sala 800", "centro", "78954899", c1, cli1);
        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.save(cli1);
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),e1, cli1);
        Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),e2, cli1);
        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        Pagamento pagto1 = new PagamentoComCartao(6,EstadoPagamento.QUITADO, ped1);
        ped1.setPagamento(pagto1);
        Pagamento pagto2 = new PagamentoComBoleto(sdf.parse("20/10/2017 00:00"),null,EstadoPagamento.PENDENTE, ped2);
        ped2.setPagamento(pagto2);

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
        
        ItemPedido ip1 = new ItemPedido(p1,ped1,0.00,1,2000.00);
        ItemPedido ip2 = new ItemPedido(p3,ped1,0.00,2,80.00);
        ItemPedido ip3 = new ItemPedido(p2,ped2,100.00,1,800.00);
        
        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
        
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        ped1.getItens().addAll(Arrays.asList(ip2));
        
        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
    }

}
