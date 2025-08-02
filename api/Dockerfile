FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY api/pom.xml api/

# Copy the source code
COPY api/src api/src

# Make mvnw executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw -f api/pom.xml clean package -DskipTests

# Create a new stage for the runtime
FROM openjdk:17-jre-slim

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=0 /app/api/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Set the entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"] 