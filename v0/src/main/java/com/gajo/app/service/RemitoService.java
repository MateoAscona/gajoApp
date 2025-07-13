// src/main/java/com/gajo/app/service/RemitoService.java
package com.gajo.app.service;

import com.gajo.app.dto.RemitoDto;
import com.gajo.app.model.*;
import com.gajo.app.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RemitoService {

    private final RemitoRepository remitoRepo;
    private final ProductoRepository productoRepo;
    private final DeudaRepository deudaRepo;
    private final ClienteRepository clienteRepo;

    public RemitoService(RemitoRepository remitoRepo,
                         ProductoRepository productoRepo,
                         DeudaRepository deudaRepo,
                         ClienteRepository clienteRepo) {
        this.remitoRepo = remitoRepo;
        this.productoRepo = productoRepo;
        this.deudaRepo = deudaRepo;
        this.clienteRepo = clienteRepo;
    }

    @Transactional
    public Remito crearRemito(RemitoDto req) {
        // 1. Validar cliente
        Cliente cli = clienteRepo.findById(req.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no existe: " + req.getClienteId()));

        // 2. Preparar remito
        Remito r = new Remito();
        r.setCliente(cli);
        r.setFecha(LocalDateTime.now());

        List<DetalleRemito> detalles = new ArrayList<>();
        double totalCosto = 0.0;
        double totalPrecio = 0.0;

        // 3. Procesar cada detalle
        for (var drq : req.getDetalles()) {
            Producto p = productoRepo.findById(drq.getProductoId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no existe: " + drq.getProductoId()));

            if (p.getStockActual() < drq.getCantidad()) {
                throw new IllegalArgumentException(
                        "Stock insuficiente para producto " + p.getId() +
                                " (disponible: " + p.getStockActual() + ")");
            }

            // 3.1 descontar stock
            p.setStockActual(p.getStockActual() - drq.getCantidad());
            productoRepo.save(p);

            // 3.2 crear detalle
            DetalleRemito dr = new DetalleRemito();
            dr.setRemito(r);
            dr.setProducto(p);
            dr.setCantidad(drq.getCantidad());
            dr.setPrecioCosto(p.getCostoCompra());
            dr.setPrecioVenta(p.getPrecioVenta());
            detalles.add(dr);

            totalCosto += p.getCostoCompra() * drq.getCantidad();
            totalPrecio += p.getPrecioVenta() * drq.getCantidad();
        }

        r.setDetalles(detalles);
        r.setTotalCosto(totalCosto);
        r.setTotalPrecio(totalPrecio);

        // 4. Guardar remito y detalles
        Remito guardado = remitoRepo.save(r);

        // 5. Crear deuda asociada
        Deuda deuda = new Deuda();
        deuda.setCliente(cli);
        deuda.setRemito(guardado);
        deuda.setMontoOriginal(totalPrecio);
        deuda.setMontoPagado(0.0);
        deudaRepo.save(deuda);

        return guardado;
    }

    public List<Remito> listarTodos() {
        return remitoRepo.findAll();
    }

    public Remito buscarPorId(Integer id) {
        return remitoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Remito no existe: " + id));
    }
}
