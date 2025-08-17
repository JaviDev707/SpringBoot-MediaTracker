# ---- Compilación ----
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Copio el pom.xml y las dependencias primero para aprovechar cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copio el código fuente
COPY src ./src
# Compilo el proyecto y genero el jar
RUN mvn -DskipTests package



# ---- Ejecución ----
FROM eclipse-temurin:21-jdk
WORKDIR /app
# Copio el jar generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar
# Puerto de la aplicación
EXPOSE 8080
# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]

