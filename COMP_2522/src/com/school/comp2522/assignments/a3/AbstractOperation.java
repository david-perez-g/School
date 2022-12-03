package com.school.comp2522.assignments.a3;

public class AbstractOperation implements Operation {
    protected final String operationType;

    public AbstractOperation(String operationType) {
        this.operationType = operationType;
    }

    @Override
    final public String getSymbol() {
        return operationType;
    }

    @Override
    public double perform(double operandA, double operandB) throws ZeroDivisionException {
        return 0.0;
    }
}
