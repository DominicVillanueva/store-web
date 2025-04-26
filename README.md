# 🛍️ Tienda de Ropa - Proyecto Universitario

Aplicación web desarrollada con **Spring MVC** y **Thymeleaf**, utilizando **Spring Security** para la autenticación y **Spring Data JPA** para la gestión de datos.

## 📋 Requisitos

- Java 17 o superior
- Maven 3.8 o superior
- IDE recomendado: IntelliJ IDEA, Eclipse o Apache NetBeans
- Git
- Base de datos Sql Server

## 🚀 Instalación y ejecución local

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/DominicVillanueva/store-web.git
   cd store-web
   ```

2. Importar el proyecto en tu IDE como proyecto **Maven**.

3. Configurar la base de datos en el archivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bd-store
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   ```

4. Levantar la aplicación ejecutando la clase principal que contiene la anotación `@SpringBootApplication`.

5. Acceder a la aplicación en:
   ```
   http://localhost:8080
   ```

6. Credenciales de acceso iniciales (si aplican usuarios preconfigurados):
   - **Usuario:** `admin`
   - **Contraseña:** `admin`

## 🤝 ¿Cómo colaborar?

1. Haz un **fork** de este repositorio.
2. Crea una nueva rama:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza los cambios necesarios y realiza un commit:
   ```bash
   git commit -m "Agrega nueva funcionalidad"
   ```
4. Envía los cambios a tu fork:
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
5. Crea un **Pull Request** describiendo detalladamente los cambios realizados.

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Data JPA
- Spring Security
- Maven
- MySQL / PostgreSQL
- HTML / CSS / Bootstrap

## 📄 Licencia

Este proyecto ha sido desarrollado únicamente con fines educativos.
