// src/main/java/com/gajo/app/repository/ProveedorRepository.java
package com.gajo.app.repository;

import com.gajo.app.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    boolean existsByCuil(String cuil);
}
