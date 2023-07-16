package com.school.comp2522.assignments.a4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ArraySetTests {
    @Test
    public void testAdd() {
        ArraySet<Integer> set = new ArraySet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertFalse(set.add(1));
        assertEquals(2, set.size());
    }

    @Test
    public void testRemove() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        assertTrue(set.remove(1));
        assertFalse(set.remove(3));
        assertEquals(1, set.size());

        set.add(4);
        assertEquals(2, set.size());
    }

    @Test
    public void testIsEmpty() {
        ArraySet<Integer> set = new ArraySet<>();
        assertTrue(set.isEmpty());
        set.add(1);
        assertFalse(set.isEmpty());
    }

    @Test
    public void testContains() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        assertTrue(set.contains(1));
        assertFalse(set.contains(3));
    }

    @Test
    public void testClear() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.clear();
        assertEquals(0, set.size());
    }

    @Test
    public void testToArray() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        Object[] array = set.toArray();
        assertArrayEquals(new Object[]{1, 2}, array);
    }

    @Test
    public void testIterator() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> iterator = set.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testContainsAll() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        assertTrue(set.containsAll(Arrays.asList(1, 2)));
        assertFalse(set.containsAll(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testAddAll() {
        ArraySet<Integer> set = new ArraySet<>();
        assertTrue(set.addAll(Arrays.asList(1, 2)));
        assertFalse(set.addAll(Arrays.asList(1, 2)));
        assertEquals(2, set.size());
    }

    @Test
    public void testRemoveAll() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        assertTrue(set.removeAll(Arrays.asList(1, 2)));
        assertFalse(set.removeAll(Arrays.asList(1, 2)));
        assertEquals(0, set.size());
    }

    @Test
    public void testRetainAll() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        assertTrue(set.retainAll(Arrays.asList(1)));
        assertEquals(1, set.size());
    }
}
