FROM eclipse-temurin:21-jre-alpine

WORKDIR /usr/app

COPY target/GestionCinema-0.0.1-SNAPSHOT.jar .

EXPOSE 8080


ENTRYPOINT ["java", "-jar", "GestionCinema-0.0.1-SNAPSHOT.jar"]
