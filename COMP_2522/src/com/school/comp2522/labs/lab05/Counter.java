package com.school.comp2522.labs.lab05;

import java.util.HashMap;

public class Counter {
    private final Die unbiasedDie;
    public final HashMap<Byte, Integer> frequencyTable;

    public Counter() {
        unbiasedDie = new Die();
        frequencyTable = new HashMap<>();
    }

    public void roll(int numberOfRolls) {
        for (int i = 0; i < numberOfRolls; i++) {
            unbiasedDie.roll();
            byte n = unbiasedDie.number;
            if (frequencyTable.containsKey(n)) {
                frequencyTable.put(n, frequencyTable.get(n) + 1);
            } else {
                frequencyTable.put(n, 1);
            }
        }
    }
}
