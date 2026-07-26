package com.dsa.phase1.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 1 - Two Sum
 * Difficulty : Easy
 * Pattern    : HashMap
 * <p>
 * Idea:
 * Instead of checking every possible pair, store the numbers
 * encountered so far in a HashMap.
 * <p>
 * For every current element:
 * 1. Calculate the complement required to reach the target.
 * 2. Check whether the complement has already been seen.
 * 3. If yes, we've found the answer.
 * 4. Otherwise, store the current number for future iterations.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 * <p>
 * Learnings:
 * ✔ Build and query the HashMap in the same traversal.
 * ✔ HashMap provides O(1) average lookup.
 * ✔ This is a classic "store previous information" problem.
 * ==========================================================
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        // Stores <Number, Index> for every element processed so far.
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            // Number required along with nums[i] to reach the target.
            int complement = target - nums[i];

            // If we've already seen the complement,
            // we've found the required pair.
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // Store current number so future elements can use it
            // as their complement.
            map.put(nums[i], i);
        }

        // Problem guarantees an answer.
        return new int[]{-1, -1};
    }
}