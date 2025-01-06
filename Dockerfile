FROM eclipse-temurin:21-jre-alpine

WORKDIR /usr/app

COPY target/GestionCinema-*.jar .

EXPOSE 8080

CMD java -jar GestionCinema-*.jar
