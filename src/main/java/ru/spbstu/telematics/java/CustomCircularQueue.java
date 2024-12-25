package ru.spbstu.telematics.java;

import java.util.*;

public class CustomCircularQueue<T> implements Iterable<T> {

    private final T[] elements;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public CustomCircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        this.elements = (T[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T element) {
        for (T item : this) {
            if ((item == null && element == null) || (item != null && item.equals(element))) {
                return true;
            }
        }
        return false;
    }

    public boolean add(T element) {
        if (size == elements.length) {
            throw new IllegalStateException("Queue is full");
        }
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
        return true;
    }

    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T element = elements[head];
        elements[head] = null; // Avoid memory leak
        head = (head + 1) % elements.length;
        size--;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[head];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = head;
            private int elementsSeen = 0;

            @Override
            public boolean hasNext() {
                return elementsSeen < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = elements[currentIndex];
                currentIndex = (currentIndex + 1) % elements.length;
                elementsSeen++;
                return element;
            }
        };
    }

    public static void main(String[] args) {
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(5);

        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println("Queue contains 2: " + queue.contains(2)); // true
        System.out.println("Queue size: " + queue.size()); // 3

        System.out.println("Removed: " + queue.remove()); // 1
        System.out.println("Peek: " + queue.peek()); // 2

        queue.add(4);
        queue.add(5);
        queue.add(6);

        for (int value : queue) {
            System.out.println("Queue element: " + value);
        }
    }
}


