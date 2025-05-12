package com.producto.producto.model;

import com.producto.producto.model.dto.ProductoDto;
import com.producto.producto.model.entity.ProductoEntity;

public class ProductoMapper {

    public static ProductoDto toDto(ProductoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new ProductoDto(
                entity.getIdProducto(),
                entity.getNombre(),
                entity.getPrecio(),
                entity.getStock(),
                entity.getTipo(),
                entity.getMaterial()
        );
    }

    public static ProductoEntity toEntity(ProductoDto dto) {
        if (dto == null) {
            return null;
        }

        ProductoEntity entity = new ProductoEntity();
        entity.setIdProducto(dto.getIdProducto());
        entity.setNombre(dto.getNombre());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        entity.setTipo(dto.getTipo());
        entity.setMaterial(dto.getMaterial());
        return entity;
    }
}
