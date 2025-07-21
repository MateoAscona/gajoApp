// PrecioVentaController.java
package com.gajo.app.controller;

import com.gajo.app.model.PrecioVenta;
import com.gajo.app.repository.PrecioVentaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/precios")
@CrossOrigin(origins = "*")
public class PrecioVentaController {

    private final PrecioVentaRepository repo;

    public PrecioVentaController(PrecioVentaRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/producto/{productoId}")
    public List<PrecioVenta> preciosPorProducto(@PathVariable Integer productoId) {
        return repo.findAllByProductoId(productoId);
    }

    @GetMapping("/producto/{productoId}/vendedor/{vendedorId}")
    public ResponseEntity<PrecioVenta> precioPorProductoYVendedor(@PathVariable Integer productoId,
                                                                  @PathVariable Integer vendedorId) {
        return repo.findByProductoIdAndVendedorId(productoId, vendedorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PrecioVenta> crear(@RequestBody PrecioVenta precio) {
        if (repo.existsByProductoIdAndVendedorId(
                precio.getProducto().getId(),
                precio.getVendedor().getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.save(precio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrecioVenta> actualizar(@PathVariable Integer id,
                                                  @RequestBody PrecioVenta body) {
        return repo.findById(id).map(pv -> {
            pv.setPrecio(body.getPrecio());
            return ResponseEntity.ok(repo.save(pv));
        }).orElse(ResponseEntity.notFound().build());
    }
}
