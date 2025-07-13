package com.gajo.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "remitos", schema = "Negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remito {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private LocalDateTime fecha;
    private Double totalCosto;
    private Double totalPrecio;

    // ↙ aquí marcamos la referencia "gestionada"
    @OneToMany(mappedBy = "remito", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DetalleRemito> detalles;
}
