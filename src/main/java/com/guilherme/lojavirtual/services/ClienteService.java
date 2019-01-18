/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services;

import com.guilherme.lojavirtual.domain.Cidade;
import com.guilherme.lojavirtual.domain.Cliente;
import com.guilherme.lojavirtual.domain.Endereco;
import com.guilherme.lojavirtual.domain.enums.TipoCliente;
import com.guilherme.lojavirtual.dto.ClienteDTO;
import com.guilherme.lojavirtual.dto.ClienteNewDTO;
import com.guilherme.lojavirtual.repositories.ClienteRepository;
import com.guilherme.lojavirtual.repositories.EnderecoRepository;
import com.guilherme.lojavirtual.services.exception.DataIntegrityViolationExceptionCustom;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Guilherme
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundErrorCustom("Objeto não encontrado para o Id: "
                + id + ",Tipo: " + Cliente.class.getSimpleName()));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findAllPage(int page, int elementsPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, elementsPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        Cliente newCliente = findById(cliente.getId());
        newCliente = updateData(newCliente, cliente);
        return clienteRepository.save(newCliente);
    }

    public void deleteById(Integer id) {
        findById(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionCustom("Não é possível deletar clientes que possuem pedidos cadastrados");
        }
    }

    public Cliente toCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
        return cliente;
    }

    public Cliente toCliente(ClienteNewDTO cli) {
        Cliente cliente = new Cliente(null, cli.getNome(), cli.getEmail(), cli.getCpfOuCnpj(),
                TipoCliente.toEnum(cli.getTipoCliente()), encoder.encode(cli.getSenha()));
        cliente.getTelefones().addAll(Arrays.asList(cli.getTelefone1(), cli.getTelefone2()));
        Cidade cidade = new Cidade(cli.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, cli.getLogradouro(), cli.getNumero(), cli.getComplemento(),
                cli.getBairro(), cli.getCep(), cidade, cliente);
        cliente.getEnderecos().add(endereco);
        return cliente;
    }

    public Cliente updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
        return newCliente;
    }
}
