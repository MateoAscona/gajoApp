package com.gajo.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recibos", schema = "Negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recibo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private LocalDateTime fecha;
    private Double montoTotal;

    @OneToMany(mappedBy = "recibo", cascade = CascadeType.ALL)
    @JsonManagedReference(value="recibo-detalle")
    private List<DetalleRecibo> detalles;
}
