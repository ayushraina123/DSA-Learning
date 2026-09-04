package com.dsa.phase2.binarySearch;

/**
 * ==========================================================
 * Problem    : LeetCode 35 - Search Insert Position
 * Difficulty : Easy
 * Pattern    : Binary Search
 * <p>
 * Idea:
 * We use Binary Search to either find the target or determine
 * where it should be inserted while maintaining sorted order.
 * <p>
 * At each iteration:
 * <ul>
 *     <li>If {@code nums[mid]} equals the target, return {@code mid}</li>
 *     <li>If {@code nums[mid]} is smaller, search the right half</li>
 *     <li>If {@code nums[mid]} is greater, search the left half</li>
 * </ul>
 * <p>
 * If the target is not found, the search space eventually becomes
 * invalid ({@code left > right}).
 * <p>
 * At that point, {@code left} represents the correct insertion
 * position because all elements before it are smaller than the
 * target, while all elements at and after it are greater than
 * the target.
 * <p>
 * ==========================================================
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {

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

            // Target is greater, so search the right half.
            else if (nums[mid] < target) {
                left = mid + 1;
            }

            // Target is smaller, so search the left half.
            else {
                right = mid - 1;
            }

            // Recalculate mid for the updated search space.
            mid = left + (right - left) / 2;
        }

        // Target does not exist, so mid represents the insertion position.
        return mid;
    }
}