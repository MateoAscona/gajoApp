package com.gajo.app.service;

import com.gajo.app.model.Producto;
import com.gajo.app.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Producto> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
