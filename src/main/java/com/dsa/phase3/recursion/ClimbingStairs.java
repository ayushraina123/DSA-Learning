package com.dsa.phase3.recursion;

/**
 * ==========================================================
 * Problem    : LeetCode 70 - Climbing Stairs
 * Difficulty : Easy
 * Pattern    : Recursion / Dynamic Programming
 * ==========================================================
 *
 * <p>
 * Problem:
 * We are given a staircase containing {@code n} steps.
 * We can climb either 1 step or 2 steps at a time.
 * The objective is to calculate the total number of distinct
 * ways to reach the top of the staircase.
 * </p>
 *
 * <p>
 * Example:
 * </p>
 *
 * <p>
 * For {@code n = 3}, the possible ways are:
 * </p>
 *
 * <p>
 * {@code 1 + 1 + 1}
 * </p>
 *
 * <p>
 * {@code 1 + 2}
 * </p>
 *
 * <p>
 * {@code 2 + 1}
 * </p>
 *
 * <p>
 * Therefore, the answer is {@code 3}.
 * </p>
 *
 * <p>
 * Approach 1: Recursion
 * </p>
 *
 * <p>
 * The main observation is that to reach step {@code n},
 * the last move must be either:
 * </p>
 *
 * <p>
 * 1. A single step from step {@code n - 1}.
 * </p>
 *
 * <p>
 * 2. A two-step move from step {@code n - 2}.
 * </p>
 *
 * <p>
 * Therefore, the total number of ways to reach step {@code n}
 * is the sum of the ways to reach the previous two steps:
 * </p>
 *
 * <p>
 * {@code climbStairs(n) = climbStairs(n - 1) + climbStairs(n - 2)}
 * </p>
 *
 * <p>
 * Base cases:
 * </p>
 *
 * <p>
 * {@code climbStairs(0) = 1}
 * </p>
 *
 * <p>
 * There is one way to complete a staircase containing zero
 * steps: do nothing.
 * </p>
 *
 * <p>
 * {@code climbStairs(1) = 1}
 * </p>
 *
 * <p>
 * There is only one way to climb a staircase containing
 * one step.
 * </p>
 *
 * <p>
 * This approach directly expresses the mathematical
 * relationship using recursive method calls. However,
 * it recalculates the same subproblems multiple times,
 * making it inefficient for larger inputs.
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
 * The time complexity is exponential because each recursive
 * call can generate two additional calls. The space complexity
 * is O(n) because the maximum depth of the recursive call
 * stack is proportional to n.
 * </p>
 *
 * <p>
 * Approach 2: Iteration / Dynamic Programming
 * </p>
 *
 * <p>
 * Since each answer depends only on the previous two answers,
 * we do not need to calculate the same subproblem repeatedly.
 * We can store the previous two results in variables and
 * calculate each new result iteratively.
 * </p>
 *
 * <p>
 * {@code prev2} stores the number of ways to reach the step
 * two positions behind the current step.
 * </p>
 *
 * <p>
 * {@code prev1} stores the number of ways to reach the
 * immediately preceding step.
 * </p>
 *
 * <p>
 * For every step starting from 3, we calculate:
 * </p>
 *
 * <p>
 * {@code current = prev1 + prev2}
 * </p>
 *
 * <p>
 * We then shift the previous values forward so they are
 * ready for the next iteration.
 * </p>
 *
 * <p>
 * This avoids repeated calculations and improves the
 * performance significantly.
 * </p>
 *
 * <p>
 * Time Complexity : O(n)
 * </p>
 *
 * <p>
 * Space Complexity : O(1)
 * </p>
 *
 * <p>
 * The iterative approach processes each step once and
 * uses only a constant number of variables.
 * </p>
 * <p>
 * ==========================================================
 */
public class ClimbingStairs {

    /**
     * Calculates the number of ways to climb a staircase
     * using recursion.
     *
     * @param n the number of steps in the staircase
     * @return the total number of distinct ways to reach the top
     */
    public int climbStairs(int n) {

        // Base cases: zero steps or one step have one way.
        if (n == 1 || n == 0) {
            return 1;
        }

        // The final move is either one step or two steps.
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * Calculates the number of ways to climb a staircase
     * using an iterative approach.
     *
     * @param n the number of steps in the staircase
     * @return the total number of distinct ways to reach the top
     */
    public int climbStairs2(int n) {

        // Handle the first two steps directly.
        if (n <= 2) {
            return n;
        }

        // Number of ways to reach steps 1 and 2.
        int prev2 = 1;
        int prev1 = 2;

        // Calculate the number of ways for each subsequent step.
        for (int i = 3; i <= n; i++) {

            int current = prev1 + prev2;

            // Move the previous values forward.
            prev2 = prev1;
            prev1 = current;
        }

        // The last calculated value is the answer.
        return prev1;
    }
}