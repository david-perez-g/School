package com.school.comp2522.assignments.a3;

public class MultiplicationOperation extends AbstractOperation {
    public MultiplicationOperation() {
        super(OperationSymbol.MULTIPLICATION_CODE);
    }

    @Override
    public double perform(double operandA, double operandB) throws ZeroDivisionException {
        return operandA * operandB;
    }
}
