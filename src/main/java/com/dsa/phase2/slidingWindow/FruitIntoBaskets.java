package com.dsa.phase2.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 904 - Fruit Into Baskets
 * Difficulty : Medium
 * Pattern    : Sliding Window
 * <p>
 * Idea:
 * We need to find the longest contiguous subarray containing
 * at most two distinct types of fruits.
 * <p>
 * Since we can only carry two types of fruits, we maintain a
 * sliding window containing at most two distinct fruit types.
 * <p>
 * `map` stores the frequency of each fruit inside the current
 * sliding window.
 * <p>
 * `sum` represents the current window size, i.e. the number of
 * fruits currently inside our two baskets.
 * <p>
 * Whenever the window contains more than two distinct fruit
 * types, we remove the leftmost fruit and move `left` forward.
 * <p>
 * Because adding the current fruit can make the window invalid,
 * we first remove one fruit from `sum` when `map.size() > 2`.
 * Then we add the current fruit to `sum`.
 * <p>
 * This keeps the window valid while maintaining the maximum
 * window size seen so far.
 * ==========================================================
 */
public class FruitIntoBaskets {

    public int totalFruit(int[] fruits) {

        // Stores the maximum number of fruits collected
        // from any valid window.
        int max = Integer.MIN_VALUE;

        // Stores the size of the current sliding window.
        int sum = 0;

        // Left boundary of the sliding window.
        int left = 0;

        // Stores the frequency of each fruit type
        // present in the current window.
        Map<Integer, Integer> map = new HashMap<>();

        // Expand the sliding window by adding each fruit.
        for (int fruit : fruits) {

            // Add the current fruit to the window.
            map.put(fruit, map.getOrDefault(fruit, 0) + 1);

            /*
             * We can only have two distinct fruit types.
             *
             * If a third type enters the window, remove the
             * leftmost fruit and shrink the window from the left.
             */
            if (map.size() > 2) {

                // One fruit is removed from the current window.
                sum -= 1;

                // Decrease the frequency of the leftmost fruit.
                map.put(fruits[left], map.get(fruits[left]) - 1);

                // If no fruits of this type remain in the window,
                // remove the fruit type from the map.
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }

                // Move the left boundary forward.
                left++;
            }

            // Add the current fruit to the window size.
            sum += 1;

            // Update the maximum valid window size.
            max = Math.max(max, sum);
        }

        return max;
    }
}