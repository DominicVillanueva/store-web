package com.example.store_web.controller;

import com.example.store_web.dto.VentaDTO;
import com.example.store_web.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ventas")
public class VentaController {
  
  @Autowired
  private VentaService ventaService;
  
  @PostMapping("/procesar")
  @ResponseBody
  public ResponseEntity<String> procesarVenta(@RequestBody VentaDTO ventaDTO) {
    try {
      ventaService.procesarVenta(ventaDTO);
      return ResponseEntity.ok("Venta registrada correctamente");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar la venta: " + e.getMessage());
    }
  }
}
