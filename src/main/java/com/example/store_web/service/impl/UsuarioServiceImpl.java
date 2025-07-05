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
    if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null) {
      throw new RuntimeException("El nombre de usuario ya está registrado");
    }
    
    if(usuarioRepository.findByEmail(usuario.getCorreo()) != null) {
      throw new RuntimeException("El correo electrónico ya está registrado");
    }
    
    usuario.setClave(passwordEncoder.encode(usuario.getClave()));
    return usuarioRepository.save(usuario);
  }
}
