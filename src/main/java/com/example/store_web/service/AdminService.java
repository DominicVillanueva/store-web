package com.example.store_web.service;

import com.example.store_web.model.AdminUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @PersistenceContext
  private EntityManager entityManager;
  
  /**
   * Busca un usuario administrador en la base de datos a partir del nombre de usuario proporcionado.Realiza una consulta JPQL para obtener el objeto AdminUsuario que coincide con el usuario.
   * Si no encuentra ningún resultado, captura la excepción NoResultException y devuelve null.
   * Este método es utilizado principalmente para obtener los datos del usuario para la autenticación.
   * @return 
   */
  public AdminUsuario buscarPorUsuario(String usuario) {
    try {
      return entityManager.createQuery("SELECT a FROM AdminUsuario a WHERE a.usuario = :usuario", AdminUsuario.class)
              .setParameter("usuario", usuario)
              .getSingleResult();
    } catch(NoResultException e) {
      return null;
    }
  }
}
