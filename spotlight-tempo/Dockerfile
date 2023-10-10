FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/spotlight-tempo

COPY ./pom.xml /app
COPY ./spotlight-tempo/pom.xml .
COPY ./spotlight-tempo/.mvn ./.mvn
COPY ./spotlight-tempo/mvnw .

RUN ./mvnw dependency:go-offline

COPY ./spotlight-tempo/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/spotlight-tempo/target/spotlight-tempo-1.0.0-SNAPSHOT.jar .

EXPOSE 8001

CMD ["java", "-jar", "spotlight-tempo-1.0.0-SNAPSHOT.jar"]
