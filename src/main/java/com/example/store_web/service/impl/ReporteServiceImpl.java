package com.example.store_web.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteServiceImpl {
  @Autowired
  private DataSource dataSource;
  
  public byte[] generarReporteVentas() throws JRException, SQLException, IOException {
    try {
      InputStream jasperStream = getClass().getResourceAsStream("/reportes/reporte_ventas.jasper");
      if (jasperStream == null) {
        throw new FileNotFoundException("No se encontró el archivo ventas.jasper");
      }
      InputStream logo = getClass().getResourceAsStream("/images/invoice_logo.png");
      Map<String, Object> parametros = new HashMap<>();
      parametros.put("invoice_logo", logo);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros, dataSource.getConnection());
      return JasperExportManager.exportReportToPdf(jasperPrint);
    } catch (Exception e) {
      System.err.println("❌ Error al generar el reporte: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }
}
