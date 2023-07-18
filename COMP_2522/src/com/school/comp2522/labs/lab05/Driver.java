package com.school.comp2522.labs.lab05;

public class Driver {
    public static void main(String[] args) {
        final Counter counter = new Counter();
        counter.roll(1_000_000);
        counter.frequencyTable.forEach((dieNumber, frequency) ->
                System.out.println(dieNumber + ": " + frequency));
    }
}
