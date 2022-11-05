package com.school.comp2522.assignments.a2;

import com.school.comp2522.labs.lab02.Mathematics;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * The next version of the {@link com.school.comp2522.assignments.a1.Guppy} class made in the Assignment 1 .
 *
 * @author david-perez-g
 * @version 4.0
 */
public class Guppy extends Fish {
    public final static int MINIMUM_AGE_IN_WEEKS_TO_SPAWN = 8;
    public final static int YOUNG_FISH_AGE_IN_WEEKS = 10;

    public final static int MATURE_FISH_AGE_IN_WEEKS = 30;
    public final static int MAXIMUM_AGE_IN_WEEKS = 50;
    public final static double MINIMUM_WATER_VOLUME_ML = 250.0;
    public final static String DEFAULT_GENUS = "Poecilia";
    public final static String DEFAULT_SPECIES = "reticulata";
    public final static double DEFAULT_HEALTH_COEFFICIENT = 0.5;
    public final static double MINIMUM_HEALTH_COEFFICIENT = 0.0;
    public final static double MAXIMUM_HEALTH_COEFFICIENT = 1.0;

    private static int numberOfGuppiesBorn = 0;

    // Instance variables
    private final Random randomNumberGenerator = new Random();
    private final int identificationNumber;

    protected Guppy() {
        super(DEFAULT_SPECIES, DEFAULT_GENUS, Gender.FEMALE, 0, LifeStatus.ALIVE, 0, DEFAULT_HEALTH_COEFFICIENT);

        increaseNumberOfGuppiesBorn();
        this.identificationNumber = numberOfGuppiesBorn;
    }

    /**
     * @param speciesName       species of the guppy
     * @param genus             genus of the guppy
     * @param gender            gender of the guppy
     * @param generationNumber  generation number of the guppy
     * @param lifeStatus        either LifeStatus.ALIVE or LifeStatus.DEAD
     * @param ageInWeeks        age of the guppy, must be not negative
     * @param healthCoefficient health coefficient of the fish, must be between 0 and 1
     * @throws IllegalArgumentException if the health coefficient or the age are invalid
     */
    protected Guppy(String speciesName, String genus, Gender gender, int generationNumber, LifeStatus lifeStatus, int ageInWeeks, double healthCoefficient) throws IllegalArgumentException {
        super(speciesName, genus, gender, generationNumber, lifeStatus, ageInWeeks, healthCoefficient);
        increaseNumberOfGuppiesBorn();
        this.identificationNumber = numberOfGuppiesBorn;
    }

    /**
     * Returns true if the guppy is in age to spawn, false otherwise.
     */
    @Override
    public boolean isYoungEnoughToSpawn() {
        return getAgeInWeeks() >= MINIMUM_AGE_IN_WEEKS_TO_SPAWN;
    }

    /**
     * Creates a baby guppy with the inherited information from the mother.
     *
     * @return the baby guppy with the inherited information from the mother.
     */
    private Guppy createChild() {
        Gender gender = randomNumberGenerator.nextBoolean() ? Gender.MALE : Gender.FEMALE;

        return new Guppy(getSpeciesName(), getGenus(), gender, getGenerationNumber() + 1, LifeStatus.ALIVE, 0, (1 + getHealthCoefficient()) / 2);
    }

    /**
     * Borns a random number between [1, 100] of new guppies.
     *
     * @return the born babies
     * @throws RuntimeException if the guppy is a male or is not at least 8 weeks old
     */
    @Override
    public ArrayList<Guppy> spawn() throws RuntimeException {
        if (isDead()) {
            throw new RuntimeException("Method spawn() called on a dead guppy.");
        }

        if (isMale()) {
            throw new RuntimeException("Method spawn() called on a non-female guppy");
        }

        if (!isYoungEnoughToSpawn()) {
            throw new RuntimeException("Method spawn() called on a guppy younger than " + MINIMUM_AGE_IN_WEEKS_TO_SPAWN + " weeks");
        }

        final ArrayList<Guppy> babyGuppies = new ArrayList<>();

        // each female has only a 50% chance of having childs
        if (randomNumberGenerator.nextDouble() > 0.5) {
            return babyGuppies;
        }

        final int numberOfChilds = Mathematics.getRandomIntBetween(1, 100, randomNumberGenerator);

        for (int i = 0; i < numberOfChilds; i++) {
            babyGuppies.add(createChild());
        }

        return babyGuppies;
    }

    private static void increaseNumberOfGuppiesBorn() {
        numberOfGuppiesBorn = getNumberOfGuppiesBorn() + 1;
    }

    /**
     * Increment the guppy's weeks of age
     *
     * @throws RuntimeException if the function is called on a dead guppy.
     */
    @Override
    public void increaseAge() throws RuntimeException {
        if (isDead()) {
            throw new RuntimeException("Method incrementAge() called on a dead guppy");
        }

        int newAge = getAgeInWeeks() + 1;

        if (newAge >= MAXIMUM_AGE_IN_WEEKS) {
            kill();
            return;
        }

        setAgeInWeeks(newAge);
    }

    /**
     * Returns the necessary volume of water in millilitres for the fish to live.
     */
    @Override
    public double getVolumeNeededMillilitres() {
        int ageInWeeks = getAgeInWeeks();

        if (isDead()) {
            throw new RuntimeException("Method getVolumeNeeded() called on a dead guppy");
        }

        if (ageInWeeks < 10) {
            return MINIMUM_WATER_VOLUME_ML;
        }
        if (ageInWeeks <= 30) {
            return MINIMUM_WATER_VOLUME_ML * ageInWeeks / YOUNG_FISH_AGE_IN_WEEKS;
        }

        return MINIMUM_WATER_VOLUME_ML * 1.5;
    }

    public static Guppy createMale() {
        return new Guppy(DEFAULT_SPECIES, DEFAULT_GENUS, Gender.MALE, 0, LifeStatus.ALIVE, 0, DEFAULT_HEALTH_COEFFICIENT);
    }

    public static Guppy createDeadGuppy() {
        return new Guppy(DEFAULT_SPECIES, DEFAULT_GENUS, Gender.MALE, 0, LifeStatus.DEAD, 0, DEFAULT_HEALTH_COEFFICIENT);
    }

    public static Guppy createGuppyWithNWeeks(int numberOfWeeks) {
        return new Guppy(DEFAULT_SPECIES, DEFAULT_GENUS, Gender.MALE, 0, LifeStatus.ALIVE, numberOfWeeks, DEFAULT_HEALTH_COEFFICIENT);
    }

    public static int getNumberOfGuppiesBorn() {
        return numberOfGuppiesBorn;
    }

    public int getGenerationNumber() {
        return generationNumber;
    }

    /**
     * @param healthCoefficient new healthCoefficient
     * @throws IllegalArgumentException if healthCoefficient is out of bounds
     */
    @Override
    public void setHealthCoefficient(double healthCoefficient) throws IllegalArgumentException {
        if (healthCoefficient > MAXIMUM_HEALTH_COEFFICIENT) {
            throw new IllegalArgumentException("healthCoefficient out of maximum bound, got: " + healthCoefficient + "and the maximum is: " + MAXIMUM_HEALTH_COEFFICIENT);
        }

        if (healthCoefficient < MINIMUM_HEALTH_COEFFICIENT) {
            throw new IllegalArgumentException("healthCoefficient out of minimum bound, got: " + healthCoefficient + "and the minimum is: " + MINIMUM_HEALTH_COEFFICIENT);
        }

        this.healthCoefficient = healthCoefficient;
    }

    @Override
    public void setAgeInWeeks(int ageInWeeks) {
        if (ageInWeeks < 0 || ageInWeeks < getAgeInWeeks() || ageInWeeks > MAXIMUM_AGE_IN_WEEKS) {
            throw new IllegalArgumentException("invalid ageInWeeks: " + ageInWeeks);
        }
        this.ageInWeeks = ageInWeeks;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guppy guppy = (Guppy) o;
        return getIdentificationNumber() == guppy.getIdentificationNumber() && randomNumberGenerator.equals(guppy.randomNumberGenerator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(randomNumberGenerator, getIdentificationNumber());
    }

    @Override
    public String toString() {
        return "Guppy{" + "identificationNumber=" + identificationNumber + ", speciesName='" + speciesName + '\'' + ", genus='" + genus + '\'' + ", gender=" + gender + ", generationNumber=" + generationNumber + ", lifeStatus=" + lifeStatus + ", ageInWeeks=" + ageInWeeks + ", healthCoefficient=" + healthCoefficient + '}';
    }
}
