package com.example.store_web.model;

import jakarta.persistence.*;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "admin_usuario")
public class AdminUsuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_admin")
  private Integer idAdmin;

  @Column(name = "usuario", nullable = false, unique = true, length = 50)
  private String usuario;

  @Column(name = "contrasenia", nullable = false)
  private String contrasenia;

  public Integer getIdAdmin() {
    return idAdmin;
  }

  public void setIdAdmin(Integer idAdmin) {
    this.idAdmin = idAdmin;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }

}
