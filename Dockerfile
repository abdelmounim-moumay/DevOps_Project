FROM eclipse-temurin:21-jre-alpine

WORKDIR /usr/app

# Copier uniquement le JAR déjà buildé
COPY target/java-maven-app-1.0-SNAPSHOT.jar .

# Exposer le port de l'application
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "java-maven-app-1.0-SNAPSHOT.jar"]
