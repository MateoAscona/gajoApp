package com.gajo.app.controller;

import com.gajo.app.dto.ReciboDto;
import com.gajo.app.model.Recibo;
import com.gajo.app.service.ReciboService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recibos")
@CrossOrigin(origins = "*")
public class ReciboController {

    private final ReciboService service;

    public ReciboController(ReciboService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Recibo> crear(@RequestBody ReciboDto req) {
        Recibo creado = service.crearRecibo(req);
        return ResponseEntity.status(201).body(creado);
    }
}
