package com.dsa.phase1.arrays;

/**
 * ==========================================================
 * Problem    : LeetCode 53 - Maximum Subarray
 * Difficulty : Medium
 * Pattern    : Kadane's Algorithm (Dynamic Programming)
 * <p>
 * Idea:
 * Instead of checking every possible subarray, process the
 * array from left to right while maintaining the maximum
 * subarray sum ending at the current index.
 * <p>
 * For every element, there are only two possibilities:
 * • Extend the previous subarray.
 * • Start a brand new subarray from the current element.
 * <p>
 * If extending the previous subarray produces a smaller sum
 * than taking the current element alone, discard the previous
 * subarray and start fresh.
 * <p>
 * DP Recurrence:
 * dp[i] = max(nums[i], dp[i - 1] + nums[i])
 * <p>
 * Since dp[i] depends only on dp[i - 1], we don't need an
 * entire DP array. We optimize the space to a single variable
 * (currentSum), making this Kadane's Algorithm.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 * <p>
 * Learnings:
 * ✔ Think about the best subarray ending at the current index,
 * not the entire array.
 * ✔ A negative running sum can never improve any future subarray.
 * ✔ Many Dynamic Programming problems can be reduced from O(n)
 * space to O(1) when only the previous state is required.
 * ✔ Kadane's Algorithm is simply a space-optimized Dynamic
 * Programming solution.
 * ==========================================================
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {

        // Stores the maximum subarray sum found so far.
        int maxSum = Integer.MIN_VALUE;

        // Stores the maximum subarray sum ending at the current index.
        int currentSum = 0;

        for (int num : nums) {

            // Try extending the previous subarray.
            currentSum += num;

            // If starting a new subarray from the current element
            // gives a better sum than extending the previous one,
            // discard the previous subarray.
            if (num > currentSum) {
                currentSum = num;
            }

            // Update the overall maximum subarray sum.
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}