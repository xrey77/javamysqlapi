FROM openjdk:8-jdk-alpine
LABEL "author"="Reynald Marquez-Gragasin"
LABEL "company"="World Taekwondo Federation"
ADD target/javamysqlapi.jar javamysqlapi.jar
EXPOSE 8089
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} javamysqlapi.jar
ENTRYPOINT ["java","-jar","/javamysqlapi.jar"]

 