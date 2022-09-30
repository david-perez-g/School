package com.school.comp2522.labs.lab03;

import com.school.comp2522.labs.lab02.Mathematics;

import java.util.Objects;

/**
 * A tree.
 *
 * @author david-perez-g
 * @version 1.0
 */
public class Tree implements Comparable<Tree> {
    public Tree(final Species type, int ageInYears, double trunkInCentimeters) {
        this.type = type;

        if (ageInYears < 0) {
            throw new IllegalArgumentException("Age must be positive");
        }

        if (trunkInCentimeters <= 0) {
            throw new IllegalArgumentException("Trunk must be positive");
        }

        this.ageInYears = ageInYears;
        this.trunkInCentimeters = trunkInCentimeters;
    }

    public Tree(int ageInYears, double trunkInCentimeters) {
        this.type = Species.MAPLE;

        if (ageInYears < 0) {
            throw new IllegalArgumentException("Age must be positive");
        }

        if (trunkInCentimeters <= 0) {
            throw new IllegalArgumentException("Trunk must be positive");
        }

        this.ageInYears = ageInYears;
        this.trunkInCentimeters = trunkInCentimeters;
    }

    /**
     * Makes a tree with random: age, trunk size and type.
     *
     * @return a tree randomly generated
     */
    public static Tree makeRandomTree() {
        final double trunkSize = Mathematics.getRandomDoubleBetween(10, 99.9999);
        final int age = Mathematics.getRandomIntBetween(0, 1000);
        final Species[] species = {Species.MAPLE, Species.ARBUTUS, Species.BLUE_SPRUCE};
        final Species type = species[Mathematics.getRandomIntBetween(0, 2)];

        return new Tree(type, age, trunkSize);
    }

    enum Species {MAPLE, ARBUTUS, BLUE_SPRUCE}

    private final Species type;
    private int ageInYears;
    private double trunkInCentimeters;

    public Species getType() {
        return type;
    }

    public int getAgeInYears() {
        return ageInYears;
    }

    public void increaseAgeInYears() {
        ageInYears++;
    }

    public double getTrunkInCentimeters() {
        return trunkInCentimeters;
    }

    /**
     * Increase the tree trunk size.
     *
     * @param centimeters number of centimeters to increase
     */
    public void increaseTrunkSizeBy(double centimeters) {
        if (centimeters <= 0) {
            throw new IllegalArgumentException("centimeters must be positive");
        }
        this.trunkInCentimeters += centimeters;
    }

    @Override
    public String toString() {
        return "Tree{" + "type=" + type + ", ageInYears=" + ageInYears + ", trunkInCentimeters=" + trunkInCentimeters + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tree tree = (Tree) o;

        return getAgeInYears() == tree.getAgeInYears() && Double.compare(tree.getTrunkInCentimeters(), getTrunkInCentimeters()) == 0 && getType() == tree.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getAgeInYears(), getTrunkInCentimeters());
    }

    @Override
    public int compareTo(Tree t) {
        return Double.compare(getTrunkInCentimeters(), t.getTrunkInCentimeters());
    }
}
