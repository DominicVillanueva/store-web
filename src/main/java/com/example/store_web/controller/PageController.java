package com.example.store_web.controller;

import com.example.store_web.entity.Categoria;
import com.example.store_web.entity.Contacto;
import com.example.store_web.entity.Producto;
import com.example.store_web.entity.Usuario;
import com.example.store_web.service.CategoriaService;
import com.example.store_web.service.ContactoService;
import com.example.store_web.service.EmailService;
import com.example.store_web.service.ProductoService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

  private final ContactoService contactoService;
  private final EmailService emailService;
  private final ProductoService productoService;
  private final CategoriaService categoriaService;

  public PageController(ContactoService contactoService, EmailService emailService, ProductoService productoService, CategoriaService categoriaService) {
    this.contactoService = contactoService;
    this.emailService = emailService;
    this.productoService = productoService;
    this.categoriaService = categoriaService;
  }

  @GetMapping("/")
  public String homePage(Model model, HttpServletRequest request) {
    model.addAttribute("currentURI", request.getRequestURI());
    return "pages/index";
  }

  @PostMapping("/correo-enviado")
  public String enviarFormularioContacto(Contacto contacto, Model model) {
    try {
      contacto.setFechaEnvio(LocalDateTime.now());

      // Guardar contacto
      contactoService.guardarContacto(contacto);

      // Enviar correo
      emailService.sendContactEmail(contacto, contacto.getMensaje());

      model.addAttribute("mensaje", "¡Gracias por tu interés! Ha sido enviado con éxito.");
      model.addAttribute("tipoMensaje", "success");
      model.addAttribute("mensaje", "Tu mensaje ha sido enviado. ¡Gracias!");

    } catch (Exception e) {
      System.err.println("Error al procesar formulario de contacto o enviar email: " + e.getMessage());
      model.addAttribute("mensaje", "Hubo un error al enviar tu mensaje. Por favor, inténtalo de nuevo más tarde.");
      model.addAttribute("tipoMensaje", "danger");
    }

    return "redirect:/";
  }

  @GetMapping("/login")
  public String loginPage(Model model, HttpServletRequest request) {
    model.addAttribute("currentURI", request.getRequestURI());
    return "pages/login";
  }
  
  @GetMapping("/catalogo")
  public String verCatalogo(Model model,
                            @RequestParam(name = "categoria", required = false) String nombreCategoria,
                            @RequestParam(name = "genero", required = false) String genero,
                            @RequestParam(name = "edad", required = false) String edadSugerida,
                            HttpServletRequest request) {
    // Convertir "" en null para enviar correctamente al procedimiento
    nombreCategoria = esValorPresente(nombreCategoria) ? nombreCategoria : null;
    genero = esValorPresente(genero) ? genero : null;
    edadSugerida = esValorPresente(edadSugerida) ? edadSugerida : null;

    List<Producto> productos;
    if(nombreCategoria != null || genero != null || edadSugerida != null) {
      productos = productoService.filtrarProductos(nombreCategoria, genero, edadSugerida);
    } else {
      productos = productoService.listarProductos();
    }

    List<Categoria> categorias = categoriaService.listarTodos();
    model.addAttribute("productos", productos);
    model.addAttribute("categorias", categorias);
    model.addAttribute("currentURI", request.getRequestURI());

    model.addAttribute("categoriaSeleccionada", nombreCategoria);
    model.addAttribute("generoSeleccionado", genero);
    model.addAttribute("edadSeleccionada", edadSugerida);

    return "pages/catalogo";
  }

  @GetMapping("/producto/{id}")
  public String verDetalleProducto(@PathVariable("id") Integer id, Model model) {
    Producto producto = productoService.buscarProductoById(id);
    if (producto == null) {
      return "redirect:/catalogo";
    }
    model.addAttribute("producto", producto);
    return "pages/detalle-producto";
  }
  
  @GetMapping("/registro")
  public String mostrarFormularioRegistro(Model model) {
      model.addAttribute("usuario", new Usuario());
      return "registro";
  }

  /**
   * Método auxiliar para validar si un string tiene un valor no nulo y no vacío.
   */
  private boolean esValorPresente(String valor) {
    return valor != null && !valor.trim().isEmpty();
  }
}
