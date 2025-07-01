package com.example.store_web.service.impl;

import com.example.store_web.entity.Usuario;
import com.example.store_web.repository.UsuarioRepository;
import com.example.store_web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

  @Autowired
  UsuarioRepository usuarioRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Override
  public Usuario buscarbyUsuario(String username) {
    return usuarioRepository.findByUsuario(username);
  }
  
  @Override
  public Usuario guardarUsuario(Usuario usuario) {
    usuario.setClave(passwordEncoder.encode(usuario.getClave()));
    return usuarioRepository.save(usuario);
  }
}
