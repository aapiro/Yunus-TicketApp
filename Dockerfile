# Etapa 1: Construcción del JAR con Maven y Java 8
FROM maven:3.8.5-openjdk-8 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen liviana con Java 8 para ejecutar la app
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
