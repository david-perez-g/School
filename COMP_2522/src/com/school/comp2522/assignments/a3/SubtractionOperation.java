package com.school.comp2522.assignments.a3;

public class SubtractionOperation extends AbstractOperation {
    public SubtractionOperation() {
        super(OperationSymbol.SUBTRACTION_CODE);
    }

    @Override
    public double perform(double operandA, double operandB) throws ZeroDivisionException {
        return operandA - operandB;
    }
}
