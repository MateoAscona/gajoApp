// src/main/java/com/gajo/app/dto/RemitoRequest.java
package com.gajo.app.dto;

import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class RemitoDto {
    private Integer clienteId;
    private List<DetalleRemitoDto> detalles;
}

