package com.school.comp2522.assignments.a2;

public class Driver {
    public static void main(String[] args) {
        Ecosystem ecosystem = new Ecosystem();
        ecosystem.setupSimulation();
        ecosystem.simulate(100);
    }
}
