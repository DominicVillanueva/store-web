/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store_web.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "contacto")
public class Contacto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_contacto")
  private Integer idContacto;

  @Column(name = "nombre", nullable = false, length = 100)
  private String nombre;

  @Column(name = "correo", nullable = false, length = 100)
  private String correo;

  @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
  private String mensaje;

  @Column(name = "fecha_envio", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime fechaEnvio;

  public Integer getIdContacto() {
    return idContacto;
  }

  public void setIdContacto(Integer idContacto) {
    this.idContacto = idContacto;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public LocalDateTime getFechaEnvio() {
    return fechaEnvio;
  }

  public void setFechaEnvio(LocalDateTime fechaEnvio) {
    this.fechaEnvio = fechaEnvio;
  }

}
