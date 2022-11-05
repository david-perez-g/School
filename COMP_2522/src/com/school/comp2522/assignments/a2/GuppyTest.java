package com.school.comp2522.assignments.a2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Guppy class.
 *
 * @author david-perez-g
 * @version 1.0
 */
class GuppyTest {
    private static Guppy testGuppy;

    @BeforeEach
    void setUp() {
        testGuppy = new Guppy();
    }

    @Test
    @DisplayName("Should increment age correctly")
    void shouldIncrementAgeCorrectly() {
        testGuppy.increaseAge();
        assertEquals(1, testGuppy.getAgeInWeeks());
        testGuppy.increaseAge();
        assertEquals(2, testGuppy.getAgeInWeeks());
        testGuppy.increaseAge();
        assertEquals(3, testGuppy.getAgeInWeeks());
    }

    @Test
    @DisplayName("Should die after age reaches MAXIMUM_AGE_IN_WEEKS")
    void shouldDieAfterGoingAfterMaximumAgeInWeeks() {
        for (int i = 0; i < Guppy.MAXIMUM_AGE_IN_WEEKS; i++) {
            testGuppy.increaseAge();
        }

        assertTrue(testGuppy.isDead());
    }

    @Test
    @DisplayName("Should throw after increasing the age on a dead guppy")
    void shouldThrowAfterIncreasingTheAgeOnADeadGuppy() {
        for (int i = 0; i < Guppy.MAXIMUM_AGE_IN_WEEKS; i++) {
            testGuppy.increaseAge();
        }

        assertThrows(RuntimeException.class, () -> testGuppy.increaseAge());
    }

    @Test
    @DisplayName("Should kill the guppy")
    void shouldKillTheGuppy() {
        testGuppy.kill();
        assertTrue(testGuppy.isDead());
    }

    @Test
    @DisplayName("Should throw when killing a dead guppy")
    void shouldThrowWhenKillingADeadGuppy() {
        testGuppy.kill();
        assertThrows(RuntimeException.class, () -> testGuppy.kill());
    }

    @Test
    @DisplayName("Should correctly increase the ID number of the guppy")
    void shouldCorrectlyIncreaseTheIdNumberOfTheGuppy() {
        Guppy guppy1 = new Guppy();

        int guppiesBorn = guppy1.getIdentificationNumber();

        Guppy guppy2 = new Guppy();
        assertEquals(guppiesBorn + 1, guppy2.getIdentificationNumber());

        Guppy guppy3 = new Guppy();
        assertEquals(guppiesBorn + 2, guppy3.getIdentificationNumber());

        Guppy guppy4 = new Guppy();
        assertEquals(guppiesBorn + 3, guppy4.getIdentificationNumber());

        Guppy guppy5 = new Guppy();
        assertEquals(guppiesBorn + 4, guppy5.getIdentificationNumber());
    }

    @Test
    @DisplayName("Should throw when invoking spawn on a male guppy")
    void shouldThrowWhenInvokingSpawnOnAMaleGuppy() {
        Guppy guppy = Guppy.createMale();
        assertThrows(RuntimeException.class, guppy::spawn);
    }

    @Test
    @DisplayName("Should throw when invoking spawn on a very young guppy")
    void shouldThrowWhenInvokingSpawnOnAVeryYoungGuppy() {
        Guppy guppy = Guppy.createGuppyWithNWeeks(Guppy.MINIMUM_AGE_IN_WEEKS_TO_SPAWN - 1);
        assertThrows(RuntimeException.class, guppy::spawn);
    }

    @Test
    @DisplayName("Should throw when trying to spawn on a dead guppy")
    void shouldThrowWhenTryingToSpawnOnADeadGuppy() {
        Guppy guppy = Guppy.createDeadGuppy();
        assertThrows(RuntimeException.class, guppy::spawn);
    }

    @Test
    @DisplayName("Should throw when invoking GetVolumeNeeded on a dead guppy")
    void shouldThrowWhenInvokingGetVolumeNeededOnADeadGuppy() {
        testGuppy.kill();
        assertThrows(RuntimeException.class, testGuppy::getVolumeNeededMillilitres);
    }

    @Test
    @DisplayName("Should return the correct volume needed for the guppy")
    void shouldReturnTheCorrectVolumeNeededForTheGuppy() {
        // (a) If the fish is less than 10 weeks old, return MINIMUM_WATER_VOLUME_ML.
        // (b) If the fish is 10 to 30 weeks old, return MINIMUM_WATER_VOLUME_ML * ageInWeeks / YOUNG_FISH_WEEKS.
        // (c) If the fish is 31 to 49 weeks old, return MINIMUM_WATER_VOLUME_ML * 1.5.

        ArrayList<Guppy> caseA = new ArrayList<>();
        caseA.add(Guppy.createGuppyWithNWeeks(0));
        caseA.add(Guppy.createGuppyWithNWeeks(9));
        caseA.add(Guppy.createGuppyWithNWeeks(5));

        caseA.forEach(guppy -> assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML / 1000, guppy.getVolumeNeededLitres()));

        ArrayList<Guppy> caseB = new ArrayList<>();
        caseB.add(Guppy.createGuppyWithNWeeks(20));
        caseB.add(Guppy.createGuppyWithNWeeks(30));
        caseB.add(Guppy.createGuppyWithNWeeks(10));

        caseB.forEach((guppy) -> {
            double res = Guppy.MINIMUM_WATER_VOLUME_ML / 1000 * guppy.getAgeInWeeks() / Guppy.YOUNG_FISH_AGE_IN_WEEKS;
            assertEquals(res, guppy.getVolumeNeededLitres());
        });

        ArrayList<Guppy> caseC = new ArrayList<>();
        caseC.add(Guppy.createGuppyWithNWeeks(31));
        caseC.add(Guppy.createGuppyWithNWeeks(40));
        caseC.add(Guppy.createGuppyWithNWeeks(49));

        caseC.forEach((guppy) -> {
            double res = Guppy.MINIMUM_WATER_VOLUME_ML * 1.5 / 1000;
            assertEquals(res, guppy.getVolumeNeededLitres());
        });
    }
}