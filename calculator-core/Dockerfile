FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=builder /build/calculator-core/target/calculator-core-1.0.0-exec.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
