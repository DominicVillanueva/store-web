package com.example.store_web.config;

import com.example.store_web.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private CustomUserDetailsService userDetailsService;
  
  /**
   * Define la configuración principal de seguridad HTTP para la aplicación.Configura las reglas de autorización de URL, la página de login personalizada,
   * la URL de redirección tras login exitoso y la configuración de logout.Permite que solo usuarios con rol ADMIN accedan a rutas bajo /admin/**
   * y permite acceso libre al resto de URLs. 
   * @param http
   * @return 
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/admin/**").hasRole("ADMIN")
          .anyRequest().permitAll()
      )
      .formLogin(form -> form
          .loginPage("/login")
          .defaultSuccessUrl("/admin/listar", true)
          .permitAll()
      )
      .logout(logout -> logout
          .logoutUrl("/logout")
          .logoutSuccessUrl("/login?logout=true")
          .permitAll()
      );
    return http.build();
  }

  /**
   * Proporciona un bean para la codificación de contraseñas.Utiliza el algoritmo BCrypt, que es seguro y recomendado para almacenar passwords.
   * @returns
   */
  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  /**
   * Obtiene el AuthenticationManager que gestiona el proceso de autenticación.Lo inyecta desde la configuración automática de Spring Security.
   * @param config
   * @return 
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
  
  /**
   * Define el proveedor de autenticación que usa el servicio personalizado de detalles de usuario
   * y el codificador de contraseñas definido para verificar credenciales durante el login.
   * @return 
   */
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }
}

