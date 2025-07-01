package com.example.store_web.controller;

import com.example.store_web.entity.Categoria;
import com.example.store_web.entity.Producto;
import com.example.store_web.service.CategoriaService;
import com.example.store_web.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

  @Autowired
  private ProductoService productoService;
  
  @Autowired
  private CategoriaService categoriaService;

  @GetMapping("/listar")
  public String listarProductos(Model model) {
    List<Producto> productos = productoService.listarProductos();
    List<Categoria> categorias = categoriaService.listarTodos();
    model.addAttribute("productos", productos);
    model.addAttribute("categorias", categorias);
    return "pages/admin/dashboard";
  }

  @PostMapping("/agregar")
  public String agregarProducto(@ModelAttribute Producto producto) {
    productoService.guardarProducto(producto);
    return "redirect:/admin/listar";
  }

  @GetMapping("/editar/{idProducto}")
  public String mostrarFormularioEditar(@PathVariable("idProducto") int idProducto,
                                        Model model,
                                        RedirectAttributes redirectAttributes) {

    Producto producto = productoService.buscarProductoById(idProducto);
    if (producto == null) {
      redirectAttributes.addFlashAttribute("error", "No se encontró el producto con ID: " + idProducto);
      return "redirect:/admin/listar";
    }
    model.addAttribute("producto", producto);
    model.addAttribute("categorias", categoriaService.listarTodos());

    return "pages/admin/editar";
  }

  @PostMapping("/editar/{idProducto}")
  public String actualizarProducto(@PathVariable Integer idProducto,
                                   @ModelAttribute("producto") Producto productoNuevo,
                                   RedirectAttributes redirectAttributes) {
    Producto existentProducto = productoService.buscarProductoById(idProducto);
    existentProducto.setIdProducto(idProducto);
    existentProducto.setNombre(productoNuevo.getNombre());
    existentProducto.setDescripcion(productoNuevo.getDescripcion());
    existentProducto.setEdadSugerida(productoNuevo.getEdadSugerida());
    existentProducto.setPrecio(productoNuevo.getPrecio());
    existentProducto.setImagenUrl(productoNuevo.getImagenUrl());
    existentProducto.setTalla(productoNuevo.getTalla());
    existentProducto.setGenero(productoNuevo.getGenero());
    existentProducto.setCategoria(productoNuevo.getCategoria());
    productoService.actualizarProducto(existentProducto);
    return "redirect:/admin/listar";
  }

  @PostMapping("/eliminar/{idProducto}")
  public String eliminarProducto(@PathVariable("idProducto") int id, RedirectAttributes redirectAttributes) {
    productoService.eliminarProducto(id);
    return "redirect:/admin/listar";
  }
}
