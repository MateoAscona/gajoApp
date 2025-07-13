package com.gajo.app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deudas", schema = "Negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "remito_id", nullable = false)
    private Remito remito;

    private Double montoOriginal;
    private Double montoPagado;

    @Column(insertable = false, updatable = false)
    private Double saldo;  // columna calculada en la BD
}
