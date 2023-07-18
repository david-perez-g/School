package com.school.comp2522.labs.lab05;

import com.school.comp2522.labs.lab02.Mathematics;

public class Die {
    public byte number;

    public Die() {
        roll();
    }

    void roll() {
        number = (byte) Mathematics.getRandomIntBetween(1, 6);
    }
}
