package com.dsa.phase2.binarySearch;

/**
 * ==========================================================
 * Problem    : LeetCode 153 - Find Minimum in Rotated Sorted Array
 * Difficulty : Medium
 * Pattern    : Binary Search on Rotated Sorted Array
 * ==========================================================
 * <p>
 * Idea:
 * In a rotated sorted array, at least one half of the current
 * search space is always sorted.
 * <p>
 * We use this property to identify the minimum:
 * <p>
 * - If nums[left] <= nums[mid], the left half is sorted.
 * Therefore, nums[left] is the smallest element in that
 * sorted portion, so we consider it for the answer and
 * search the right half.
 * <p>
 * - Otherwise, the rotation point lies in the left half.
 * nums[mid] can potentially be the minimum, so we consider
 * it and continue searching the left half.
 * <p>
 * The variable {@code minn} stores the smallest candidate
 * encountered while eliminating sorted portions of the array.
 * <p>
 * Time Complexity  : O(log n)
 * Space Complexity : O(1)
 * <p>
 * ==========================================================
 */
public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int minn = Integer.MAX_VALUE;

        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;

        while (left <= right) {

            // Left portion is sorted, so its minimum is nums[left].
            if (nums[left] <= nums[mid]) {
                minn = Math.min(nums[left], minn);

                // The minimum may still exist in the unsorted/right portion.
                left = mid + 1;

            } else {
                // The rotation point lies in the left portion,
                // and nums[mid] is a potential minimum.
                minn = Math.min(nums[mid], minn);

                right = mid - 1;
            }

            mid = left + (right - left) / 2;
        }

        return minn;
    }
}