# LABORATORIO 07 - DESARROLLO DE APLICACIONES WEB CON JSP, SERVLETS, JSTL Y MVC

## Descripción
Este proyecto implementa un CRUD completo para dos entidades relacionadas: Producto y Categoría. Utiliza Jakarta EE (Servlets, JSP), JSTL, el patrón MVC, y está construido sobre Maven en IntelliJ IDEA.

## Estructura del Proyecto
- `modelo/`: Clases Java para Producto y Categoría.
- `dao/`: Clases DAO con la lógica JDBC para interactuar con MySQL.
- `servlets/`: Controladores (Servlets) para manejar operaciones del CRUD.
- `web/`: JSPs con JSTL para mostrar, agregar, editar y eliminar datos.
- `pom.xml`: Archivo de configuración Maven con las dependencias necesarias.

---

## Requisitos
- IntelliJ IDEA
- Java 11 o superior
- Apache Tomcat 10
- MySQL / XAMPP
- Navegador Web
- Conexión JDBC (MySQL Connector)
- Dependencia JSTL

---

## Configuración de la Base de Datos (MySQL)

Copia y ejecuta lo siguiente en SQLyog, phpMyAdmin o consola MySQL:

```sql
-- Crear base de datos
CREATE DATABASE IF NOT EXISTS tienda;

-- Usar la base de datos
USE tienda;

-- Crear tabla Categoria
CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(255) NOT NULL
);

-- Crear tabla Producto
CREATE TABLE Producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2),
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

-- Insertar categorías
INSERT INTO Categoria (nombre_categoria) VALUES ('Electrónica');
INSERT INTO Categoria (nombre_categoria) VALUES ('Ropa');
INSERT INTO Categoria (nombre_categoria) VALUES ('Muebles');

-- Insertar productos
INSERT INTO Producto (nombre, descripcion, precio, id_categoria) 
VALUES 
    ('Smartphone', 'Smartphone de última generación', 799.99, 1),
    ('Camiseta', 'Camiseta de algodón', 19.99, 2),
    ('Sofa', 'Sofa de tres plazas', 499.99, 3);

-- Verificar datos insertados
SELECT * FROM Categoria;
SELECT * FROM Producto;
```

---

## Pasos para ejecutar el proyecto

1. **Clonar el repositorio** o copiar la carpeta `laboratorio_07` a tu máquina local.

2. **Importar en IntelliJ IDEA**
   - Abrir IntelliJ
   - Seleccionar *Open* y elegir la carpeta `laboratorio_07`
   - IntelliJ detectará automáticamente que es un proyecto Maven

3. **Verificar el archivo `pom.xml`**
   - Asegúrate de tener las siguientes dependencias:
     <dependency>
         <groupId>jakarta.servlet.jsp.jstl</groupId>
         <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
         <version>2.0.0</version>
     </dependency>

4. **Configurar el servidor Tomcat**
   - Ir a `Run > Edit Configurations`
   - Añadir configuración de Tomcat con la ruta al proyecto
   - Asegúrate de desplegar el `.war` correctamente

5. **Verificar conexión JDBC**
   - Revisar las credenciales y puerto de tu base de datos en el DAO
   - Ejemplo: `jdbc:mysql://localhost:3306/tienda`

6. **Ejecutar el servidor y abrir en el navegador**
   - Iniciar Tomcat desde IntelliJ
   - Visita: `http://localhost:8080/laboratorio_07/` (dependiendo de tu configuración)

---

## Funcionalidad CRUD (Vista en Navegador)

- **Producto**
  - Listado con nombre, descripción, precio y categoría
  - Agregar nuevo producto (con selector de categoría)
  - Editar producto existente
  - Eliminar producto

- **Categoría**
  - Listado de categorías
  - Agregar nueva categoría
  - Editar categoría
  - Eliminar categoría

---

## Autor
Coello Palomino, Ricardo  
Desarrollo de Aplicaciones Web  
TECSUP - Sección 5 - C24 - A - B  
Semana 07
## Autor
Coello Palomino, Ricardo  
Desarrollo de Aplicaciones Web  
TECSUP - Sección 5 - C24 - A - B  
Semana 07
