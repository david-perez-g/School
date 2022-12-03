package com.school.comp2522.assignments.a3;

/**
 * @author david-perez-g
 * @version 1.0
 */
public class Main {
    /**
     * Drives the program by evaluating the RPN calculation provided as
     * a command line argument.
     * Example usage: RPNCalculator 10 "1 2 +"
     * Note that the formula MUST be placed inside double quotes.
     *
     * @param argv - the command line arguments are the size of the Stack
     *             to be created followed by the expression to evaluate.
     */
    public static void main(final String[] argv) {
        // Checks for correct number of command line arguments.
        if (argv.length != 2) {
            System.err.println("Usage: Main <stack size> <formula>");
            System.exit(1);
        }
        // Initializes stack and RPNCalculator.
        final int stackSize = Integer.parseInt(argv[0]);

        if (stackSize < RPNCalculator.MIN_STACK_SIZE) {
            System.out.println("invalid stack size");
            return;
        }

        final RPNCalculator calculator = new RPNCalculator(stackSize);
        try {
            System.out.println("[" + argv[1] + "] = "
                    + calculator.processFormula(argv[1]));
        } catch (InvalidOperationTypeException ignored) {
            System.err.println("formula can only contain integers, +, -, *, and /");
        } catch (StackOverflowException ignored) {
            System.err.println("too many operands in the formula, increase the stack size");
        } catch (StackUnderflowException ignored) {
            System.err.println("too few operands in the formula");
        } catch (ZeroDivisionException ignored) {
            System.out.println("attempted to divide by zero");
        }
    }
}
