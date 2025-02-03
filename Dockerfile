# Usa una imagen de Java 17 como base
FROM openjdk:17-jdk-slim

# Configura el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR al contenedor
COPY build/libs/busqueda.jar app.jar

# Establece la variable de entorno para que Spring Boot use SQLite
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/app/database

# Expone el puerto en el que corre tu aplicación
EXPOSE 8090

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
