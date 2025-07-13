package com.gajo.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="localidades", schema="negocio")
@Data
public class Localidad {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
}
