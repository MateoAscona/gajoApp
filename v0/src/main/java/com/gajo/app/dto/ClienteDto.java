// src/main/java/com/gajo/app/dto/ClienteDto.java
package com.gajo.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private String nombreLocal;
    private String nombreContacto;
    private String telefono;
    private String cuitCuil;
    private String direccion;
    private Integer localidadId;
    private Integer vendedorId;
}
