package com.producto.producto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producto.producto.model.Producto;
import com.producto.producto.model.dto.ProductoDto;
import com.producto.producto.model.entity.ProductoEntity;
import com.producto.producto.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public String crearProducto(Producto prod) {
        if (productoRepository.existsByNombre(prod.getNombre())) {
            return "El producto ya existe";
        }
        ProductoEntity productoNuevo = mapToEntity(prod);
        productoRepository.save(productoNuevo);
        return "Producto creado exitosamente";
    }

    public Producto obtenerProducto(String nombreProducto) {
        ProductoEntity producto = productoRepository.findByNombre(nombreProducto).orElse(null);
        if (producto != null) {
            return mapToModel(producto);
        }
        return null;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll().stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public String actualizarProducto(String nombre, Producto prod) {
        ProductoEntity existente = productoRepository.findByNombre(nombre).orElse(null);
        if (existente != null) {
            existente.setPrecio(prod.getPrecio());
            existente.setStock(prod.getStock());
            existente.setTipo(prod.getTipo());
            existente.setMaterial(prod.getMaterial());
            productoRepository.save(existente);
            return "Producto actualizado correctamente";
        }
        return "Producto no encontrado";
    }

    @Transactional
    public String eliminarProducto(String nombreProducto) {
        ProductoEntity existente = productoRepository.findByNombre(nombreProducto).orElse(null);
        if (existente != null) {
            productoRepository.delete(existente);
            return "Producto eliminado correctamente";
        }
        return "Producto no encontrado";
    }

    // Métodos auxiliares para mapear entre Producto y ProductoEntity
    private ProductoEntity mapToEntity(Producto prod) {
        ProductoEntity entity = new ProductoEntity();
        entity.setIdProducto(prod.getIdProducto());
        entity.setNombre(prod.getNombre());
        entity.setPrecio(prod.getPrecio());
        entity.setStock(prod.getStock());
        entity.setTipo(prod.getTipo());
        entity.setMaterial(prod.getMaterial());
        return entity;
    }

    private Producto mapToModel(ProductoEntity entity) {
        return new Producto(
                entity.getIdProducto(),
                entity.getNombre(),
                entity.getPrecio(),
                entity.getStock(),
                entity.getTipo(),
                entity.getMaterial()
        );
    }

    public ProductoDto obtenerProductoPorId(Long id) {
        try {
            ProductoEntity producto = productoRepository.findById(id).orElse(null);
            if (producto == null) {
                return null;
            }

            ProductoDto nuevoProducto = new ProductoDto();
            nuevoProducto.setNombre(producto.getNombre());
            nuevoProducto.setPrecio(producto.getPrecio());
            nuevoProducto.setTipo(producto.getTipo());

            return nuevoProducto;
        } catch (Exception e) {
            return null;
        }
    }
}
