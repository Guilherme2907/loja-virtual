/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.ItemPedido;
import com.guilherme.lojavirtual.domain.PagamentoComBoleto;
import com.guilherme.lojavirtual.domain.Pedido;
import com.guilherme.lojavirtual.domain.enums.EstadoPagamento;
import com.guilherme.lojavirtual.repositories.ItemPedidoRepository;
import com.guilherme.lojavirtual.repositories.PagamentoRepository;
import com.guilherme.lojavirtual.repositories.PedidoRepository;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundErrorCustom("Objeto não encontrado para o Id: "
                + id + ",Tipo: " + Pedido.class.getSimpleName()));
    }

    @Transactional
    public Pedido save(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pcb = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.setDatavencimento(pcb, pedido.getInstante());
        }

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.findById(ip.getProduto().getId()).getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        return pedido;
    }
}
