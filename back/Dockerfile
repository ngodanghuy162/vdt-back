# Build stage
FROM maven:3.8.3-openjdk-17 as build

WORKDIR /app

COPY . .

RUN mvn install -DskipTests=true

# Run stage
# Copy the built JAR file
FROM openjdk:17-jdk-slim

WORKDIR /run

COPY --from=build /app/target/back-0.0.1-SNAPSHOT.jar /run/back-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080

# Use environment variables for database configuration


# Run the JAR file with environment variables
ENTRYPOINT ["java", "-jar", "/run/back-0.0.1-SNAPSHOT.jar"]
