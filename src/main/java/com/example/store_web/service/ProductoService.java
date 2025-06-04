package com.example.store_web.service;

import com.example.store_web.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public void guardar(Producto producto) {
    entityManager.persist(producto);
  }

  @Transactional
  public String actualizar(Producto productoNuevo, int id) {
    String message = "mensaje";
    Producto producto = entityManager.find(Producto.class, id);
    // Actualizar datos
    producto.setNombre(productoNuevo.getNombre());
    producto.setDescripcion(productoNuevo.getDescripcion());
    producto.setPrecio(productoNuevo.getPrecio());
    producto.setImagenUrl(productoNuevo.getImagenUrl());
    producto.setTalla(productoNuevo.getTalla());
    producto.setGenero(productoNuevo.getGenero());
    producto.setEdadSugerida(productoNuevo.getEdadSugerida());
    producto.setCategoria(productoNuevo.getCategoria());
    entityManager.merge(producto);
    
    message = "¡Producto actualizado correctamente!";
    return message;
  }

  @Transactional
  public void eliminar(Integer id) {
    Producto producto = entityManager.find(Producto.class, id);
    if (producto != null) {
      entityManager.remove(producto);
    }
  }

  public Producto buscarPorId(Integer id) {
    return entityManager.find(Producto.class, id);
  }
  
  public List<Producto> listarTodos() {
    return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
  }

  public List<Producto> listarProductosPaginados(int firstResult, int maxResults) {
    TypedQuery<Producto> query = entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
    query.setFirstResult(firstResult);
    query.setMaxResults(maxResults);
    return query.getResultList();
  }
  
  public long contarProductos() {
    return (long) entityManager.createQuery("SELECT COUNT(p) FROM Producto p").getSingleResult();
  }

  // metodos para filtrar
  public List<Producto> filtrarProductos(String nombreCategoria, String genero, String edadSugerida) {
    StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_filtrar_productos", Producto.class);

    // Registrar parametros de entrada
    storedProcedure.registerStoredProcedureParameter("p_nombre_categoria", String.class, ParameterMode.IN);
    storedProcedure.registerStoredProcedureParameter("p_genero", String.class, ParameterMode.IN);
    storedProcedure.registerStoredProcedureParameter("p_edad_sugerida", String.class, ParameterMode.IN);

    // Ingresar datos a los parametros de entrada
    storedProcedure.setParameter("p_nombre_categoria", nombreCategoria);
    storedProcedure.setParameter("p_genero", genero);
    storedProcedure.setParameter("p_edad_sugerida", edadSugerida);

    return storedProcedure.getResultList();
  }
}
