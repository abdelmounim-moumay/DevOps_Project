FROM eclipse-temurin:21-jdk-alpine as builder

WORKDIR /usr/src/app

COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

Expose 8080

COPY --from=builder /usr/src/app/target/java-maven-app-1.0-SNAPSHOT.jar /usr/app/


ENTRYPOINT ["java","-jar","java-maven-app-1.0-SNAPSHOT.jar"]
