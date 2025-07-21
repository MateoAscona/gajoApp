// src/main/java/com/gajo/app/service/impl/VendedorServiceImpl.java
package com.gajo.app.service;

import com.gajo.app.model.Vendedor;
import com.gajo.app.repository.VendedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorServiceImpl implements VendedorService {
    private final VendedorRepository repo;
    public VendedorServiceImpl(VendedorRepository repo) { this.repo = repo; }

    @Override
    public List<Vendedor> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Vendedor crear(Vendedor v) {
        return repo.save(v);
    }

    @Override
    public Optional<Vendedor> findById(Integer id){
        return repo.findById(id);
    }
}
