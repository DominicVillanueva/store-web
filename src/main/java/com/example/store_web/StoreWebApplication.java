package com.example.store_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreWebApplication {
  public static void main(String[] args) {
    SpringApplication.run(StoreWebApplication.class, args);
    System.out.println("Hola Mundo");
  }
}
