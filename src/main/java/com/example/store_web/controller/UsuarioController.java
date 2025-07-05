package com.example.store_web.controller;

import com.example.store_web.entity.Usuario;
import com.example.store_web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
  @Autowired
  private UsuarioService usuarioService;
  
  @PostMapping("/registro")
  public String registrarUsuario(@ModelAttribute("usuarioRegistrado") Usuario usuario, Model model) {
    try {
      usuario.setRol("Usuario");
      usuarioService.guardarUsuario(usuario);
      return "pages/index";
    } catch (RuntimeException e) {
      model.addAttribute("usuarioRegistrado", usuario);
      model.addAttribute("error", e.getMessage());
      return "pages/registro";
    }
  }
}
