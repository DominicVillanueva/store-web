package com.example.store_web.service;

import com.example.store_web.entity.Producto;
import java.util.List;

public interface ProductoService {
  Producto guardarProducto(Producto producto);

  Producto actualizarProducto(Producto producto);

  void eliminarProducto(Integer id);

  Producto buscarProductoById(Integer id);

  List<Producto> listarProductos();

  List<Producto> filtrarProductos(String nombreCategoria, String genero, String edadSugerida);
}

