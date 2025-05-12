package com.producto.producto.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producto.producto.model.Producto;
import com.producto.producto.model.dto.ProductoDto;
import com.producto.producto.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<String> crearProducto(@Valid @RequestBody Producto producto) {
        productoService.crearProducto(producto);
        return ResponseEntity.ok("Producto creado exitosamente");
    }

    @GetMapping("/{nombreProducto}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable String nombreProducto) {
        Producto producto = productoService.obtenerProducto(nombreProducto);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }

    /*
    ! Método que no estaba funcionando
    @PutMapping("/{nombreProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable String nombreProducto, @Valid @RequestBody Producto producto) {
        try {
            Producto productoExistente = productoService.obtenerProducto(nombreProducto);
            if (productoExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "Producto no encontrado"));
            }

            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setStock(producto.getStock());
            productoExistente.setTipo(producto.getTipo());
            productoExistente.setMaterial(producto.getMaterial());

            productoService.crearProducto(productoExistente);  // Actualiza el producto

            return ResponseEntity.ok(Map.of("mensaje", "Producto actualizado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensaje", "Error al actualizar el producto"));
        }
    } */
    @PutMapping("/{nombreProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable String nombreProducto, @Valid @RequestBody Producto producto) {
        try {
            String resultado = productoService.actualizarProducto(nombreProducto, producto);

            if (resultado.equals("Producto no encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Producto no encontrado"));

            }
            return ResponseEntity.ok(Map.of("mensaje", "Producto actualizado exitosamente"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensaje", "Error al actualizar el producto" + e.getMessage()));

        }
    }

    @DeleteMapping("/{nombreProducto}")
    public ResponseEntity<String> eliminarProducto(@PathVariable String nombreProducto) {
        String mensaje = productoService.eliminarProducto(nombreProducto);
        if ("Producto eliminado correctamente".equals(mensaje)) {
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/obtenerProducto/{IdProducto}")
    public ResponseEntity<ProductoDto> obtenerProductoDto(@PathVariable Long IdProducto) {
        ProductoDto productoDto = productoService.obtenerProductoPorId(IdProducto);
        if (productoDto != null) {
            return ResponseEntity.ok(productoDto);
        }
        return ResponseEntity.notFound().build();
    }
}
