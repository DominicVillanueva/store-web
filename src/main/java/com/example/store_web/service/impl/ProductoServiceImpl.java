package com.example.store_web.service.impl;

import com.example.store_web.entity.Producto;
import com.example.store_web.repository.ProductoRepository;
import com.example.store_web.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{
  
  @Autowired
  ProductoRepository productoRepository;

  @Override
  public Producto guardarProducto(Producto producto) {
    return productoRepository.save(producto);
  }
  
  @Override
  public Producto actualizarProducto(Producto producto) {
    return productoRepository.save(producto);
  }
  
  @Override
  public void eliminarProducto(Integer id) {
    productoRepository.deleteById(id);
  }
  
  @Override
  public List<Producto> listarProductos() {
    return productoRepository.findAll();
  }
  
  @Override
  public List<Producto> filtrarProductos(String nombreCategoria, String genero, String edadSugerida) {
    return productoRepository.filtrarProductos(nombreCategoria, genero, edadSugerida);
  }

  @Override
  public Producto buscarProductoById(Integer id) {
    return productoRepository.findById(id).orElse(null);
  }
}
