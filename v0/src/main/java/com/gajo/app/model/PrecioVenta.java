package com.gajo.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "precios_venta", schema = "Negocio",
        uniqueConstraints = @UniqueConstraint(columnNames = {"producto_id", "vendedor_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PrecioVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @Column(nullable = false)
    private BigDecimal precio;
}
