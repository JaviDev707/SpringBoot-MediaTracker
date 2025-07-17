## Media Tracker App - Seguimiento de Libros, Series, Películas y Videojuegos (Proyecto en desarrollo)

Gestor personal donde podrás registrar, listar y filtrar el contenido multimedia que consumes (libros, películas, series y videojuegos).

## Tecnologías utilizadas

Backend:
- Java 17
- Spring Boot
- Spring Web (REST API)
- Spring Data JPA
- MySQL

Frontend:
- HTML5 / CSS3
- Bootstrap 5
- JavaScript puro (fetch API)

## Funcionalidades actuales (V 0.1)

📚 Módulo Libros

CRUD completo:
- Crear libros
- Ver listado de libros
- Editar libros
- Eliminar libros

Filtros disponibles:
- Por título
- Por autor
- Por género
- Por año de finalización
- Por mes y año de finalización

## Estructura actual

- LibroController → Endpoints REST
- LibroService → Lógica de negocio
- LibroRepository → Acceso a base de datos
- Libro → Entidad
- LibroDTO → Actualización parcial

## Vista actual (Frontend)

- Barra de filtros combinada encima de la tabla
- Tabla con lista de libros
- Botones de editar y borrar integrados en la tabla
- Interacción vía JavaScript puro (fetch)

## Próximos pasos

Añadir módulos para:
- 🎬 Películas
- 🎮 Videojuegos
- 📺 Series

- Sistema de valoración personal con estrellas
- Registro y gestión de cada tipo de media con sus atributos propio
- Posible exportación a Android en el futuro

## Cómo ejecutar el proyecto

- Levantar el backend (Spring Boot)
- Crear la base de datos media_tracker en MySQL
- Ejecutar el frontend con Live Server o desde Spring Boot usando Thymeleaf (en proceso)

## Autor - JaviDev707


