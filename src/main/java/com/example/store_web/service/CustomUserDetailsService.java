package com.example.store_web.service;

import com.example.store_web.model.AdminUsuario;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final AdminService adminService;
  
  public CustomUserDetailsService(AdminService adminService) {
    this.adminService = adminService;
  }
  
  /**
   * Método encargado de cargar los detalles del usuario a partir del nombre de usuario proporcionado.Utiliza el servicio AdminService para buscar el usuario en la base de datos. 
   * Si el usuario no se encuentra, lanza una excepción UsernameNotFoundException para indicar que no existe.
   * Si el usuario existe, crea un objeto UserDetails con el nombre de usuario, la contraseña cifrada y el rol "ADMIN".
   * Este objeto será utilizado por Spring Security para la autenticación y autorización.
   * @return 
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AdminUsuario admin = adminService.buscarPorUsuario(username);
    if (admin == null) throw new UsernameNotFoundException("Usuario no encontrado");
    return User
        .withUsername(admin.getUsuario())
        .password(admin.getContrasenia())
        .roles("ADMIN")
        .build();
  }
}
