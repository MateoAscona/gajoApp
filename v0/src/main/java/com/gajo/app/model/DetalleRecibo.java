package com.gajo.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_recibo", schema = "Negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleRecibo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne @JoinColumn(name = "recibo_id", nullable = false)
    @JsonBackReference(value="recibo-detalle")
    private Recibo recibo;

    @ManyToOne @JoinColumn(name = "remito_id", nullable = false)
    private Remito remito;

    private Double montoAplicado;
}
