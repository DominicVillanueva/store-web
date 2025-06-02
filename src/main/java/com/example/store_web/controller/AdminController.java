package com.example.store_web.controller;

import com.example.store_web.model.Categoria;
import com.example.store_web.model.Producto;
import com.example.store_web.service.CategoriaService;
import com.example.store_web.service.ProductoService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

  private final ProductoService productoService;
  private final CategoriaService categoriaService;

  public AdminController(ProductoService productoService, CategoriaService categoriaService) {
    this.productoService = productoService;
    this.categoriaService = categoriaService;
  }

  @GetMapping("/listar")
  public String listarProductos(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                Model model) {

    int firstResult = page * size;
    List<Producto> productos = productoService.listarProductosPaginados(firstResult, size);
    long totalProductos = productoService.contarProductos();
    int totalPaginas = (int) Math.ceil((double) totalProductos / size);

    List<Categoria> categorias = categoriaService.listarTodos();
    model.addAttribute("productos", productos);
    model.addAttribute("categorias", categorias);
    model.addAttribute("totalProductos", totalProductos);
    model.addAttribute("totalPaginas", totalPaginas);
    model.addAttribute("paginaActual", page);
    model.addAttribute("tamanoPagina", size);

    return "pages/admin/dashboard";
  }

  @PostMapping("/agregar")
  public String agregarProducto(@ModelAttribute Producto producto) {
    productoService.guardar(producto);
    return "redirect:/admin/listar";
  }

  @GetMapping("/editar/{idProducto}")
  public String mostrarFormularioEditar(@PathVariable("idProducto") int idProducto,
                                        Model model,
                                        RedirectAttributes redirectAttributes) {

    Producto producto = productoService.buscarPorId(idProducto);
    if (producto == null) {
      redirectAttributes.addFlashAttribute("error", "No se encontró el producto con ID: " + idProducto);
      return "redirect:/admin/listar";
    }

    model.addAttribute("producto", producto);
    model.addAttribute("categorias", categoriaService.listarTodos());

    return "pages/admin/editar";
  }

  @PostMapping("/editar/{idProducto}")
  public String actualizarProducto(@PathVariable int idProducto,
                                   @ModelAttribute("producto") Producto productoNuevo,
                                   RedirectAttributes redirectAttributes) {
    String respuesta = productoService.actualizar(productoNuevo, idProducto);
    redirectAttributes.addFlashAttribute("success", respuesta);
    return "redirect:/admin/listar";
  }

  @PostMapping("/eliminar/{idProducto}")
  public String eliminarProducto(@PathVariable("idProducto") int id,
                                 RedirectAttributes redirectAttributes) {
    try {
      productoService.eliminar(id);
      redirectAttributes.addFlashAttribute("success", "Producto eliminado correctamente.");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el producto.");
    }
    return "redirect:/admin/listar";
  }
}
