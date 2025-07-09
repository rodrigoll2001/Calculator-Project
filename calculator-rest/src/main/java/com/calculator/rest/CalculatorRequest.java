package com.calculator.rest;

import java.math.BigDecimal;
import java.util.UUID;

public class CalculatorRequest {

    private UUID requestId;
    private String operation;
    private BigDecimal operandA;
    private BigDecimal operandB;

    public CalculatorRequest() {}

    public CalculatorRequest(UUID requestId, String operation, BigDecimal operandA, BigDecimal operandB) {
        this.requestId = requestId;
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    // Getters & Setters
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
}
