# Use a lightweight Java image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper files and project
COPY . .

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Expose the port
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "target/*.jar"]
