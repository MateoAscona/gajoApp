package com.gajo.app.service;

import com.gajo.app.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Integer id);
    Producto save(Producto producto);
    void deleteById(Integer id);

    List<Producto> buscar(
            Optional<Double> pesoMin,
            Optional<Double> pesoMax,
            Optional<Double> precioMin,
            Optional<Double> precioMax,
            Optional<Integer> proveedorId);

    List<Producto> autocomplete(String term);
}
