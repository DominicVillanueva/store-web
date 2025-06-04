
package com.example.store_web.service;

import com.example.store_web.model.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
  @PersistenceContext
  private EntityManager entityManager;
  
  /**
   * Obtiene la lista completa de categorías almacenadas en la base de datos.Ejecuta una consulta JPQL que recupera todas las instancias de la entidad Categoria.
   * Retorna una lista con todos los objetos Categoria existentes.
   * Este método se utiliza para mostrar o manipular todas las categorías disponibles.
   * @return 
   */
  public List<Categoria> listarTodos() {
    return entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
  }
}
