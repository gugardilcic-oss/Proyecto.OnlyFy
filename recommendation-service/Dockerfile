# 1. Imagen base ligera de Java 21
FROM eclipse-temurin:21-jre-jammy

# 2. Directorio de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiar el archivo .jar de la carpeta target hacia el contenedor
COPY target/recommendation-service-0.0.1-SNAPSHOT.jar app.jar

# 4. Exponer el puerto configurado en tus propiedades (8082)
EXPOSE 8082

# 5. Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]