package com.example.store_web.service;

import com.example.store_web.model.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class EmailService {
  
  @Autowired
  private JavaMailSender mailSender;
  
  private final String emailDestino = "softwarelibre97@gmail.com";
  
  public void sendContactEmail(Contacto contacto, String mensaje) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(contacto.getCorreo());
    message.setTo(emailDestino);
    message.setSubject("Mensaje de " + contacto.getNombre() + " desde la web");
    message.setText("Nombre: " + contacto.getNombre() + " " + contacto.getApellido() + "\n"
                    + "Numero: " + contacto.getNumero() + "\n"
                    + "Correo: " + contacto.getCorreo() + "\n\n" 
                    + "Mensaje:\n" + mensaje);
    mailSender.send(message);
  }
}
