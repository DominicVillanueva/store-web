package com.example.store_web.service;

import com.example.store_web.entity.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UsuarioService usuarioService;
  
  /**
   * Método encargado de cargar los detalles del usuario a partir del nombre de usuario proporcionado.Utiliza el servicio AdminService para buscar el usuario en la base de datos. 
   * Si el usuario no se encuentra, lanza una excepción UsernameNotFoundException para indicar que no existe.
   * Si el usuario existe, crea un objeto UserDetails con el nombre de usuario, la contraseña cifrada y el rol "ADMIN".
   * Este objeto será utilizado por Spring Security para la autenticación y autorización.
   * @return 
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioService.buscarbyUsuario(username);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuario no encontrado");
    }
    String role = "ROLE_" + usuario.getRol().toUpperCase();
    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
    return new User(usuario.getCorreo(), usuario.getPassword(), authorities); 
  }
}
