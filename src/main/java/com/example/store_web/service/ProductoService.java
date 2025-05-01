package com.example.store_web.service;

import com.example.store_web.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class ProductoService {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public void guardar(Producto producto) {
    entityManager.persist(producto);
  }

  @Transactional
  public void actualizar(Producto producto) {
    entityManager.merge(producto);
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
