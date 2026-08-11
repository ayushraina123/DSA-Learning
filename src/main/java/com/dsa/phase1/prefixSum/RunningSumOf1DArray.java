package com.dsa.phase1.prefixSum;

/**
 * ==========================================================
 * Problem    : LeetCode 1480 - Running Sum of 1D Array
 * Difficulty : Easy
 * Pattern    : Prefix Sum
 * <p>
 * Idea:
 * The running sum at index i is the sum of all elements from
 * index 0 to i.
 * <p>
 * We can store these sums in a separate prefix array.
 * <p>
 * For every index i:
 * <p>
 * prefix[i] = prefix[i - 1] + nums[i]
 * <p>
 * Example:
 * nums   = [1, 2, 3, 4]
 * <p>
 * prefix = [1, 3, 6, 10]
 * <p>
 * Each prefix[i] represents the sum of the subarray [0, i].
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 * <p>
 * ==========================================================
 */
public class RunningSumOf1DArray {

    public int[] runningSum(int[] nums) {

        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];

        // Build the prefix sum array.
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        return prefix;
    }
}
