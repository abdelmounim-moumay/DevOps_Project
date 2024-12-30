FROM eclipse-temurin:21-jdk-alpine as builder

WORKDIR /usr/src/app

# Copier les fichiers du projet dans le conteneur
COPY . .

# Ajouter les permissions d'exécution à mvnw
RUN chmod +x mvnw

# Construire l'application avec Maven
RUN ./mvnw clean package -DskipTests

# Utiliser une image plus légère pour exécuter l'application
FROM eclipse-temurin:21-jre-alpine

# Exposer le port 8080 pour l'application
EXPOSE 8080

# Copier le jar généré vers l'image finale
COPY --from=builder /usr/src/app/target/java-maven-app-1.0-SNAPSHOT.jar /usr/app/

# Lancer l'application
ENTRYPOINT ["java", "-jar", "/usr/app/java-maven-app-1.0-SNAPSHOT.jar"]
