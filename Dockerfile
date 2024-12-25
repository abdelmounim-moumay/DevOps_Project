FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY ./target/java-maven-app-1.0-SNAPSHOT.jar /user/app/

Expose 8080

ENTRYPOINT ["java","-jar","java-maven-app-1.0-SNAPSHOT.jar"]
