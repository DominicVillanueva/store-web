package com.example.store_web.config;

import com.example.store_web.entity.Usuario;
import com.example.store_web.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

  @Autowired
  private UsuarioRepository usuarioRepository;
  
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    String email = authentication.getName();
    Usuario usuario = usuarioRepository.findByEmail(email);
    HttpSession session = request.getSession();
    session.setAttribute("usuarioLogueado", usuario);
    Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
    if (roles.contains("ROLE_ADMIN")) {
        response.sendRedirect("/admin/listar");
    } else if (roles.contains("ROLE_USUARIO")) {
        response.sendRedirect("/");
    } else {
        response.sendRedirect("/login?error");
    }

  }
}
