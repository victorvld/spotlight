FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/spotlight-time

COPY pom.xml /app
COPY ./spotlight-time/pom.xml .
COPY ./spotlight-time/.mvn ./.mvn
COPY ./spotlight-time/mvnw .

RUN ./mvnw dependency:go-offline

COPY ./spotlight-time/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/spotlight-time/target/spotlight-time-1.0.0-SNAPSHOT.jar .

ARG PORT_APP=9000
ENV PORT $PORT_APP
EXPOSE $PORT

CMD ["java", "-jar", "spotlight-time-1.0.0-SNAPSHOT.jar"]
