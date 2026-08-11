package com.dsa.phase1.prefixSum;

/**
 * ==========================================================
 * Problem    : LeetCode 303 - Range Sum Query - Immutable
 * Difficulty : Easy
 * Pattern    : Prefix Sum
 * <p>
 * Idea:
 * Since the array is immutable, build the prefix sum array
 * once in the constructor.
 * <p>
 * For every index:
 * <p>
 * prefix[i] = sum of elements from index 0 to i
 * <p>
 * For a range [left, right]:
 * <p>
 * If left == 0:
 * sum = prefix[right]
 * <p>
 * Otherwise:
 * sum = prefix[right] - prefix[left - 1]
 * <p>
 * The prefix array allows every range sum query to be
 * answered in O(1) time.
 * <p>
 * Time Complexity : O(n) preprocessing
 * O(1) per query
 * <p>
 * Space Complexity: O(n)
 * <p>
 * ==========================================================
 */
public class RangeSumQuery {
}

class NumArray {

    private int[] prefix;

    public NumArray(int[] nums) {

        prefix = new int[nums.length];
        prefix[0] = nums[0];

        // Build prefix sum once during initialization.
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {

        if (left == 0) {
            return prefix[right];
        }

        return prefix[right] - prefix[left - 1];
    }
}