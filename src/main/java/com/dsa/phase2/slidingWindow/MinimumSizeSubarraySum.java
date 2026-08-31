package com.dsa.phase2.slidingWindow;

/**
 * ==========================================================
 * Problem    : LeetCode 209 - Minimum Size Subarray Sum
 * Difficulty : Medium
 * Pattern    : Sliding Window
 * <p>
 * Problem:
 * Given an array of positive integers `nums` and a positive
 * integer `target`, find the minimum length of a contiguous
 * subarray whose sum is greater than or equal to `target`.
 * <p>
 * If no such subarray exists, return 0.
 * ==========================================================
 */
public class MinimumSizeSubarraySum {

    /**
     * ==========================================================
     * Approach 1 : Sliding Window - User's Approach
     * <p>
     * Idea:
     * We maintain a sliding window using `left` and `right`.
     * <p>
     * Whenever the current window's sum becomes greater than
     * or equal to `target`, we record its length and then move
     * `left` forward.
     * <p>
     * Before continuing, we temporarily remove both the
     * leftmost and rightmost elements from the sum. The next
     * iteration adds the rightmost element back, effectively
     * allowing us to test the smaller window with the same
     * `right` boundary.
     * <p>
     * Example:
     * [2, 3, 1, 4]
     * L        R
     * sum = 10
     * <p>
     * After finding a valid window, we remove `2` and `4`.
     * On the next iteration, `4` is added back, giving:
     * <p>
     * [3, 1, 4]
     * L     R
     * <p>
     * This allows us to test the smaller window.
     * <p>
     * The approach is accepted, but it performs an unnecessary
     * remove-right/add-right operation.
     * ==========================================================
     */
    public int minSubArrayLen(int target, int[] nums) {

        int min = Integer.MAX_VALUE;

        // Left boundary of the sliding window.
        int left = 0;

        // Sum of the current window.
        int sum = 0;

        // Right boundary of the sliding window.
        int right = 0;

        // Continue while both pointers are within the array.
        while (right < nums.length && left < nums.length) {

            // Add the rightmost element to the window.
            sum += nums[right];

            // Once the window reaches the target sum,
            // record its length.
            if (sum >= target) {

                min = Math.min(min, right - left + 1);

                // Remove the leftmost element.
                sum -= nums[left];

                // Temporarily remove the rightmost element.
                // It will be added again in the next iteration
                // if right does not need to be reset.
                sum -= nums[right];

                // Move the left boundary forward.
                left++;

                // If right has reached/passed left,
                // restart the window from left.
                if (right <= left) {
                    right = left;
                }

            } else {

                // Current sum is not enough, so expand
                // the window by moving right forward.
                right++;
            }
        }

        // No valid subarray was found.
        if (min == Integer.MAX_VALUE) {
            return 0;
        }

        return min;
    }


    /**
     * ==========================================================
     * Approach 2 : Optimized Sliding Window
     * <p>
     * Idea:
     * We expand the window by moving `right` forward until the
     * sum becomes greater than or equal to `target`.
     * <p>
     * Once the window becomes valid, there is no need to move
     * `right` or remove `nums[right]`.
     * <p>
     * Instead, we keep `right` fixed and repeatedly move
     * `left` forward, shrinking the window while its sum is
     * still greater than or equal to `target`.
     * <p>
     * This allows us to test every smaller valid window ending
     * at the current `right`.
     * <p>
     * Example:
     * [2, 3, 1, 4]
     * L        R
     * sum = 10
     * <p>
     * Check:
     * [2, 3, 1, 4] -> length 4
     * [3, 1, 4] -> length 3
     * [1, 4] -> sum < target, stop shrinking
     * <p>
     * The right pointer only moves forward and the left pointer
     * only moves forward, so each element is processed at most
     * twice.
     * ==========================================================
     */
    public int minSubArrayLenOptimized(int target, int[] nums) {

        int min = Integer.MAX_VALUE;

        // Left boundary of the sliding window.
        int left = 0;

        // Sum of the current window.
        int sum = 0;

        // Expand the window using right.
        for (int right = 0; right < nums.length; right++) {

            // Add the current element to the window.
            sum += nums[right];

            // While the current window satisfies the target,
            // try shrinking it from the left.
            while (sum >= target) {

                // Update the minimum valid window length.
                min = Math.min(min, right - left + 1);

                // Remove the leftmost element.
                sum -= nums[left];

                // Shrink the window from the left.
                left++;
            }
        }

        // No valid subarray was found.
        if (min == Integer.MAX_VALUE) {
            return 0;
        }

        return min;
    }
}