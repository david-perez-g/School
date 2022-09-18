package com.school.comp2522.labs.lab01;

enum RaceResult {
    TORTOISE, HARE, TIE
}

/**
 * @author david-perez-g
 * @version 1.0
 */
public class Race {
    private final Hare hare;
    private final Tortoise tortoise;
    private final int raceLength;
    private int clockTicks = 0;

    public Race(Hare hare, Tortoise tortoise, int length) {
        this.hare = hare;
        this.tortoise = tortoise;
        this.raceLength = length;
    }

    private void reset() {
        byte DEFAULT_POSITION = 0;
        hare.setPosition(DEFAULT_POSITION);
        tortoise.setPosition(DEFAULT_POSITION);
    }

    /**
     * Simulates a race between a hare and a tortoise.
     *
     * @return the result of the race, either RaceResult.TORTOISE, RaceResult.HARE, or RaceResult.TIE
     */
    public final RaceSummary simulateRace() {
        reset();
        race();
        RaceResult result = getResult();
        int ticks = getClockTicks();
        return new RaceSummary(ticks, result);
    }

    private void increaseClockTicks() {
        clockTicks++;
    }

    private void race() {
        while (!isRaceOver()) {
            hare.move();
            tortoise.move();
            increaseClockTicks();
        }
    }

    private RaceResult getResult() {
        int harePosition = hare.getPosition();
        int tortoisePosition = tortoise.getPosition();

        if (harePosition <= raceLength && tortoisePosition <= raceLength) {
            throw new RuntimeException("Function `getWinner` called before anyone has won.");
        }

        if (harePosition == tortoisePosition) {
            return RaceResult.TIE;
        }

        if (harePosition > tortoisePosition) {
            return RaceResult.HARE;
        }

        return RaceResult.TORTOISE;
    }

    private boolean isRaceOver() {
        int harePosition = hare.getPosition();
        int tortoisePosition = tortoise.getPosition();
        return harePosition > raceLength || tortoisePosition > raceLength;
    }


    public int getClockTicks() {
        return clockTicks;
    }
}
