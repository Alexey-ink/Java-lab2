package ru.spbstu.telematics.java;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomCircularQueueTest {

    @Test
    void testQueueInitialization() {
        // Проверка корректной инициализации
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(5);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void testInvalidInitialization() {
        // Проверка исключения при некорректной инициализации
        assertThrows(IllegalArgumentException.class, () -> new CustomCircularQueue<>(0));
        assertThrows(IllegalArgumentException.class, () -> new CustomCircularQueue<>(-1));
    }

    @Test
    void testAddElements() {
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(3);

        assertTrue(queue.add(1));
        assertTrue(queue.add(2));
        assertTrue(queue.add(3));

        assertEquals(3, queue.size());
        assertThrows(IllegalStateException.class, () -> queue.add(4)); // Очередь переполнена
    }

    @Test
    void testRemoveElements() {
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(3);

        queue.add(10);
        queue.add(20);
        queue.add(30);

        assertEquals(10, queue.remove());
        assertEquals(20, queue.remove());
        assertEquals(30, queue.remove());

        assertTrue(queue.isEmpty());
        assertThrows(NoSuchElementException.class, queue::remove); // Очередь пуста
    }

    @Test
    void testPeek() {
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(3);

        assertNull(queue.peek()); // Очередь пуста

        queue.add(100);
        queue.add(200);

        assertEquals(100, queue.peek()); // Проверка первого элемента
        assertEquals(100, queue.peek()); // Проверка без удаления

        queue.remove();
        assertEquals(200, queue.peek());
    }

    @Test
    void testCircularBehavior() {
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(3);

        queue.add(1);
        queue.add(2);
        queue.add(3);

        queue.remove(); // Удаляем первый элемент (1)

        queue.add(4);

        assertEquals(2, queue.remove());
        assertEquals(3, queue.remove());
        assertEquals(4, queue.remove());

        assertTrue(queue.isEmpty());
    }

    @Test
    void testContains() {
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(3);

        queue.add(5);
        queue.add(10);
        queue.add(15);

        assertTrue(queue.contains(10));
        assertFalse(queue.contains(20));

        queue.remove(); // Удаляем 5
        assertFalse(queue.contains(5));
    }

    @Test
    void testIterator() {
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(3);

        queue.add(1);
        queue.add(2);
        queue.add(3);

        int sum = 0;
        for (int value : queue) {
            sum += value;
        }

        assertEquals(6, sum); // 1 + 2 + 3 = 6
    }

    @Test
    public void testClear() {
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void testIteratorEmptyQueue() {
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(3);

        for (int value : queue) {
            fail("Итератор не должен возвращать элементы для пустой очереди");
        }
    }
}
