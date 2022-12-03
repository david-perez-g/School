package com.school.comp2522.assignments.a3;

public interface Operation {
    String getSymbol();

    double perform(double operandA, double operandB) throws ZeroDivisionException;
}
