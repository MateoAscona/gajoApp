package com.gajo.app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Productos", schema = "Negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String categoria;

    private Double peso;

    private Double costoCompra;

    private Double precioVenta;

    private Integer stockActual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;
}

