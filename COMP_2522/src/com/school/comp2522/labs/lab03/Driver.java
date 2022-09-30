package com.school.comp2522.labs.lab03;

import java.util.Scanner;

/**
 * Driver for lab03.
 *
 * @author david-perez-g
 * @version 1.0
 */
public class Driver {
    private static long getMinimumHarvestCircumference() {
        System.out.println("Enter the minimum harvest circumference:");
        Scanner scn = new Scanner(System.in);
        return scn.nextLong();
    }

    public static void main(String[] args) {
        final Plantation lotuslandLumber = new Plantation();

        lotuslandLumber.seed();
        lotuslandLumber.seed();
        lotuslandLumber.seed();
        lotuslandLumber.seed();
        lotuslandLumber.seed();

        System.out.println("After seeding, the plantation has " + lotuslandLumber.size() + " trees.");
        final long minimumHarvestCircumference = getMinimumHarvestCircumference();
        final long numberOfTreesReadyToBeHarvested = lotuslandLumber.harvestCount(minimumHarvestCircumference);
        System.out.println("There are " + numberOfTreesReadyToBeHarvested + " trees ready to be harvested.");
    }
}
