FROM eclipse-temurin:20

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=prod

COPY target/gestao-vagas-api.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]




