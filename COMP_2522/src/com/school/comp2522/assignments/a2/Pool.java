package com.school.comp2522.assignments.a2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A pool for the fishes.
 *
 * @author david-perez-g
 * @version 1.0
 */
public class Pool<T extends Fish> {
    public static final String DEFAULT_POOL_NAME = "Unnamed";
    public static final double DEFAULT_VOLUME_LITRES = 25.0;
    public static final double DEFAULT_POOL_TEMP_CELSIUS = 40.0;
    public static final double MINIMUM_POOL_TEMP_CELSIUS = 0.0;
    public static final double MAXIMUM_POOL_TEMP_CELSIUS = 100.0;
    public static final double NEUTRAL_PH = 7.0;
    public static final double DEFAULT_NUTRIENT_COEFFICIENT = 0.50;
    public static final double MINIMUM_NUTRIENT_COEFFICIENT = 0.0;
    public static final double MAXIMUM_NUTRIENT_COEFFICIENT = 1.0;

    private final String name;
    private double volumeLitres;
    private double temperatureCelsius;
    private double pH;
    private double nutrientCoefficient;
    private final int identificationNumber;
    private final ArrayList<T> fishesInPool = new ArrayList<>();
    private final ArrayList<T> aliveFishes = new ArrayList<>();
    private final ArrayList<T> deadFishes = new ArrayList<>();
    private final Random randomGenerator = new Random();

    private static int numberOfPools = 0;

    private static void increasePoolCount() {
        numberOfPools++;
    }

    public static int getNumberOfCreatedPools() {
        return numberOfPools;
    }

    public Pool() {
        this.name = DEFAULT_POOL_NAME;
        this.temperatureCelsius = DEFAULT_POOL_TEMP_CELSIUS;
        this.pH = NEUTRAL_PH;
        this.nutrientCoefficient = DEFAULT_NUTRIENT_COEFFICIENT;
        this.volumeLitres = DEFAULT_VOLUME_LITRES;
        increasePoolCount();
        this.identificationNumber = numberOfPools;
    }

    public Pool(final String name, double temperatureCelsius, double pH, double nutrientCoefficient, double volumeLitres) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(name);

        if (name.equals("")) {
            throw new IllegalArgumentException("\"\" is not a valid pool name");
        }

        this.name = Utils.capitalize(name.trim());

        setTemperatureCelsius(temperatureCelsius);
        setPH(pH);
        setNutrientCoefficient(nutrientCoefficient);
        setVolumeLitres(volumeLitres);

        increasePoolCount();
        this.identificationNumber = numberOfPools;
    }

    /**
     * Adds a delta value to the pool nutrient coefficient.
     * If the addition of the delta value and the current nutrient coefficient
     * is out of the maximum or minimum range, the pool's nutrient coefficient
     * will be set to the closest range.
     *
     * @param delta value to be added to the pool's nutrient coefficient
     */
    public void changeNutrientCoefficient(double delta) {
        double changeResult = getNutrientCoefficient() + delta;

        if (changeResult < MINIMUM_POOL_TEMP_CELSIUS) {
            changeResult = MINIMUM_POOL_TEMP_CELSIUS;
        } else if (changeResult > MAXIMUM_NUTRIENT_COEFFICIENT) {
            changeResult = MAXIMUM_NUTRIENT_COEFFICIENT;
        }
        setNutrientCoefficient(changeResult);
    }

    /**
     * Changes the pool's temperature.
     *
     * @param delta new temperature
     */
    public void changeTemperature(double delta) {
        if (delta < MINIMUM_POOL_TEMP_CELSIUS) {
            delta = MINIMUM_POOL_TEMP_CELSIUS;
        } else if (delta > MAXIMUM_POOL_TEMP_CELSIUS) {
            delta = MAXIMUM_POOL_TEMP_CELSIUS;
        }

        setTemperatureCelsius(delta);
    }

    private void addDeadFish(T fish) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(fish);
        Fish.requireDead(fish);
        deadFishes.add(fish);
    }

    private void addAliveFish(T fish) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(fish);
        Fish.requireAlive(fish);
        aliveFishes.add(fish);
    }

    /**
     * Adds a new fish to the pool.
     *
     * @param fish fish to be added
     * @throws NullPointerException if fish is null
     */
    public void add(T fish) throws NullPointerException, IllegalArgumentException {
        if (fish.isAlive()) {
            addAliveFish(fish);
            return;
        }
        addDeadFish(fish);
    }

    /**
     * Adds an array of fishes to the pool.
     *
     * @param fishes fishes to be added
     */
    public void addAll(T[] fishes) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(fishes);

        for (T fish : fishes) {
            add(fish);
        }
    }

    /**
     * Returns the number of living fishes in the pool.
     */
    public int getPopulation() {
        return aliveFishes.size();
    }

    /**
     * Calculates the number of fishes that have died of malnutrition in the week.
     */
    public int applyNutrientCoefficient() {
        int numberOfDeaths = 0;

        Iterator<T> iterator = aliveFishes.iterator();

        do {
            T fish = iterator.next();

            if (!(randomGenerator.nextDouble() > getNutrientCoefficient())) {
                continue;
            }

            // kills the fish, adds it to the list of dead fishes and
            // removes it from the list of alive fishes
            fish.kill();
            addDeadFish(fish);
            iterator.remove();
            ++numberOfDeaths;

        } while (iterator.hasNext());

        return numberOfDeaths;
    }

    /**
     * Removes all the dead guppies from the pool.
     *
     * @return the number of dead fishes.
     */
    public int removeDeadFishes() {
        int numberOfDeaths = deadFishes.size();
        deadFishes.clear();
        return numberOfDeaths;
    }

    /**
     * Returns the total number of litres required by all the living fishes in the pool.
     */
    public double getGuppyVolumeRequirementInLitres() {
        return aliveFishes.stream().mapToDouble(Fish::getVolumeNeededLitres).sum();
    }

    /**
     * Returns the age average of the alive fishes in the pool.
     */
    public double getAverageAge() {
        OptionalDouble result = aliveFishes.stream().mapToInt(Fish::getAgeInWeeks).average();

        if (result.isPresent()) {
            return result.getAsDouble();
        }
        return 0;
    }

    /**
     * Returns the health coefficient of the alive fishes in the pool.
     */
    public double getAverageHealthCoefficient() {
        OptionalDouble result = aliveFishes.stream().mapToDouble(Fish::getHealthCoefficient).average();

        if (result.isPresent()) {
            return result.getAsDouble();
        }
        return 0;
    }

    /**
     * Returns the percentage of alive female fishes in the pool.
     */
    public double getFemalePercentage() {
        if (getPopulation() == 0) {
            return 0;
        }

        long numberOfFemales = aliveFishes.stream().filter(Fish::isFemale).count();

        return (double) numberOfFemales / getPopulation() * 100;
    }

    /**
     * Returns the median age of the fish population.
     */
    public double getMedianAge() {
        if (getPopulation() == 0) {
            return 0;
        }

        List<T> sortedPool = aliveFishes.stream().sorted(Comparator.comparingInt(Fish::getAgeInWeeks)).collect(Collectors.toList());

        int poolSize = sortedPool.size();

        if (poolSize % 2 != 0) {
            return sortedPool.get((poolSize - 1) / 2).getAgeInWeeks();
        }

        double age1 = sortedPool.get(poolSize / 2).getAgeInWeeks();
        double age2 = sortedPool.get((poolSize / 2) - 1).getAgeInWeeks();
        return (age1 + age2) / 2;
    }

    /**
     * Simulates the birth of each female fish in the pool,
     * adding each new child to the pool.
     *
     * @return the number of child fishes added to the pool
     */
    public int spawn() {
        ArrayList<T> babyGuppies = new ArrayList<>();
        for (T fish : aliveFishes) {
            if (fish.isFemale() && fish.isYoungEnoughToSpawn()) {
                babyGuppies.addAll(fish.spawn());
            }
        }

        aliveFishes.addAll(babyGuppies);

        return babyGuppies.size();
    }

    /**
     * Increments the age of each fish in the pool.
     *
     * @return the number of fishes that died due to old age
     */
    public int incrementAges() {
        int numberOfDeaths = 0;

        Iterator<T> iterator = aliveFishes.iterator();

        do {
            T fish = iterator.next();
            fish.increaseAge();
            if (fish.isDead()) {
                addDeadFish(fish);
                iterator.remove();
                ++numberOfDeaths;
            }

        } while (iterator.hasNext());

        return numberOfDeaths;
    }

    /**
     * Returns the fish with the least health coefficient in the pool.
     *
     * @return Optional.empty() if the pool is empty, otherwise Optional.of(fish) with the weakest fish
     */
    protected Optional<T> getWeakestFish() {
        if (aliveFishes.size() == 0) {
            return Optional.empty();
        }

        T weaker = aliveFishes.get(0);

        for (T fish : aliveFishes) {
            if (fish.getHealthCoefficient() < weaker.getHealthCoefficient()) {
                weaker = fish;
            }
        }

        return Optional.of(weaker);

    }

    /**
     * Extinguishes the fishes that have suffocated due to overcrowding.
     *
     * @return the number of dead fishes
     */
    public int adjustForCrowding() {
        int numberOfDeaths = 0;
        double volumeRequired = getGuppyVolumeRequirementInLitres();

        // there is not enough water in the pool for every fish
        while (getVolumeLitres() < volumeRequired) {
            Optional<T> weakestFish = getWeakestFish();

            if (!weakestFish.isPresent()) {
                // this means there's an error in the logic somewhere,
                // since getWeakestFish should only return an absent fish
                // if there are no alive fishes in the pool, in that case
                // the condition of this loop shouldn't be true, and the
                // method should return 0 immediately.
                throw new IllegalStateException("Received an absent fish");
            }

            T weakest = weakestFish.get();
            volumeRequired -= weakest.getVolumeNeededLitres();
            weakest.kill();
            addDeadFish(weakest);
            aliveFishes.remove(weakest);
            ++numberOfDeaths;
        }

        return numberOfDeaths;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getVolumeLitres() {
        return volumeLitres;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public double getPH() {
        return pH;
    }

    public double getNutrientCoefficient() {
        return nutrientCoefficient;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    // Setters
    public void setVolumeLitres(double volumeLitres) throws IllegalArgumentException {
        if (volumeLitres < 0) {
            throw new IllegalArgumentException("volumeLitres must be positive");
        }

        this.volumeLitres = volumeLitres;
    }

    public void setTemperatureCelsius(double temperatureCelsius) throws IllegalArgumentException {
        if (temperatureCelsius < MINIMUM_POOL_TEMP_CELSIUS || temperatureCelsius > MAXIMUM_POOL_TEMP_CELSIUS) {
            throw new IllegalArgumentException("temperatureCelsius out of bounds");
        }

        this.temperatureCelsius = temperatureCelsius;
    }

    public void setPH(double pH) throws IllegalArgumentException {
        if (pH < 0 || pH > 14) {
            throw new IllegalArgumentException("pH must be between 0 and 14");
        }
        this.pH = pH;
    }

    public void setNutrientCoefficient(double nutrientCoefficient) throws IllegalArgumentException {
        if (nutrientCoefficient < 0 || nutrientCoefficient > 1) {
            throw new IllegalArgumentException("nutrientCoefficient must between 0 and 1");
        }

        this.nutrientCoefficient = nutrientCoefficient;
    }

    @Override
    public String toString() {
        return "Pool{" + "name='" + name + '\'' + ", volumeLitres=" + volumeLitres + ", temperatureCelsius=" + temperatureCelsius + ", pH=" + pH + ", nutrientCoefficient=" + nutrientCoefficient + ", identificationNumber=" + identificationNumber + ", randomNumberGenerator=" + randomGenerator + '}';
    }
}
