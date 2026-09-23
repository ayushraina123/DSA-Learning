package com.dsa.phase3.stack;

import java.util.Stack;

/**
 * ==========================================================
 * Problem    : LeetCode 739 - Daily Temperatures
 * Difficulty : Medium
 * Pattern    : Monotonic Stack
 * ==========================================================
 *
 * <p>
 * Idea:
 * </p>
 *
 * <p>
 * For every day, we need to find the number of days until a
 * warmer temperature occurs.
 * </p>
 *
 * <p>
 * A monotonic decreasing stack is used to keep track of
 * temperatures for which we have not yet found a warmer day.
 * </p>
 *
 * <p>
 * Each element in the stack stores:
 * </p>
 *
 * <ul>
 *     <li>Temperature of the day</li>
 *     <li>Index of the day</li>
 * </ul>
 *
 * <p>
 * The stack maintains temperatures in decreasing order from
 * bottom to top.
 * </p>
 *
 * <p>
 * For every current temperature:
 * </p>
 *
 * <ul>
 *     <li>
 *         If the current temperature is not warmer than the
 *         temperature at the top of the stack, push the current
 *         temperature and its index onto the stack.
 *     </li>
 *     <li>
 *         If the current temperature is warmer than the
 *         temperature at the top of the stack, the current day
 *         is the first warmer day for that element.
 *     </li>
 * </ul>
 *
 * <p>
 * When a warmer temperature is found, pop the previous day
 * from the stack and calculate the number of days between
 * the two indices.
 * </p>
 *
 * <pre>
 * Example:
 *
 * temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
 *
 * 73 -> stack = [(73,0)]
 *
 * 74 -> 74 > 73
 *       ans[0] = 1
 *       stack = [(74,1)]
 *
 * 75 -> 75 > 74
 *       ans[1] = 1
 *       stack = [(75,2)]
 *
 * 71 -> 71 < 75
 *       stack = [(75,2), (71,3)]
 *
 * 69 -> 69 < 71
 *       stack = [(75,2), (71,3), (69,4)]
 *
 * 72 -> 72 > 69
 *       ans[4] = 1
 *
 *       72 > 71
 *       ans[3] = 2
 *
 *       72 < 75
 *       stack = [(75,2), (72,5)]
 *
 * 76 -> 76 > 72
 *       ans[5] = 1
 *
 *       76 > 75
 *       ans[2] = 4
 *
 * Final result:
 * [1, 1, 4, 2, 1, 1, 0, 0]
 * </pre>
 *
 * <p>
 * The important observation is that when an element is popped,
 * the current index is guaranteed to be the first index to its
 * right containing a warmer temperature.
 * </p>
 *
 * <p>
 * Elements remaining in the stack after processing all
 * temperatures do not have a warmer day in the future, so their
 * corresponding values in the answer remain 0.
 * </p>
 *
 * <p>
 * Time Complexity: O(n)
 * </p>
 *
 * <p>
 * Space Complexity: O(n)
 * </p>
 *
 * <p>
 * where {@code n} is the number of temperatures.
 * </p>
 * <p>
 * ==========================================================
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {

        /*
         * The answer array is initialized with 0.
         *
         * If a temperature does not have a warmer day in the
         * future, its value remains 0.
         */
        int[] ans = new int[temperatures.length];

        /*
         * The stack stores:
         *
         * key   -> temperature
         * value -> index of that temperature
         *
         * The stack maintains temperatures in decreasing order.
         */
        Stack<KeyValuePair> stack = new Stack<>();

        int index;

        /*
         * Process each temperature from left to right.
         */
        for (int i = 0; i < temperatures.length; i++) {

            /*
             * If the current temperature is warmer than the
             * temperature at the top of the stack, we have found
             * the next warmer day for that previous temperature.
             */
            while (!stack.isEmpty()
                    && stack.peek().getKey() < temperatures[i]) {

                /*
                 * Remove the temperature for which we have now
                 * found a warmer day.
                 */
                KeyValuePair pair = stack.pop();

                /*
                 * Calculate how many days we had to wait for
                 * the warmer temperature.
                 *
                 * Current index - previous temperature's index.
                 */
                ans[pair.getValue()] = i - pair.getValue();
            }

            /*
             * The current temperature has not yet found a warmer
             * temperature, so push it onto the stack along with
             * its index.
             */
            stack.push(new KeyValuePair(temperatures[i], i));
        }

        /*
         * Return the number of days until a warmer temperature
         * for every day.
         */
        return ans;
    }

    /*
     * Stores a temperature and its corresponding index.
     */
    static class KeyValuePair {

        int key;
        int value;

        public KeyValuePair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return this.key;
        }

        public int getValue() {
            return this.value;
        }
    }
}