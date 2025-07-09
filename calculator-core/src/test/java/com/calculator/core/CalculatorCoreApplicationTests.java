package com.calculator.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CalculatorCoreApplicationTests {

	private final CalculatorService calculatorService = new CalculatorService();

	@Test
	public void testSum() {
		BigDecimal result = calculatorService.sum(BigDecimal.valueOf(2), BigDecimal.valueOf(3));
		assertEquals(BigDecimal.valueOf(5), result, "Sum of 2 and 3 should be 5");
		System.out.println("Meu nobre, o resultado foi: " + result);
	}

	@Test
	public void testSubtraction() {
		BigDecimal result = calculatorService.subtract(BigDecimal.valueOf(5), BigDecimal.valueOf(3));
		assertEquals(BigDecimal.valueOf(2), result, "Subtraction of 5 and 3 should be 2");
	}

	@Test
	public void testMultiplication() {
		BigDecimal result = calculatorService.multiply(BigDecimal.valueOf(4), BigDecimal.valueOf(3));
		assertEquals(BigDecimal.valueOf(12), result, "Multiplication of 4 and 3 should be 12");
	}

	@Test
	public void testDivision() {
		BigDecimal result = calculatorService.divide(BigDecimal.valueOf(10), BigDecimal.valueOf(2));
		assertEquals(BigDecimal.valueOf(5), result, "Division of 10 by 2 should be 5");
	}

	@Test
	public void testDivisionByZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			calculatorService.divide(BigDecimal.valueOf(1), BigDecimal.ZERO);
		});
	}

}
