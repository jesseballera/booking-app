#FROM eclipse-temurin:17-jdk-ubi9-minimal
FROM amazoncorretto:17-alpine

WORKDIR /app
COPY app-iam/target/*.jar app.jar
EXPOSE 8050

ENTRYPOINT ["java","-jar","app.jar"]