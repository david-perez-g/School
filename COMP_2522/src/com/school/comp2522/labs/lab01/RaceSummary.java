package com.school.comp2522.labs.lab01;

/**
 * @author david-perez-g
 * @version 2.0
 */
public class RaceSummary {
    private final int clockTicks;
    private final RaceResult result;

    public RaceSummary(int clockTicks, final RaceResult result) {
        this.clockTicks = clockTicks;
        this.result = result;
    }
    public int getClockTicks() {
        return clockTicks;
    }

    public RaceResult getResult() {
        return result;
    }

    @Override
    public String toString() {
        String resultString;
        final RaceResult result = getResult();

        if (result.equals(RaceResult.TIE)) {
            resultString = "tie";
        } else if (result.equals(RaceResult.HARE)) {
            resultString = "hare";
        } else {
            resultString = "tortoise";
        }

        return String.format(
                "Race result: %s with %d clock ticks.",
                resultString, clockTicks
        );
    }
}
