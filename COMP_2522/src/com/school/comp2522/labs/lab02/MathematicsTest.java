package com.school.comp2522.labs.lab02;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


/**
 * MathematicsTest.
 *
 * @author BCIT
 * @version 2.0
 */
public class MathematicsTest {

    /**
     * Test method for the value of PI.
     */
    @Test
    public void testPIValue() {
        assertEquals(3.14159, Mathematics.PI, 0.00001);
    }

    /**
     * Test method for the value of ONE_FOOT_TO_KILOMETER_RATIO.
     */
    @Test
    public void testFeetToKMRatioValue() {
        assertEquals(0.0003048, Mathematics.ONE_FOOT_TO_KILOMETRE_RATIO, 0.0000001);
    }

    /**
     * Tests getCircleArea method.
     */
    @Test
    public void testGetCircleArea1() {
        double area = Mathematics.getCircleArea(1.0);
        assertEquals(3.14159, area, 0.00001);
    }

    /**
     * Tests getCircleArea method.
     */
    @Test
    public void testGetCircleArea2() {
        double area = Mathematics.getCircleArea(15.0);
        assertEquals(706.85775, area, 0.00001);
    }

    /**
     * Tests getSquareArea method.
     */
    @Test
    public void testGetSquareArea1() {
        double area = Mathematics.getSquareArea(2.0);
        assertEquals(4.0, area, 0.1);
    }

    /**
     * Tests getSquareArea method.
     */
    @Test
    public void testGetSquareArea2() {
        double area = Mathematics.getSquareArea(32.0);
        assertEquals(1024.0, area, 0.1);
    }

    /**
     * Tests add method.
     */
    @Test
    public void testAdd1() {
        double sum = Mathematics.add(6.0, 7.0);
        assertEquals(13.0, sum, 0.1);
    }

    /**
     * Tests add method.
     */
    @Test
    public void testAdd2() {
        double sum = Mathematics.add(-6.0, 6.0);
        assertEquals(0.0, sum, 0.1);
    }

    /**
     * Tests multiply method.
     */
    @Test
    public void testMultiply1() {
        double product = Mathematics.multiply(1.0, 5.0);
        assertEquals(5.0, product, 0.1);
    }

    /**
     * Tests multiply method.
     */
    @Test
    public void testMultiply2() {
        double product = Mathematics.multiply(20.0, 20.0);
        assertEquals(400.0, product, 0.1);
    }

    /**
     * Tests subtract method.
     */
    @Test
    public void testSubtract1() {
        double difference = Mathematics.subtract(0.0, 20.0);
        assertEquals(-20.0, difference, 0.1);
    }

    /**
     * Tests subtract method.
     */
    @Test
    public void testSubtract2() {
        double difference = Mathematics.subtract(20.0, 20.0);
        assertEquals(0.0, difference, 0.1);
    }

    /**
     * Tests divide method.
     */
    @Test
    public void testDivide1() {
        double division = Mathematics.divide(20, 10);
        assertEquals(2.0, division, 0.00001);
    }

    /**
     * Tests divide method.
     */
    @Test
    public void testDivide2() {
        double division = Mathematics.divide(20, 0);
        assertEquals(0.0, division, 0.00001);
    }

    /**
     * Tests absolute value method.
     */
    @Test
    public void testAbsolute1() {
        int abs = Mathematics.absolute(1);
        assertEquals(1, abs);
    }

    /**
     * Tests absolute value method.
     */
    @Test
    public void testAbsolute2() {
        int abs = Mathematics.absolute(-5000);
        assertEquals(5000, abs);
    }

    /**
     * Tests the random number generator method.
     */
    @Test
    public void testRandomNumberBetweenTenAndTwentyButNotFifteen() {
        HashSet<Integer> values = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            int number = Mathematics.getRandomNumberBetweenTenAndTwentyButNotFifteen();

            assertFalse(number < 10);
            assertFalse(number > 20);
            assertNotEquals(15, number);
            values.add(number);
        }

        assertEquals(10, values.size());
    }

    /**
     * Tests the method that converts feet to kilometres.
     */
    @Test
    public void testFootToKM1() {
        double km = Mathematics.convertFeetToKilometres(1.0);
        assertEquals(0.0003048, km, 0.1);
    }

    /**
     * Tests the method that converts feet to kilometres.
     */
    @Test
    public void testFootToKM2() {
        double km = Mathematics.convertFeetToKilometres(892.0);
        assertEquals(0.272186, km, 0.1);
    }

    /**
     * Tests the method that sums the positive integers
     * between 0 and the specified value.
     */
    @Test
    public void testSumOfInts() {
        assertEquals(0, Mathematics.sumOfInts(0));
    }

    /**
     * Tests the method that sums the positive integers
     * between 0 and the specified value.
     */
    @Test
    public void testSumOfInts2() {
        assertEquals(55, Mathematics.sumOfInts(10));
    }

    /**
     * Tests the method that sums the positive integers
     * between 0 and the specified value.
     */
    @Test
    public void testSumOfInts3() {
        assertEquals(1225, Mathematics.sumOfInts(49));
    }

    /**
     * Tests the method that sums the positive integers
     * between 0 and the specified value.
     */
    @Test
    public void testSumOfInts4() {
        assertEquals(0, Mathematics.sumOfInts(-49));
    }

    /**
     * Tests the method that returns true if a specified
     * number is positive, else false.
     */
    @Test
    public void testIsPositive() {
        assertTrue(Mathematics.isPositive(2));
    }

    /**
     * Tests the method that returns true if a specified
     * number is positive, else false.
     */
    @Test
    public void testIsPositive2() {
        assertFalse(Mathematics.isPositive(-2));
    }

    /**
     * Tests the method that returns true if a specified
     * number is positive, else false.
     */
    @Test
    public void testIsPositive3() {
        assertFalse(Mathematics.isPositive(0));
    }

    /**
     * Tests the method that returns true if a specified
     * number is even, else false.
     */
    @Test
    public void testIsEven() {
        assertTrue(Mathematics.isEven(0));
    }

    /**
     * Tests the method that returns true if a specified
     * number is even, else false.
     */
    @Test
    public void testIsEven2() {
        assertFalse(Mathematics.isEven(1));
    }

    /**
     * Tests the method that returns true if a specified
     * number is even, else false.
     */
    @Test
    public void testIsEven3() {
        assertTrue(Mathematics.isEven(2));
    }

    /**
     * Tests the method that returns the sum of the even
     * numbers between 0 and the specified value inclusive.
     */
    @Test
    public void testSumOfEvens() {
        assertEquals(0, Mathematics.sumOfEvens(0));
    }

    /**
     * Tests the method that returns the sum of the even
     * numbers between 0 and the specified value inclusive.
     */
    @Test
    public void testSumOfEvens2() {
        assertEquals(30, Mathematics.sumOfEvens(10));
    }

    /**
     * Tests the method that returns the sum of the even
     * numbers between 0 and the specified value inclusive.
     */
    @Test
    public void testSumOfEvens3() {
        assertEquals(-30, Mathematics.sumOfEvens(-10));
    }

    /**
     * Tests the method that returns the sum of the numbers
     * between 0 and the first parameter that are divisible
     * by the second parameter.
     */
    @Test
    public void testSumOfProducts() {
        assertEquals(30, Mathematics.sumOfProducts(12, 3)); // 3 + 6 + 9
    }

    /**
     * Tests the method that returns the sum of the numbers
     * between 0 and the first parameter that are divisible
     * by the second parameter.
     */
    @Test
    public void testSumOfProducts2() {
        assertEquals(-30, Mathematics.sumOfProducts(-12, 3)); // -3 + -6 + -9
    }

    /**
     * Tests the method that returns the sum of the numbers
     * between 0 and the first parameter that are divisible
     * by the second parameter.
     */
    @Test
    public void testSumOfProducts3() {
        assertEquals(180, Mathematics.sumOfProducts(40, 5)); // 5 + 10 + ... + 40
    }

}
