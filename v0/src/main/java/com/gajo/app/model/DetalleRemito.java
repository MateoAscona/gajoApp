package com.gajo.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_remito", schema = "Negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleRemito {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ↙ esta es la “vuelta” de la referencia
    @ManyToOne
    @JoinColumn(name = "remito_id", nullable = false)
    @JsonBackReference
    private Remito remito;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    private Integer cantidad;
    private Double precioVenta;
    private Double precioCosto;
}
