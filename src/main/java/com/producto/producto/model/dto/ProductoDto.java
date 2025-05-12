package com.producto.producto.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

    private Long idProducto;
    private String nombre;
    private Double precio;
    private Integer stock;
    private String tipo;
    private String material;
}
