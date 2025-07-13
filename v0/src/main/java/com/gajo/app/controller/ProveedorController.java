// src/main/java/com/gajo/app/controller/ProveedorController.java
package com.gajo.app.controller;

import com.gajo.app.model.Proveedor;
import com.gajo.app.service.ProveedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    private final ProveedorService service;

    public ProveedorController(ProveedorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Proveedor> getAll() {
        return service.listarProveedores();
    }

    @GetMapping("/{id}")
    public Proveedor getById(@PathVariable Integer id) {
        return service.obtenerProveedorPorId(id);
    }

    @PostMapping
    public Proveedor create(@RequestBody Proveedor proveedor) {
        return service.crearProveedor(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor update(@PathVariable Integer id,
                            @RequestBody Proveedor proveedor) {
        return service.actualizarProveedor(id, proveedor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.eliminarProveedor(id);
    }
}
