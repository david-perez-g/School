package com.school.comp2522.labs.lab02;

import java.util.Random;

/**
 * @author david-perez-g
 * @version 1.0
 */
public class Mathematics {

    public final static double PI = 3.14159;
    public final static double ONE_FOOT_TO_KILOMETRE_RATIO = 0.0003048;

    public static double getCircleArea(double radius) {
        return PI * radius * radius;
    }

    public static double getSquareArea(double sideSize) {
        return sideSize * sideSize;
    }

    public static double add(double x, double y) {
        return x + y;
    }

    public static double multiply(double x, double y) {
        return x * y;
    }

    public static double divide(double x, double y) {
        if (y == 0) {
            return y;
        }
        return x / y;
    }

    public static double subtract(double x, double y) {
        return x - y;
    }

    public static int absolute(int value) {
        if (value >= 0) {
            return value;
        }

        return value * -1;
    }

    /**
     * Returns a random X number in the range innerBound <= X <= upperBound
     */
    public static int getRandomIntBetween(int innerBound, int upperBound) {
        if (upperBound <= innerBound) {
            throw new IllegalArgumentException("upperBound must be greater than innerBound");
        }
        int zeroUntilMax = (new Random()).nextInt(upperBound + 1);

        if (zeroUntilMax <= innerBound) {
            return innerBound + zeroUntilMax;
        }

        return zeroUntilMax;
    }

    public static int getRandomIntBetween(int innerBound, int upperBound, Random randomInstance) {
        if (upperBound <= innerBound) {
            throw new IllegalArgumentException("upperBound must be greater than innerBound");
        }
        int zeroUntilMax = randomInstance.nextInt(upperBound + 1);

        if (zeroUntilMax <= innerBound) {
            return innerBound + zeroUntilMax;
        }

        return zeroUntilMax;
    }

    /**
     * Returns a random X double in the range innerBound <= X < upperBound
     */
    public static double getRandomDoubleBetween(double innerBound, double upperBound) {
        if (upperBound <= innerBound) {
            throw new IllegalArgumentException("upperBound must be greater than innerBound");
        }

        return innerBound + (upperBound - innerBound) * (new Random()).nextDouble();
    }

    public static int getRandomNumberBetweenTenAndTwentyButNotFifteen() {
        int number;
        do {
            number = getRandomIntBetween(10, 20);
        } while (number == 15);
        return number;
    }

    public static double convertFeetToKilometres(double foots) {
        return foots / 3280.8;
    }

    public static boolean isPositive(int number) {
        return number > 0;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Sums the products of `divisableBy` until `bound`
     * Examples:
     * >>> sumOfProducts(11, 1)
     * returns: 1 + 2 + 3 + ... + 9 + 10 + 11
     * >>> sumOfProducts(14, 2)
     * returns: 2 + 4 + 6 + ... + 10 + 12 + 14
     * >>> sumOfProducts(27, 3)
     * returns: 3 + 6 + 9 + ... + 21 + 24 + 27
     * >>> sumOfProducts(-12, 2)
     * returns: -2 + -4 + -6 + -8 + -10 + -12
     *
     * @throws IllegalArgumentException if `bound` is not divisible by `divisableBy`.
     */
    public static int sumOfProducts(int bound, int divisableBy) {
        if (divisableBy <= 0) {
            throw new IllegalArgumentException("divisableBy must be greater than 0");
        }

        if (bound % divisableBy != 0) {
            throw new IllegalArgumentException(bound + " is not divisible by " + divisableBy);
        }

        int multiplyFinalResultBy;

        if (bound < 0) {
            bound = -bound;
            multiplyFinalResultBy = -1;
        } else {
            multiplyFinalResultBy = 1;
        }

        /*
        Formula:
        bound ( bound         )
              ( _____   +  1  )
              ( divBy         )
        ________________________
                  2
         */
        return multiplyFinalResultBy * ((bound * ((bound / divisableBy) + 1)) / 2);
    }

    public static int sumOfInts(int number) {
        if (number <= 0) {
            return 0;
        }
        return sumOfProducts(number, 1);
    }

    public static int sumOfEvens(int number) {
        return sumOfProducts(number, 2);
    }
}
