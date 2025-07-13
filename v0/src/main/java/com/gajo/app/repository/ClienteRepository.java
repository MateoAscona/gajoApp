package com.gajo.app.repository;

import com.gajo.app.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByLocalidadIdAndVendedorId(
            Integer localidadId, Integer vendedorId
    );
    List<Cliente> findByLocalidadId(
            Integer localidadId
    );
    List<Cliente> findByVendedorId(
            Integer vendedorId
    );

}
