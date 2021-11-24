
FROM openjdk:11.0.12-slim-bullseye
EXPOSE 8080
RUN apt-get update && apt-get install -y netcat;

# Criado uma variavel JAR_FILE
ARG JAR_FILE_GS_VENDAS=target/*.jar
#Copiando
COPY ${JAR_FILE_GS_VENDAS} /app.jar
COPY mysql_wait_for.sh /mysql_wait_for.sh
RUN chmod 777 /mysql_wait_for.sh
#ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=test", "/app.jar"]
# vai ser executado pelo docker-compose após o mysql - ENTRYPOINT ["java","-jar",   "/app.jar"]

