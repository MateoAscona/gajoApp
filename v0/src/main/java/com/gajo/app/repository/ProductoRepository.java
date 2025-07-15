// src/main/java/com/gajo/app/repository/ProductoRepository.java
package com.gajo.app.repository;

import com.gajo.app.model.Producto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends
        JpaRepository<Producto, Integer>,
        JpaSpecificationExecutor<Producto> {

    // Para autocompletar nombre
    List<Producto> findTop10ByNombreContainingIgnoreCase(String term);

    // Para detectar duplicados al guardar
    boolean existsByNombreIgnoreCaseAndProveedor_Id(String nombre, Integer proveedorId);
    Producto findByNombreIgnoreCaseAndProveedor_Id(String nombre, Integer proveedorId);
}
