package com.example.store_web.repository;

import com.example.store_web.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  
  @Query("SELECT a FROM Usuario a WHERE a.usuario = :usuario")
  public Usuario findByUsuario(@Param("usuario") String usuario);

  @Query("SELECT a FROM Usuario a WHERE a.correo = :correo")
  public Usuario findByEmail(@Param("correo") String correo);
}
