package com.dsa.phase2.twoPointers;

/**
 * ==========================================================
 * Problem    : LeetCode 977 - Squares of a Sorted Array
 * Difficulty : Easy
 * Pattern    : Two Pointers
 * <p>
 * Idea:
 * The input array is sorted in non-decreasing order, but after
 * squaring the elements, the resulting array may no longer be
 * sorted because negative values can produce large squares.
 * <p>
 * Example:
 * nums = [-7, -3, 2, 3, 11]
 * squares = [49, 9, 4, 9, 121]
 * <p>
 * Instead of squaring every element and then sorting, use the
 * sorted property of the original array.
 * <p>
 * The largest square must come from either:
 * • The leftmost element (most negative value).
 * • The rightmost element (largest positive value).
 * <p>
 * Therefore, use two pointers:
 * • i points to the leftmost remaining element.
 * • j points to the rightmost remaining element.
 * • k points to the next position to fill in the result array.
 * <p>
 * Since we are finding the largest square first, fill the result
 * array from right to left.
 * <p>
 * At every step:
 * • Compare |nums[i]| and |nums[j]|.
 * • Place the larger square at result[k].
 * • Move the corresponding pointer.
 * <p>
 * If both absolute values are equal, both elements produce the
 * same square, so place both squares and move both pointers.
 * <p>
 * This avoids sorting completely.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 * <p>
 * Learnings:
 * ✔ A sorted array can often be exploited with Two Pointers.
 * ✔ After squaring, the largest value can only come from one
 * of the two ends of the original sorted array.
 * ✔ When constructing the result in sorted order, filling from
 * right to left allows us to place the largest values first.
 * ✔ Comparing absolute values is enough to determine which
 * element produces the larger square.
 * ✔ We can reduce the O(n log n) sorting approach to O(n).
 * ==========================================================
 */
public class SquaresOfSortedArray {

    public int[] sortedSquares(int[] nums) {

        // Stores the squared values in sorted order.
        int[] result = new int[nums.length];

        // i points to the leftmost remaining element.
        int i = 0;

        // j points to the rightmost remaining element.
        int j = nums.length - 1;

        // k points to the position where the next largest
        // square should be placed.
        int k = nums.length - 1;

        while (i < j) {

            // The element with the larger absolute value will
            // produce the larger square.
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {

                result[k--] = nums[i] * nums[i];
                i++;

            } else if (Math.abs(nums[j]) > Math.abs(nums[i])) {

                result[k--] = nums[j] * nums[j];
                j--;

            } else {

                // Both elements have the same absolute value,
                // so both produce the same square.
                result[k--] = nums[i] * nums[i];
                result[k--] = nums[j] * nums[j];

                i++;
                j--;
            }
        }

        // Handle the final remaining element when i == j.
        if (k >= 0) {
            result[k] = nums[i] * nums[i];
        }

        return result;
    }
}