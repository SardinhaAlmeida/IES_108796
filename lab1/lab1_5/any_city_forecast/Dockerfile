# Use an official Maven runtime as a parent image
FROM maven:3.8.4-openjdk-17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project's pom.xml file to the container
COPY pom.xml .

# Download and cache the Maven dependencies
RUN mvn dependency:go-offline

# Copy the project source code to the container
COPY src ./src

# Use a lightweight Alpine-based OpenJDK image for the final runtime image
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder image to the runtime image
COPY --from=builder /app/target/any_city_forecast-1.0-SNAPSHOT.jar ./app.jar

# Expose the port your application listens on (if necessary)
# EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]