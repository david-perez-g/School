package com.school.comp2522.assignments.a5;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    @Test
    void testSize() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertEquals(0, list.size());
        list.pushLast(1);
        assertEquals(1, list.size());
        list.pushLast(2);
        assertEquals(2, list.size());
    }

    @Test
    void testIsEmpty() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertTrue(list.isEmpty());
        list.pushLast(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testPeekFirst() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushLast(1);
        assertEquals(1, list.peekFirst());
        list.pushFront(2);
        assertEquals(2, list.peekFirst());
    }

    @Test
    void testPeekLast() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushLast(1);
        assertEquals(1, list.peekLast());
        list.pushLast(2);
        assertEquals(2, list.peekLast());
    }

    @Test
    void testPushLast() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushLast(1);
        assertEquals(1, list.peekLast());
        assertEquals(1, list.size());
        list.pushLast(2);
        assertEquals(2, list.peekLast());
        assertEquals(2, list.size());
    }

    @Test
    void testPushFront() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushFront(1);
        assertEquals(1, list.peekFirst());
        assertEquals(1, list.size());
        list.pushFront(2);
        assertEquals(2, list.peekFirst());
        assertEquals(2, list.size());
    }

    @Test
    void testPopFirst() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushFront(1);
        assertEquals(1, list.popFirst());
        assertTrue(list.isEmpty());

        list.pushFront(2);
        list.pushFront(3);
        assertEquals(3, list.popFirst());
        assertFalse(list.isEmpty());
    }

    @Test
    void testPopLast() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushFront(1);
        assertEquals(1, list.popLast());
        assertTrue(list.isEmpty());

        list.pushFront(2);
        list.pushFront(3);
        assertEquals(2, list.popLast());
        assertFalse(list.isEmpty());
    }

    @Test
    void testInsert() {
        // Test insert at the beginning of the linked list
        DoublyLinkedList<Integer> llist = new DoublyLinkedList<>();
        llist.insert(10, 0);
        llist.insert(-10, 0);

        // Test insert at the end of the linked list
        llist.insert(-20, 2);

        // Test insert in the middle of the linked list
        llist.insert(-15, 2);

        // Test insert at invalid position
        assertThrows(IllegalArgumentException.class,
                () -> llist.insert(-15, 10));

        // Test insert at invalid position
        assertThrows(IllegalArgumentException.class,
                () -> llist.insert(-15, -10));

        // Test size of the linkedlist after insertion
        assertEquals(llist.size(), 4);

        // Test values of the linkedlist after insertion
        int[] expectedValues = {-10, 10, -15, -20};
        int i = 0;
        for (int value : llist) {
            assertEquals(value, expectedValues[i++]);
        }
    }

    @Test
    void testRemove() {
        // Test remove from an empty linkedlist
        DoublyLinkedList<Integer> llist = new DoublyLinkedList<>();
        assertNull(llist.remove(-10));

        // Test remove from a non-empty linkedlist
        llist.insert(-10, 0);
        llist.insert(-20, 0);
        llist.insert(-30, 0);

        // Test remove from the beginning of the linkedlist
        int removedValue = llist.remove(-30);
        assertEquals(-30, removedValue);

        // Test remove from the end of the linkedlist
        removedValue = llist.remove(-10);
        assertEquals(-10, removedValue);

        // Test remove from the middle of the linkedlist
        removedValue = llist.remove(-20);
        assertEquals(-20, removedValue);

        // Test size of the linkedlist after removals
        assertEquals(llist.size(), 0);

    }

    @Test
    void testRemoveAt() {
        // Test removeAt from an empty linkedlist
        DoublyLinkedList<Integer> llist = new DoublyLinkedList<>();
        assertThrows(IllegalArgumentException.class,
                () -> llist.removeAt(0));

        // Test removeAt from a non-empty linkedlist
        llist.insert(-10, 0);
        llist.insert(-20, 0);
        llist.insert(-30, 0);

        // Test removeAt from the beginning of the linkedlist
        int removedValue = llist.removeAt(0);
        assertEquals(-30, removedValue);

        // Test removeAt from the end of the linkedlist
        removedValue = llist.removeAt(1);
        assertEquals(-10, removedValue);

        // Test removeAt from the middle of the linkedlist
        removedValue = llist.removeAt(0);
        assertEquals(-20, removedValue);

        // Test size of the linkedlist after removals
        assertEquals(llist.size(), 0);
    }

    @Test
    void testIndexOf() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushLast(1);
        list.pushLast(2);
        list.pushLast(3);
        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(2, list.indexOf(3));
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    void testContains() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushLast(1);
        list.pushLast(2);
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertFalse(list.contains(3));
    }

    @Test
    void testClear() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushLast(1);
        list.pushLast(2);
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void testIterator() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushLast(1);
        list.pushLast(2);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
