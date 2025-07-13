// src/main/java/com/gajo/app/repository/VendedorRepository.java
package com.gajo.app.repository;

import com.gajo.app.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
}

