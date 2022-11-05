package com.school.comp2522.assignments.a2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link com.school.comp2522.assignments.a2.Pool}
 *
 * @author david-perez-g
 * @version 1.0
 */
class PoolTest {

    private Pool<Guppy> pool;

    @BeforeEach
    void setUp() {
        pool = new Pool<>();
    }

    private Guppy createGuppyWithXHealthCoefficient(double healthCoefficient) {
        if (healthCoefficient < Guppy.MINIMUM_HEALTH_COEFFICIENT || healthCoefficient > Guppy.MAXIMUM_HEALTH_COEFFICIENT) {
            throw new IllegalArgumentException("healthCoefficient is out of bounds, " + healthCoefficient);
        }

        Guppy g = new Guppy();
        g.setHealthCoefficient(healthCoefficient);
        return g;
    }

    private Guppy createGuppyWithNWeeks(int numberOfWeeks) {
        Guppy g = new Guppy();
        g.setAgeInWeeks(numberOfWeeks);
        return g;
    }

    private int getNumberOfHoldableGuppies(double poolVolumeLitres, int ageOfGuppies) {
        Guppy g = new Guppy();
        g.setAgeInWeeks(ageOfGuppies);
        double volumeForTheAge = g.getVolumeNeededLitres();
        return (int) (poolVolumeLitres / volumeForTheAge);
    }

    /**
     * Creates an array of guppies with default values.
     */
    private Guppy[] createArrayOfGuppies(int numberOfGuppies) throws IllegalArgumentException {
        if (numberOfGuppies <= 0) {
            throw new IllegalArgumentException("numberOfGuppies must be positive");
        }

        Guppy[] guppies = new Guppy[numberOfGuppies];

        for (int i = 0; i < numberOfGuppies; i++) {
            guppies[i] = new Guppy();
        }

        return guppies;
    }


    /*
        add() checklist:
        * (DONE) adds the guppy
        * (DONE) throws NullPointerException if the guppy is null
     */

    @Test
    @DisplayName("[add()] Should add the guppy")
    void shouldAddTheGuppy() {
        int sizeBeforeAdding = pool.getPopulation();
        pool.add(new Guppy());
        assertEquals(sizeBeforeAdding + 1, pool.getPopulation());
        pool.add(new Guppy());
        assertEquals(sizeBeforeAdding + 2, pool.getPopulation());
    }

    @Test
    @DisplayName("[add()] Should not add a null guppy")
    void shouldNotAddANullGuppy() {
        assertThrows(NullPointerException.class, () -> pool.add(null));
    }

    /*
        getPopulation() checklist:
        * (DONE) returns 0 if there are no guppies in the pool.
        * (DONE) returns the number of living guppies in the pool.
        * (DONE) correctly ignores dead guppies.
     */

    @Test
    @DisplayName("[getPopulation()] Should return 0 if there are no guppies in the pool")
    void shouldReturn0IfThereAreNoGuppiesInThePool() {
        assertEquals(0, pool.getPopulation());
    }

    @Test
    @DisplayName("[getPopulation()] Should return 0 if there are no alive guppies in the pool")
    void shouldReturn0IfThereAreNoAliveGuppiesInThePool() {
        Guppy[] guppies = createArrayOfGuppies(5);

        for (Guppy guppy : guppies) {
            guppy.kill();
        }

        pool.addAll(guppies);

        assertEquals(0, pool.getPopulation());
    }

    @Test
    @DisplayName("[getPopulation()] Should return the number of living guppies in the pool")
    void shouldReturnTheNumberOfLivingGuppiesInThePool() {
        pool.add(new Guppy());

        assertEquals(1, pool.getPopulation());

        pool.add(new Guppy());
        pool.add(new Guppy());
        pool.add(new Guppy());

        assertEquals(4, pool.getPopulation());
    }

       /*
        changeTemperature() checklist:
        * (DONE) changes to the selected temperature
        * (DONE) if the new temperature is too big, it sets it to the closest bound
        * (DONE) if the new temperature is too small, it sets it to the closest bound
     */

    @Test
    @DisplayName("[changeTemperature()] Should change the temperature")
    void shouldChangeTheTemperature() {
        // pool temperature is 40 by default and should be in the range of [0, 100]

        assertEquals(pool.getTemperatureCelsius(), Pool.DEFAULT_POOL_TEMP_CELSIUS);

        for (int i = (int) Pool.MINIMUM_POOL_TEMP_CELSIUS; i < Pool.MAXIMUM_POOL_TEMP_CELSIUS + 1; i++) {
            pool.setTemperatureCelsius(i);
            assertEquals(pool.getTemperatureCelsius(), i);
        }
    }

    @Test
    @DisplayName("[changeTemperature()] Should set the temperature to the maximum bound")
    void shouldSetTheTemperatureToTheMaximumBound() {
        pool.changeTemperature(Pool.MAXIMUM_POOL_TEMP_CELSIUS + 1);
        assertEquals(pool.getTemperatureCelsius(), Pool.MAXIMUM_POOL_TEMP_CELSIUS);
    }

    @Test
    @DisplayName("[changeTemperature()] Should set the temperature to the minimum bound")
    void shouldSetTheTemperatureToTheMinimumBound() {
        pool.changeTemperature(Pool.MINIMUM_POOL_TEMP_CELSIUS - 1);
        assertEquals(pool.getTemperatureCelsius(), Pool.MINIMUM_POOL_TEMP_CELSIUS);
    }

    /*
        changeNutrientCoefficient() checklist:
        * (DONE) adds the passed value to the pool's nutrient coefficient
        * (DONE) if the new nutrient coefficient is too big, it sets it to the closest bound
        * (DONE) if the new nutrient coefficient is too small, it sets it to the closest bound
     */

    @Test
    @DisplayName("[changeNutrientCoefficient()] Should change the nutrient coefficient")
    void shouldChangeTheNutrientCoefficient() {
        // pool's nutrient coefficient is 0.5 by default and should be in the range of [0, 1]

        pool.changeNutrientCoefficient(0.5);
        assertEquals(1, pool.getNutrientCoefficient());

        pool.changeNutrientCoefficient(-0.5);
        assertEquals(0.5, pool.getNutrientCoefficient());

        pool.changeNutrientCoefficient(-0.5);
        assertEquals(0, pool.getNutrientCoefficient());

        pool.changeNutrientCoefficient(0.25);
        assertEquals(0.25, pool.getNutrientCoefficient());

    }

    @Test
    @DisplayName("[changeNutrientCoefficient()] Should set the nutrient coefficient to the maximum bound")
    void shouldSetTheNutrientCoefficientToTheMaximumBound() {
        pool.changeNutrientCoefficient(10);
        assertEquals(pool.getNutrientCoefficient(), Pool.MAXIMUM_NUTRIENT_COEFFICIENT);
    }

    @Test
    @DisplayName("[changeNutrientCoefficient()] Should set the nutrient coefficient to the minimum bound")
    void shouldSetTheNutrientCoefficientToTheMinimumBound() {
        pool.changeNutrientCoefficient(-10);
        assertEquals(pool.getNutrientCoefficient(), Pool.MINIMUM_NUTRIENT_COEFFICIENT); // 0.0
    }

    /*
        removeDeadFishes() checklist:
        * (DONE) correctly removes dead guppies from the pool
     */

    @Test
    @DisplayName("[removeDeadFishes()] Should remove all dead guppies")
    void shouldRemoveAllDeadGuppies() {
        Guppy guppyA = new Guppy();
        Guppy guppyB = new Guppy();
        guppyB.kill();
        guppyA.kill();

        pool.add(guppyA);
        pool.add(guppyB);
        pool.addAll(createArrayOfGuppies(10));


        assertEquals(2, pool.removeDeadFishes());
    }

    @Test
    @DisplayName("[removeDeadFishes()] Should not remove any guppy")
    void shouldNotRemoveAnyGuppy() {
        pool.addAll(createArrayOfGuppies(5));
        assertEquals(0, pool.removeDeadFishes());
    }

    /*
        incrementAges() checklist:
        * (DONE) increments correctly the age for each guppy
        * (DONE) ignores dead guppies in the pool
        * (DONE) correctly returns the number of guppies that died due to old age after incrementing it
     */

    @Test
    @DisplayName("[incrementAges()] Should increment the age of every guppy in the pool")
    void shouldIncrementTheAgeOfEveryFishInThePool() {
        // each guppy by default has an age of 0
        Guppy[] guppies = createArrayOfGuppies(5);

        pool.addAll(guppies);

        for (int i = 0; i < 10; i++) {
            pool.incrementAges();

            // checking each fish
            for (Guppy guppy : guppies) {
                assertEquals(guppy.getAgeInWeeks(), i + 1);
            }
        }
    }

    @Test
    @DisplayName("[incrementAges()] Should ignore dead guppies when increasing age")
    void shouldIgnoreDeadGuppiesWhenIncreasingAge() {
        Guppy guppy = new Guppy();
        guppy.kill();

        Guppy[] guppies = createArrayOfGuppies(5);
        pool.add(guppy);
        pool.addAll(guppies);

        int deaths = pool.incrementAges();
        assertEquals(0, deaths);
        assertEquals(0, guppy.getAgeInWeeks());
    }

    @Test
    @DisplayName("[incrementAges()] Should return the number of dead guppies after incrementing the age")
    void shouldReturnTheNumberOfDeadGuppiesAfterIncrementingTheAge() {
        Guppy[] guppies = createArrayOfGuppies(5);

        pool.addAll(guppies);

        for (int i = 0; i < Guppy.MAXIMUM_AGE_IN_WEEKS - 1; i++) {
            pool.incrementAges();
        }

        // should be 5 since the 5 added guppies died at their maximum age
        int numberOfDeaths = pool.incrementAges();
        assertEquals(5, numberOfDeaths);
    }

    /*
        getGuppyVolumeRequirementInLitres() checklist:
        * (DONE) returns 0 if the pool is empty
        * (DONE) correctly ignores dead guppies
        * (DONE) calculates the volume for all the guppies in the pool
     */

    @Test
    @DisplayName("[getVolumeRequirementsInLitres()] Should return 0 if the pool is empty")
    void shouldReturn0IfThePoolIsEmpty() {
        assertEquals(0, pool.getGuppyVolumeRequirementInLitres());
    }

    @Test
    @DisplayName("[getVolumeRequirementsInLitres()] Should ignore dead guppies when calculating the volume")
    void shouldIgnoreDeadGuppiesWhenCalculatingTheVolume() {
        Guppy[] guppies = createArrayOfGuppies(5);

        for (Guppy guppy : guppies) {
            guppy.kill();
        }

        pool.addAll(guppies);


        assertEquals(0, pool.getGuppyVolumeRequirementInLitres());
    }

    @Test
    @DisplayName("[getVolumeRequirementsInLitres()] Should return the volume requirement of the guppies in the pool")
    void shouldReturnTheVolumeRequirementOfTheGuppiesInThePool() {
        Guppy[] guppies = createArrayOfGuppies(5);
        pool.addAll(guppies);

        double expected = 0.0;

        for (Guppy guppy : guppies) {
            expected += guppy.getVolumeNeededLitres();
        }

        assertEquals(expected, pool.getGuppyVolumeRequirementInLitres());
    }

    /*
        getWeakestFish() checklist:
        * (DONE) returns Optional.empty() if there are no guppies in the list
        * (DONE) returns the weakest guppy inside Optional<Guppy>
        * (DONE) ignores dead guppies.
     */

    @Test
    @DisplayName("[getWeakestFish()] Should return Optional.empty")
    void shouldReturnOptionalEmpty() {
        assertFalse(pool.getWeakestFish().isPresent());
    }

    @Test
    @DisplayName("Should return Optional empty since there are no alive guppies")
    void shouldReturnOptionalEmptySinceThereAreNoAliveGuppies() {

        final Guppy[] arrayOfGuppies = createArrayOfGuppies(5);

        for (Guppy guppy : arrayOfGuppies) {
            guppy.kill();
        }

        pool.addAll(arrayOfGuppies);

        assertFalse(pool.getWeakestFish().isPresent());
    }

    @Test
    @DisplayName("[getWeakestGuppy()] Should find the weakest guppy")
    void shouldFindTheWeakestGuppy() {
        // the weakest guppy is the one with the lowest health coefficient

        Guppy guppyA = createGuppyWithXHealthCoefficient(0); // minimum health coefficient
        Guppy guppyB = createGuppyWithXHealthCoefficient(0.5);
        Guppy guppyC = createGuppyWithXHealthCoefficient(1); // maximum health coefficient

        pool.add(guppyA);
        assertEquals(guppyA, pool.getWeakestFish().get());

        pool.add(guppyB);
        assertEquals(guppyA, pool.getWeakestFish().get());

        pool.add(guppyC);
        assertEquals(guppyA, pool.getWeakestFish().get());
    }

    /*
        adjustForCrowding() checklist:
        * (DONE) should do nothing if the current volume is enough for sustaining the current population
        * (DONE) should ignore dead guppies
        * (DONE) should kill the weaker guppies until the required volume is enough
     */

    @Test
    @DisplayName("[adjustForCrowding()] Should do nothing if the volume is enough")
    void shouldDoNothingIfTheVolumeIsEnough() {
        assertEquals(0, pool.adjustForCrowding());

        int ageCaseA = 5;
        int numberOfHoldableGuppies = getNumberOfHoldableGuppies(pool.getVolumeLitres(), ageCaseA);
        for (int i = 0; i < numberOfHoldableGuppies; i++) {
            pool.add(createGuppyWithNWeeks(ageCaseA));
        }

        assertEquals(0, pool.adjustForCrowding());

        setUp();
        int ageCaseB = 20;
        int numberOfHoldableGuppiesCaseB = getNumberOfHoldableGuppies(pool.getVolumeLitres(), ageCaseB);
        for (int i = 0; i < numberOfHoldableGuppiesCaseB; i++) {
            pool.add(createGuppyWithNWeeks(ageCaseB));
        }

        assertEquals(0, pool.adjustForCrowding());

        setUp();
        int ageCaseC = 50;
        int numberOfHoldableGuppiesCaseC = getNumberOfHoldableGuppies(pool.getVolumeLitres(), ageCaseC);
        for (int i = 0; i < numberOfHoldableGuppiesCaseC; i++) {
            pool.add(createGuppyWithNWeeks(ageCaseC));
        }

        assertEquals(0, pool.adjustForCrowding());
    }

    @Test
    @DisplayName("[adjustForCrowding()] Should ignore the dead guppies when adjusting")
    void shouldIgnoreTheDeadGuppiesWhenAdjusting() {
        Guppy[] guppies = createArrayOfGuppies(5);

        for (Guppy guppy : guppies) {
            guppy.kill();
        }

        pool.addAll(guppies);


        assertEquals(0, pool.adjustForCrowding());
    }

    @Test
    @DisplayName("[adjustForCrowding()] Should kill the weakest guppy until theres enough volume")
    void shouldKillTheWeakestGuppyUntilTheresEnoughVolume() {
        Guppy shouldDie = createGuppyWithXHealthCoefficient(0);

        int holdableGuppies = getNumberOfHoldableGuppies(pool.getVolumeLitres(), 0);

        for (int i = 0; i < holdableGuppies; i++) {
            pool.add(new Guppy());
        }

        pool.add(shouldDie);

        assertEquals(1, pool.adjustForCrowding());
        assertTrue(shouldDie.isDead());
    }

    /*
        getAverageAge() checklist:
        * (DONE) returns 0 when there are no guppies
        * (DONE) ignores dead guppies
        * (DONE) correctly calculates the average age of all guppies
     */

    @Test
    @DisplayName("[getAverageAge()] Should return 0 when there is no population")
    void shouldReturn0WhenThereIsNoPopulation() {
        assertEquals(0, pool.getAverageAge());
    }

    @Test
    @DisplayName("[getAverageAge()] Should ignore dead guppies")
    void shouldIgnoreDeadGuppies() {
        Guppy[] guppies = createArrayOfGuppies(5);

        pool.addAll(guppies);

        for (Guppy guppy : guppies) {
            guppy.kill();
        }

        assertEquals(0, pool.getAverageAge());
    }

    @Test
    @DisplayName("[getAverageAge()] Should calculate the average age")
    void shouldCalculateTheAverageAge() {
        pool.add(new Guppy());
        pool.add(new Guppy());
        pool.add(new Guppy());
        assertEquals(0, pool.getAverageAge());

        setUp(); // restarting the pool

        pool.add(createGuppyWithNWeeks(0));
        pool.add(createGuppyWithNWeeks(1));
        assertEquals(0.5, pool.getAverageAge());

        setUp();

        pool.add(createGuppyWithNWeeks(10));
        pool.add(createGuppyWithNWeeks(20));
        pool.add(createGuppyWithNWeeks(30));
        assertEquals(20, pool.getAverageAge());
    }

    /*
        getAverageHealthCoefficient() checklist:
        * (DONE) returns 0 when there are no guppies
        * (DONE) ignores dead guppies
        * (DONE) correctly calculates the average health coefficient of all guppies
     */

    @Test
    @DisplayName("[getAverageHealthCoefficient()] Should return 0 when no population")
    void shouldReturn0WhenNoPopulation() {
        assertEquals(0, pool.getAverageHealthCoefficient());
    }

    @Test
    @DisplayName("[getAverageHealthCoefficient()] Should ignore dead guppies when calculating the average health coefficient")
    void shouldIgnoreDeadGuppiesWhenCalculatingTheAverageHealthCoefficient() {
        Guppy[] guppies = createArrayOfGuppies(5);

        for (Guppy guppy : guppies) {
            guppy.kill();
        }

        pool.addAll(guppies);

        assertEquals(0, pool.getAverageHealthCoefficient());
    }

    @Test
    @DisplayName("[getAverageHealthCoefficient()] Should calculate the average health coefficient")
    void shouldCalculateTheAverageHealthCoefficient() {
        pool.add(new Guppy());
        pool.add(new Guppy());
        pool.add(new Guppy());
        assertEquals(0.5, pool.getAverageHealthCoefficient());

        setUp(); // restarting the pool

        pool.add(createGuppyWithXHealthCoefficient(0));
        pool.add(createGuppyWithXHealthCoefficient(1));
        assertEquals(0.5, pool.getAverageHealthCoefficient());

        setUp();

        pool.add(createGuppyWithXHealthCoefficient(0.25));
        pool.add(createGuppyWithXHealthCoefficient(0.50));
        pool.add(createGuppyWithXHealthCoefficient(0.75));
        assertEquals(0.5, pool.getAverageHealthCoefficient());
    }

    /*
        getFemalePercentage() checklist:
        * (DONE) returns 0 if there is no population
        * (DONE) returns 0 if there are no female guppies
        * (DONE) returns the percentage of the female population
     */

    @Test
    @DisplayName("[getFemalePercentage()] Should return 0 if there is no population")
    void shouldReturn0IfThereIsNoPopulation() {
        assertEquals(0, pool.getFemalePercentage());
    }

    @Test
    @DisplayName("[getFemalePercentage()] Should return 0 if there are no female guppies")
    void shouldReturn0IfThereAreNoFemaleGuppies() {
        pool.add(Guppy.createMale());
        pool.add(Guppy.createMale());
        pool.add(Guppy.createMale());
        pool.add(Guppy.createMale());
        pool.add(Guppy.createMale());

        assertEquals(0, pool.getFemalePercentage());
    }

    @Test
    @DisplayName("[getFemalePercentage()] Should calculate the percent of female guppies")
    void shouldCalculateThePercentOfFemaleGuppies() {
        pool.add(new Guppy()); // female by default
        pool.add(Guppy.createMale());

        assertEquals(50, pool.getFemalePercentage());

        setUp(); // restarting the pool

        pool.add(new Guppy());
        assertEquals(100, pool.getFemalePercentage());

        pool.add(Guppy.createMale());
        pool.add(Guppy.createMale());
        pool.add(Guppy.createMale());
        assertEquals(25, pool.getFemalePercentage());
    }

    /*
        getMedianAge() checklist:
        * (DONE) returns 0 if there is no population
        * (DONE) ignores dead guppies
        * (DONE) for n % 2 == 0, being n the number of guppies, should return
          the sum of the guppy's ages in the indexes: n / 2 and (n / 2) - 1
          divided by two

          for example:
          1 2 3 4 5 6 7 8
               (4+5) / 2

          1 2 3 4
           (2+3) / 2

        * (DONE) for n % 2 != 0, should return the age of the guppy
          in the index (n - 1) / 2

          for example:
          1 2 3
            ^
            
          1 2 3 4 5 6 7
                ^
     */

    @Test
    @DisplayName("[getMedianAge()] Should return 0 if there is no population when getting the median age")
    void shouldReturn0IfThereIsNoPopulationWhenGettingTheMedianAge() {
        assertEquals(0, pool.getMedianAge());
    }

    @Test
    @DisplayName("[getMedianAge()] Should ignore dead guppies when getting the median age")
    void shouldIgnoreDeadGuppiesWhenGettingTheMedianAge() {
        Guppy[] guppies = createArrayOfGuppies(5);
        pool.addAll(guppies);
        for (Guppy g : guppies) {
            g.kill();
        }
        assertEquals(0, pool.getMedianAge());
    }

    @Test
    @DisplayName("[getMedianAge()] Should return the correct median age for even number of guppies")
    void shouldReturnTheCorrectMedianAgeForEvenNumberOfGuppies() {
        pool.add(createGuppyWithNWeeks(1));
        pool.add(createGuppyWithNWeeks(2));

        assertEquals(1.5, pool.getMedianAge());

        pool.add(createGuppyWithNWeeks(3));
        pool.add(createGuppyWithNWeeks(4));
        pool.add(createGuppyWithNWeeks(5));
        pool.add(createGuppyWithNWeeks(6));
        pool.add(createGuppyWithNWeeks(7));
        pool.add(createGuppyWithNWeeks(8));

        assertEquals(4.5, pool.getMedianAge());

        pool.add(createGuppyWithNWeeks(9));
        pool.add(createGuppyWithNWeeks(10));

        assertEquals(5.5, pool.getMedianAge());
    }

    @Test
    @DisplayName("[getMedianAge()] Should return the correct median age for odd number of guppies")
    void shouldReturnTheCorrectMedianAgeForOddNumberOfGuppies() {
        pool.add(createGuppyWithNWeeks(1));
        assertEquals(1, pool.getMedianAge());

        pool.add(createGuppyWithNWeeks(2));
        pool.add(createGuppyWithNWeeks(3));

        assertEquals(2, pool.getMedianAge());

        pool.add(createGuppyWithNWeeks(4));
        pool.add(createGuppyWithNWeeks(5));
        pool.add(createGuppyWithNWeeks(6));
        pool.add(createGuppyWithNWeeks(7));

        assertEquals(4, pool.getMedianAge());
    }
}
