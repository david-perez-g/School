package com.school.comp2522.assignments.a3;

import java.util.Scanner;

/**
 * A class that processes mathematical RPN formulas. <br>
 * More info about this at <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">RPN notation at Wikipedia</a>
 *
 * @author david-perez-g
 * @version 1.0
 */
public class RPNCalculator {
    final static byte MIN_STACK_SIZE = 2;

    final Stack<Double> stack;

    public RPNCalculator(int stackSize) throws IllegalArgumentException {
        if (stackSize < MIN_STACK_SIZE) {
            throw new IllegalArgumentException("stackSize must be >= " + MIN_STACK_SIZE);
        }
        this.stack = new Stack<>(stackSize);
    }

    /**
     * Processes the given formula in RPN notation and returns the result.
     *
     * @throws IllegalArgumentException      if formula is null or empty
     * @throws StackOverflowException        if the stack of operands ran out of space
     * @throws InvalidOperationTypeException if the formula contains an invalid operation or unknown symbol
     * @throws ZeroDivisionException         if the formula involved division by zero
     * @throws StackUnderflowException       if there were insufficient operands when doing an operation
     */
    public double processFormula(String formula) throws
            IllegalArgumentException,
            StackOverflowException,
            InvalidOperationTypeException,
            ZeroDivisionException,
            StackUnderflowException {
        if (formula == null || formula.isEmpty()) {
            throw new IllegalArgumentException("The passed formula is either null or empty");
        }
        stack.clear();
        Scanner scanner = new Scanner(formula);
        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                // may throw StackOverflowException
                push(scanner.nextDouble());
                continue;
            }

            // may throw InvalidOperationTypeException
            Operation operation = getOperation(scanner.next());
            perform(operation); // may throw StackUnderflowException
            // if the number of operands is insufficient
            // or ZeroDivisionException
        }
        return stack.peek();
    }

    private Operation getOperation(String operationString) throws InvalidOperationTypeException {
        switch (operationString) {
            case OperationSymbol.ADDITION_CODE:
                return new AdditionOperation();
            case OperationSymbol.SUBTRACTION_CODE:
                return new SubtractionOperation();
            case OperationSymbol.MULTIPLICATION_CODE:
                return new MultiplicationOperation();
            case OperationSymbol.DIVISION_CODE:
                return new DivisionOperation();
            default:
                throw new InvalidOperationTypeException("Unknown operation '" + operationString + "'");
        }
    }

    /**
     * Pushes the operand onto the stack.
     *
     * @throws StackOverflowException if there is no space available in the stack
     */
    private void push(double operand) throws StackOverflowException {
        if (stack.unused() == 0) {
            throw new StackOverflowException("Insufficient space available in the stack");
        }
        stack.push(operand);
    }

    /**
     * Performs the operation with the two top operands on the stack,
     * and puts the result at the top of the stack.
     *
     * @throws StackUnderflowException if there are no operands in the stack
     * @throws ZeroDivisionException   if the operation involved dividing by zero
     */
    private void perform(Operation operation) throws
            StackUnderflowException,
            ZeroDivisionException {
        if (stack.size() < 2) {
            throw new StackUnderflowException("insufficient operands to do the operation");
        }

        double operandB = stack.pop();
        double operandA = stack.pop();
        double result = operation.perform(operandA, operandB); // may throw ZeroDivisionException
        stack.push(result);
    }
}
