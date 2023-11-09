FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/spotlight-time-flyway

COPY pom.xml /app
COPY ./spotlight-time-flyway/pom.xml .
COPY ./spotlight-time-flyway/.mvn ./.mvn
COPY ./spotlight-time-flyway/mvnw .

RUN ./mvnw dependency:go-offline

COPY ./spotlight-time-flyway/src ./src

RUN ./mvnw package spring-boot:repackage -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/spotlight-time-flyway/target/spotlight-time-flyway-1.0.0-SNAPSHOT.jar .

CMD ["java", "-jar", "spotlight-time-flyway-1.0.0-SNAPSHOT.jar"]
