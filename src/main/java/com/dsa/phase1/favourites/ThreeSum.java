package com.dsa.phase1.favourites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ==========================================================
 * Problem    : LeetCode 15 - 3Sum
 * Difficulty : Medium
 * Pattern    : Sorting + Two Pointers
 * <p>
 * Idea:
 * We need to find all unique triplets [a, b, c] such that:
 * <p>
 * a + b + c = 0
 * <p>
 * The problem can be reduced to a Two Sum problem by fixing
 * one element and searching for the remaining two elements
 * using two pointers.
 * <p>
 * First, sort the array.
 * <p>
 * Once sorted:
 * - Fix nums[i] as the first element.
 * - Use j = i + 1 and k = nums.length - 1 as two pointers.
 * - If the sum is too small, move j forward.
 * - If the sum is too large, move k backward.
 * - If the sum is zero, we found a valid triplet.
 * <p>
 * Sorting also allows us to eliminate duplicate triplets
 * without using a HashSet by skipping duplicate values.
 * <p>
 * Duplicate handling:
 * 1. Skip duplicate nums[i] values so we don't solve the same
 * Two Sum problem more than once.
 * 2. After finding a valid triplet, move both pointers and
 * skip duplicate nums[j] and nums[k] values.
 * <p>
 * Why two pointers work:
 * Since the array is sorted:
 * - If sum < 0, we need a larger value -> move j forward.
 * - If sum > 0, we need a smaller value -> move k backward.
 * - If sum == 0, record the triplet and move both pointers.
 * ==========================================================
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Sorting enables the two-pointer technique
        // and allows duplicates to be skipped efficiently.
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            // Skip duplicate values for the first element.
            // Example:
            // [-1, -1, 0, 1, 2]
            // The second -1 does not need to be processed again.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    // Found a valid triplet.
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    // Move both pointers to search for another triplet.
                    j++;
                    k--;

                    // Skip duplicate values for the second element.
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }

                    // Skip duplicate values for the third element.
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }

                } else if (sum > 0) {
                    // Sum is too large.
                    // Since the array is sorted, decrease k
                    // to obtain a smaller value.
                    k--;

                } else {
                    // Sum is too small.
                    // Increase j to obtain a larger value.
                    j++;
                }
            }
        }

        return result;
    }

    /*
     * ==========================================================
     * Alternative Approach:
     * Sorting + Two Pointers + HashSet
     *
     * The HashSet version also works, but it handles duplicates
     * by storing every triplet in a Set.
     *
     * The optimized solution above avoids generating duplicate
     * triplets in the first place by skipping duplicate values
     * during traversal.
     *
     * Time Complexity : O(n²)
     * Space Complexity: O(n²) in the worst case for the HashSet
     *                    / result storage.
     * ==========================================================
     *
     * public List<List<Integer>> threeSum(int[] nums) {
     *     Set<List<Integer>> result = new HashSet<>();
     *
     *     Arrays.sort(nums);
     *
     *     for (int i = 0; i < nums.length; i++) {
     *         int j = i + 1;
     *         int k = nums.length - 1;
     *
     *         while (j < k) {
     *             int sum = nums[i] + nums[j] + nums[k];
     *
     *             if (sum == 0) {
     *                 result.add(
     *                     Arrays.asList(nums[i], nums[j], nums[k])
     *                 );
     *
     *                 j++;
     *                 k--;
     *
     *                 while (j < k && nums[j] == nums[j - 1]) {
     *                     j++;
     *                 }
     *
     *                 while (j < k && nums[k] == nums[k + 1]) {
     *                     k--;
     *                 }
     *
     *             } else if (sum > 0) {
     *                 k--;
     *             } else {
     *                 j++;
     *             }
     *         }
     *     }
     *
     *     return new ArrayList<>(result);
     * }
     */
}