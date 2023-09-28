# Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/any_city_forecast-1.0-SNAPSHOT.jar /app/any_city_forecast-1.0-SNAPSHOT.jar

# Specify the default command to run when the container starts
CMD ["java", "-jar", "any_city_forecast-1.0-SNAPSHOT.jar"]