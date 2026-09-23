package com.dsa.phase3.stack;

/**
 * ==========================================================
 * Problem    : LeetCode 155 - Min Stack
 * Difficulty : Medium
 * Pattern    : Stack / Doubly Linked List / Auxiliary Minimum
 * ==========================================================
 *
 * <p>
 * Idea:
 * We need to design a stack that supports push, pop, top,
 * and retrieving the minimum element in O(1) time.
 * </p>
 *
 * <p>
 * A doubly linked list is used to represent the stack. The
 * {@code tail} represents the top of the stack, so elements
 * can be added and removed from the tail in O(1) time.
 * </p>
 *
 * <p>
 * Each node stores two important values:
 * </p>
 *
 * <ul>
 *     <li>{@code val} - The actual value stored in the stack.</li>
 *     <li>
 *         {@code min} - The minimum value in the stack from
 *         the head up to this node.
 *     </li>
 * </ul>
 *
 * <p>
 * The {@code min} value stored in every node allows us to
 * retrieve the minimum of the current stack directly from
 * {@code tail.min}.
 * </p>
 *
 * <p>
 * For the first node:
 * </p>
 *
 * <pre>
 * min = value
 * </pre>
 *
 * <p>
 * For every subsequent node:
 * </p>
 *
 * <pre>
 * min = min(current value, previous node's min)
 * </pre>
 *
 * <p>
 * This means every node remembers the minimum value that
 * existed in the stack at the time that node was pushed.
 * </p>
 *
 * <p>
 * When a node is popped, we simply move {@code tail} to the
 * previous node. The new tail already contains the minimum
 * value for the remaining stack, so no recalculation is
 * required.
 * </p>
 *
 * <p>
 * Example:
 * </p>
 *
 * <pre>
 * push(5)
 *
 * 5
 * min = 5
 *
 * push(3)
 *
 * 5 -> 3
 *      min = 3
 *
 * push(7)
 *
 * 5 -> 3 -> 7
 *      3    3
 *
 * getMin() -> tail.min -> 3
 * </pre>
 *
 * <p>
 * If {@code 7} is popped:
 * </p>
 *
 * <pre>
 * 5 -> 3
 *      3
 *
 * getMin() -> tail.min -> 3
 * </pre>
 *
 * <p>
 * The previous minimum is therefore restored automatically
 * by moving the tail pointer backward.
 * </p>
 *
 * <p>
 * Time Complexity:
 * </p>
 *
 * <ul>
 *     <li>{@code push()}  : O(1)</li>
 *     <li>{@code pop()}   : O(1)</li>
 *     <li>{@code top()}   : O(1)</li>
 *     <li>{@code getMin()}: O(1)</li>
 * </ul>
 *
 * <p>
 * Space Complexity : O(n)
 * </p>
 *
 * <p>
 * where {@code n} is the number of elements currently stored
 * in the stack.
 * </p>
 * <p>
 * ==========================================================
 */
public class MinStack {

    Node head;
    Node tail;

    /**
     * Node representing an element in the stack.
     *
     * <p>
     * Each node stores its own value as well as the minimum
     * value from the head up to this node.
     * </p>
     */
    static class Node {

        int val;
        int min;

        Node next;
        Node prev;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    public MinStack() {
        head = null;
        tail = null;
    }

    /**
     * Adds a value to the top of the stack.
     *
     * <p>
     * The new node stores the minimum between its own value
     * and the minimum stored by the previous tail.
     * </p>
     *
     * @param value value to push onto the stack
     */
    public void push(int value) {

        Node newNode = new Node(value);

        // First element in the stack.
        if (head == null) {

            head = newNode;

            // For the first node, its own value is the minimum.
            newNode.min = value;

        } else {

            // Add the new node after the current tail.
            tail.next = newNode;
            newNode.prev = tail;

            // Store the minimum for the stack up to this node.
            newNode.min = Math.min(value, tail.min);
        }

        // New node becomes the top of the stack.
        tail = newNode;
    }

    /**
     * Removes the element at the top of the stack.
     *
     * <p>
     * Since the tail represents the top of the stack, we move
     * the tail pointer to the previous node.
     * </p>
     */
    public void pop() {

        // Only one element exists in the stack.
        if (head == tail) {

            head = null;
            tail = null;

        } else {

            // Move the tail to the previous node.
            Node temp = tail.prev;

            // Disconnect the old tail.
            tail.prev = null;

            tail = temp;
            tail.next = null;
        }
    }

    /**
     * Returns the value at the top of the stack.
     *
     * @return value of the top element
     */
    public int top() {
        return tail.val;
    }

    /**
     * Returns the minimum value currently present in the stack.
     *
     * <p>
     * The tail node always contains the minimum value for the
     * entire current stack.
     * </p>
     *
     * @return minimum value in the stack
     */
    public int getMin() {
        return tail.min;
    }
}