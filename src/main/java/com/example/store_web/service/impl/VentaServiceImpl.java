package com.example.store_web.service.impl;

import com.example.store_web.dto.ProductoVentaDTO;
import com.example.store_web.dto.VentaDTO;
import com.example.store_web.entity.Producto;
import com.example.store_web.entity.Venta;
import com.example.store_web.entity.VentaDetalle;
import com.example.store_web.repository.ProductoRepository;
import com.example.store_web.repository.VentaRepository;
import com.example.store_web.service.VentaService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServiceImpl implements VentaService{

  @Autowired
  private ProductoRepository productoRepository;
  
  @Autowired
  private VentaRepository ventaRepository;
  
  @Transactional(rollbackFor = Exception.class)
  @Override
  public void procesarVenta(VentaDTO ventaDTO) {
    Venta venta = new Venta();
    venta.setFecha(LocalDateTime.now());
    venta.setTotal(ventaDTO.getTotal());
    
    List<VentaDetalle> detalles = new ArrayList<>();
    for(ProductoVentaDTO productoVentaDTO: ventaDTO.getProductos()) {
      Producto producto = productoRepository.findById(productoVentaDTO.getId())
        .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productoVentaDTO.getId()));
     
      if(producto.getStock() < productoVentaDTO.getCantidad()) {
        throw new RuntimeException("Stock insuficiente para " + producto.getNombre());
      }
      producto.setStock(producto.getStock() - productoVentaDTO.getCantidad());
      productoRepository.save(producto);
      
      VentaDetalle detalle = new VentaDetalle();
      detalle.setVenta(venta);
      detalle.setProducto(producto);
      detalle.setCantidad(productoVentaDTO.getCantidad());
      detalle.setPrecioUnitario(productoVentaDTO.getPrecio());
      detalle.setSubtotal(productoVentaDTO.getCantidad() * productoVentaDTO.getPrecio());
      detalles.add(detalle);
    }
    venta.setDetalles(detalles);
    ventaRepository.save(venta);
  }
}
