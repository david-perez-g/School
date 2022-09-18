package com.school.comp2522.labs.lab01;

import java.util.Objects;
import java.util.Random;

/**
 * @author david-perez-g
 * @version 1.0
 */
public class Hare {
    private int position = 0;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Move the hare.
     *
     * @return The hare position after moving.
     */
    public int move() {
        // i. 20% of the time the Hare sleeps and doesn’t move.
        // ii. 10% of the time the Hare take a big hop and moves forward 9 units.
        // iii. 10% of the time the Hare suffers a big slip and moves backward 12 units.
        // iv. 30% of the time the Hare takes a small hop and moves forward 1 unit.
        // v. the rest of the time, the Hard suffers a small slip and moves backward 2 units.

        final byte BIG_HOP = 9;
        final byte BIG_SLIP = -12;
        final byte SMALL_HOP = 1;
        final byte SMALL_SLIP = -2;
        final int randomNumber = (new Random()).nextInt(11);
        int newPosition = getPosition();

        if (randomNumber <= 2) {
            // case i.
            return newPosition;

        } else if (randomNumber <= 3) {
            // case ii.
            newPosition += BIG_HOP;

        } else if (randomNumber <= 4) {
            // case iii.
            newPosition += BIG_SLIP;

        } else if (randomNumber <= 7) {
            // case iv.
            newPosition += SMALL_HOP;

        } else {
            // case v.
            newPosition += SMALL_SLIP;
        }

        if (newPosition < 0) {
            newPosition = 0;
        }

        setPosition(newPosition);
        return newPosition;
    }

    @Override
    public String toString() {
        return "Hare{" +
                "position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hare hare = (Hare) o;
        return getPosition() == hare.getPosition();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }
}
