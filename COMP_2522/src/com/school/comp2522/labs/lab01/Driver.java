package com.school.comp2522.labs.lab01;

import java.util.ArrayList;
import java.util.List;


public class Driver {
    private static RaceSummary simulateRace(int raceLength) {
        final Hare hare = new Hare();
        final Tortoise tortoise = new Tortoise();
        Race race = new Race(hare, tortoise, raceLength);
        return race.simulateRace();
    }

    private static List<RaceSummary> simulateRaces(int numberOfRaces, int raceLength) {
        List<RaceSummary> races = new ArrayList<>();

        for (int i = 0; i < numberOfRaces; i++) {
            races.add(simulateRace(raceLength));
        }

        return races;
    }

    private static String makeRacesSummary(List<RaceSummary> races, int raceLength) {
        int numberOfHareWins = 0;
        int numberOfTortoiseWins = 0;
        int numberOfTies = 0;
        for (RaceSummary race : races) {
            switch (race.result()) {
                case TIE -> numberOfTies++;
                case TORTOISE -> numberOfTortoiseWins++;
                case HARE -> numberOfHareWins++;
            }
        }
        return String.format("""
                %d race(s) with a length of %d were done
                                
                The hare won: %d time(s),
                                
                The tortoise won: %d time(s),
                                
                Both tied: %d time(s)
                """, races.size(), raceLength, numberOfHareWins, numberOfTortoiseWins, numberOfTies);
    }

    public static void main(String[] args) {
        final byte NUMBER_OF_RACES = 100;
        final byte RACE_LENGTH = 100;

        // example race
        final RaceSummary summary = simulateRace(RACE_LENGTH);
        System.out.println(summary);

        final List<RaceSummary> races = simulateRaces(NUMBER_OF_RACES, RACE_LENGTH);
        final String racesSummary = makeRacesSummary(races, RACE_LENGTH);
        System.out.println(racesSummary);
    }
}
