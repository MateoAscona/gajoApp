package com.gajo.app.dto;

import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class ReciboDto {
    private Integer clienteId;
    private Double montoTotal;
    private List<DetalleReciboDto> detalles;
}

