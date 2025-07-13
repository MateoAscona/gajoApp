package com.gajo.app.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "FacturasCompra", schema = "Negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime fecha;

    private String observaciones;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<DetalleFacturaCompra> detalles;
}
