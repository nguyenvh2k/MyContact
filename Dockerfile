FROM openjdk:11
FROM maven:3.8-jdk-11 as maven_build
COPY pom.xml pom.xml
COPY src src
RUN mvn clean package
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

