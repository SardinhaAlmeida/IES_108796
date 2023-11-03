FROM maven:3.8.5-openjdk-17
WORKDIR /java
COPY . .

RUN mvn install -DskipTests

CMD ["java", "-jar", "target/java-0.0.1-SNAPSHOT.jar"]