package com.dsa.phase1.prefixSum;

/**
 * ==========================================================
 * Problem    : LeetCode 724 - Find Pivot Index
 * Difficulty : Easy
 * Pattern    : Prefix Sum
 * <p>
 * Idea:
 * A pivot index is an index where the sum of all elements
 * to its left is equal to the sum of all elements to its right.
 * <p>
 * We first build a prefix sum array:
 * <p>
 * prefix[i] = sum of elements from index 0 to i
 * <p>
 * For every index i:
 * <p>
 * leftSum:
 * prefix[i - 1]                  (if i > 0)
 * <p>
 * rightSum:
 * prefix[n - 1] - prefix[i]
 * <p>
 * If leftSum == rightSum, i is the pivot index.
 * <p>
 * The pivot element itself is excluded from both sums.
 * <p>
 * Special Case:
 * For i == 0, there are no elements on the left,
 * so leftSum = 0.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 * <p>
 * ==========================================================
 */
public class FindPivotIndex {

    public int pivotIndex(int[] nums) {

        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];

        // Build the prefix sum array.
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        // Check whether left sum equals right sum at each index.
        for (int i = 0; i < prefix.length; i++) {

            int rightSum = prefix[prefix.length - 1] - prefix[i];

            // No elements exist to the left of index 0.
            if (i == 0) {
                if (rightSum == 0) {
                    return i;
                }
                continue;
            }

            int leftSum = prefix[i - 1];

            if (rightSum == leftSum) {
                return i;
            }
        }

        return -1;
    }
}