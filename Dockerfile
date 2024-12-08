# Stage 1: Build
FROM ubuntu:latest AS build

# Atualiza o sistema e instala o OpenJDK 21 e Maven
RUN apt-get update && apt-get install -y openjdk-21-jdk maven

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e baixa dependências
COPY oceanmap/pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY oceanmap/. .

# Compila o projeto e cria o JAR
RUN mvn clean install

# Stage 2: Run
FROM openjdk:21-jdk-slim

# Expondo a porta 8080
EXPOSE 8080

# Copia o JAR gerado para o contêiner final
COPY --from=build /app/target/oceanmap-0.0.1-SNAPSHOT.jar /app/app.jar

# Define o comando de entrada para iniciar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
