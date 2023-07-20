package com.school.comp2522.assignments.a5;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    static class Node<T> {
        T value;
        Node<T> previous;
        Node<T> next;

        public Node(T value, Node<T> previous, Node<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        public Node(T value) {
            this.value = value;
            this.previous = null;
            this.next = null;
        }
    }

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    /**
     * Return the size of the linked list.
     * Time complexity: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Check weather the linked list is empty.
     * Time complexity: O(1)
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Get the first item of the linked list.
     * Time complexity: O(1)
     */
    public T peekFirst() {
        return head.value;
    }

    /**
     * Get the last item of the linked list.
     * Time complexity: O(1)
     */
    public T peekLast() {
        return tail.value;
    }

    /**
     * Add <code>item</code> to the bottom of the linked list.
     * Time complexity: O(1)
     */
    public void pushLast(T item) {
        Node<T> node = new Node<>(item, tail, null);

        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;

        size++;
    }

    /**
     * Add <code>item</code> to the front of the linked list.
     * Time complexity: O(1)
     */
    public void pushFront(T item) {
        Node<T> node = new Node<>(item, null, head);

        if (isEmpty()) {
            tail = node;
        } else {
            head.previous = node;
        }
        head = node;

        size++;
    }

    /**
     * Remove the first element of the linked list.
     * Time complexity: O(1)
     */
    public T popFirst() {
        if (isEmpty()) {
            return null;
        }

        T value = head.value;

        if (size() == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }

        size--;
        return value;
    }

    /**
     * Remove the last element of the linked list.
     * Time complexity: O(1)
     */
    public T popLast() {
        if (isEmpty()) {
            return null;
        }

        T value = tail.value;

        if (size() == 1) {
            tail = null;
            head = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }

        size--;
        return value;
    }

    /**
     * Insert the item at the wanted position in the linked list.
     * Time complexity: O(n)
     */
    public void insert(T item, int position) throws IllegalArgumentException {
        if (position == 0) {
            pushFront(item);
            return;
        }

        if (position == size()) {
            pushLast(item);
            return;
        }

        if (position > size() || position < 0) {
            throw new IllegalArgumentException();
        }

        // Traversing the list until reaching to the
        // desired node position
        Node<T> node = head;
        for (int n = position; n > 0; n--) {
            node = node.next;
        }

        Node<T> newNode = new Node<>(item, node.previous, node);
        node.previous.next = newNode;
        node.previous = newNode;
        size++;
    }

    /**
     * Remove the given object from the linked list.
     * Time complexity: O(n)
     */
    public T remove(Object o) {
        Node<T> node = head;
        int index = 0;
        while (node != null) {
            if (node.value == o || node.value.equals(o)) {
                if (index == 0) {
                    return popFirst();
                }

                if (index == size() - 1) {
                    return popLast();
                }

                node.previous.next = node.next;
                node.next.previous = node.previous;
                return node.value;
            }
            node = node.next;
            index++;
        }
        return null;
    }

    /**
     * Remove the item at the given <code>index</code>
     * Time complexity: O(n)
     */
    public T removeAt(int index) throws IllegalArgumentException {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }

        if (index == 0) {
            return popFirst();
        }

        if (index == size() - 1) {
            return popLast();
        }

        Node<T> node = head;
        while (index > 0) {
            index--;
            node = node.next;
        }
        node.previous.next = node.next;
        node.next.previous = node.previous;
        return node.value;
    }

    /**
     * Get the index of an item present in the linked list.
     * Time complexity: O(n)
     *
     * @return the index of the item or -1 if not found
     */
    public int indexOf(Object o) {
        int index = -1;
        Node<T> node = head;

        while (node != null) {
            index++;
            if (node.value == o || node.value.equals(o)) {
                return index;
            }
            node = node.next;
        }

        return -1;
    }

    /**
     * Check if an object is contained in the linked list.
     * <p>
     * Time complexity: O(n)
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Delete all the elements in the linked list.
     * <p>
     * Time complexity: O(1)
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

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
        };
    }
}
