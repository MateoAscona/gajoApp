package com.gajo.app.service;

import com.gajo.app.model.FacturaCompra;
import com.gajo.app.model.Producto;
import com.gajo.app.repository.FacturaCompraRepository;
import com.gajo.app.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaCompraService {

    private final FacturaCompraRepository facturaRepo;
    private final ProductoRepository productoRepo;

    public FacturaCompraService(FacturaCompraRepository facturaRepo, ProductoRepository productoRepo) {
        this.facturaRepo = facturaRepo;
        this.productoRepo = productoRepo;
    }

    public FacturaCompra crearFactura(FacturaCompra factura) {
        // Actualiza stock por cada detalle
        factura.getDetalles().forEach(detalle -> {
            Producto producto = detalle.getProducto();
            producto.setStockActual(producto.getStockActual() + detalle.getCantidad());
            productoRepo.save(producto);
            detalle.setFactura(factura);
        });

        return facturaRepo.save(factura);
    }

    public List<FacturaCompra> obtenerTodas() {
        return facturaRepo.findAll();
    }

    public FacturaCompra obtenerPorId(Integer id) {
        return facturaRepo.findById(id).orElse(null);
    }
}
