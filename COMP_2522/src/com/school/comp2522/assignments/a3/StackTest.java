package com.school.comp2522.assignments.a3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void capacity() {
        assertThrows(IllegalArgumentException.class, () -> new Stack<Integer>(0));
        assertThrows(IllegalArgumentException.class, () -> new Stack<Integer>(-1));
        assertDoesNotThrow(() -> new Stack<Integer>(2));
        assertDoesNotThrow(() -> new Stack<Integer>(5));
        assertDoesNotThrow(() -> new Stack<Integer>(10));

        Stack<Integer> stack = new Stack<>(2);
        assertEquals(2, stack.capacity());

        stack = new Stack<>(25);
        assertEquals(25, stack.capacity());
    }

    @Test
    void size() {
        Stack<Integer> stack = new Stack<>(10);
        assertEquals(0, stack.size());

        stack.push(0);
        assertEquals(1, stack.size());

        stack.push(0);
        assertEquals(2, stack.size());

        stack.push(0);
        assertEquals(3, stack.size());

        stack.push(0);
        assertEquals(4, stack.size());
    }

    @Test
    void unused() {
        Stack<Integer> stack = new Stack<>(5);
        assertEquals(5, stack.unused());

        stack.push(0);
        assertEquals(4, stack.unused());

        stack.push(0);
        stack.push(0);
        stack.push(0);
        stack.push(0);

        assertEquals(0, stack.unused());
    }

    @Test
    void get() {
        Stack<Object> stack = new Stack<>(4);
        assertThrows(IndexOutOfBoundsException.class, () -> stack.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> stack.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> stack.get(1));

        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();

        stack.push(o1);
        stack.push(o2);
        stack.push(o3);

        assertEquals(o1, stack.get(0));
        assertEquals(o2, stack.get(1));
        assertEquals(o3, stack.get(2));

        stack.pop();
        stack.pop();
        stack.pop();

        assertThrows(IndexOutOfBoundsException.class, () -> stack.get(0));
    }

    @Test
    void push() {
        Stack<Integer> stack = new Stack<>(3);
        stack.push(2);
        assertEquals(2, stack.get(0));

        stack.push(5);
        assertEquals(5, stack.get(1));

        stack.push(10);
        assertEquals(10, stack.get(2));

        assertThrows(StackOverflowException.class, () -> stack.push(1));
    }

    @Test
    void pop() {
        Stack<Integer> stack = new Stack<>(5);
        assertThrows(StackUnderflowException.class, stack::pop);

        stack.push(2);
        assertEquals(1, stack.size());
        assertEquals(2, stack.pop());
        assertEquals(0, stack.size());

        assertThrows(IndexOutOfBoundsException.class, () -> stack.get(0));

        stack.push(1);
        assertEquals(1, stack.peek());

    }

    @Test
    void peek() {
        Stack<Integer> stack = new Stack<>(5);
        assertThrows(RuntimeException.class, stack::peek);

        stack.push(5);
        assertEquals(5, stack.peek());

        stack.push(10);
        assertEquals(10, stack.peek());
    }
}