// src/main/java/com/gajo/app/controller/VendedorController.java
package com.gajo.app.controller;

import com.gajo.app.model.Vendedor;
import com.gajo.app.service.VendedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
@CrossOrigin(origins = "*")
public class VendedorController {
    private final VendedorService service;
    public VendedorController(VendedorService service) { this.service = service; }

    @GetMapping
    public List<Vendedor> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Vendedor> crear(@RequestBody Vendedor v) {
        return ResponseEntity.ok(service.crear(v));
    }
}
