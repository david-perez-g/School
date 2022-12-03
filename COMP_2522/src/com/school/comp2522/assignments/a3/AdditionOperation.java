package com.school.comp2522.assignments.a3;

public class AdditionOperation extends AbstractOperation {
    public AdditionOperation() {
        super(OperationSymbol.ADDITION_CODE);
    }

    @Override
    public double perform(double operandA, double operandB) throws ZeroDivisionException {
        return operandA + operandB;
    }
}
