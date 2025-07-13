// 3. Nueva entidad JPA: Proveedor.java
package com.gajo.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Proveedores", schema = "Negocio",
        uniqueConstraints = @UniqueConstraint(columnNames = "cuil"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 20, unique = true)
    private String cuil;

    @Column(length = 255)
    private String direccion;

    @Column(length = 50)
    private String telefono;

    @Column(length = 100)
    private String email;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Producto> productos;
}

