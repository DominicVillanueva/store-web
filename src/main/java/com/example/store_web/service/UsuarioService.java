package com.example.store_web.service;

import com.example.store_web.entity.Usuario;

public interface UsuarioService {
  public Usuario buscarbyUsuario(String username);
  public Usuario guardarUsuario(Usuario usuario);
}
