-- Crear la base de datos solo si no existe
CREATE DATABASE IF NOT EXISTS store_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE store_db;

-- Crear tablas necesarias ------------------------

-- Crear tabla de categorías
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Crear tabla de productos
CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    imagen_url VARCHAR(255),
    talla VARCHAR(10),
    genero ENUM('NIÑO', 'NIÑA', 'UNISEX') NOT NULL,
    edad_sugerida VARCHAR(20),
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

-- Crear tabla de contactos
CREATE TABLE contacto (
    id_contacto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla de administradores
CREATE TABLE admin_usuario (
    id_admin INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasenia VARCHAR(255) NOT NULL
);

