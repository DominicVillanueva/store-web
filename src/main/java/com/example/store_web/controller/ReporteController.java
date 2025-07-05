package com.example.store_web.controller;

import com.example.store_web.service.impl.ReporteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
  @Autowired
  private ReporteServiceImpl reporteServiceImpl;
  
  @GetMapping("/ventas")
  public ResponseEntity<byte[]> generarReporteVentas() {
    try {
      byte[] pdfBytes = reporteServiceImpl.generarReporteVentas();
      return ResponseEntity.ok()
              .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=reporte_ventas.pdf")
              .contentType(MediaType.APPLICATION_PDF)
              .body(pdfBytes);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
