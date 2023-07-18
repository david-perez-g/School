package com.school.comp2522.labs.lab06;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class LinkedList<E> implements Collection<E> {
    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    private class LinkedListIterator<T> implements Iterator<T> {
        Node<T> node = (Node<T>) head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T value = node.value;
            node = node.next;
            return value;
        }
    }

    private Node<E> head;
    private Node<E> lastNode;
    private int size = 0;


    /**
     * Complexity: O(1)
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Complexity: O(1)
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Complexity: O(n)
     */
    @Override
    public boolean contains(Object o) {
        for (E item : this) {
            if (item == o || item.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>();
    }

    /**
     * Complexity: O(n)
     */
    @Override
    public Object[] toArray() {
        final Object[] array = new Object[size];

        int index = 0;
        for (E item : this) {
            array[index++] = item;
        }

        return array;
    }

    /**
     * Complexity: O(a + n)
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            return (T[]) Arrays.copyOf(toArray(), size(), a.getClass());
        }

        System.arraycopy(toArray(), 0, a, 0, size());
        return a;
    }

    /**
     * Complexity: O(1)
     */
    @Override
    public boolean add(E e) {
        final Node<E> node = new Node<>(e);

        if (head == null) {
            head = node;
            lastNode = head;
        } else {
            lastNode.next = node;
            lastNode = node;
        }

        size++;
        return true;
    }

    /**
     * Complexity: O(n)
     */
    @Override
    public boolean remove(Object o) {
        Node<E> previousNode = null;
        Node<E> node = head;

        while (node != null) {
            if (node.value == o || node.value.equals(o)) {
                size--;
                // The node to remove is the first node
                if (previousNode == null) {
                    head = node.next;
                } else {
                    previousNode.next = node.next;
                }
                return true;
            }

            previousNode = node;
            node = node.next;
        }

        return false;
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
     * Complexity: O(c)
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E item : c) {
            add(item);
        }
        return true;
    }

    /**
     * Complexity: O(c * n)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;

        for (Object item : c) {
            if (remove(item)) {
                changed = true;
            }
        }

        return changed;
    }

    /**
     * Complexity: O(c * n)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;

        for (E item : this) {
            // delete our item if it's not included in the other collection
            if (!c.contains(item)) {
                remove(item);
                changed = true;
            }
        }

        return changed;
    }

    /**
     * Complexity: O(1)
     */
    @Override
    public void clear() {
        head = null;
        lastNode = null;
        size = 0;
    }
}

