package com.example.store_web.dto;

import java.util.List;

public class VentaDTO {
  private List<ProductoVentaDTO> productos;
  private Double total;

  public List<ProductoVentaDTO> getProductos() {
    return productos;
  }

  public void setProductos(List<ProductoVentaDTO> productos) {
    this.productos = productos;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }
  
  
}
