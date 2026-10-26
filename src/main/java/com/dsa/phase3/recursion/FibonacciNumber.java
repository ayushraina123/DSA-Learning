package com.dsa.phase3.recursion;

/**
 * ==========================================================
 * Problem    : LeetCode 509 - Fibonacci Number
 * Difficulty : Easy
 * Pattern    : Recursion
 * ==========================================================
 *
 * <p>
 * Problem:
 * The Fibonacci sequence is a sequence where each number is
 * the sum of the two numbers before it.
 * </p>
 *
 * <p>
 * The sequence starts with:
 * </p>
 *
 * <p>
 * {@code F(0) = 0}
 * </p>
 *
 * <p>
 * {@code F(1) = 1}
 * </p>
 *
 * <p>
 * For every number greater than 1:
 * </p>
 *
 * <p>
 * {@code F(n) = F(n - 1) + F(n - 2)}
 * </p>
 *
 * <p>
 * Example:
 * </p>
 *
 * <p>
 * {@code n = 5}
 * </p>
 *
 * <p>
 * The Fibonacci sequence up to 5 is:
 * </p>
 *
 * <p>
 * {@code 0, 1, 1, 2, 3, 5}
 * </p>
 *
 * <p>
 * Therefore:
 * </p>
 *
 * <p>
 * {@code F(5) = 5}
 * </p>
 *
 * <p>
 * Approach:
 * </p>
 *
 * <p>
 * We solve the problem recursively using the Fibonacci
 * relationship:
 * </p>
 *
 * <p>
 * {@code F(n) = F(n - 1) + F(n - 2)}
 * </p>
 *
 * <p>
 * For every value of {@code n}, the method recursively
 * calculates the previous two Fibonacci numbers and adds
 * their results.
 * </p>
 *
 * <p>
 * Base cases:
 * </p>
 *
 * <p>
 * {@code F(0) = 0}
 * </p>
 *
 * <p>
 * {@code F(1) = 1}
 * </p>
 *
 * <p>
 * These base cases are important because they stop the
 * recursion. Without them, the method would continue
 * calling itself indefinitely.
 * </p>
 *
 * <p>
 * Example execution for {@code fib(4)}:
 * </p>
 *
 * <p>
 * {@code fib(4)}
 * </p>
 *
 * <p>
 * {@code = fib(3) + fib(2)}
 * </p>
 *
 * <p>
 * {@code = (fib(2) + fib(1)) + (fib(1) + fib(0))}
 * </p>
 *
 * <p>
 * {@code = (1 + 1) + (1 + 0)}
 * </p>
 *
 * <p>
 * {@code = 3}
 * </p>
 *
 * <p>
 * One important limitation of this recursive approach is
 * that the same Fibonacci values are calculated multiple
 * times. For example, while calculating {@code fib(5)},
 * {@code fib(3)} and {@code fib(2)} are calculated more than
 * once.
 * </p>
 *
 * <p>
 * Therefore, although the recursive solution is simple and
 * useful for understanding recursion, it is inefficient for
 * larger values of {@code n}.
 * </p>
 *
 * <p>
 * Time Complexity : O(2^n)
 * </p>
 *
 * <p>
 * Space Complexity : O(n)
 * </p>
 *
 * <p>
 * The time complexity is exponential because every non-base
 * recursive call creates two additional recursive calls.
 * The space complexity is O(n) because the maximum depth of
 * the recursive call stack is proportional to {@code n}.
 * </p>
 * <p>
 * ==========================================================
 */
public class FibonacciNumber {

    /**
     * Calculates the nth Fibonacci number using recursion.
     *
     * @param n the position in the Fibonacci sequence
     * @return the nth Fibonacci number
     */
    public int fib(int n) {

        // Base cases: F(0) = 0 and F(1) = 1.
        if (n == 0 || n == 1) {
            return n;
        }

        // Calculate the current Fibonacci number
        // using the previous two Fibonacci numbers.
        return fib(n - 1) + fib(n - 2);
    }
}