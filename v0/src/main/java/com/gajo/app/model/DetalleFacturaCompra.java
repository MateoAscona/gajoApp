package com.gajo.app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DetalleFacturaCompra", schema = "Negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFacturaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FacturaId")
    private FacturaCompra factura;

    @ManyToOne
    @JoinColumn(name = "ProductoId")
    private Producto producto;

    private Integer cantidad;

    private Double precioUnitario;
}
