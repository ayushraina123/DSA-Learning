package com.dsa.phase1.prefixSum;

import java.util.Arrays;

/**
 * ==========================================================
 * Pattern    : Prefix Sum
 * <p>
 * Idea:
 * Prefix Sum is used when we need to calculate the sum of
 * a subarray/range efficiently.
 * <p>
 * We build a prefix array where:
 * <p>
 * prefix[i] = sum of elements from index 0 to i
 * <p>
 * Example:
 * nums   = [2, 4, 1, 5, 3]
 * <p>
 * prefix = [2, 6, 7, 12, 15]
 * <p>
 * Once the prefix array is built, the sum of any range
 * [l, r] can be calculated in O(1):
 * <p>
 * If l == 0:
 * sum = prefix[r]
 * <p>
 * Otherwise:
 * sum = prefix[r] - prefix[l - 1]
 * <p>
 * Time Complexity : O(n) for building prefix array
 * O(1) for each range sum query
 * <p>
 * Space Complexity: O(n)
 * <p>
 * ==========================================================
 */

public class PrefixSum {
    public int[] buildPrefixSum(int[] nums) {

        int[] prefix = new int[nums.length];

        prefix[0] = nums[0];

        // Each prefix[i] stores the sum from index 0 to i.
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        return prefix;
    }

    // Returns the sum of elements in the range [left, right].
    public int rangeSum(int[] prefix, int left, int right) {

        if (left == 0) {
            return prefix[right];
        }

        return prefix[right] - prefix[left - 1];
    }

    public static void main(String[] args) {
        PrefixSum ps = new PrefixSum();
        System.out.println(Arrays.toString(ps.buildPrefixSum(new int[]{1, 2, 3, 4})));
        System.out.println((ps.rangeSum(new int[]{1, 2, 3, 4}, 0, 2)));
    }
}
