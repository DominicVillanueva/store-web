package com.example.store_web.service;

import com.example.store_web.entity.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  
  @Autowired
  private JavaMailSender mailSender;
  
  private final String emailDestino = "softwarelibre97@gmail.com";
  
  /**
   * Envía un correo electrónico con la información de contacto recibida desde la web.
   * Construye el mensaje con los datos del objeto Contacto y el mensaje adicional.
   * El correo se envía al destinatario fijo definido en la variable emailDestino.
   * 
   * @param contacto contiene los datos del remitente (nombre, apellido, correo y número).
   * @param mensaje es el texto del mensaje que el usuario quiere enviar.
   * 
   * Esta función utiliza JavaMailSender para enviar un correo simple con el contenido
   * formado concatenando los datos del contacto y el mensaje.
   */
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
