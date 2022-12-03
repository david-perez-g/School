package com.school.comp2522.assignments.a3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link RPNCalculator}
 *
 * @author david-perez-g
 * @version 1.0
 */
class RPNCalculatorTest {
    final RPNCalculator calculator = new RPNCalculator(100);

    double calc(String formula) {
        return calculator.processFormula(formula);
    }

    @Test
    void testSums() {
        assertEquals(0, calc("0 0 +"));
        assertEquals(1, calc("1 0 +"));
        assertEquals(2, calc("1 1 +"));
        assertEquals(25, calc("12 13 +"));
        assertEquals(1125, calc("25 100 1000 + +"));
        assertEquals(100, calc("25 20 20 35 + + +"));
        assertEquals(250000, calc("100000 100000 50000 -50000 50000 + + + +"));
        assertEquals(125, calc("62.5 62.5 +"));
        assertEquals(0, calc("-134.1231 134.1231 +"));
    }

    @Test
    void testSubtractions() {
        assertEquals(0, calc("0 0 -"));
        assertEquals(1, calc("1 0 -"));
        assertEquals(0, calc("1 1 -"));
        assertEquals(-1, calc("12 13 -"));
        assertEquals(925, calc("25 100 1000 - -"));
        assertEquals(-10, calc("25 20 20 35 - - -"));
        assertEquals(150000, calc("100000 100000 50000 -50000 50000 - - - -"));
        assertEquals(0, calc("62.5 62.5 -"));
        assertEquals(0, calc("-134.1231 -134.1231 -"));
    }

    @Test
    void testMultiplications() {
        assertEquals(0, calc("0 0 *"));
        assertEquals(0, calc("1 0 *"));
        assertEquals(0, calc("123 12332 22 31 165 0 * * * * *"));
        assertEquals(10, calc("2 5 *"));
        assertEquals(125, calc("5 5 5 * *"));
        assertEquals(10000, calc("10 10 10 10 * * *"));
        assertEquals(-10000, calc("10 10 10 -10 * * *"));
        assertEquals(5550.25, calc("-74.5 -74.5 *"));
    }

    @Test
    void testDivisions() {
        assertEquals(0, calc("0 1 /"));
        assertEquals(0.5, calc("1 2 /"));
        assertEquals(50, calc("10000 200 /"));
        assertEquals(-50, calc("-10000 200 /"));
        assertEquals(607500, calc("20250000 50 45 30 / / /"));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ZeroDivisionException.class, () -> calc("1 0 /"));
        assertThrows(ZeroDivisionException.class, () -> calc("1 1 - 0 /"));
        assertThrows(ZeroDivisionException.class, () -> calc("25 0 25 / /"));
    }

    @Test
    void testCombinedExpressions() {
        assertEquals(3, calc("3 6 + 1 2 + /")); // (3 + 6) / (1 + 2)
        assertEquals(0, calc("14 1 + 15 -"));
        assertEquals(0, calc("25 5 30 + + 60 -"));
        assertEquals(1, calc("100 10 * 1000 /"));
        // ((100 * 10) / 1000) - ((25 + 25) - 50)
        assertEquals(1, calc("100 10 * 1000 / 25 25 + 50 - -"));
    }

    @Test
    void testCalculatorStackOutOfSpace() {
        RPNCalculator rpnCalculator = new RPNCalculator(5);

        assertDoesNotThrow(() -> rpnCalculator.processFormula("1 2 3 4 5 + + + +"));

        assertThrows(StackOverflowException.class, () ->
                rpnCalculator.processFormula("1 2 3 4 5 6 + + + + +"));

    }

    @Test
    void testUnknownSymbolsInFormula() {
        assertThrows(InvalidOperationTypeException.class, () -> calc("a"));
        assertThrows(InvalidOperationTypeException.class, () -> calc("1 ["));
        assertThrows(InvalidOperationTypeException.class, () -> calc("1 12 ~"));
        assertThrows(InvalidOperationTypeException.class, () -> calc("12 xa +"));
    }

    @Test
    void testMalformedFormulas() {
        assertThrows(StackUnderflowException.class, () -> calc("1 +"));
        assertThrows(StackUnderflowException.class, () -> calc("1 1 + +"));
        assertThrows(StackUnderflowException.class, () -> calc("1 1 1 + + +"));
    }
}