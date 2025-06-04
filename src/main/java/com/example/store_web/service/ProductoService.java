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

  /**
   * Persiste un nuevo producto en la base de datos.
   * Recibe un objeto Producto y lo guarda como una nueva entidad.
   * Se ejecuta dentro de una transacción para asegurar la integridad.
   *
   * @param producto el objeto Producto a guardar.
   */
  @Transactional
  public void guardar(Producto producto) {
    entityManager.persist(producto);
  }

  /**
   * Actualiza un producto existente identificado por su id.
   * Busca el producto en la base de datos y actualiza sus campos con los valores del producto recibido.
   * Finalmente, sincroniza los cambios mediante merge y devuelve un mensaje de éxito.
   * Esta operación es transaccional para garantizar que los cambios se apliquen de forma atómica.
   *
   * @param productoNuevo objeto con los nuevos datos para actualizar.
   * @param id identificador del producto a actualizar.
   * @return mensaje indicando el resultado de la operación.
   */
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

  /**
   * Elimina un producto de la base de datos identificado por su id.
   * Busca la entidad Producto y si existe, la elimina.
   * La operación está envuelta en una transacción para garantizar la consistencia.
   *
   * @param id identificador del producto a eliminar.
   */
  @Transactional
  public void eliminar(Integer id) {
    Producto producto = entityManager.find(Producto.class, id);
    if (producto != null) {
      entityManager.remove(producto);
    }
  }

  /**
   * Busca un producto en la base de datos por su identificador.
   * Retorna el producto encontrado o null si no existe.
   *
   * @param id identificador del producto.
   * @return el producto encontrado o null.
   */
  public Producto buscarPorId(Integer id) {
    return entityManager.find(Producto.class, id);
  }

  /**
   * Recupera la lista completa de productos almacenados en la base de datos.
   *
   * @return lista con todos los productos.
   */
  public List<Producto> listarTodos() {
    return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
  }

  /**
   * Recupera una lista paginada de productos.
   * Permite controlar desde qué resultado empezar y cuántos obtener.
   * Útil para mostrar resultados en páginas o cargar datos de forma segmentada.
   *
   * @param firstResult índice del primer resultado a obtener.
   * @param maxResults cantidad máxima de resultados a retornar.
   * @return lista paginada de productos.
   */
  public List<Producto> listarProductosPaginados(int firstResult, int maxResults) {
    TypedQuery<Producto> query = entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
    query.setFirstResult(firstResult);
    query.setMaxResults(maxResults);
    return query.getResultList();
  }

  /**
   * Cuenta el total de productos almacenados en la base de datos.
   *
   * @return número total de productos.
   */
  public long contarProductos() {
    return (long) entityManager.createQuery("SELECT COUNT(p) FROM Producto p").getSingleResult();
  }

  /**
   * Llama a un procedimiento almacenado para filtrar productos según criterios específicos.
   * El procedimiento espera los parámetros nombre de categoría, género y edad sugerida.
   * Retorna una lista de productos que cumplen con los filtros aplicados.
   *
   * @param nombreCategoria filtro por nombre de categoría.
   * @param genero filtro por género.
   * @param edadSugerida filtro por edad sugerida.
   * @return lista filtrada de productos según los parámetros.
   */
  public List<Producto> filtrarProductos(String nombreCategoria, String genero, String edadSugerida) {
    StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_filtrar_productos", Producto.class);
    storedProcedure.registerStoredProcedureParameter("p_nombre_categoria", String.class, ParameterMode.IN);
    storedProcedure.registerStoredProcedureParameter("p_genero", String.class, ParameterMode.IN);
    storedProcedure.registerStoredProcedureParameter("p_edad_sugerida", String.class, ParameterMode.IN);
    storedProcedure.setParameter("p_nombre_categoria", nombreCategoria);
    storedProcedure.setParameter("p_genero", genero);
    storedProcedure.setParameter("p_edad_sugerida", edadSugerida);

    return storedProcedure.getResultList();
  }
}

