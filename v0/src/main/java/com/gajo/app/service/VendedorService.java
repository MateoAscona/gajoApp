// src/main/java/com/gajo/app/service/VendedorService.java
package com.gajo.app.service;

import com.gajo.app.model.Vendedor;
import java.util.List;
import java.util.Optional;

public interface VendedorService {
    List<Vendedor> listarTodos();
    Vendedor crear(Vendedor v);
    Optional<Vendedor> findById(Integer id);
    // editar, eliminar si se desea
}
