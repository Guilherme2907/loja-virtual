/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.resources;

import com.guilherme.lojavirtual.domain.Cliente;
import com.guilherme.lojavirtual.dto.ClienteDTO;
import com.guilherme.lojavirtual.dto.ClienteNewDTO;
import com.guilherme.lojavirtual.services.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteResources {

    @Autowired
    ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> clientes = service.findAll();
        List<ClienteDTO> clientesDto = clientes.stream().map(cat -> new ClienteDTO(cat)).collect(Collectors.toList());
        return ResponseEntity.ok(clientesDto);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> findAllPage(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "elementsPerPage", defaultValue = "24") int elementsPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

        Page<Cliente> clientePage = service.findAllPage(page, elementsPerPage, direction, orderBy);
        Page<ClienteDTO> clienteDtoPage = clientePage.map(cat -> new ClienteDTO(cat));
        return ResponseEntity.ok(clienteDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable("id") Integer id) {
        Cliente Cliente = service.findById(id);
        return ResponseEntity.ok(Cliente);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClienteNewDTO clienteNewDto) {
        Cliente Cliente = service.toCliente(clienteNewDto);
        Cliente = service.save(Cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody ClienteDTO clienteDto) {
        Cliente cliente = service.toCliente(clienteDto);
        cliente.setId(id);
        service.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
