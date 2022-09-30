package com.school.comp2522.labs.lab03;

import com.school.comp2522.labs.lab02.Mathematics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A plantation of trees.
 *
 * @author david-perez-g
 * @version 1.0
 */
public class Plantation {
    private final ArrayList<Tree> farm = new ArrayList<>();
    private int lastItemIndex = -1;

    private void increaseLastItemIndex() {
        lastItemIndex++;
    }

    /**
     * Returns the number of tries in the plantation.
     *
     * @return the number of tries in the plantation.
     */
    public int size() {
        return farm.size();
    }

    /**
     * Adds a tree to the plantation.
     *
     * @param t tree to be added to the plantation
     * @return the index of the added tree after adding it
     * @throws NullPointerException if the passed tree is null
     */
    public int add(Tree t) {
        if (t == null) {
            throw new NullPointerException("Tried to add a null Tree to the plantation");
        }
        farm.add(t);
        increaseLastItemIndex();
        return size() - 1;
    }

    /**
     * Adds an array of trees to the plantation.
     *
     * @param trees array of trees to be added to the plantation
     * @return the index of the last added tree after adding it.
     * @throws NullPointerException if trees is null
     */
    public int addMany(Tree[] trees) {
        if (trees == null) {
            throw new NullPointerException("Tried to add a null Tree to the plantation");
        }

        for (Tree t : trees) {
            farm.add(t);
            increaseLastItemIndex();
        }

        return size() - 1;
    }

    /**
     * Plant a random number of randomly generated trees.
     *
     * @return the number of trees planted.
     */
    public int seed() {
        final int LIMIT = Mathematics.getRandomIntBetween(10, 1001);

        for (int i = 0; i < LIMIT; i++) {
            add(Tree.makeRandomTree());
        }

        return LIMIT;
    }

    /**
     * Gets the tree at the specified index.
     *
     * @param index index of the tree
     * @return the wanted tree
     * @throws IllegalArgumentException if index is out of bounds
     */
    public Tree get(int index) {
        if (index > lastItemIndex || index < 0) {
            throw new IllegalArgumentException("index out of range");
        }
        return farm.get(index);
    }

    private List<Tree> getOrderedTrees() {
        return farm
                .stream()
                .sorted(Tree::compareTo)
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of trees with the given cutOffCircumference.
     *
     * @param cutOffCircumference the trunk size ready to be cut off
     * @return the number of trees ready to be cut off
     */
    public long harvestCount(double cutOffCircumference) {
        if (farm.isEmpty()) {
            return 0;
        }

        final List<Tree> sortedFarm = getOrderedTrees();

        int startIndex = 0;
        int endIndex = size() - 1;

        while (startIndex <= endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            double middleItem = sortedFarm.get(middleIndex).getTrunkInCentimeters();

            if (startIndex == middleIndex && middleIndex == endIndex && middleItem >= cutOffCircumference) {
                return size() - middleIndex;
            }

            if (middleItem > cutOffCircumference) {
                endIndex = middleIndex - 1;
            } else if (middleItem < cutOffCircumference) {
                startIndex = middleIndex + 1;
            } else {
                return size() - middleIndex;
            }
        }
        return 0;
    }
}
