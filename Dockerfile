FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080

COPY --from=build /target/gestao-vagas-api.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]