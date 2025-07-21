package com.gajo.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PrecioVenta> preciosVenta;

    private Integer stockActual;

    @JsonIgnoreProperties("productos")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @JsonProperty("proveedor")
    public String getProveedorNombre() {
        return proveedor != null ? proveedor.getNombre() : null;
    }

    @JsonProperty("proveedorId")
    public Integer getProveedorId() {
        return proveedor != null ? proveedor.getId() : null;
    }

}

