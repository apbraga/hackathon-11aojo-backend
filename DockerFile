# Use a imagem do OpenJDK com JDK 17 para executar a aplicação
FROM openjdk:17

# Copie o arquivo JAR construído para um diretório
COPY /build/libs/hackaton-0.0.1-SNAPSHOT.jar /app/hackaton-0.0.1-SNAPSHOT.jar

# Execute a aplicação
ENTRYPOINT ["java","-jar","/app/hackaton-0.0.1-SNAPSHOT.jar"]
