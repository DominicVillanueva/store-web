package com.example.store_web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_categoria")
  private Integer idCategoria;

  @Column(name = "nombre", nullable = false, length = 100)
  private String nombre;

  public Integer getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(Integer idCategoria) {
    this.idCategoria = idCategoria;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
