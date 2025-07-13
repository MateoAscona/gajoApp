package com.gajo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DetalleRemitoDto {
    private Integer productoId;
    private Integer cantidad;
}
