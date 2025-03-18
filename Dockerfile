# Etapa 1: Construcción de la aplicación
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copiar los archivos de configuración de Maven
COPY pom.xml .

# Descargar dependencias para optimizar la caché
RUN mvn dependency:go-offline

# Copiar el código fuente y construir la aplicación
COPY src ./src
RUN mvn package -DskipTests

# Etapa 2: Ejecución de la aplicación
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/target/security-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto donde corre la aplicación
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
