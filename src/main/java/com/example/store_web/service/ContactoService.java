package com.example.store_web.service;

import com.example.store_web.model.Contacto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ContactoService {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Guarda un nuevo objeto Contacto en la base de datos.
   * Utiliza la anotación @Transactional para asegurar que la operación 
   * se ejecute dentro de una transacción, garantizando la atomicidad.
   * El método persiste la entidad Contacto recibida, agregándola a la base de datos.
   *
   * @param contacto instancia de Contacto que será almacenada en la base de datos.
   */
  @Transactional
  public void guardar(Contacto contacto) {
    entityManager.persist(contacto);
  }
}
