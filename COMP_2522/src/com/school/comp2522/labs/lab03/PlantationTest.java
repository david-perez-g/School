package com.school.comp2522.labs.lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link com.school.comp2522.labs.lab03.Plantation}
 *
 * @author david-perez-g
 * @version 1.0
 */
class PlantationTest {

    private Plantation testFarm;

    @BeforeEach
    void setUp() {
        testFarm = new Plantation();
    }

    @Test
    void testSizeEmptyFarm() {
        assertEquals(0, testFarm.size());
    }

    @Test
    void testSize1TreeFarm() {
        testFarm.add(Tree.makeRandomTree());
        assertEquals(1, testFarm.size());
    }

    @Test
    void testSizeManyTreesFarm() {
        for (int i = 0; i < 100; i++) {
            testFarm.add(Tree.makeRandomTree());
        }
        assertEquals(100, testFarm.size());
    }

    @Test
    void testGet() {
        Tree tree1 = Tree.makeRandomTree();
        Tree tree2 = Tree.makeRandomTree();
        Tree tree3 = Tree.makeRandomTree();
        Tree tree4 = Tree.makeRandomTree();

        testFarm.add(tree1);
        testFarm.add(tree2);
        testFarm.add(tree3);
        testFarm.add(tree4);

        assertEquals(tree1, testFarm.get(0));
        assertEquals(tree2, testFarm.get(1));
        assertEquals(tree3, testFarm.get(2));
        assertEquals(tree4, testFarm.get(3));
    }

    @Test
    void testAddCorrectlyAddsTrees() {
        for (int i = 0; i < 1000; i++) {
            final Tree tree = Tree.makeRandomTree();
            final int atIndex = testFarm.add(tree);
            assertEquals(tree, testFarm.get(atIndex));
        }
    }

    @Test
    void testAddMany() {
        final Tree[] trees = {
                new Tree(1, 10),
                new Tree(1, 15),
                new Tree(1, 20),
                new Tree(1, 20),
                new Tree(1, 25),
                new Tree(1, 30)
        };

        final int lastAddedIndex = testFarm.addMany(trees);
        assertEquals(5, lastAddedIndex);

        assertEquals(trees[0], testFarm.get(0));
        assertEquals(trees[1], testFarm.get(1));
        assertEquals(trees[2], testFarm.get(2));
        assertEquals(trees[3], testFarm.get(3));
        assertEquals(trees[4], testFarm.get(4));
        assertEquals(trees[5], testFarm.get(5));
    }

    @Test
    void testHarvestCountCase1() {
        final Tree[] trees = {
                new Tree(1, 10),
                new Tree(1, 15),
                new Tree(1, 20),
                new Tree(1, 20),
                new Tree(1, 25),
                new Tree(1, 30)
        };

        testFarm.addMany(trees);

        final double CUT_OFF_CIRCUMFERENCE = 25;
        final int EXPECTED_RESULT = 2;

        assertEquals(EXPECTED_RESULT, testFarm.harvestCount(CUT_OFF_CIRCUMFERENCE));
    }

    @Test
    void testHarvestCountCase2() {
        final Tree[] trees = {
                new Tree(1, 10),
                new Tree(1, 12),
                new Tree(1, 22),
                new Tree(1, 24),
                new Tree(1, 25),
                new Tree(1, 30)
        };

        testFarm.addMany(trees);

        final double CUT_OFF_CIRCUMFERENCE = 11;
        final int EXPECTED_RESULT = 5;

        assertEquals(EXPECTED_RESULT, testFarm.harvestCount(CUT_OFF_CIRCUMFERENCE));
    }

    /**
     * An algorithm that produces the same result as harvestCount
     * for testing purposes that works 100% of the times but slower.
     */
    long harvestCountLinearAlgorithm(Tree[] trees, double cutOffCircumference) {
        int count = 0;
        for (Tree t : trees) {
            if (t.getTrunkInCentimeters() >= cutOffCircumference) {
                count++;
            }
        }
        return count;
    }

    @Test
    void testHarvestCountCase3() {
        final Tree[] trees = {
                new Tree(1, 12),
                new Tree(1, 21),
                new Tree(1, 23),
                new Tree(1, 24),
                new Tree(1, 54),
                new Tree(1, 100)
        };

        testFarm.addMany(trees);

        final double CUT_OFF_CIRCUMFERENCE = 100;
        final int EXPECTED_RESULT = (int) harvestCountLinearAlgorithm(trees, CUT_OFF_CIRCUMFERENCE);

        assertEquals(EXPECTED_RESULT, testFarm.harvestCount(CUT_OFF_CIRCUMFERENCE));
    }

    @Test
    void testHarvestCountCase4() {
        final Tree[] trees = {
                new Tree(1, 10),
                new Tree(1, 15),
                new Tree(1, 20),
                new Tree(1, 34),
                new Tree(1, 43),
                new Tree(1, 46),
                new Tree(1, 59)
        };

        testFarm.addMany(trees);

        final double CUT_OFF_CIRCUMFERENCE = 34;
        final int EXPECTED_RESULT = (int) harvestCountLinearAlgorithm(trees, CUT_OFF_CIRCUMFERENCE);

        assertEquals(EXPECTED_RESULT, testFarm.harvestCount(CUT_OFF_CIRCUMFERENCE));
    }

    @Test
    void testHarvestCountCase5() {
        final Tree[] trees = {
                new Tree(1, 10),
                new Tree(1, 15),
                new Tree(1, 20),
                new Tree(1, 34),
                new Tree(1, 43),
                new Tree(1, 46),
                new Tree(1, 59)
        };

        testFarm.addMany(trees);

        final double CUT_OFF_CIRCUMFERENCE = 10;
        final int EXPECTED_RESULT = (int) harvestCountLinearAlgorithm(trees, CUT_OFF_CIRCUMFERENCE);

        assertEquals(EXPECTED_RESULT, testFarm.harvestCount(CUT_OFF_CIRCUMFERENCE));
    }

    @Test
    void testHarvestCount0TreesFoundCase1() {
        final Tree[] trees = {
                new Tree(1, 10),
                new Tree(1, 15),
                new Tree(1, 20),
                new Tree(1, 34),
                new Tree(1, 43),
                new Tree(1, 46),
                new Tree(1, 59)
        };

        testFarm.addMany(trees);

        final double CUT_OFF_CIRCUMFERENCE = 10000;
        final int EXPECTED_RESULT = (int) harvestCountLinearAlgorithm(trees, CUT_OFF_CIRCUMFERENCE);

        assertEquals(EXPECTED_RESULT, testFarm.harvestCount(CUT_OFF_CIRCUMFERENCE));
    }

    @Test
    void testHarvestCount0TreesFoundCase2() {
        final Tree[] trees = {
                new Tree(1, 10),
                new Tree(1, 15),
                new Tree(1, 59)
        };

        testFarm.addMany(trees);

        final double CUT_OFF_CIRCUMFERENCE = 70;
        final int EXPECTED_RESULT = (int) harvestCountLinearAlgorithm(trees, CUT_OFF_CIRCUMFERENCE);

        assertEquals(EXPECTED_RESULT, testFarm.harvestCount(CUT_OFF_CIRCUMFERENCE));
    }

    @Test
    void testHarvestCount0TreesFoundCase3() {
        final Tree[] trees = {
                new Tree(1, 10),
                new Tree(1, 59)
        };

        testFarm.addMany(trees);

        final double CUT_OFF_CIRCUMFERENCE = 60;
        final int EXPECTED_RESULT = (int) harvestCountLinearAlgorithm(trees, CUT_OFF_CIRCUMFERENCE);

        assertEquals(EXPECTED_RESULT, testFarm.harvestCount(CUT_OFF_CIRCUMFERENCE));
    }


    @Test
    void testHarvestCountCaseEmptyFarm() {
        final double CUT_OFF_CIRCUMFERENCE = 25;
        final int EXPECTED_RESULT = 0;

        assertEquals(EXPECTED_RESULT, testFarm.harvestCount(CUT_OFF_CIRCUMFERENCE));
    }
}