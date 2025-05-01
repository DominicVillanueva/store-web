
package com.example.store_web.service;

import com.example.store_web.model.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class CategoriaService {
  @PersistenceContext
  private EntityManager entityManager;
  
  public List<Categoria> listarTodos() {
    return entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
  }
}
