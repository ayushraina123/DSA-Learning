package com.dsa.phase1.matrix;

/**
 * ==========================================================
 * Problem    : LeetCode 189 - Rotate Array
 * Difficulty : Medium
 * Pattern    : Array / In-place Array Manipulation
 * <p>
 * Idea:
 * We need to rotate the array to the right by k positions.
 * <p>
 * Instead of using an additional array, we perform the rotation
 * in-place using the reversal technique.
 * <p>
 * Consider the array as two parts:
 * <p>
 * [1 2 3 4 | 5 6 7]
 * A          B
 * <p>
 * For k = 3, we need:
 * <p>
 * [5 6 7 | 1 2 3 4]
 * B          A
 * <p>
 * We can achieve this by reversing the two parts individually
 * and then reversing the entire array.
 * <p>
 * The process is:
 * <p>
 * 1. Reverse the last k elements.
 * 2. Reverse the first n-k elements.
 * 3. Reverse the entire array.
 * <p>
 * Example:
 * <p>
 * Original:
 * 1 2 3 4 5 6 7
 * <p>
 * k = 3
 * <p>
 * Step 1 - Reverse last k elements:
 * 1 2 3 4 7 6 5
 * <p>
 * Step 2 - Reverse first n-k elements:
 * 4 3 2 1 7 6 5
 * <p>
 * Step 3 - Reverse the entire array:
 * 5 6 7 1 2 3 4
 * <p>
 * Therefore, the array is rotated to the right by k positions.
 * <p>
 * Before performing the rotation, k is reduced using:
 * <p>
 * k = k % nums.length
 * <p>
 * This handles cases where k is greater than the array length.
 * For example, rotating an array of length 7 by 10 positions
 * is equivalent to rotating it by 3 positions.
 * <p>
 * If k becomes 0, the array is already in its required state,
 * so we return immediately.
 * <p>
 * The reverse() helper performs an in-place reversal of the
 * elements between the given left and right indices using
 * two pointers.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 * ==========================================================
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {

        // Reduce k so that it is within the array's range.
        k = k % nums.length;

        // If k is 0, no rotation is required.
        if (k == 0) {
            return;
        }

        // Reverse the last k elements.
        //
        // Example:
        // [1 2 3 4 | 5 6 7]
        //              ↓
        // [1 2 3 4 | 7 6 5]
        reverse(nums, nums.length - k, nums.length - 1);

        // Reverse the first n-k elements.
        //
        // [1 2 3 4 | 7 6 5]
        //      ↓
        // [4 3 2 1 | 7 6 5]
        reverse(nums, 0, nums.length - k - 1);

        // Reverse the entire array.
        //
        // [4 3 2 1 | 7 6 5]
        //      ↓
        // [5 6 7 | 1 2 3 4]
        reverse(nums, 0, nums.length - 1);
    }

    /**
     * Reverses the elements of the array between left and right
     * indices in-place.
     * <p>
     * Two pointers are used:
     * <p>
     * left  -> starts from the beginning of the range
     * right -> starts from the end of the range
     * <p>
     * The elements at left and right are swapped, and both
     * pointers move towards the center until they meet.
     */
    public void reverse(int[] nums, int left, int right) {

        while (left < right) {

            // Swap the elements at left and right.
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            // Move both pointers towards the center.
            left++;
            right--;
        }
    }
}