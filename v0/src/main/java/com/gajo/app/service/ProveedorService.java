// src/main/java/com/gajo/app/service/ProveedorService.java
package com.gajo.app.service;

import com.gajo.app.model.Proveedor;
import java.util.List;

public interface ProveedorService {
    Proveedor crearProveedor(Proveedor proveedor);
    Proveedor actualizarProveedor(Integer id, Proveedor proveedor);
    void eliminarProveedor(Integer id);
    Proveedor obtenerProveedorPorId(Integer id);
    List<Proveedor> listarProveedores();
}
