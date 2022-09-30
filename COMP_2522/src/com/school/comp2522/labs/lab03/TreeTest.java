package com.school.comp2522.labs.lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link com.school.comp2522.labs.lab03.Tree}
 *
 * @author david-perez-g
 * @version 1.0
 */
class TreeTest {

    private Tree testTree;

    @BeforeEach
    void setUp() {
        final int DEFAULT_AGE = 100;
        final double DEFAULT_TRUNK_SIZE = 1000;
        testTree = new Tree(DEFAULT_AGE, DEFAULT_TRUNK_SIZE);
    }

    @Test
    void testIncreaseAge() {
        int oldAge = testTree.getAgeInYears();
        testTree.increaseAgeInYears();
        assertEquals(++oldAge, testTree.getAgeInYears());

        testTree.increaseAgeInYears();
        assertEquals(++oldAge, testTree.getAgeInYears());

        testTree.increaseAgeInYears();
        assertEquals(++oldAge, testTree.getAgeInYears());

        testTree.increaseAgeInYears();
        assertEquals(++oldAge, testTree.getAgeInYears());

        testTree.increaseAgeInYears();
        assertEquals(++oldAge, testTree.getAgeInYears());

    }

    @Test
    void testIncreaseTrunkSizeByCorrectInput() {
        double oldTrunkSize = testTree.getTrunkInCentimeters();
        double increaseBy = 20.0;
        testTree.increaseTrunkSizeBy(increaseBy);
        assertEquals(oldTrunkSize + increaseBy, testTree.getTrunkInCentimeters());

        double oldTrunkSizeCase2 = testTree.getTrunkInCentimeters();
        double increaseByCase2 = 14.0;
        testTree.increaseTrunkSizeBy(increaseByCase2);
        assertEquals(oldTrunkSizeCase2 + increaseByCase2, testTree.getTrunkInCentimeters());

        double oldTrunkSizeCase3 = testTree.getTrunkInCentimeters();
        double increaseByCase3 = 2.0;
        testTree.increaseTrunkSizeBy(increaseByCase3);
        assertEquals(oldTrunkSizeCase3 + increaseByCase3, testTree.getTrunkInCentimeters());
    }

    @Test
    void testIncreaseTrunkSizeByIllegalInput() {
        assertThrows(IllegalArgumentException.class, () -> testTree.increaseTrunkSizeBy(0));
        assertThrows(IllegalArgumentException.class, () -> testTree.increaseTrunkSizeBy(-5));
        assertThrows(IllegalArgumentException.class, () -> testTree.increaseTrunkSizeBy(-25));
    }

    @Test
    void testShouldBeEqual() {
        Tree t1 = new Tree(Tree.Species.MAPLE, 10, 25);
        Tree t2 = new Tree(Tree.Species.MAPLE, 10, 25);
        assertEquals(t1, t2);

        Tree t3 = new Tree(24, 10);
        Tree t4 = t3;
        assertEquals(t3, t4);

        Tree t5 = new Tree(24, 10);
        Tree t6 = new Tree(24, 10);
        assertEquals(t5, t6);

        Tree t7 = new Tree(Tree.Species.BLUE_SPRUCE, 0, 100);
        Tree t8 = new Tree(Tree.Species.BLUE_SPRUCE, 0, 100);
        assertEquals(t7, t8);

    }

    @Test
    void testShouldNotBeEqual() {
        // different species
        Tree t1 = new Tree(Tree.Species.BLUE_SPRUCE, 10, 25);
        Tree t2 = new Tree(Tree.Species.MAPLE, 10, 25);
        assertNotEquals(t1, t2);

        // different age
        Tree t3 = new Tree(Tree.Species.BLUE_SPRUCE, 12, 25);
        Tree t4 = new Tree(Tree.Species.BLUE_SPRUCE, 10, 25);
        assertNotEquals(t3, t4);

        // different trunk size
        Tree t5 = new Tree(Tree.Species.MAPLE, 10, 25);
        Tree t6 = new Tree(Tree.Species.MAPLE, 10, 1);
        assertNotEquals(t5, t6);

        Tree t7 = new Tree(Tree.Species.BLUE_SPRUCE, 10, 25);
        Tree t8 = new Tree(Tree.Species.MAPLE, 0, 30);
        assertNotEquals(t7, t8);


    }

    @Test
    void testCompareShouldBeBigger() {
        Tree tree1 = new Tree(10, testTree.getTrunkInCentimeters() + 1);
        assertTrue(testTree.compareTo(tree1) < 0);

        Tree tree2 = new Tree(10, tree1.getTrunkInCentimeters() + 10);
        assertTrue(testTree.compareTo(tree2) < 0);

        Tree tree3 = new Tree(10, tree2.getTrunkInCentimeters() + 100);
        assertTrue(testTree.compareTo(tree3) < 0);

        assertTrue(tree1.compareTo(tree2) < 0);
        assertTrue(tree1.compareTo(tree2) < 0);
        assertTrue(tree2.compareTo(tree3) < 0);

    }

    @Test
    void testCompareShouldBeSmaller() {
        Tree tree1 = new Tree(10, testTree.getTrunkInCentimeters() - 1);
        assertTrue(testTree.compareTo(tree1) > 0);

        Tree tree2 = new Tree(10, tree1.getTrunkInCentimeters() - 10);
        assertTrue(testTree.compareTo(tree2) > 0);

        Tree tree3 = new Tree(10, tree2.getTrunkInCentimeters() - 100);
        assertTrue(testTree.compareTo(tree3) > 0);

        assertTrue(tree1.compareTo(tree2) > 0);
        assertTrue(tree1.compareTo(tree2) > 0);
        assertTrue(tree2.compareTo(tree3) > 0);
    }

    @Test
    void testCompareShouldBeEqual() {
        Tree tree1 = new Tree(10, testTree.getTrunkInCentimeters());
        assertEquals(0, testTree.compareTo(tree1));
        assertEquals(0, tree1.compareTo(testTree));

        Tree tree2 = new Tree(14, 20);
        Tree tree3 = new Tree(14, 20);
        assertEquals(0, tree2.compareTo(tree3));
        assertEquals(0, tree3.compareTo(tree2));

        Tree tree4 = new Tree(15, 100);
        Tree tree5 = new Tree(15, 100);
        assertEquals(0, tree4.compareTo(tree5));
        assertEquals(0, tree5.compareTo(tree4));
    }
}