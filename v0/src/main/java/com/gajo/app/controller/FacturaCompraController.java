package com.gajo.app.controller;

import com.gajo.app.model.FacturaCompra;
import com.gajo.app.service.FacturaCompraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas-compra")
@CrossOrigin(origins = "*")
public class FacturaCompraController {

    private final FacturaCompraService service;

    public FacturaCompraController(FacturaCompraService service) {
        this.service = service;
    }

    @PostMapping
    public FacturaCompra crear(@RequestBody FacturaCompra factura) {
        return service.crearFactura(factura);
    }

    @GetMapping
    public List<FacturaCompra> obtenerTodas() {
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public FacturaCompra obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }
}
