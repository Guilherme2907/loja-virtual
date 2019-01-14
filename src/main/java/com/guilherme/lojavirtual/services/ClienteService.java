/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Cliente;
import com.guilherme.lojavirtual.dto.ClienteDTO;
import com.guilherme.lojavirtual.repositories.ClienteRepository;
import com.guilherme.lojavirtual.services.exception.DataIntegrityViolationExceptionCustom;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guilherme
 */
@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public Cliente findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundErrorCustom("Objeto não encontrado para o Id: "
                + id + ",Tipo: " + Cliente.class.getSimpleName()));
    }
    
     public Cliente save(Cliente cliente) {
        cliente.setId(null);
        return repository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        Cliente newCliente = findById(cliente.getId());
        newCliente = updateData(newCliente,cliente);
        return repository.save(newCliente);
    }

    public void deleteById(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionCustom("Não é possível deletar clientes que possuem produtos pedidos cadastrados");
        }
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Page<Cliente> findAllPage(int page, int elementsPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, elementsPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente toCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getNome(),clienteDTO.getEmail(),null,null);
        cliente.setId(clienteDTO.getId());
        return cliente;
    }
    
    public Cliente updateData(Cliente newCliente,Cliente cliente){
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
        return newCliente;
    }
}
