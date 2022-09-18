package com.school.comp2522.labs.lab01;

/**
 * @param clockTicks duration of the race in ticks.
 * @param result     race result, either the tortoise, the hare or a tie.
 * @author david-perez-g
 * @version 1.0
 */
public record RaceSummary(int clockTicks, RaceResult result) {
    @Override
    public String toString() {
        String resultString = null;

        switch (result) {
            case TIE -> resultString = "tie";
            case TORTOISE -> resultString = "tortoise wins";
            case HARE -> resultString = "hare wins";
        }

        return String.format(
                "Race result: %s with %d clock ticks.",
                resultString, clockTicks
        );
    }
}
