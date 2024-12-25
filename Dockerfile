FROM eclipse-temurin:21-jdk-alpine


COPY ./target/java-maven-app-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /app


Expose 8080

ENTRYPOINT ["java","-jar","java-maven-app-1.0-SNAPSHOT.jar"]
