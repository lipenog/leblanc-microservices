# Stage 1: Build the Java application using Maven and OpenJDK
FROM openjdk:21-jdk-slim AS build

WORKDIR /app
COPY pom.xml ./
COPY src ./src
COPY mvnw ./
COPY .mvn ./.mvn

COPY main.py /app/main.py
COPY Download.mp4 /app/Download.mp4

RUN chmod +x mvnw
RUN sed -i 's/\r$//' mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y ffmpeg && \
    apt-get install -y python3 python3-pip python3-venv

# Create a Python virtual environment and install Whisper
RUN python3 -m venv /app/venv && \
    /app/venv/bin/pip install --no-cache-dir --upgrade pip && \
    /app/venv/bin/pip install --no-cache-dir -U openai-whisper

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

COPY main.py /app/main.py
COPY Download.mp4 /app/Download.mp4

EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]