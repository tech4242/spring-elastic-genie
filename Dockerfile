FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY build/libs/spring-elastic-genie-0.0.1.jar ./spring-elastic-genie-0.0.1.jar
COPY entrypoint.sh ./entrypoint.sh
