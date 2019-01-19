/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Cliente;
import com.guilherme.lojavirtual.domain.ItemPedido;
import com.guilherme.lojavirtual.domain.PagamentoComBoleto;
import com.guilherme.lojavirtual.domain.Pedido;
import com.guilherme.lojavirtual.domain.enums.EstadoPagamento;
import com.guilherme.lojavirtual.repositories.ItemPedidoRepository;
import com.guilherme.lojavirtual.repositories.PagamentoRepository;
import com.guilherme.lojavirtual.repositories.PedidoRepository;
import com.guilherme.lojavirtual.security.UserDetailsApp;
import com.guilherme.lojavirtual.services.exception.AuthorizationException;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Guilherme
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private UserService userService;

    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundErrorCustom("Objeto não encontrado para o Id: "
                + id + ",Tipo: " + Pedido.class.getSimpleName()));
    }
    
    public Page<Pedido> findAllPage(Integer page,Integer elementsPerPage,String direction,String orderBy){
        UserDetailsApp user = userService.authenticated();
        PageRequest pageRequest = PageRequest.of(page, elementsPerPage, Sort.Direction.valueOf(direction), orderBy);
        if(user == null){
            throw new AuthorizationException("Acesso negado");
        }
        
        Cliente cliente = clienteService.findById(user.getId());
        return pedidoRepository.findByCliente(cliente, pageRequest);
    }

    @Transactional
    public Pedido save(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pcb = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.setDatavencimento(pcb, pedido.getInstante());
        }

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.findById(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        emailService.sendOrderConfirmationHtmlEmail(pedido);
        return pedido;
    }
}
