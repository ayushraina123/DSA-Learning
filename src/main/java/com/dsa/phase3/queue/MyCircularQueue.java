package com.dsa.phase3.queue;

/**
 * ==========================================================
 * Problem    : LeetCode 622 - Design Circular Queue
 * Difficulty : Medium
 * Pattern    : Queue / Circular Array / Modular Arithmetic
 * ==========================================================
 *
 * <p>
 * Idea:
 * </p>
 *
 * <p>
 * A circular queue is a FIFO (First In, First Out) data
 * structure in which the last position of the array connects
 * back to the first position.
 * </p>
 *
 * <p>
 * Unlike a regular queue implemented using an array, a circular
 * queue reuses previously occupied positions after elements
 * are removed. This avoids wasting array space and eliminates
 * the need to shift elements after every dequeue operation.
 * </p>
 *
 * <p>
 * The queue is implemented using a fixed-size array and four
 * state variables:
 * </p>
 *
 * <ul>
 *     <li>array: Stores the queue elements.</li>
 *     <li>front: Points to the first valid queue element.</li>
 *     <li>rear: Points to the next available insertion position.</li>
 *     <li>count: Tracks the number of elements currently in
 *         the queue.</li>
 *     <li>size: Represents the maximum capacity of the queue.</li>
 * </ul>
 *
 * <p>
 * Circular indexing:
 * </p>
 *
 * <p>
 * Both front and rear are maintained within the range
 * [0, size - 1]. Whenever either pointer advances, modulo
 * arithmetic wraps it back to index 0 when it reaches the
 * end of the array.
 * </p>
 *
 * <pre>
 * nextIndex = (currentIndex + 1) % size
 * </pre>
 *
 * <p>
 * For example, when size = 3:
 * </p>
 *
 * <pre>
 * Index:  0   1   2
 *
 * After index 2:
 * (2 + 1) % 3 = 0
 * </pre>
 *
 * <p>
 * Enqueue operation:
 * </p>
 *
 * <p>
 * First, check whether the queue is full. If it is full,
 * insertion is rejected.
 * </p>
 *
 * <p>
 * Otherwise, insert the new value at array[rear], advance
 * rear using modulo arithmetic, and increment count.
 * </p>
 *
 * <p>
 * Dequeue operation:
 * </p>
 *
 * <p>
 * First, check whether the queue is empty. If it is empty,
 * removal is rejected.
 * </p>
 *
 * <p>
 * Otherwise, advance front using modulo arithmetic and
 * decrement count. The removed element does not need to be
 * physically deleted from the array because front and count
 * determine which elements logically belong to the queue.
 * </p>
 *
 * <p>
 * Front operation:
 * </p>
 *
 * <p>
 * If the queue is empty, return -1. Otherwise, return
 * array[front], which contains the first valid element.
 * This operation does not modify the queue.
 * </p>
 *
 * <p>
 * Rear operation:
 * </p>
 *
 * <p>
 * If the queue is empty, return -1. Otherwise, the last
 * valid element is located immediately before rear.
 * Its circular index is:
 * </p>
 *
 * <pre>
 * (rear - 1 + size) % size
 * </pre>
 *
 * <p>
 * Adding size before applying modulo prevents a negative
 * index when rear is 0.
 * </p>
 *
 * <p>
 * Empty and full conditions:
 * </p>
 *
 * <ul>
 *     <li>Empty: count == 0</li>
 *     <li>Full: count == size</li>
 * </ul>
 *
 * <p>
 * The count variable is essential because front == rear
 * alone cannot distinguish an empty queue from a full queue
 * when circular indexing is used.
 * </p>
 *
 * <p>
 * Example:
 * </p>
 *
 * <pre>
 * MyCircularQueue q = new MyCircularQueue(3);
 *
 * q.enQueue(10);  // true
 * q.enQueue(20);  // true
 * q.enQueue(30);  // true
 *
 * q.isFull();     // true
 * q.Rear();       // 30
 *
 * q.deQueue();    // true, removes 10
 *
 * q.enQueue(40);  // true, reuses index 0
 *
 * q.Front();      // 20
 * q.Rear();       // 40
 *
 * Logical queue: 20 -> 30 -> 40
 * </pre>
 *
 * <p>
 * Why use count instead of rear - front?
 * </p>
 *
 * <p>
 * Since front and rear wrap around independently, their
 * numerical difference does not reliably represent the
 * number of elements in the queue. For example, rear may
 * wrap to index 0 while front remains at index 1.
 * </p>
 *
 * <p>
 * Maintaining count explicitly makes the empty and full
 * conditions unambiguous, even when front == rear.
 * </p>
 *
 * <p>
 * Time Complexity:
 * </p>
 *
 * <ul>
 *     <li>enQueue: O(1)</li>
 *     <li>deQueue: O(1)</li>
 *     <li>Front: O(1)</li>
 *     <li>Rear: O(1)</li>
 *     <li>isEmpty: O(1)</li>
 *     <li>isFull: O(1)</li>
 * </ul>
 *
 * <p>
 * Every operation performs a constant number of array
 * accesses, arithmetic operations, and comparisons.
 * </p>
 *
 * <p>
 * Space Complexity: O(k)
 * </p>
 *
 * <p>
 * The array has a fixed capacity of k elements, and the
 * additional state variables require O(1) space.
 * </p>
 * <p>
 * ==========================================================
 */
public class MyCircularQueue {

    private final int[] array;
    private int front;
    private int rear;
    private int count;
    private final int size;

    public MyCircularQueue(int k) {
        size = k;
        array = new int[size];
        front = 0;
        rear = 0;
        count = 0;
    }

    /*
     * Adds an element to the rear of the queue.
     *
     * Rejects insertion when the queue is full.
     * Advances rear circularly and increments count.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        array[rear] = value;
        rear = (rear + 1) % size;
        count++;

        return true;
    }

    /*
     * Removes the front element from the queue.
     *
     * Rejects removal when the queue is empty.
     * Advances front circularly and decrements count.
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % size;
        count--;

        return true;
    }

    /*
     * Returns the front element without removing it.
     *
     * Returns -1 when the queue is empty.
     */
    public int Front() {
        return isEmpty() ? -1 : array[front];
    }

    /*
     * Returns the rear element without removing it.
     *
     * rear points to the next insertion position, so the
     * previous circular index contains the last element.
     * Returns -1 when the queue is empty.
     */
    public int Rear() {
        return isEmpty()
                ? -1
                : array[(rear - 1 + size) % size];
    }

    /*
     * Returns true when no elements are present.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /*
     * Returns true when the queue has reached its capacity.
     */
    public boolean isFull() {
        return count == size;
    }
}