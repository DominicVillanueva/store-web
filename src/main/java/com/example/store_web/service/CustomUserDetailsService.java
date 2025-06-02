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
