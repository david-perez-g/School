package com.school.comp2522.labs.lab01;

import java.util.Objects;
import java.util.Random;


/**
 * @author david-perez-g
 * @version 1.0
 */
public class Tortoise {
    private int position = 0;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        if (position < 0) {
            throw new RuntimeException("Position must be greater than zero");
        }
        this.position = position;
    }

    /**
     * Move the Tortoise.
     *
     * @return the tortoise position after moving.
     */
    public int move() {
        // i. 50% of the time the Tortoise moves forward 3 units with a fast plod.
        // ii. 20% of the time the Tortoise slips and moves backward 6 units.
        // iii. The rest of the time, the Tortoise moves forward 1 unit with a slow plod.

        final byte FAST_PLOD = 3;
        final byte SLEEP = -6;
        final byte SLOW_PLOD = 1;
        final int randomNumber = (new Random()).nextInt(11);
        int newPosition = getPosition();

        if (randomNumber <= 5) {
            // case i.
            newPosition += FAST_PLOD;

        } else if (randomNumber <= 7) {
            // case ii.
            newPosition += SLEEP;

        } else {
            newPosition += SLOW_PLOD;
        }

        if (newPosition < 0) {
            newPosition = 0;
        }

        setPosition(newPosition);
        return newPosition;
    }

    @Override
    public String toString() {
        return "Tortoise{" +
                "position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tortoise tortoise = (Tortoise) o;
        return getPosition() == tortoise.getPosition();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }
}
