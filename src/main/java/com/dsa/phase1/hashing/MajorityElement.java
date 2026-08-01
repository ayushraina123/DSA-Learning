package com.dsa.phase1.hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 169 - Majority Element
 * Difficulty : Easy
 * Pattern    : Hashing
 * <p>
 * Idea:
 * Count the frequency of every number using a HashMap.
 * <p>
 * Since the majority element is guaranteed to appear more than
 * ⌊n / 2⌋ times, we increment the frequency of each element
 * while traversing the array and immediately check whether its
 * frequency has crossed the majority threshold.
 * <p>
 * This allows us to return the answer as soon as it is found,
 * avoiding an unnecessary full traversal in many cases.
 * <p>
 * ----------------------------------------------------------
 * Example:
 * <p>
 * nums = [2,2,1,1,1,2,2]
 * <p>
 * Frequency updates:
 * <p>
 * 2 -> 1
 * 2 -> 2
 * 1 -> 1
 * 1 -> 2
 * 1 -> 3
 * 2 -> 3
 * 2 -> 4   (> 7/2 = 3)
 * <p>
 * Answer = 2
 * <p>
 * ----------------------------------------------------------
 * Time Complexity  : O(n)
 * Space Complexity : O(n)
 * ==========================================================
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        // A majority element must occur more than n / 2 times.
        int majorityThreshold = nums.length / 2;

        // Stores the frequency of each number encountered.
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count frequencies while traversing the array.
        for (int num : nums) {

            // Increment the frequency of the current number.
            int count = frequencyMap.getOrDefault(num, 0) + 1;
            frequencyMap.put(num, count);

            // As soon as a number crosses the majority threshold,
            // it must be the majority element.
            if (count > majorityThreshold) {
                return num;
            }
        }

        /*
         * Defensive fallback.
         *
         * The problem guarantees that a majority element always exists,
         * so execution should never reach this point.
         */
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > majorityThreshold) {
                return entry.getKey();
            }
        }

        return 0;
    }
}