FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=builder /build/calculator-rest/target/calculator-rest-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
