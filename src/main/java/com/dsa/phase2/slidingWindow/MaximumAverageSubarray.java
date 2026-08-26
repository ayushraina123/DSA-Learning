package com.dsa.phase2.slidingWindow;

/**
 * ==========================================================
 * Problem    : LeetCode 643 - Maximum Average Subarray I
 * Difficulty : Easy
 * Pattern    : Sliding Window - Fixed Size
 * ==========================================================
 *
 * <p>
 * Given an integer array nums and an integer k, find the
 * contiguous subarray of length k that has the maximum
 * average value.
 * </p>
 *
 * <p>
 * Example:
 * <p>
 * nums = [1, 12, -5, -6, 50, 3]
 * k = 4
 * <p>
 * Possible windows:
 * <p>
 * [1, 12, -5, -6]  -> sum = 2
 * [12, -5, -6, 50] -> sum = 51
 * [-5, -6, 50, 3]  -> sum = 42
 * <p>
 * Maximum average:
 * 51 / 4 = 12.75
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Approach: Fixed-Size Sliding Window
 * ==========================================================
 *
 * <p>
 * Since every subarray must contain exactly k elements, this
 * is a Fixed-Size Sliding Window problem.
 * </p>
 *
 * <p>
 * We maintain a window between two pointers:
 * </p>
 * <p>
 * left  -> beginning of the current window
 * right -> end of the current window
 *
 * <p>
 * We also maintain:
 * </p>
 * <p>
 * currentSum -> sum of all elements inside the window
 *
 * <p>
 * As right moves forward, the new element is added to the
 * current window.
 * </p>
 *
 * <p>
 * Once the window reaches size k, we calculate its average
 * and update the maximum average.
 * </p>
 *
 * <p>
 * After processing the window, nums[left] is removed from
 * currentSum and left is moved forward.
 * This slides the window by one position.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Window Size
 * ==========================================================
 *
 * <p>
 * The current window size is calculated as:
 * </p>
 * <p>
 * right - left + 1
 *
 * <p>
 * We process the window only when:
 * </p>
 * <p>
 * right - left + 1 == k
 *
 * <p>
 * This guarantees that every processed window contains
 * exactly k elements.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Example of Sliding
 * ==========================================================
 *
 * <p>
 * Suppose:
 * </p>
 * <p>
 * nums = [1, 12, -5, -6, 50, 3]
 * k = 4
 *
 * <p>
 * First window:
 * </p>
 * <p>
 * [1, 12, -5, -6]
 * ↑           ↑
 * left        right
 *
 * <p>
 * After processing it:
 * </p>
 * <p>
 * currentSum -= nums[left]
 * left++
 *
 * <p>
 * The window then becomes:
 * </p>
 * <p>
 * [12, -5, -6, 50]
 * ↑           ↑
 * left        right
 *
 * <p>
 * Instead of calculating this new sum from scratch, we reuse
 * the previous window's sum.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Why This Works
 * ==========================================================
 *
 * <p>
 * Consecutive windows overlap by k - 1 elements.
 * Therefore, recalculating the complete sum for every window
 * would perform unnecessary work.
 * </p>
 *
 * <p>
 * When the window moves one position:
 * </p>
 * <p>
 * Remove the element leaving the window.
 * <p>
 * Add the element entering the window.
 *
 * <p>
 * Therefore:
 * </p>
 * <p>
 * new window sum
 * =
 * previous window sum
 * - outgoing element
 * + incoming element
 *
 * <p>
 * This allows us to process every window efficiently.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Algorithm
 * ==========================================================
 *
 * <p>
 * 1. Initialize maxAvg to the smallest possible value.
 * </p>
 *
 * <p>
 * 2. Initialize currentSum to 0.
 * </p>
 *
 * <p>
 * 3. Initialize left to 0.
 * </p>
 *
 * <p>
 * 4. Move right from the beginning of the array to the end.
 * </p>
 *
 * <p>
 * 5. Add nums[right] to currentSum.
 * </p>
 *
 * <p>
 * 6. Check whether the current window has reached size k.
 * </p>
 *
 * <p>
 * 7. If the window size is k:
 * </p>
 * <p>
 * - Calculate the average of the current window.
 * - Update maxAvg.
 * - Remove nums[left] from currentSum.
 * - Move left forward.
 *
 * <p>
 * 8. Continue until right reaches the end of the array.
 * </p>
 *
 * <p>
 * 9. Return maxAvg.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Implementation
 * ==========================================================
 */
public class MaximumAverageSubarray {

    public double findMaxAverage(int[] nums, int k) {

        /*
         * ======================================================
         * Track the maximum average found so far.
         * ======================================================
         *
         * We initialize it to a very small value so that the
         * first valid window can always update it.
         */
        double maxAvg = Integer.MIN_VALUE;

        /*
         * Sum of the elements currently inside the window.
         */
        int currentSum = 0;

        /*
         * left marks the beginning of the current window.
         */
        int left = 0;

        /*
         * right expands the window one element at a time.
         */
        for (int right = 0; right < nums.length; right++) {

            /*
             * Add the newly included element to the
             * current window's sum.
             */
            currentSum += nums[right];

            /*
             * ==================================================
             * Check whether the window has reached size k.
             * ==================================================
             *
             * Window size:
             *
             *     right - left + 1
             *
             * Once it becomes k, we have a complete window
             * that can be evaluated.
             */
            if (right - left + 1 == k) {

                /*
                 * Calculate the average of the current
                 * window and update the maximum average.
                 *
                 * Cast currentSum to double so that the
                 * division produces a decimal result.
                 */
                maxAvg = Math.max(
                        (double) currentSum / k,
                        maxAvg
                );

                /*
                 * ==================================================
                 * Slide the window forward.
                 * ==================================================
                 *
                 * nums[left] is the element that is leaving
                 * the current window.
                 *
                 * Remove it from currentSum.
                 */
                currentSum -= nums[left];

                /*
                 * Move left forward so that the next window
                 * starts at the following element.
                 */
                left++;
            }
        }

        /*
         * Return the maximum average found among all
         * contiguous subarrays of length k.
         */
        return maxAvg;
    }
}

/*
 * ==========================================================
 * Alternative Approach: Brute Force
 * ==========================================================
 *
 * <p>
 * A brute-force solution could calculate the sum of every
 * possible subarray of length k independently.
 * </p>
 *
 * <p>
 * For every starting position, we would iterate through the
 * next k elements and calculate their sum.
 * </p>
 *
 * Time  : O(n * k)
 * Space : O(1)
 *
 * <p>
 * The Sliding Window approach improves this to O(n) by
 * reusing the sum of the previous window instead of
 * recalculating all k elements.
 * </p>
 *
 *
 * ==========================================================
 * Complexity
 * ==========================================================
 *
 * Time  : O(n)
 *
 * <p>
 * The right pointer moves through the array once.
 * The left pointer also moves only forward and never moves
 * backward.
 * </p>
 *
 * <p>
 * Therefore, the total amount of pointer movement is
 * linear with respect to the input size.
 * </p>
 *
 * Space : O(1)
 *
 * <p>
 * Only a constant number of variables are used regardless
 * of the input size.
 * </p>
 */