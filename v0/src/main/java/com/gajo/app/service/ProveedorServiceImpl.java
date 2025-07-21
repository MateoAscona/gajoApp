// src/main/java/com/gajo/app/service/impl/ProveedorServiceImpl.java
package com.gajo.app.service;

import com.gajo.app.model.Proveedor;
import com.gajo.app.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public Proveedor crearProveedor(Proveedor proveedor) {
        if (proveedorRepository.existsByCuil(proveedor.getCuil())) {
            throw new RuntimeException("YA EXISTE un proveedor con CUIL: " + proveedor.getCuil());
        }
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor actualizarProveedor(Integer id, Proveedor proveedor) {
        Proveedor existente = obtenerProveedorPorId(id);
        existente.setNombre(proveedor.getNombre());
        existente.setDireccion(proveedor.getDireccion());
        existente.setTelefono(proveedor.getTelefono());
        existente.setEmail(proveedor.getEmail());
        return proveedorRepository.save(existente);
    }

    @Override
    public void eliminarProveedor(Integer id) {
        // Si quisieras lanzar una excepción si no existe:
        // obtenerProveedorPorId(id);
        proveedorRepository.deleteById(id);
    }

    @Override
    public Proveedor obtenerProveedorPorId(Integer id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado: " + id));
    }

    @Override
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }


}
