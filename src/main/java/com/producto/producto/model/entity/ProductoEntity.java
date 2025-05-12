package com.producto.producto.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Productos")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name = "nombre_producto", nullable = false, length = 100)
    @NotNull
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @Column(name = "precio_producto", nullable = false)
    @NotNull
    @Positive(message = "El precio debe ser positivo")
    private Double precio;

    @Column(name = "stock_producto", nullable = false)
    @NotNull
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Column(name = "tipo_producto", length = 50)
    @Size(max = 50, message = "El tipo no puede tener más de 50 caracteres")
    private String tipo;

    @Column(name = "material_producto", length = 50)
    @Size(max = 50, message = "El material no puede tener más de 50 caracteres")
    private String material;
}
