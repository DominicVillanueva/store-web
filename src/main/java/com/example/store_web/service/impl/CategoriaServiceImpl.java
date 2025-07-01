package com.example.store_web.service.impl;

import com.example.store_web.entity.Categoria;
import com.example.store_web.repository.CategoriaRepository;
import com.example.store_web.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {
  
  @Autowired
  CategoriaRepository categoriaRepository;
  
  @Override
  public List<Categoria> listarTodos() {
    return categoriaRepository.findAll();
  }
}
