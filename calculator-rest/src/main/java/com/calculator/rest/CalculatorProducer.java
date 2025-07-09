package com.calculator.rest;

import com.calculator.core.KafkaMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CalculatorProducer {

    private static final String TOPIC = "calculator-requests";

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public CalculatorProducer(KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRequest(CalculatorRequest request) {
        KafkaMessage message = new KafkaMessage(
                request.getRequestId(),
                request.getOperation(),
                request.getOperandA(),
                request.getOperandB()
        );
        kafkaTemplate.send(TOPIC, message);
    }

}
