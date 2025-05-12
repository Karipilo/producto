package com.producto.producto.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {

    private Long idProducto;
    @NotNull
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotNull
    @Positive(message = "El precio debe ser un valor positivo")
    private Double precio;

    @NotNull
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Size(max = 50, message = "El tipo no puede tener más de 50 caracteres")
    private String tipo;

    @Size(max = 50, message = "El material no puede tener más de 50 caracteres")
    private String material;
}
