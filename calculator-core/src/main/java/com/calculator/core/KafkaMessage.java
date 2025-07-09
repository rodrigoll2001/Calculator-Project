package com.calculator.core;

import java.math.BigDecimal;
import java.util.UUID;

public class KafkaMessage {

    private UUID requestId;
    private String operation; // sum, subtract, multiply, divide
    private BigDecimal operandA;
    private BigDecimal operandB;
    private BigDecimal result;

    public KafkaMessage() {
        // Default constructor for serialization frameworks like Jackson
    }

    public KafkaMessage(UUID requestId, String operation, BigDecimal operandA, BigDecimal operandB) {
        this.requestId = requestId;
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    // Getters & setters

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getOperandA() {
        return operandA;
    }

    public void setOperandA(BigDecimal operandA) {
        this.operandA = operandA;
    }

    public BigDecimal getOperandB() {
        return operandB;
    }

    public void setOperandB(BigDecimal operandB) {
        this.operandB = operandB;
    }

    public BigDecimal getResult() {
        return result;
    }

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public void setResult(BigDecimal result) {
        this.result = result;
    }
}