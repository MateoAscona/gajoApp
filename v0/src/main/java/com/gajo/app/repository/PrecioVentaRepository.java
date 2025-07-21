package com.gajo.app.repository;

import com.gajo.app.model.PrecioVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PrecioVentaRepository extends JpaRepository<PrecioVenta, Integer> {

    Optional<PrecioVenta> findByProductoIdAndVendedorId(Integer productoId, Integer vendedorId);

    List<PrecioVenta> findAllByProductoId(Integer productoId);

    boolean existsByProductoIdAndVendedorId(Integer productoId, Integer vendedorId);
}
