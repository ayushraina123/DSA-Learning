package com.dsa.phase2.binarySearch;

/**
 * ==========================================================
 * Problem    : LeetCode 704 - Binary Search
 * Difficulty : Easy
 * Pattern    : Binary Search
 * <p>
 * Idea:
 * Since the array is sorted, we can eliminate half of the
 * remaining search space after comparing the target with the
 * middle element.
 * <p>
 * We maintain two pointers:
 * <ul>
 *     <li>`left` - beginning of the current search space</li>
 *     <li>`right` - end of the current search space</li>
 * </ul>
 * <p>
 * At each iteration:
 * <ul>
 *     <li>If `nums[mid]` equals the target, return `mid`</li>
 *     <li>If `nums[mid]` is smaller, search the right half</li>
 *     <li>If `nums[mid]` is greater, search the left half</li>
 * </ul>
 * <p>
 * The search continues while `left <= right`, meaning there
 * is still at least one possible element in the search space.
 * ==========================================================
 */
public class BinarySearch {

    public int search(int[] nums, int target) {

        // Define the initial search space.
        int left = 0;
        int right = nums.length - 1;

        // Calculate the middle index.
        int mid = left + (right - left) / 2;

        // Continue while at least one possible element remains.
        while (left <= right) {

            // Target found.
            if (nums[mid] == target) {
                return mid;
            }

            // Target must be in the right half.
            else if (nums[mid] < target) {
                left = mid + 1;
            }

            // Target must be in the left half.
            else {
                right = mid - 1;
            }

            // Recalculate mid for the updated search space.
            mid = left + (right - left) / 2;
        }

        // Target does not exist in the array.
        return -1;
    }
}