FROM maven:3.9.0-eclipse-temurin-19 as build

WORKDIR /spring

COPY pom.xml ./
COPY src ./src

RUN mvn -e -X clean package


FROM openjdk:19-alpine
COPY --from=build /spring/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]