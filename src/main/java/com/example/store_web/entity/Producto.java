package com.example.store_web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_producto")
  private Integer idProducto;

  @Column(name = "nombre", nullable = false, length = 100)
  private String nombre;

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;

  @Column(name = "precio", nullable = false, precision = 10)
  private Double precio;

  @Column(name = "imagen_url", length = 255)
  private String imagenUrl;

  @Column(name = "talla", length = 10)
  private String talla;

  @Column(name = "genero", nullable = false, length = 10)
  private String genero;

  @Column(name = "edad_sugerida", length = 20)
  private String edadSugerida;
  
  @Column(name = "stock", nullable = false)
  private Integer stock;

  @ManyToOne
  @JoinColumn(name = "id_categoria")
  private Categoria categoria;

  public Integer getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(Integer idProducto) {
    this.idProducto = idProducto;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public String getImagenUrl() {
    return imagenUrl;
  }

  public void setImagenUrl(String imagenUrl) {
    this.imagenUrl = imagenUrl;
  }

  public String getTalla() {
    return talla;
  }

  public void setTalla(String talla) {
    this.talla = talla;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getEdadSugerida() {
    return edadSugerida;
  }

  public void setEdadSugerida(String edadSugerida) {
    this.edadSugerida = edadSugerida;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }
}
