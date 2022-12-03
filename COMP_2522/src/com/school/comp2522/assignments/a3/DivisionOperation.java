package com.school.comp2522.assignments.a3;

public class DivisionOperation extends AbstractOperation {
    public DivisionOperation() {
        super(OperationSymbol.DIVISION_CODE);
    }

    @Override
    public double perform(double operandA, double operandB) throws ZeroDivisionException {
        if (operandB == 0) {
            throw new ZeroDivisionException();
        }
        return operandA / operandB;
    }
}
