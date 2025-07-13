// src/main/java/com/gajo/app/model/Vendedor.java
package com.gajo.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vendedores", schema = "negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;

}

