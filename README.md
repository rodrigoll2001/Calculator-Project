# Calculator Project with Kafka

## Overview

This project is a REST API for basic math operations (addition, subtraction, multiplication, division), divided into two modules:

- **calculator-core**: module responsible for processing calculations via Kafka.
- **calculator-rest**: REST API that receives requests, sends messages to the core, and returns responses.

Communication between modules is done via Kafka (using ReplyingKafkaTemplate), and the project includes error handling, structured logging with SLF4J/Logback, and a health check endpoint.

## How to Build and Run

### 1. Build the project

From the project's root directory, run:

```bash
  mvn clean install
```

### 2. Run with Docker Compose

At the project root, run:
```bash
   docker-compose up --build
```

This will start:

* Zookeeper

* Kafka

* The calculator-core and calculator-rest containers


### 3. Test the API

Make a POST request to:

```bash
   http://localhost:8080/api/calculate
```

With JSON body, for example:

```json
   {
      "operation": "sum",
      "operandA": 5,
      "operandB": 10
   }
```

Operations:

    sum
    subtraction
    multiplication
    division
    

Test example:

```bash
  curl -X POST http://localhost:8080/api/calculate -H "Content-Type: application/json" -d "{\"operation\":\"division\",\"operandA\":2,\"operandB\":2}"
```

### 4. Health Check

Endpoint to verify if the API and Kafka are running:

```bash
  GET http://localhost:8080/api/health
```
Returns 200 OK if everything is up.

### Logs
Logs are generated inside the container at `/app/logs/app.log` and also printed in the Docker console.