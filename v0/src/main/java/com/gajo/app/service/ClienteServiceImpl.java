package com.gajo.app.service;

import com.gajo.app.model.Cliente;
import com.gajo.app.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cliente> findByFilters(Integer localidadId, Integer vendedorId) {
        if (localidadId != null && vendedorId != null) {
            return repository.findByLocalidadIdAndVendedorId(localidadId, vendedorId);
        } else if (localidadId != null) {
            return repository.findByLocalidadId(localidadId);
        } else if (vendedorId != null) {
            return repository.findByVendedorId(vendedorId);
        } else {
            return repository.findAll();
        }
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
