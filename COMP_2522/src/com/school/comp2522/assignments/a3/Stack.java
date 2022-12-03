package com.school.comp2522.assignments.a3;

import static com.school.comp2522.assignments.a3.RPNCalculator.MIN_STACK_SIZE;

/**
 * @author david-perez-g
 * @version 1.0
 */
public class Stack<T> {
    private final Object[] stack;
    private int lastAddedItemIndex = -1;

    public Stack(int size) throws IllegalArgumentException {
        if (size < MIN_STACK_SIZE) {
            throw new IllegalArgumentException("Stack size must be at least " + MIN_STACK_SIZE);
        }

        this.stack = new Object[size];
    }

    private T elementAt(int index) {
        return (T) stack[index];
    }

    /**
     * Returns the capacity of the stack.
     */
    public int capacity() {
        return stack.length;
    }

    /**
     * Returns the number of items in the stack.
     */
    public int size() {
        return lastAddedItemIndex + 1;
    }

    /**
     * Returns the number of space available in the stack.
     */
    public int unused() {
        return capacity() - size();
    }

    /**
     * Returns the element at the given index.
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > lastAddedItemIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return elementAt(index);
    }

    /**
     * Adds the given element to the end of the stack.
     */
    public void push(T item) throws StackOverflowException {
        if (unused() == 0) {
            throw new StackOverflowException("Stack out of space");
        }
        stack[++lastAddedItemIndex] = item;
    }

    /**
     * Removes the element at the top of the stack and returns it.
     *
     * @throws StackUnderflowException if the stack has no elements
     */
    public T pop() throws StackUnderflowException {
        if (lastAddedItemIndex < 0) {
            throw new StackUnderflowException("There are no items in the stack to pop");
        }
        T removedItem = elementAt(lastAddedItemIndex);
        stack[lastAddedItemIndex] = null;
        lastAddedItemIndex--;
        return removedItem;
    }

    /**
     * Returns the element at the top of the stack.
     *
     * @throws RuntimeException if the stack is empty
     */
    public T peek() throws RuntimeException {
        if (size() == 0) {
            throw new RuntimeException("There are no items in the stack to peek");
        }

        return elementAt(lastAddedItemIndex);
    }

    /**
     * Sets every element of the stack to null.
     */
    public void clear() {
        if (size() == 0) {
            return;
        }

        for (int i = 0; i < size(); i++) {
            this.stack[i] = null;
        }

        lastAddedItemIndex = -1;
    }
}
