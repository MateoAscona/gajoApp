package com.gajo.app.controller;

import com.gajo.app.model.Producto;
import com.gajo.app.model.Proveedor;
import com.gajo.app.service.ProductoService;
import com.gajo.app.service.ProveedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;
    private final ProveedorService proveedorService;

    public ProductoController(ProductoService productoService,
                              ProveedorService proveedorService) {
        this.productoService = productoService;
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public List<Producto> getAll() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Producto> getById(@PathVariable Integer id) {
        return productoService.findById(id);
    }

    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        // Asocia el proveedor si viene en el JSON
        if (producto.getProveedor() != null && producto.getProveedor().getId() != null) {
            Proveedor prov = proveedorService.obtenerProveedorPorId(producto.getProveedor().getId());
            producto.setProveedor(prov);
        }
        return productoService.save(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Integer id,
                           @RequestBody Producto producto) {
        producto.setId(id);
        // Asocia o actualiza el proveedor si viene en el JSON
        if (producto.getProveedor() != null && producto.getProveedor().getId() != null) {
            Proveedor prov = proveedorService.obtenerProveedorPorId(producto.getProveedor().getId());
            producto.setProveedor(prov);
        }
        return productoService.save(producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productoService.deleteById(id);
    }

    /**
     * Filtrado dinámico:
     * /api/productos/search?pesoMin=…&pesoMax=…&precioMin=…&precioMax=…&proveedorId=…
     */
    @GetMapping("/search")
    public List<Producto> search(
            @RequestParam Optional<Double> pesoMin,
            @RequestParam Optional<Double> pesoMax,
            @RequestParam Optional<Double> precioMin,
            @RequestParam Optional<Double> precioMax,
            @RequestParam Optional<Integer> proveedorId
    ) {
        return productoService.buscar(pesoMin, pesoMax, precioMin, precioMax, proveedorId);
    }

    /**
     * Autocomplete de nombre:
     * /api/productos/autocomplete?term=par
     */

    @GetMapping("/autocomplete")
    public List<Producto> autocomplete(@RequestParam String term) {
        return productoService.autocomplete(term);
    }
}
