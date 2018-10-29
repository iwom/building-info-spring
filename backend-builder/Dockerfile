FROM maven:3.5-jdk-8-alpine AS build
COPY /src /usr/src/spring/src
COPY pom.xml /usr/src/spring
COPY Dockerfile /usr/src/spring
RUN mvn -f /usr/src/spring/pom.xml clean install

FROM openjdk:8-jre-alpine
COPY --from=build /usr/src/spring/target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]