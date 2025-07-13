package com.gajo.app.service;

import com.gajo.app.dto.ReciboDto;
import com.gajo.app.model.*;
import com.gajo.app.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class ReciboService {

    private final ReciboRepository reciboRepo;
    private final RemitoRepository remitoRepo;
    private final DeudaRepository deudaRepo;
    private final ClienteRepository clienteRepo;

    public ReciboService(ReciboRepository reciboRepo,
                         RemitoRepository remitoRepo,
                         DeudaRepository deudaRepo,
                         ClienteRepository clienteRepo) {
        this.reciboRepo = reciboRepo;
        this.remitoRepo = remitoRepo;
        this.deudaRepo = deudaRepo;
        this.clienteRepo = clienteRepo;
    }

    @Transactional
    public Recibo crearRecibo(ReciboDto req) {
        // 1. Validar cliente
        Cliente cli = clienteRepo.findById(req.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no existe"));

        // 2. Crear recibo base
        Recibo r = new Recibo();
        r.setCliente(cli);
        r.setFecha(LocalDateTime.now());
        r.setMontoTotal(req.getMontoTotal());

        // 3. Procesar cada detalle
        for (var drq : req.getDetalles()) {
            Remito rem = remitoRepo.findById(drq.getRemitoId())
                    .orElseThrow(() -> new IllegalArgumentException("Remito no existe: " + drq.getRemitoId()));
            if (!rem.getCliente().getId().equals(cli.getId())) {
                throw new IllegalArgumentException("Remito " + rem.getId() + " no pertenece a cliente " + cli.getId());
            }
            // Obtener o crear deuda
            Deuda deuda = deudaRepo.findByRemito(rem)
                    .orElseGet(() -> {
                        Deuda d = new Deuda();
                        d.setCliente(cli);
                        d.setRemito(rem);
                        d.setMontoOriginal(rem.getTotalPrecio());
                        d.setMontoPagado(0.0);
                        return deudaRepo.save(d);
                    });
            double saldo = deuda.getSaldo();
            if (drq.getMontoAplicado() > saldo) {
                throw new IllegalArgumentException(
                        "Monto aplicado (" + drq.getMontoAplicado() +
                                ") supera saldo de deuda (" + saldo + ") del remito " + rem.getId());
            }
            // Actualizar deuda
            deuda.setMontoPagado(deuda.getMontoPagado() + drq.getMontoAplicado());
            deudaRepo.save(deuda);

            // Asociar detalle al recibo
            DetalleRecibo dr = new DetalleRecibo();
            dr.setRecibo(r);
            dr.setRemito(rem);
            dr.setMontoAplicado(drq.getMontoAplicado());
            r.getDetalles().add(dr);
        }

        // 4. Guardar recibo con todos los detalles
        return reciboRepo.save(r);
    }
}
