package com.producto.producto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.producto.producto.model.Producto;
import com.producto.producto.model.entity.ProductoEntity;
import com.producto.producto.repository.ProductoRepository;
import com.producto.producto.service.ProductoService;

@SpringBootTest
public class TestProducto {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;
    private ProductoEntity productoEntity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        producto = new Producto();
        producto.setIdProducto(1L);
        producto.setNombre("Laptop");
        producto.setPrecio(1000000.0);
        producto.setStock(10);
        producto.setTipo("Alta Gama");
        producto.setMaterial("acero");

        productoEntity = new ProductoEntity();
        productoEntity.setIdProducto(1L);
        productoEntity.setNombre("Laptop");
        productoEntity.setPrecio(1000000.0);
        productoEntity.setStock(10);
        productoEntity.setTipo("Alta Gama");
        productoEntity.setMaterial("acero");
    }

    @Test
    public void testCrearProducto_nuevo() {
        when(productoRepository.existsByNombre("Laptop")).thenReturn(false);
        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(productoEntity);

        String resultado = productoService.crearProducto(producto);

        assertEquals("Producto creado exitosamente", resultado);
        verify(productoRepository, times(1)).save(any(ProductoEntity.class));
    }

    @Test
    public void testCrearProducto_yaExiste() {
        when(productoRepository.existsByNombre("Laptop")).thenReturn(true);

        String resultado = productoService.crearProducto(producto);

        assertEquals("El producto ya existe", resultado); // <- mensaje corregido
        verify(productoRepository, never()).save(any(ProductoEntity.class));
    }

    @Test
    public void testActualizarProducto_existente() {
        when(productoRepository.findByNombre("Laptop")).thenReturn(Optional.of(productoEntity));
        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(productoEntity);

        String resultado = productoService.actualizarProducto("Laptop", producto);

        assertEquals("Producto actualizado correctamente", resultado); // <- mensaje corregido
        verify(productoRepository, times(1)).save(any(ProductoEntity.class));
    }

    @Test
    public void testActualizarProducto_noExiste() {
        when(productoRepository.findByNombre("Laptop")).thenReturn(Optional.empty());

        String resultado = productoService.actualizarProducto("Laptop", producto);

        assertEquals("Producto no encontrado", resultado); // <- mensaje corregido
    }

    @Test
    public void testEliminarProducto_existente() {
        when(productoRepository.findByNombre("Laptop")).thenReturn(Optional.of(productoEntity));
        doNothing().when(productoRepository).delete(any(ProductoEntity.class));

        String resultado = productoService.eliminarProducto("Laptop");

        assertEquals("Producto eliminado correctamente", resultado);
        verify(productoRepository, times(1)).delete(productoEntity);
    }

    @Test
    public void testEliminarProducto_noExiste() {
        when(productoRepository.findByNombre("Laptop")).thenReturn(Optional.empty());

        String resultado = productoService.eliminarProducto("Laptop");

        assertEquals("Producto no encontrado", resultado);
        verify(productoRepository, never()).delete(any(ProductoEntity.class));
    }
}
