package com.school.comp2522.assignments.a4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArraySet<E> implements Collection<E> {
    class SetIterator<T> implements Iterator<T> {
        int nextIndex = 0;

        @Override
        public boolean hasNext() {
            return nextIndex < size();
        }

        @Override
        public T next() {
            return (T) set[nextIndex++];
        }
    }

    private static final double GROWTH_RATE = 2;
    private static final int DEFAULT_SIZE = 10;

    private Object[] set;
    private int lastItemIndex = -1;

    public ArraySet() {
        set = new Object[DEFAULT_SIZE];
    }

    public ArraySet(int size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException("Illegal size: " + size);
        }

        set = new Object[size];
    }

    /**
     * Complexity: O(2n)
     * In the worst case the array would need to be resized.
     */
    @Override
    public boolean add(E item) throws NullPointerException {
        if (contains(item)) {
            // item is duplicated, nothing will be done
            return false;
        }

        if (item == null) {
            throw new NullPointerException();
        }

        if (isFull()) {
            resize();
        }

        return append(item);
    }

    /**
     * Complexity: O(1)
     */
    private boolean append(E item) {
        try {
            set[++lastItemIndex] = item;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * Complexity: O(1)
     */
    private boolean isFull() {
        return set.length == size();
    }


    /**
     * Complexity: O(1)
     */
    @Override
    public int size() {
        return lastItemIndex + 1;
    }

    /**
     * Complexity: O(n)
     */
    private void resize() {
        final int newSize = (int) (size() * GROWTH_RATE);
        Object[] newSet = new Object[newSize];

        // Copying old set
        System.arraycopy(set, 0, newSet, 0, size());

        set = newSet;
    }

    /**
     * Complexity: O(n)
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }

        for (Object item : set) {
            if (item == o) {
                return true;
            }
        }

        return false;
    }

    /**
     * Complexity: O(n)
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (set[i] == o) {
                // setting the last item at the removed item position
                set[i] = set[lastItemIndex];
                set[lastItemIndex] = null;
                lastItemIndex--;
                return true;
            }
        }

        return false;
    }

    /**
     * Complexity: O(n)
     */
    @Override
    public Object[] toArray() {
        final Object[] copy = new Object[size()];
        System.arraycopy(set, 0, copy, 0, size());
        return copy;
    }

    /**
     * Complexity: O(1)
     */
    @Override
    public void clear() {
        set = new Object[DEFAULT_SIZE];
        lastItemIndex = -1;
    }

    @Override
    public SetIterator<E> iterator() {
        return new SetIterator<>();
    }

    /**
     * Complexity: O(1)
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Complexity: O(a)
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            return (T[]) Arrays.copyOf(set, size(), a.getClass());
        }

        System.arraycopy(set, 0, a, 0, size());
        return a;
    }

    /**
     * Complexity: O(c * n)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Complexity: O(c * n)
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E item : c) {
            if (!add(item)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Complexity: O(c * n)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object item : c) {
            if (!remove(item)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Complexity: O(c * n)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;

        for (Object item : set) {
            // delete our item if it's not included in the other collection
            if (!c.contains(item)) {
                remove(item);
                changed = true;
            }
        }

        return changed;
    }
}
