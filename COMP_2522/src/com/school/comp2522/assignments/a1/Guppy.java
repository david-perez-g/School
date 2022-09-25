package com.school.comp2522.assignments.a1;

import java.util.Objects;

/**
 * @author david-perez-g
 * @version 2.0
 */
public class Guppy {
    // Static variables
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
    private final String genus;
    private final String species;
    private int ageInWeeks;
    private final boolean isFemale;
    private final int generationNumber;
    private boolean isAlive = true;

    private double healthCoefficient;
    private final int identificationNumber;

    // Constructors
    public Guppy() {
        this.generationNumber = 0;
        this.ageInWeeks = 0;
        this.genus = DEFAULT_GENUS;
        this.species = DEFAULT_SPECIES;
        this.isFemale = true;
        this.healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;
        increaseNumberOfGuppiesBorn();
        this.identificationNumber = numberOfGuppiesBorn;
    }

    public Guppy(final String genus, final String species, int ageInWeeks, boolean isFemale, int generationNumber, double healthCoefficient) {
        this.genus = capitalize(genus);
        this.species = species.toLowerCase();
        this.isFemale = isFemale;
        setAgeInWeeks(ageInWeeks);
        setHealthCoefficient(healthCoefficient);

        if (generationNumber < 0) {
            throw new IllegalArgumentException("generationNumber must be >= 0");
        }

        this.generationNumber = generationNumber;

        increaseNumberOfGuppiesBorn();
        this.identificationNumber = getNumberOfGuppiesBorn();
    }

    // Methods
    private static void increaseNumberOfGuppiesBorn() {
        numberOfGuppiesBorn = getNumberOfGuppiesBorn() + 1;
    }

    private static String capitalize(final String string) {
        String lowerCased = string.toLowerCase();
        return Character.toUpperCase(lowerCased.charAt(0)) + lowerCased.substring(1);
    }

    /**
     * Increment the guppy's weeks of age
     * @throws RuntimeException if the function is called on a dead guppy.
     */
    public void incrementAge() {
        if (!isAlive()) {
            throw new RuntimeException("incrementAge called on a dead guppy");
        }

        int newAgeInWeeks = getAgeInWeeks() + 1;

        if (newAgeInWeeks > MAXIMUM_AGE_IN_WEEKS) {
            setAlive(false);
        }

        setAgeInWeeks(newAgeInWeeks);
    }

    public double getVolumeNeeded() {
        int ageInWeeks = getAgeInWeeks();

        if (ageInWeeks < 10) {
            return MINIMUM_WATER_VOLUME_ML;
        }
        if (ageInWeeks <= 30) {
            return MINIMUM_WATER_VOLUME_ML * ageInWeeks / YOUNG_FISH_AGE_IN_WEEKS;
        }

        return MINIMUM_WATER_VOLUME_ML * 1.5;
    }

    // Getters
    public static int getNumberOfGuppiesBorn() {
        return numberOfGuppiesBorn;
    }

    public String getGenus() {
        return genus;
    }

    public String getSpecies() {
        return species;
    }

    public int getAgeInWeeks() {
        return ageInWeeks;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public int getGenerationNumber() {
        return generationNumber;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public double getHealthCoefficient() {
        return healthCoefficient;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    // Setters
    public void setAgeInWeeks(int ageInWeeks) {
        if (ageInWeeks < 0) {
            throw new IllegalArgumentException("setAgeInWeeks called with a value below than zero");
        }

        this.ageInWeeks = ageInWeeks;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * @param healthCoefficient new healthCoefficient
     * @throws IllegalArgumentException if healthCoefficient is out of bounds
     */
    public void setHealthCoefficient(double healthCoefficient) {
        if (healthCoefficient > MAXIMUM_HEALTH_COEFFICIENT) {
            throw new IllegalArgumentException(
                    "healthCoefficient out of maximum bound, got: "
                            + healthCoefficient
                            + "and the maximum is: "
                            + MAXIMUM_HEALTH_COEFFICIENT);
        }
        if (healthCoefficient < MINIMUM_HEALTH_COEFFICIENT) {
            throw new IllegalArgumentException(
                    "healthCoefficient out of minimum bound, got: "
                            + healthCoefficient
                            + "and the minimum is: "
                            + MINIMUM_HEALTH_COEFFICIENT);
        }

        this.healthCoefficient = healthCoefficient;
    }

    @Override
    public String toString() {
        return "Guppy{" +
                "genus='" + genus + '\'' +
                ", species='" + species + '\'' +
                ", ageInWeeks=" + ageInWeeks +
                ", isFemale=" + isFemale +
                ", generationNumber=" + generationNumber +
                ", isAlive=" + isAlive +
                ", healthCoefficient=" + healthCoefficient +
                ", identificationNumber=" + identificationNumber +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guppy guppy = (Guppy) o;
        return getAgeInWeeks() == guppy.getAgeInWeeks() && isFemale() == guppy.isFemale() && getGenerationNumber() == guppy.getGenerationNumber() && isAlive() == guppy.isAlive() && Double.compare(guppy.getHealthCoefficient(), getHealthCoefficient()) == 0 && getIdentificationNumber() == guppy.getIdentificationNumber() && getGenus().equals(guppy.getGenus()) && getSpecies().equals(guppy.getSpecies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGenus(), getSpecies(), getAgeInWeeks(), isFemale(), getGenerationNumber(), isAlive(), getHealthCoefficient(), getIdentificationNumber());
    }
}
