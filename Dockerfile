FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Build the Spring Boot app
COPY . .
RUN ./mvnw clean package -DskipTests \
 && cp target/*SNAPSHOT.jar app.jar   # <‑‑ copy to a known name

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
