package com.dsa.phase2.slidingWindow;

/**
 * ==========================================================
 * Problem    : LeetCode 1004 - Max Consecutive Ones III
 * Difficulty : Medium
 * Pattern    : Sliding Window
 * ==========================================================
 * <p>
 * Problem:
 * Given a binary array `nums` and an integer `k`, return the
 * maximum number of consecutive 1s in the array if we can flip
 * at most `k` zeroes into ones.
 * <p>
 * ==========================================================
 * <p>
 * APPROACH 1
 * ----------
 * Maintain `k` as the number of flips still available.
 * <p>
 * `k` acts as our window's "budget":
 * <p>
 * Encounter 0  -> k--
 * Remove 0     -> k++
 * <p>
 * If `k < 0`, the current window contains more zeroes than
 * we are allowed to flip, so we start moving `left`.
 * <p>
 * IMPORTANT:
 * In this implementation, `right` represents the NEXT element
 * that needs to be processed, rather than the current right
 * boundary of the window.
 * <p>
 * Therefore:
 * <p>
 * window length = right - left
 * <p>
 * We only move `right` when the current window is valid again.
 * <p>
 * ==========================================================
 */
public class MaxConsecutiveOnesIII {

    /*
     * ======================================================
     * APPROACH 1
     * ======================================================
     *
     * User's original solution.
     */
    public int longestOnesApproach1(int[] nums, int k) {

        // Stores the maximum valid window length.
        int max = 0;

        // Left boundary of the sliding window.
        int left = 0;

        // Represents the next element to process.
        int right = 0;

        while (right < nums.length) {

            /*
             * If the current element is zero, we need to
             * spend one available flip.
             *
             * k is allowed to become negative temporarily.
             * This indicates that the current window has more
             * zeroes than the allowed number of flips.
             */
            if (nums[right] == 0 && k >= 0) {
                k--;
            }

            /*
             * If k is still >= 0, the window is valid.
             *
             * Move right forward to process the next element.
             */
            if (k >= 0) {
                right++;

            } else {

                /*
                 * Window is invalid because we have exceeded
                 * the allowed number of zeroes.
                 *
                 * Remove the leftmost element.
                 */
                if (nums[left] == 0) {

                    // Removing a zero gives us one flip back.
                    k++;

                    /*
                     * If removing this zero makes the window
                     * valid again, move right forward.
                     */
                    if (k >= 0) {
                        right++;
                    }
                }

                // Move the left boundary forward.
                left++;
            }

            /*
             * `right` represents the next element to process,
             * so the current window length is:
             *
             *     right - left
             */
            max = Math.max(max, right - left);
        }

        return max;
    }


    /**
     * ======================================================
     * APPROACH 2
     * ======================================================
     * <p>
     * Cleaner Sliding Window implementation.
     * <p>
     * Here, `right` represents the CURRENT right boundary
     * of the window.
     * <p>
     * We first add `nums[right]` to the window.
     * <p>
     * If it is zero:
     * <p>
     * k--
     * <p>
     * If k becomes negative, the window is invalid, so we
     * shrink it from the left until the window becomes valid.
     * <p>
     * Once the window is valid:
     * <p>
     * window length = right - left + 1
     * <p>
     * This approach uses the standard sliding-window invariant:
     * <p>
     * [left ... right] always contains at most k zeroes.
     * ======================================================
     */
    public int longestOnesApproach2(int[] nums, int k) {

        // Stores the maximum valid window length.
        int max = 0;

        // Left boundary of the sliding window.
        int left = 0;

        /*
         * `right` represents the current right boundary
         * of the sliding window.
         */
        for (int right = 0; right < nums.length; right++) {

            /*
             * If we encounter a zero, consume one flip.
             */
            if (nums[right] == 0) {
                k--;
            }

            /*
             * If k becomes negative, the window contains
             * more zeroes than we are allowed to flip.
             *
             * Shrink the window from the left until it
             * becomes valid again.
             */
            while (k < 0) {

                /*
                 * If the element leaving the window is zero,
                 * we get that flip back.
                 */
                if (nums[left] == 0) {
                    k++;
                }

                // Move the left boundary forward.
                left++;
            }

            /*
             * At this point the window is valid.
             *
             * Since both left and right are INCLUSIVE:
             *
             *     window length = right - left + 1
             */
            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}