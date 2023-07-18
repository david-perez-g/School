package com.school.comp2522.labs.lab06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    private LinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testContains() {
        assertFalse(list.contains(1));
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
        list.add(2);
        assertTrue(list.contains(2));
    }

    @Test
    void testIterator() {
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testToArray() {
        Object[] array = list.toArray();
        assertEquals(0, array.length);
        list.add(1);
        array = list.toArray();
        assertEquals(1, array.length);
        assertEquals(1, array[0]);
        list.add(2);
        array = list.toArray();
        assertEquals(2, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
    }

    @Test
    void testToArrayWithArgument() {
        Integer[] array = new Integer[0];
        array = list.toArray(array);
        assertEquals(0, array.length);
        list.add(1);
        array = list.toArray(array);
        assertEquals(1, array.length);
        assertEquals(1, array[0]);
        list.add(2);
        array = new Integer[2];
        array = list.toArray(array);
        assertEquals(2, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
    }

    @Test
    void testAdd() {
        assertTrue(list.add(1));
        assertTrue(list.contains(1));
        assertTrue(list.add(2));
        assertTrue(list.contains(2));
        assertEquals(Arrays.asList(1, 2), Arrays.asList(list.toArray()));
        assertTrue(list.add(null));
        assertTrue(list.contains(null));
        assertEquals(Arrays.asList(1, 2, null), Arrays.asList(list.toArray()));
    }

    @Test
    void testRemove() {
        assertFalse(list.remove(null));
        assertFalse(list.remove(2));
        assertFalse(list.remove(3));
        assertFalse(list.remove(4));
        assertFalse(list.remove(5));
        assertFalse(list.remove(6));
        assertFalse(list.remove(7));
        assertFalse(list.remove(8));
        assertFalse(list.remove(9));

        assertTrue(list.add(null));
        assertTrue(list.remove(null));

        assertTrue(list.add(Integer.valueOf("3")));
        assertTrue(list.remove(Integer.valueOf("3")));

        assertTrue(list.add(Integer.valueOf("4")));
        assertTrue(list.remove(Integer.valueOf("4")));

        assertTrue(list.add(Integer.valueOf("5")));
        assertTrue(list.remove(Integer.valueOf("5")));

        assertTrue(list.add(Integer.valueOf("6")));
        assertTrue(list.remove(Integer.valueOf("6")));

        assertTrue(list.add(Integer.valueOf("7")));
        assertTrue(list.remove(Integer.valueOf("7")));

        assertTrue(list.add(Integer.valueOf("8")));
        assertTrue(list.remove(Integer.valueOf("8")));

        assertTrue(list.add(Integer.valueOf("9")));
        assertTrue(list.remove(Integer.valueOf("9")));
    }

    @Test
    void testContainsAll() {
        Collection<Integer> c = Arrays.asList(1, 2);
        assertFalse(list.containsAll(c));
        list.add(1);
        assertFalse(list.containsAll(c));
        list.add(2);
        assertTrue(list.containsAll(c));
    }

    @Test
    void testAddAll() {
        Collection<Integer> c = Arrays.asList(1, 2);
        assertTrue(list.addAll(c));
        assertTrue(list.containsAll(c));
    }

    @Test
    void testRemoveAll() {
        Collection<Integer> c = Arrays.asList(1, 2);
        assertFalse(list.removeAll(c));
        list.add(1);
        assertTrue(list.removeAll(c));
        assertFalse(list.contains(1));
        assertFalse(list.removeAll(c));
        list.add(2);
        assertTrue(list.removeAll(c));
        assertFalse(list.contains(2));
    }

    @Test
    void testRetainAll() {
        Collection<Integer> c = Arrays.asList(1, 2);
        assertFalse(list.retainAll(c));
        list.add(3);
        assertTrue(list.retainAll(c));
        assertFalse(list.contains(3));
    }

    @Test
    void testClear() {
        list.clear();
        assertEquals(0, list.size());
        list.add(1);
        list.clear();
        assertEquals(0, list.size());
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        list.add(null);
        list.clear();
        assertEquals(0, list.size());
    }
}
