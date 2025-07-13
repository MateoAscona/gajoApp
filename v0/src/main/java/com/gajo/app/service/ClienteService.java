package com.gajo.app.service;

import com.gajo.app.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(Integer id);
    Cliente save(Cliente cliente);
    void deleteById(Integer id);
    List<Cliente> findByFilters(Integer localidadId, Integer vendedorId);
}
