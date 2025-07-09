package com.calculator.core;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.messaging.handler.annotation.Header;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Service
public class CalculatorConsumer {

    private static final String REQUEST_TOPIC = "calculator-requests";

    private static final Logger log = LoggerFactory.getLogger(CalculatorConsumer.class);

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;
    private final CalculatorService calculatorService;

    public CalculatorConsumer(KafkaTemplate<String, KafkaMessage> kafkaTemplate,
                              CalculatorService calculatorService) {
        this.kafkaTemplate = kafkaTemplate;
        this.calculatorService = calculatorService;
    }

    @KafkaListener(topics = REQUEST_TOPIC)
    public void listen(KafkaMessage request,
                       @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId,
                       @Header(KafkaHeaders.REPLY_TOPIC) byte[] replyTopic) {
        MDC.put("requestId", request.getRequestId().toString());
        log.info("Received Kafka message: {} {} {}", request.getOperandA(), request.getOperation(), request.getOperandB());

        try {
            BigDecimal result;

            switch (request.getOperation()) {
                case "sum":
                    result = calculatorService.sum(request.getOperandA(), request.getOperandB());
                    break;
                case "subtraction":
                    result = calculatorService.subtract(request.getOperandA(), request.getOperandB());
                    break;
                case "multiplication":
                    result = calculatorService.multiply(request.getOperandA(), request.getOperandB());
                    break;
                case "division":
                    result = calculatorService.divide(request.getOperandA(), request.getOperandB());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operation: " + request.getOperation());
            }

            request.setResult(result);

        } catch (Exception e) {
            log.error("Error while processing Kafka message: {}", e.getMessage());
            request.setErrorMessage(e.getMessage());
        } finally {
            Message<KafkaMessage> replyMessage = MessageBuilder
                    .withPayload(request)
                    .setHeader(KafkaHeaders.TOPIC, replyTopic)
                    .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                    .build();

            kafkaTemplate.send(replyMessage);
            log.info("Replied message (success or error) sent to topic.");
            MDC.clear();
        }
    }
}
