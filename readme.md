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

## Funcionalidades actuales (V 0.2)

📚 Módulo Libros

CRUD completo:
- Crear libros (V 0.1)
- Ver listado de libros (V 0.1)
- Editar libros (V 0.1)
- Eliminar libros (V 0.1)

Filtros disponibles:
- Por título (V 0.1)
- Por autor (V 0.1)
- Por género (V 0.1)
- Por año de finalización (V 0.1)
- Por mes y año de finalización (V 0.1)

- Funcionalidad de valoración con estrellas (V.02)

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

- Registro y gestión de cada tipo de media con sus atributos propio
- Posible exportación a Android en el futuro

## Cómo ejecutar el proyecto

- Levantar el backend (Spring Boot)
- Crear la base de datos media_tracker en MySQL
- Ejecutar el frontend desde el buscador (http://localhost:8080/)

## Autor - JaviDev707


