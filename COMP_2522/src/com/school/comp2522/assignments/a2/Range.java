package com.school.comp2522.assignments.a2;

import com.school.comp2522.labs.lab02.Mathematics;

/**
 * A range of numbers.
 *
 * @author david-perez-g
 * @version 1.0
 */
public class Range<T extends Number> {
    private final T innerBound;
    private final T upperBound;

    private Range(T innerBound, T upperBound) {
        this.innerBound = innerBound;
        this.upperBound = upperBound;
    }

    public static <T extends Number> Range<T> of(T innerBound, T upperBound) throws IllegalArgumentException {
        if (innerBound.doubleValue() >= upperBound.doubleValue()) {
            throw new IllegalArgumentException("Min must be less than max");
        }

        return new Range<>(innerBound, upperBound);
    }

    /**
     * Returns a random X double in the range of innerBound <= X < upperBound
     */
    public double getRandomDoubleBetween() {
        return Mathematics.getRandomDoubleBetween(innerBound.doubleValue(), upperBound.doubleValue());
    }

    /**
     * Returns a random X double in the range of innerBound <= X < upperBound
     */
    public int getRandomIntegerBetween() {
        return Mathematics.getRandomIntBetween(innerBound.intValue(), upperBound.intValue());
    }
}
