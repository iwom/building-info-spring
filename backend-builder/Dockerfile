FROM maven:3.5-jdk-8-alpine AS build
COPY /src /usr/src/spring/src
COPY pom.xml /usr/src/spring
COPY Dockerfile /usr/src/spring
RUN mvn -f /usr/src/spring/pom.xml clean javadoc:javadoc install

FROM openjdk:8-jre-alpine
RUN apk update && apk add bash
COPY --from=build /usr/src/spring/target/backend-builder-0.0.3.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]
