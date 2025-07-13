package com.gajo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DetalleReciboDto {
    private Integer remitoId;
    private Double montoAplicado;
}
