package com.example.store_web.service.impl;

import com.example.store_web.entity.Contacto;
import com.example.store_web.repository.ContactoRepository;
import com.example.store_web.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoServiceImpl implements ContactoService {
  
  @Autowired
  ContactoRepository contactoRepository;
  
  @Override
  public Contacto guardarContacto(Contacto contacto) {
    return contactoRepository.save(contacto);
  }
}
