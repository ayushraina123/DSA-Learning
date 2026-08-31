package com.dsa.phase2.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 930 - Binary Subarrays With Sum
 * Difficulty : Medium
 * Pattern    : Prefix Sum + HashMap
 * <p>
 * Idea:
 * We need to count the number of subarrays whose sum is
 * exactly equal to `goal`.
 * <p>
 * A subarray from `left` to `right` can be represented using
 * prefix sums:
 * <p>
 * subarraySum = prefix[right] - prefix[left - 1]
 * <p>
 * We want:
 * <p>
 * prefix[right] - prefix[left - 1] = goal
 * <p>
 * Rearranging:
 * <p>
 * prefix[left - 1] = prefix[right] - goal
 * <p>
 * Therefore, while iterating through the prefix sums, for the
 * current prefix sum we need to know how many times we have
 * previously seen:
 * <p>
 * currentPrefix - goal
 * <p>
 * The HashMap stores:
 * <p>
 * prefix sum -> number of times that prefix sum occurred
 * <p>
 * If `currentPrefix - goal` has occurred multiple times, then
 * there are multiple different subarrays ending at the current
 * position whose sum is exactly `goal`.
 * <p>
 * ==========================================================
 * <p>
 * Example:
 * <p>
 * nums = [1, 0, 1]
 * goal = 1
 * <p>
 * Prefix sums:
 * <p>
 * [1, 1, 2]
 * <p>
 * At prefix = 1:
 * <p>
 * 1 - 1 = 0
 * <p>
 * We have already stored prefix sum 0 once, representing the
 * prefix before the array starts.
 * <p>
 * Therefore:
 * <p>
 * [1]
 * <p>
 * is a valid subarray.
 * <p>
 * ==========================================================
 */
public class BinarySubarraysWithSum {

    public int numSubarraysWithSum(int[] nums, int goal) {

        // Stores the number of valid subarrays found.
        int count = 0;

        /*
         * Stores:
         *
         *     prefix sum -> frequency
         *
         * The frequency tells us how many previous prefix sums
         * can be used to form a subarray with sum = goal.
         */
        Map<Integer, Integer> map = new HashMap<>();

        /*
         * Represents the prefix sum before the array starts.
         *
         * This is necessary for subarrays that begin at index 0.
         */
        map.put(0, 1);

        // Stores the prefix sum up to every index.
        int[] prefix = new int[nums.length];

        // Prefix sum ending at index 0.
        prefix[0] = nums[0];

        // Build the prefix sum array.
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        /*
         * Iterate through every prefix sum.
         */
        for (int i = 0; i < prefix.length; i++) {

            /*
             * We know:
             *
             *     prefix[right] - prefix[left - 1] = goal
             *
             * Therefore:
             *
             *     prefix[left - 1] = prefix[right] - goal
             *
             * So we look for previous occurrences of:
             *
             *     prefix[i] - goal
             */
            if (map.containsKey(prefix[i] - goal)) {

                /*
                 * If the required prefix sum has occurred
                 * multiple times, each occurrence represents
                 * a different valid subarray ending at i.
                 */
                count += map.get(prefix[i] - goal);
            }

            /*
             * Store the current prefix sum so that it can be
             * used as a previous prefix sum for future indices.
             */
            map.put(prefix[i], map.getOrDefault(prefix[i], 0) + 1);
        }

        return count;
    }
}