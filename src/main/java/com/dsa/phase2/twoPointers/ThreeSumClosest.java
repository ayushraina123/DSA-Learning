package com.dsa.phase2.twoPointers;

import java.util.Arrays;

/**
 * ==========================================================
 * Problem    : LeetCode 16 - 3Sum Closest
 * Difficulty : Medium
 * Pattern    : Two Pointers
 * ==========================================================
 *
 * <p>
 * Given an integer array {@code nums} and an integer
 * {@code target}, find three integers in {@code nums} whose
 * sum is closest to {@code target}.
 * </p>
 *
 * <p>
 * Return the sum of the three integers.
 * </p>
 *
 * <p>
 * Example:
 *
 * <pre>
 * nums   = [-1, 2, 1, -4]
 * target = 1
 *
 * After sorting:
 *
 * [-4, -1, 1, 2]
 *
 * Consider:
 *
 * -4 + 1 + 2 = -1
 * Difference from target = |1 - (-1)| = 2
 *
 * -1 + 1 + 2 = 2
 * Difference from target = |1 - 2| = 1
 *
 * Therefore:
 *
 * closest sum = 2
 * </pre>
 *
 * <p>
 * Key Observation:
 * <p>
 * This problem is essentially an extension of the Two Sum
 * technique.
 *
 * <p>
 * We first sort the array. Then:
 *
 * <ul>
 *     <li>
 *         {@code i} fixes the first element of the triplet.
 *     </li>
 *     <li>
 *         {@code j} starts immediately after {@code i}.
 *     </li>
 *     <li>
 *         {@code k} starts at the end of the array.
 *     </li>
 * </ul>
 *
 * <p>
 * For every fixed {@code i}, we use {@code j} and {@code k}
 * as two pointers to search for the sum closest to
 * {@code target}.
 * </p>
 *
 * <p>
 * Because the array is sorted, we can determine which pointer
 * to move based on the current sum.
 *
 * <ul>
 *     <li>
 *         If {@code sum < target}, we need a larger sum, so
 *         move {@code j} to the right.
 *     </li>
 *     <li>
 *         If {@code sum > target}, we need a smaller sum, so
 *         move {@code k} to the left.
 *     </li>
 *     <li>
 *         If {@code sum == target}, we have found the exact
 *         target and can immediately return it.
 *     </li>
 * </ul>
 *
 * <p>
 * At every iteration, we calculate the difference between the
 * current sum and the target.
 *
 * <pre>
 * difference = |target - sum|
 * </pre>
 * <p>
 * If this difference is smaller than the best difference seen
 * so far, we update {@code closestSum}.
 *
 * <p>
 * Why does the Two Pointers approach work?
 * <p>
 * After sorting, increasing {@code j} increases the sum while
 * decreasing {@code k} decreases the sum.
 * <p>
 * Therefore:
 *
 * <pre>
 * sum < target  → j++
 * sum > target  → k--
 * </pre>
 * <p>
 * This lets us systematically move toward the target without
 * checking every possible triplet.
 *
 * <p>
 * Complexity:
 *
 * <pre>
 * Sorting      : O(n log n)
 * Outer loop   : O(n)
 * Two pointers : O(n) for each i
 *
 * Total time   : O(n²)
 * Space        : O(1) auxiliary space
 *                (ignoring the space used internally by sorting)
 * </pre>
 */
public class ThreeSumClosest {

    /**
     * Finds the sum of three integers that is closest to the
     * given target.
     *
     * <p>
     * The algorithm fixes one element using {@code i} and then
     * uses the Two Pointers technique on the remaining portion
     * of the sorted array.
     *
     * @param nums   input array of integers
     * @param target target sum
     * @return the sum of the three integers closest to target
     */
    public int threeSumClosest(int[] nums, int target) {

        /*
         * ======================================================
         * Track the best answer found so far
         * ======================================================
         *
         * minDiff:
         *     Smallest absolute difference between any triplet
         *     sum and the target found so far.
         *
         * closestSum:
         *     The actual triplet sum corresponding to minDiff.
         */
        int minDiff = Integer.MAX_VALUE;
        int closestSum = 0;

        /*
         * Variables used during the Two Pointers traversal.
         */
        int diff;
        int j;
        int k;
        int sum;

        /*
         * ======================================================
         * Sort the array
         * ======================================================
         *
         * Sorting is essential because it allows us to decide
         * which pointer to move based on the current sum.
         *
         * After sorting:
         *
         * nums[j] increases when j moves right.
         * nums[k] decreases when k moves left.
         */
        Arrays.sort(nums);

        /*
         * ======================================================
         * Fix the first element
         * ======================================================
         *
         * i represents the first element of the triplet.
         *
         * For every i:
         *
         * j -> starts immediately after i
         * k -> starts at the end of the array
         *
         * This reduces the problem to finding the best pair
         * for each fixed nums[i].
         */
        for (int i = 0; i < nums.length; i++) {

            /*
             * The second pointer must start after i so that
             * the same element is never used twice.
             */
            j = i + 1;

            /*
             * Start the third pointer at the largest element.
             */
            k = nums.length - 1;

            /*
             * Continue while there are at least two elements
             * available for the current fixed i.
             */
            while (j < k) {

                /*
                 * ==================================================
                 * Calculate the current triplet sum
                 * ==================================================
                 */
                sum = nums[i] + nums[j] + nums[k];

                /*
                 * Calculate how far the current sum is from
                 * the target.
                 *
                 * The absolute value represents the actual
                 * distance from the target regardless of whether
                 * the sum is smaller or larger.
                 */
                diff = target - sum;

                /*
                 * ==================================================
                 * Exact target found
                 * ==================================================
                 *
                 * This is the best possible answer.
                 *
                 * No other triplet can have a smaller difference
                 * than zero, so we can immediately return.
                 */
                if (sum == target) {
                    return sum;

                    /*
                     * ==================================================
                     * Current sum is smaller than target
                     * ==================================================
                     *
                     * We need to increase the sum.
                     *
                     * Since the array is sorted, moving j to the
                     * right gives us a larger value.
                     *
                     * Therefore:
                     *
                     *     j++
                     */
                } else if (sum < target) {

                    /*
                     * Check whether this triplet is closer to
                     * the target than the best triplet found so far.
                     */
                    if (Math.abs(diff) < minDiff) {
                        minDiff = Math.abs(diff);
                        closestSum = sum;
                    }

                    /*
                     * Need a larger sum.
                     */
                    j++;

                    /*
                     * ==================================================
                     * Current sum is larger than target
                     * ==================================================
                     *
                     * We need to decrease the sum.
                     *
                     * Since the array is sorted, moving k to the
                     * left gives us a smaller value.
                     *
                     * Therefore:
                     *
                     *     k--
                     */
                } else {

                    /*
                     * Check whether this triplet is closer to
                     * the target than the best triplet found so far.
                     */
                    if (Math.abs(diff) < minDiff) {
                        minDiff = Math.abs(diff);
                        closestSum = sum;
                    }

                    /*
                     * Need a smaller sum.
                     */
                    k--;
                }
            }
        }

        /*
         * No exact target was found, so return the triplet sum
         * that had the smallest difference from the target.
         */
        return closestSum;
    }
}