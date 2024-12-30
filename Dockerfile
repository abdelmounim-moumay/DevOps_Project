FROM eclipse-temurin:21-jdk-alpine as builder

WORKDIR /usr/src/app

COPY . .

RUN chmod +x mvnw

# Verify Maven build output

RUN ./mvnw clean package -DskipTests && ls -la target/


FROM eclipse-temurin:21-jre-alpine

WORKDIR /usr/app

COPY --from=builder /usr/src/app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
