package com.gajo.app.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Clientes", schema = "Negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreLocal;

    private String nombreContacto;

    private String telefono;

    private String cuitCuil;

    private String direccion;

    private Integer localidadId;

    private Double deudaTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="vendedor_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Vendedor vendedor;
}
