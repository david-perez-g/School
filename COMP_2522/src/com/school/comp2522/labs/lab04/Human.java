package com.school.comp2522.labs.lab04;

public class Human implements Terrestrial, Aquatic {
    @Override
    public void swim() {
        System.out.println("Swimming!");
    }

    @Override
    public void walk() {
        System.out.println("Walking");
    }
}
