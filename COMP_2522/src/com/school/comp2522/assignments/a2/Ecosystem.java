package com.school.comp2522.assignments.a2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * An ecosystem where pools are managed.
 *
 * @author david-perez-g
 * @version 1.0
 */
public class Ecosystem {
    private final ArrayList<Pool<Fish>> pools;

    private long numberOfDeathsDueToAge = 0;
    private long numberOfDeathsDueToOvercrowding = 0;
    private long numberOfDeathsDueToMalnutrition = 0;
    private long maximumPopulation = 0;
    private long maximumOfSpawnsInAWeek = 0;
    private long maximumOfDeathsInAWeek = 0;

    public Ecosystem() {
        this.pools = new ArrayList<>();
    }

    /**
     * Adds a new pool to the ecosystem.
     *
     * @throws NullPointerException if the passed pool is null
     */
    public void addPool(Pool<Fish> pool) throws NullPointerException {
        Objects.requireNonNull(pool, "pool cannot be null");
        pools.add(pool);
    }

    /**
     * Resets the ecosystem deleting the current pools.
     */
    public void reset() {
        pools.clear();
    }

    /**
     * Randomizes the process of deciding whether a fish will be female or not.
     *
     * @param chancesOfBeingFemale a value between 0 and 1 indicating the probability of being female
     * @return true if the guppy will be female, false otherwise
     * @throws IllegalArgumentException if chancesOfBeingFemale is less than 0 or greater than 1
     */
    private Fish.Gender getGender(double chancesOfBeingFemale) throws IllegalArgumentException {
        if (chancesOfBeingFemale < 0 || chancesOfBeingFemale > 1) {
            throw new IllegalArgumentException("chancesOfBeingFemale must be between 0 and 1");
        }

        if (chancesOfBeingFemale == 0) {
            return Fish.Gender.MALE;
        }

        return (new Random()).nextDouble() < chancesOfBeingFemale ? Fish.Gender.FEMALE : Fish.Gender.MALE;
    }

    /**
     * Creates a guppy with the given params.
     *
     * @param genus                  genus of the guppy
     * @param species                species of the guppy
     * @param ageRange               a Range object containing the minimum and maximum age possible
     * @param chancesOfBeingFemale   a value between 0 and 1 indicating the chances of being a female
     * @param generationNumber       generation of the guppy
     * @param healthCoefficientRange a Range object containing the minimum and maximum health coefficient possible
     * @return a guppy with the given params
     */
    private Guppy createParametrizedGuppy(String genus, String species, Range<Integer> ageRange, double chancesOfBeingFemale, int generationNumber, Range<Double> healthCoefficientRange) {

        return new Guppy(species, genus, getGender(chancesOfBeingFemale), generationNumber, Fish.LifeStatus.ALIVE, ageRange.getRandomIntegerBetween(), healthCoefficientRange.getRandomDoubleBetween());
    }

    private void setupSkookumchuk() {
        Pool<Fish> skookumchuk = new Pool<>("Skookumchuk", 42, 7.9, 0.9, 3000);

        for (int i = 0; i < 300; i++) {
            Guppy g = createParametrizedGuppy(Guppy.DEFAULT_GENUS, Guppy.DEFAULT_SPECIES, Range.of(10, 25), 0.75, 0, Range.of(0.5, 0.8));

            skookumchuk.add(g);
        }

        addPool(skookumchuk);
    }

    private void setupSquamish() {
        Pool<Fish> squamish = new Pool<>("Squamish", 39, 7.7, 0.85, 15_000);

        for (int i = 0; i < 100; i++) {
            Guppy g = createParametrizedGuppy(Guppy.DEFAULT_GENUS, Guppy.DEFAULT_SPECIES, Range.of(10, 15), 0.75, 0, Range.of(0.8, 1.0));

            squamish.add(g);
        }

        addPool(squamish);
    }

    private void setupSemiahmo() {
        Pool<Fish> semiahmoo = new Pool<>("Semiahmoo", 37, 7.5, 1, 4500);

        for (int i = 0; i < 200; i++) {
            Guppy g = createParametrizedGuppy(Guppy.DEFAULT_GENUS, Guppy.DEFAULT_SPECIES, Range.of(15, 49), 0.35, 0, Range.of(0.0, 1.0));

            semiahmoo.add(g);
        }

        addPool(semiahmoo);
    }

    public void setupSimulation() {
        setupSkookumchuk();
        setupSquamish();
        setupSemiahmo();
    }

    /**
     * Returns the number of living fishes in all the pools.
     */
    public int getFishPopulation() {
        return pools.stream().mapToInt(Pool::getPopulation).sum();
    }

    private int increaseAges() {
        return pools.stream().mapToInt(Pool::incrementAges).sum();
    }

    private int removeDeadFishes() {
        return pools.stream().mapToInt(Pool::removeDeadFishes).sum();
    }

    private int applyNutrientCoefficient() {
        return pools.stream().mapToInt(Pool::applyNutrientCoefficient).sum();
    }

    private int spawn() {
        return pools.stream().mapToInt(Pool::spawn).sum();
    }

    private int adjustForCrowding() {
        return pools.stream().mapToInt(Pool::adjustForCrowding).sum();
    }

    private void printWeekSummary(int weekNumber, int diedOfOldAge, int diedOfMalnutrition, int diedOfOvercrowding, int numberOfSpawnedFishes) {
        int totalOfDeaths = diedOfOldAge + diedOfMalnutrition + diedOfOvercrowding;
        System.out.println("Week: " + weekNumber);
        System.out.println("This week, " + totalOfDeaths + " fishes died.");
        System.out.println("> " + diedOfOldAge + " of old age.");
        System.out.println("> " + diedOfMalnutrition + " of malnutrition.");
        System.out.println("> " + diedOfOvercrowding + " due to the overcrowding in the pools.");
        System.out.println();
        System.out.println(numberOfSpawnedFishes + " were born.");
        System.out.println("The current population including all the pools is " + getFishPopulation());
        System.out.println("\n");
    }

    private void printPoolSummary(Pool<Fish> pool) {
        System.out.println("Pool: " + pool.getName());
        System.out.println("Population: " + pool.getPopulation());
        System.out.println("pH=" + pool.getPH());
        System.out.println("volume=" + pool.getVolumeLitres() + " litres");
        System.out.println("temperature=" + pool.getTemperatureCelsius() + " celsius degrees");
        System.out.println("averageHealthCoefficient=" + pool.getAverageHealthCoefficient());
        System.out.println("averageAge=" + pool.getAverageAge());
        System.out.println("femalePercentage=" + pool.getFemalePercentage() + "%");
        System.out.println("medianAge=" + pool.getMedianAge());
    }

    private void printSimulationSummary() {
        System.out.println("Simulation summary");
        System.out.println("Final population: " + getFishPopulation());
        System.out.println("Maximum population: " + maximumPopulation);
        System.out.println("The maximum number of fishes spawned in a week were: " + maximumOfSpawnsInAWeek);
        System.out.println("The maximum number of deaths in a week were: " + maximumOfDeathsInAWeek);
        System.out.println("Causes of deaths:");
        System.out.println(numberOfDeathsDueToAge + " due to age.");
        System.out.println(numberOfDeathsDueToMalnutrition + " due to malnutrition");
        System.out.println(numberOfDeathsDueToOvercrowding + " due to the overcrowding of the pools.");
    }

    private void simulateWeek(int weekNumber) {
        int diedOfOldAge = increaseAges();
        int numberOfRemoved = removeDeadFishes();

        int diedOfMalnutrition = applyNutrientCoefficient();
        numberOfRemoved += removeDeadFishes();

        int spawnedBabies = spawn();

        int diedOfOvercrowding = adjustForCrowding();
        numberOfRemoved += removeDeadFishes();

        if (numberOfRemoved != (diedOfOldAge + diedOfMalnutrition + diedOfOvercrowding)) {
            throw new RuntimeException("Wrong logic");
        }

        if (numberOfRemoved > maximumOfDeathsInAWeek) {
            maximumOfDeathsInAWeek = numberOfRemoved;
        }
        if (spawnedBabies > maximumOfSpawnsInAWeek) {
            maximumOfSpawnsInAWeek = spawnedBabies;
        }

        int currentPopulation = getFishPopulation();
        if (currentPopulation > maximumPopulation) {
            maximumPopulation = currentPopulation;
        }
        numberOfDeathsDueToAge += diedOfOldAge;
        numberOfDeathsDueToMalnutrition += diedOfMalnutrition;
        numberOfDeathsDueToOvercrowding += diedOfOvercrowding;

        printWeekSummary(weekNumber, diedOfOldAge, diedOfMalnutrition, diedOfOvercrowding, spawnedBabies);
    }

    /**
     * Simulates a certain number of weeks.
     */
    public void simulate(int numberOfWeeks) {
        for (int i = 1; i <= numberOfWeeks; i++) {
            simulateWeek(i);
        }

        printSimulationSummary();
        System.out.println("\nPool's summary: ");
        for (Pool<Fish> pool : pools) {
            printPoolSummary(pool);
            System.out.println();
        }
    }
}
