FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR usr/src/springboot
COPY . ./
RUN mvn install
RUN mvn clean package
#
# Package stage
#
FROM openjdk:11-jre-slim
ARG JAR_NAME="MyConcat-0.0.1.jar"
WORKDIR /usr/src/springboot
EXPOSE 8080
COPY --from=build /usr/src/springboot/target/${JAR_NAME}.jar ./springboot.jar
CMD ["java","-jar", "./springboot.jar"]

