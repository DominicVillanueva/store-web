package com.example.store_web.controller;

import com.example.store_web.model.Categoria;
import com.example.store_web.model.Producto;
import com.example.store_web.service.CategoriaService;
import com.example.store_web.service.ProductoService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Usuario
 */
@Controller
public class ProductoController {
  private final ProductoService productoService;
  private final CategoriaService categoriaService;

  public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
    this.productoService = productoService;
    this.categoriaService = categoriaService;
  }
  
  @GetMapping("/catalogo")
  @ResponseBody
  public List<Producto> verCatalogo(Model model, 
                            @RequestParam(name = "categoria", required = false) String nombreCategoria,
                            @RequestParam(name = "genero", required = false) String genero,
                            @RequestParam(name = "edad", required = false) String edadSugerida) {
    
    List<Producto> productos;
    if(nombreCategoria != null || genero != null || edadSugerida != null) {
      productos = productoService.filtrarProductos(nombreCategoria, genero, edadSugerida);
    } else {
      productos = productoService.listarTodos();
    }
    
    List<Categoria> categorias = categoriaService.listarTodos();
    model.addAttribute("productos", productos);
    model.addAttribute("categorias", categorias);
    return productos;
  }
}
