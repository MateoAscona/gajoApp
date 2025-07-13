// src/main/java/com/gajo/app/controller/RemitoController.java
package com.gajo.app.controller;

import com.gajo.app.dto.RemitoDto;
import com.gajo.app.model.Remito;
import com.gajo.app.service.RemitoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remitos")
@CrossOrigin(origins = "*")
public class RemitoController {

    private final RemitoService service;

    public RemitoController(RemitoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Remito> crear(@RequestBody RemitoDto req) {
        Remito creado = service.crearRemito(req);
        return ResponseEntity.status(201).body(creado);
    }

    @GetMapping
    public List<Remito> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Remito> obtener(@PathVariable Integer id) {
        Remito r = service.buscarPorId(id);
        return ResponseEntity.ok(r);
    }
}
