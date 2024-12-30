FROM eclipse-temurin:21-jdk-alpine as builder

WORKDIR /usr/src/app

COPY . .

# Add execute permissions to mvnw
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

EXPOSE 8080

COPY --from=builder /usr/src/app/target/java-maven-app-1.0-SNAPSHOT.jar /usr/app/

ENTRYPOINT ["java", "-jar", "/usr/app/java-maven-app-1.0-SNAPSHOT.jar"]
