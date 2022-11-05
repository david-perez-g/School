package com.school.comp2522.assignments.a2;

import java.util.ArrayList;

public abstract class Fish {
    public enum Gender {
        FEMALE, MALE
    }

    public enum LifeStatus {
        ALIVE, DEAD
    }

    protected final String speciesName;
    protected final String genus;
    protected final Gender gender;
    protected final int generationNumber;
    protected LifeStatus lifeStatus;
    protected int ageInWeeks;
    protected double healthCoefficient;

    protected Fish(String speciesName, String genus, Gender gender, int generationNumber, LifeStatus lifeStatus, int ageInWeeks, double healthCoefficient) throws IllegalArgumentException {

        setHealthCoefficient(healthCoefficient);
        setAgeInWeeks(ageInWeeks);

        this.speciesName = Utils.capitalize(speciesName.trim());
        this.genus = genus.trim();
        this.gender = gender;
        this.generationNumber = generationNumber;
        this.lifeStatus = lifeStatus;
    }

    public abstract void increaseAge() throws IllegalStateException;

    /**
     * Kills the fish.
     *
     * @throws IllegalStateException if the fish is already dead
     */
    public void kill() throws IllegalStateException {
        if (isDead()) {
            throw new IllegalStateException("Cannot kill an already dead fish");
        }

        lifeStatus = LifeStatus.DEAD;
    }

    public abstract double getVolumeNeededMillilitres() throws RuntimeException;

    public double getVolumeNeededLitres() throws RuntimeException {
        return getVolumeNeededMillilitres() / 1000;
    }

    public abstract boolean isYoungEnoughToSpawn();

    public abstract <T extends Fish> ArrayList<T> spawn();

    public boolean isDead() {
        return lifeStatus.equals(LifeStatus.DEAD);
    }

    public boolean isAlive() {
        return !isDead();
    }

    public boolean isFemale() {
        return gender.equals(Gender.FEMALE);
    }

    public boolean isMale() {
        return !isFemale();
    }

    public abstract void setAgeInWeeks(int ageInWeeks) throws IllegalArgumentException;

    public abstract void setHealthCoefficient(double healthCoefficient);

    public String getGenus() {
        return genus;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getGenerationNumber() {
        return generationNumber;
    }

    public LifeStatus getLifeStatus() {
        return lifeStatus;
    }

    public int getAgeInWeeks() {
        return ageInWeeks;
    }

    public double getHealthCoefficient() {
        return healthCoefficient;
    }

    public static void requireAlive(Fish fish) throws IllegalArgumentException {
        if (fish.isDead()) {
            throw new IllegalArgumentException("requireAlive called on a dead fish");
        }
    }

    public static void requireAlive(Fish fish, String message) throws IllegalArgumentException {
        if (fish.isDead()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void requireDead(Fish fish) throws IllegalArgumentException {
        if (fish.isAlive()) {
            throw new IllegalArgumentException("requireDead called on a living fish");
        }
    }

    public static void requireDead(Fish fish, String message) throws IllegalArgumentException {
        if (fish.isAlive()) {
            throw new IllegalArgumentException(message);
        }
    }


}
