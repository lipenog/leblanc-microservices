# Stage 1: Build the Java application using Maven and OpenJDK
FROM openjdk:21-jdk-slim AS build

WORKDIR /app
COPY pom.xml ./
COPY src ./src
COPY mvnw ./
COPY .mvn ./.mvn

RUN chmod +x mvnw
RUN sed -i 's/\r$//' mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/app.jar"]