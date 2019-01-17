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

    

    public static void main(String[] args) {
        SpringApplication.run(LojaVirtualApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
      
    }

}
