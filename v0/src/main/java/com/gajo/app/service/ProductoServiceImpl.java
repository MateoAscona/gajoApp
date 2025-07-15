// src/main/java/com/gajo/app/service/impl/ProductoServiceImpl.java
package com.gajo.app.service;

import com.gajo.app.model.Producto;
import com.gajo.app.repository.ProductoRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Producto save(Producto producto) {
            // Si es creación y ya existe (mismo nombre + mismo proveedor), sumo stockActual
            if (producto.getId() == null
                    && producto.getProveedor() != null
                    && repository.existsByNombreIgnoreCaseAndProveedor_Id(
                    producto.getNombre(),
                    producto.getProveedor().getId())) {

                Producto existente = repository.findByNombreIgnoreCaseAndProveedor_Id(
                        producto.getNombre(),
                        producto.getProveedor().getId());
                existente.setStockActual(
                        existente.getStockActual() + producto.getStockActual()
                );
                return repository.save(existente);
            }
            // Creación o actualización normal
            return repository.save(producto);
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
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Producto> buscar(
            Optional<Double> pesoMin,
            Optional<Double> pesoMax,
            Optional<Double> precioMin,
            Optional<Double> precioMax,
            Optional<Integer> proveedorId) {

        Specification<Producto> spec = (root, query, cb) -> {
            List<Predicate> preds = new ArrayList<>();
            pesoMin.ifPresent(v -> preds.add(cb.ge(root.get("peso"), v)));
            pesoMax.ifPresent(v -> preds.add(cb.le(root.get("peso"), v)));
            precioMin.ifPresent(v -> preds.add(cb.ge(root.get("precio"), v)));
            precioMax.ifPresent(v -> preds.add(cb.le(root.get("precio"), v)));
            proveedorId.ifPresent(id ->
                    preds.add(cb.equal(root.get("proveedor").get("id"), id))
            );
            return cb.and(preds.toArray(new Predicate[0]));
        };

        return repository.findAll(spec);
    }

    @Override
    public List<Producto> autocomplete(String term) {
        return repository.findTop10ByNombreContainingIgnoreCase(term);
    }
}
