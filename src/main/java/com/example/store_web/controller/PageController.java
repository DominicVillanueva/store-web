package com.example.store_web.controller;

import com.example.store_web.model.Categoria;
import com.example.store_web.model.Contacto;
import com.example.store_web.model.Producto;
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

  /**
   * Método para mostrar la página principal.
   * Agrega al modelo el URI actual para poder usarlo en la vista.
   * Retorna la vista "pages/index".
   */
  @GetMapping("/")
  public String homePage(Model model, HttpServletRequest request) {
    model.addAttribute("currentURI", request.getRequestURI());
    return "pages/index";
  }

  /**
   * Maneja el envío del formulario de contacto.Guarda la información del contacto y envía un correo con el mensaje recibido.
   * Agrega mensajes de éxito o error al modelo para mostrarlos en la vista.
   * Finalmente redirige a la página principal.
   * @return 
   */
  @PostMapping("/correo-enviado")
  public String enviarFormularioContacto(Contacto contacto, Model model) {
    try {
      contacto.setFechaEnvio(LocalDateTime.now());

      // Guardar contacto
      contactoService.guardar(contacto);

      // Enviar correo
      emailService.sendContactEmail(contacto, contacto.getMensaje());

      System.out.println("Mensaje de contacto recibido y correo enviado");
      System.out.println("Nombres: " + contacto.getNombre() + " " + contacto.getApellido());
      System.out.println("Numero: " + contacto.getNumero());
      System.out.println("Email: " + contacto.getCorreo());
      System.out.println("Mensaje: " + contacto.getMensaje());

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

  /**
   * Muestra la página de login.Añade al modelo el URI actual para que la vista pueda acceder a él.
   * @return
   */
  @GetMapping("/login")
  public String loginPage(Model model, HttpServletRequest request) {
    model.addAttribute("currentURI", request.getRequestURI());
    return "pages/login";
  }

  /**
   * Muestra el catálogo de productos.Permite filtrar por categoría, género y edad sugerida, si se especifican.
   * Obtiene los productos filtrados o todos si no hay filtros.
   * También carga todas las categorías para la vista.
   * Añade al modelo los productos, categorías, filtros seleccionados y URI actual.
   * @return 
   */
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
      productos = productoService.listarTodos();
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

  /**
   * Muestra el detalle de un producto específico identificado por su id.
   * Si el producto no existe, redirige al catálogo.
   * Añade al modelo el producto para mostrar en la vista.
   */
  @GetMapping("/producto/{id}")
  public String verDetalleProducto(@PathVariable("id") Integer id, Model model) {
    Producto producto = productoService.buscarPorId(id);
    if (producto == null) {
      return "redirect:/catalogo";
    }
    model.addAttribute("producto", producto);
    return "pages/detalle-producto";
  }

  /**
   * Método auxiliar para validar si un string tiene un valor no nulo y no vacío.
   */
  private boolean esValorPresente(String valor) {
    return valor != null && !valor.trim().isEmpty();
  }
}
