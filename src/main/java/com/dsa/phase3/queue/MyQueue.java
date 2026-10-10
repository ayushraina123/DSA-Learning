package com.dsa.phase3.queue;

import java.util.Stack;

/**
 * ==========================================================
 * Problem    : LeetCode 232 - Implement Queue using Stacks
 * Difficulty : Easy
 * Pattern    : Stack / Queue / Amortized Analysis
 * ==========================================================
 *
 * <p>
 * Idea:
 * </p>
 *
 * <p>
 * A queue follows FIFO (First In, First Out), whereas a stack
 * follows LIFO (Last In, First Out).
 * <p>
 * We can implement a queue using two stacks by reversing the
 * order of elements when transferring them from one stack to
 * the other.
 * </p>
 *
 * <p>
 * Two stacks are used:
 * </p>
 *
 * <ul>
 *     <li>stack: Stores newly pushed elements.</li>
 *     <li>queue: Stores elements in queue removal order.</li>
 * </ul>
 *
 * <p>
 * The important invariant is that elements in {@code queue}
 * must be removed before elements still waiting in {@code stack}.
 * Therefore, elements are transferred from {@code stack} to
 * {@code queue} only when {@code queue} is empty.
 * </p>
 *
 * <p>
 * Push operation:
 * </p>
 *
 * <p>
 * A new element is added directly to {@code stack}.
 * This operation takes O(1) time.
 * </p>
 *
 * <p>
 * Pop operation:
 * </p>
 *
 * <p>
 * If {@code queue} is empty, transfer all elements from
 * {@code stack} to {@code queue}.
 * <p>
 * This reverses their order, placing the oldest element at
 * the top of {@code queue}. Remove and return that element.
 * <p>
 * If {@code queue} already contains elements, remove directly
 * from it without transferring anything.
 * </p>
 *
 * <p>
 * Peek operation:
 * </p>
 *
 * <p>
 * Uses the same transfer condition as {@code pop()}, but
 * returns the top element of {@code queue} without removing it.
 * </p>
 *
 * <p>
 * Empty operation:
 * </p>
 *
 * <p>
 * The queue is empty only when both {@code stack} and
 * {@code queue} are empty.
 * </p>
 *
 * <p>
 * Example:
 * </p>
 *
 * <pre>
 * push(1)
 * push(2)
 * push(3)
 *
 * stack = [1, 2, 3]  // 3 is at the top
 * queue = []
 *
 * pop()
 *
 * Transfer elements:
 * stack = []
 * queue = [3, 2, 1]  // 1 is at the top
 *
 * Return 1
 *
 * queue = [3, 2]     // 2 is now at the top
 *
 * push(4)
 *
 * stack = [4]
 * queue = [3, 2]
 *
 * pop()
 *
 * Return 2
 *
 * queue = [3]
 * stack = [4]
 * </pre>
 *
 * <p>
 * Why transfer only when {@code queue} is empty?
 * </p>
 *
 * <p>
 * Suppose {@code queue} contains older elements 2 and 3,
 * while {@code stack} contains the newer element 4.
 * <p>
 * Transferring 4 into the non-empty {@code queue} would place
 * it above the older elements, causing it to be removed first.
 * <p>
 * By transferring only when {@code queue} is empty, older
 * elements always retain priority over newer elements.
 * </p>
 *
 * <p>
 * Time Complexity:
 * </p>
 *
 * <ul>
 *     <li>Push: O(1)</li>
 *     <li>Pop: O(1) amortized, O(n) worst case</li>
 *     <li>Peek: O(1) amortized, O(n) worst case</li>
 *     <li>Empty: O(1)</li>
 * </ul>
 *
 * <p>
 * Each element is pushed onto {@code stack} once, transferred
 * to {@code queue} at most once, and removed from
 * {@code queue} at most once.
 * <p>
 * Therefore, a sequence of m queue operations takes O(m)
 * total time, giving O(1) amortized time per operation.
 * </p>
 *
 * <p>
 * Space Complexity: O(n)
 * </p>
 *
 * <p>
 * Both stacks together store at most n elements, where n is
 * the number of elements currently in the queue.
 * </p>
 * <p>
 * ==========================================================
 */
public class MyQueue {

    private final Stack<Integer> stack;
    private final Stack<Integer> queue;

    public MyQueue() {
        stack = new Stack<>();
        queue = new Stack<>();
    }

    /*
     * Adds an element to the back of the queue.
     *
     * New elements are stored in stack and transferred to
     * queue later when removal or inspection is required.
     */
    public void push(int x) {
        stack.push(x);
    }

    /*
     * Removes and returns the front element of the queue.
     *
     * Transfer elements only when queue is empty so that
     * older elements are always removed before newer ones.
     */
    public int pop() {
        if (queue.isEmpty()) {
            while (!stack.isEmpty()) {
                queue.push(stack.pop());
            }
        }

        return queue.pop();
    }

    /*
     * Returns the front element without removing it.
     *
     * Uses the same transfer logic as pop(), ensuring that
     * the oldest element remains at the top of queue.
     */
    public int peek() {
        if (queue.isEmpty()) {
            while (!stack.isEmpty()) {
                queue.push(stack.pop());
            }
        }

        return queue.peek();
    }

    /*
     * Returns true only when both stacks are empty.
     */
    public boolean empty() {
        return stack.isEmpty() && queue.isEmpty();
    }
}
