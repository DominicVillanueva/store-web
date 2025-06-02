package com.example.store_web.model;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "admin_usuario")
public class AdminUsuario implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_admin")
  private Integer idAdmin;

  @Column(name = "usuario", nullable = false, unique = true, length = 50)
  private String usuario;

  @Column(name = "contrasenia", nullable = false)
  private String contrasenia;
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
  }
  
  @Override
  public String getPassword() {
    return contrasenia;
  }

  @Override
  public String getUsername() {
    return usuario;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

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
