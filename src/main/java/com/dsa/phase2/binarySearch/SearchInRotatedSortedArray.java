package com.dsa.phase2.binarySearch;

/**
 * ==========================================================
 * Problem    : LeetCode 33 - Search in Rotated Sorted Array
 * Difficulty : Medium
 * Pattern    : Modified Binary Search
 * <p>
 * Idea:
 * A rotated sorted array consists of two sorted portions.
 * During every iteration, at least one half of the current
 * search space is guaranteed to be sorted.
 * <p>
 * We first determine which half is sorted:
 * <ul>
 *     <li>
 *         If `nums[left] <= nums[mid]`, the left half is sorted.
 *     </li>
 *     <li>
 *         Otherwise, the right half is sorted.
 *     </li>
 * </ul>
 * <p>
 * Once the sorted half is identified, we check whether the
 * target lies within its range.
 * <p>
 * If the target lies within the sorted half, we continue
 * searching there. Otherwise, we search the other half.
 * <p>
 * This allows us to eliminate half of the search space during
 * every iteration, just like standard Binary Search.
 * ==========================================================
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {

        // Define the initial search space.
        int left = 0;
        int right = nums.length - 1;

        // Calculate the middle index.
        int mid = left + (right - left) / 2;

        // Continue while a valid search space remains.
        while (left <= right) {

            // Target found.
            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted.
            if (nums[left] <= nums[mid]) {

                // Target lies within the sorted left half.
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }

                // Target must be in the other half.
                else {
                    left = mid + 1;
                }
            }

            // Right half is sorted.
            else {

                // Target lies within the sorted right half.
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }

                // Target must be in the other half.
                else {
                    right = mid - 1;
                }
            }

            // Recalculate mid for the updated search space.
            mid = left + (right - left) / 2;
        }

        // Target does not exist in the array.
        return -1;
    }
}