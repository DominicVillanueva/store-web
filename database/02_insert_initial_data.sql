-- Insertar categorías
INSERT INTO categoria (nombre) VALUES
('Polos'),
('Pantalones'),
('Vestidos'),
('Zapatos'),
('Accesorios'),
('Abrigos'),
('Ropa Deportiva'),
('Pijamas'),
('Trajes de baño');


-- Insertar productos
INSERT INTO producto (nombre, descripcion, precio, imagen_url, talla, id_categoria, genero, edad_sugerida, stock) VALUES
-- Polos
('Polo Dinosaurio', 'Polo de algodón con estampado de dinosaurio para niños.', 29.90, 'https://example.com/imagenes/polo_dinosaurio.jpg', 'M', 1, 'NIÑO', '3-5 años', 0),
('Polo Princesa', 'Polo rosa de algodón con diseño de princesa para niñas.', 32.90, 'https://example.com/imagenes/polo_princesa.jpg', 'S', 1, 'NIÑA', '3-5 años', 15),
('Polo Estrella', 'Polo con diseño de estrella para niños pequeños.', 28.90, 'https://example.com/imagenes/polo_estrella.jpg', 'XS', 1, 'NIÑO', '2-3 años', 8),

-- Pantalones
('Pantalón Jeans Niño', 'Jeans azul oscuro para niños, resistente y cómodo.', 59.90, 'https://example.com/imagenes/jeans_nino.jpg', 'M', 2, 'NIÑO', '6-8 años', 0),
('Pantalón Rosa Niña', 'Pantalón cómodo color rosa para niñas.', 54.90, 'https://example.com/imagenes/pantalon_rosa.jpg', 'L', 2, 'NIÑA', '3-5 años', 10),
('Pantalón Niña Pequeña', 'Pantalón de algodón para niñas pequeñas.', 49.90, 'https://example.com/imagenes/pantalon_nina_pequena.jpg', 'XS', 2, 'NIÑA', '2-3 años', 30),

-- Vestidos
('Vestido Flores', 'Vestido de flores coloridas para niñas.', 79.90, 'https://example.com/imagenes/vestido_flores.jpg', 'M', 3, 'NIÑA', '6-8 años', 5),
('Vestido Fiesta', 'Vestido elegante para eventos especiales.', 120.00, 'https://example.com/imagenes/vestido_fiesta.jpg', 'L', 3, 'NIÑA', '6-8 años', 4),

-- Zapatos
('Zapatillas Luz LED', 'Zapatillas para niños que brillan con luces LED.', 99.90, 'https://example.com/imagenes/zapatillas_led.jpg', 'S', 4, 'UNISEX', '6-8 años'. 3),
('Botines Invierno', 'Botines de invierno para niños y niñas.', 120.90, 'https://example.com/imagenes/botines_invierno.jpg', 'M', 4, 'UNISEX', '9-12 años', 6),

-- Accesorios
('Gorra Estrella', 'Gorra infantil con diseño de estrella brillante.', 19.90, 'https://example.com/imagenes/gorra_estrella.jpg', 'M', 5, 'UNISEX', '6-8 años', 4),
('Mochila Unicornio', 'Mochila escolar con diseño de unicornio.', 49.90, 'https://example.com/imagenes/mochila_unicornio.jpg', 'S', 5, 'NIÑA', '5-7 años', 0),

-- Abrigos
('Chaqueta Polar Niño', 'Chaqueta abrigadora para niños.', 89.90, 'https://example.com/imagenes/chaqueta_polar.jpg', 'M', 6, 'NIÑO', '9-12 años', 0),
('Abrigo Elegante Niña', 'Abrigo elegante color beige para niñas.', 110.00, 'https://example.com/imagenes/abrigo_nina.jpg', 'L', 6, 'NIÑA', '9-12 años', 1),

-- Ropa Deportiva
('Conjunto Deportivo Niño', 'Conjunto para actividades deportivas.', 69.90, 'https://example.com/imagenes/conjunto_nino.jpg', 'S', 7, 'NIÑO', '5-7 años', 8),
('Leggins Deportivos Niña', 'Leggins para actividades deportivas.', 45.90, 'https://example.com/imagenes/leggins_nina.jpg', 'M', 7, 'NIÑA', '5-7 años', 6),

-- Pijamas
('Pijama Dinosaurio', 'Pijama de dinosaurios para niños.', 55.90, 'https://example.com/imagenes/pijama_dinosaurio.jpg', 'L', 8, 'NIÑO', '5-7 años', 6),
('Pijama Princesa', 'Pijama de princesas para niñas.', 58.90, 'https://example.com/imagenes/pijama_princesa.jpg', 'M', 8, 'NIÑA', '5-7 años', 10),

-- Trajes de baño
('Bañador Rayas Niño', 'Bañador a rayas azul para niño.', 39.90, 'https://example.com/imagenes/banador_rayas.jpg', 'M', 9, 'NIÑO', '4-6 años', 3),
('Bañador Sirena Niña', 'Bañador con diseño de sirena para niña.', 42.90, 'https://example.com/imagenes/banador_sirena.jpg', 'S', 9, 'NIÑA', '4-6 años', 2);


-- Insertar administradores
-- Para loguearse es: usuario: admin, contraseña: admin
INSERT INTO usuario (nombre, apellido, correo, telefono, genero, edad, usuario, clave, rol)
VALUES ('Administrador', 'Del Sistema', 'admin@correo.com', '999999999', 'Masculino', 30, 'admin', '$2a$12$H/7Qaa2HRLkl/YhaxFSk5uLCrNxESCNuLAX93DlZpFmx.M8r0beri', 'Admin');

-- Insertar contactos de prueba
INSERT INTO contacto (nombre, apellido, numero correo, mensaje, fecha_envio) VALUES
('Juan', 'Pérez', '951357852' 'juan.perez@example.com', 'Hola, quiero saber más sobre los vestidos.', NOW());
