package com.example.store_web.service;

import com.example.store_web.model.AdminUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class AdminService {

  @PersistenceContext
  private EntityManager entityManager;
  
  public AdminUsuario buscarPorUsuario(String usuario) {
    try {
      return entityManager.createQuery("SELECT a FROM Admin_usuario a WHERE a.usuario = :usuario", AdminUsuario.class)
              .setParameter("usuario", usuario)
              .getSingleResult();
    } catch(NoResultException e) {
      return null;
    }
  }
  
  public boolean login(String usuario, String contrasenia) {
    AdminUsuario adminUsuario = buscarPorUsuario(usuario);
    if(adminUsuario != null) {
      return contrasenia.equals(adminUsuario.getContrasenia());
    }
    return false;
  }
}
