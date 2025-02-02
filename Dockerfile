# Usa una imagen de Java 17 como base
FROM eclipse-temurin:17-jdk

# Configura el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR al contenedor
COPY build/libs/busqueda.jar app.jar

# Copia la base de datos SQLite desde la raíz del proyecto al contenedor
COPY database.db /app/database.db

# Establece la variable de entorno para que Spring Boot use SQLite
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/app/database

# Expone el puerto en el que corre tu aplicación
EXPOSE 8090

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
