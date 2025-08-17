## Media Tracker App - Seguimiento de Libros, Series, Películas y Videojuegos (Proyecto en desarrollo)

Gestor personal donde podrás registrar, listar y filtrar el contenido multimedia que consumas (libros, películas, series y videojuegos).

## Tecnologías utilizadas

Backend:
- Java 21
- Spring Boot
- Spring Web (REST API)
- Spring Data JPA
- MySQL
- Docker

Frontend:
- HTML5 / CSS3
- Bootstrap 5
- JavaScript puro (fetch API)

## Funcionalidades (V 1.0)

📚 Módulo Libros (V 0.1)
🎮 Módulo Videojuegos (V 0.3)
🎬 Módulo Películas (V 1.0)
📺 Módulo Series (V1.0)

CRUD completo:
- Crear  (V 0.1)
- Ver listado (V 0.1)
- Editar  (V 0.1)
- Eliminar  (V 0.1)

Filtros disponibles:
- Por título (V 0.1)
- Por autor/plataforma (V 0.1)
- Por género (V 0.1)
- Por año de finalización (V 0.1)
- Por mes y año de finalización (V 0.1)

- Funcionalidad de valoración con estrellas (V 0.2)

## Estructura actual

- LibroController → Endpoints REST
- LibroService → Lógica de negocio
- LibroRepository → Acceso a base de datos
- Libro → Entidad
- LibroDTO → Actualización parcial

## Vista actual (Frontend)

- Barra de filtros combinada encima de la tabla
- Tabla con lista de libros, peliculas, series y videojuegos
- Botones de editar, borrar y agregar integrados en la tabla
- Interacción vía JavaScript puro (fetch)

## Próximos pasos

- Posible exportación a Android en el futuro

## Cómo ejecutar el proyecto

### Ejecutar en local
Clonar el proyecto y renombrar \src\main\resources\example-local.yml por application-local.yml e introducir tus credenciales. \
Ejecutar el proyecto (mvn spring-boot:run).

### Ejecutar con Docker 
Clonar el proyecto y renombrar el archivo .env.example por .env e introducir tus credenciales. \
Constuir y levantar los contenedores (docker-compose up --build). \

Acceder a http://localhost:8080/ para usar la aplicación.

## Historial de actualizaciones

- Mejora en el sistema de edicion de estrellas (V 1.1)
- Credenciales de la Base de datos locales mediante application-local.yml (V 1.1)
- Centralizado de seguridad con CorsConfig (V 1.2)
- Configuración lista para ejecutar la app y su base de datos MySQL usando Docker y Docker Compose (V 1.3)

## Desarrollado por JaviDev707


