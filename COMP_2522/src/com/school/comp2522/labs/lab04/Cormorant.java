package com.school.comp2522.labs.lab04;

public class Cormorant implements Aerial, Aquatic, Terrestrial {
    @Override
    public void fly() {
        System.out.println("Flying");
    }

    @Override
    public void swim() {
        System.out.println("Swimming");
    }

    @Override
    public void walk() {
        System.out.println("Walking");
    }
}
