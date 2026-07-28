package com.dsa.phase1.arrays;

/**
 * ==========================================================
 * Problem    : LeetCode 88 - Merge Sorted Array
 * Difficulty : Easy
 * Pattern    : Two Pointers
 * <p>
 * Idea:
 * nums1 already contains enough space to hold all elements of
 * nums1 and nums2. The challenge is to merge both sorted arrays
 * without using any extra space.
 * <p>
 * At first glance, merging from the beginning seems natural.
 * However, doing so overwrites valid elements in nums1 before
 * they have been processed, forcing us to create a temporary
 * copy of nums1.
 * <p>
 * Instead, observe that the empty positions already exist at
 * the end of nums1.
 * <p>
 * Since the largest element of the final merged array always
 * belongs at the last available position, we compare the
 * largest remaining elements of both arrays and place the
 * larger one at the end.
 * <p>
 * Three pointers are maintained:
 * <p>
 * • i -> Last valid element of nums1.
 * • j -> Last element of nums2.
 * • k -> Last position of nums1 (including empty space).
 * <p>
 * After placing the larger element, move the corresponding
 * pointer backwards and continue until one array is exhausted.
 * <p>
 * If nums2 still contains elements, copy them into nums1.
 * Any remaining elements in nums1 are already in their correct
 * positions, so no additional work is required.
 * <p>
 * Why does this work?
 * <p>
 * By filling the array from right to left, we only overwrite
 * the empty positions that were reserved in nums1. No unprocessed
 * element is ever lost, eliminating the need for extra memory.
 * <p>
 * Time Complexity : O(m + n)
 * Space Complexity: O(1)
 * <p>
 * Learnings:
 * ✔ The direction of traversal can completely change the
 * complexity of a problem.
 * ✔ Always look for unused space before allocating extra memory.
 * ✔ When merging sorted data in-place, consider filling from
 * the end if free space already exists there.
 * ✔ Not every Two Pointer problem starts from the beginning.
 * ==========================================================
 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // Points to the last valid element in nums1.
        int i = m - 1;

        // Points to the last element in nums2.
        int j = n - 1;

        // Points to the last available position in nums1.
        int k = m + n - 1;

        // Compare the largest remaining elements from both arrays
        // and place the larger one at the end of nums1.
        while (i >= 0 && j >= 0) {

            if (nums1[i] < nums2[j]) {
                nums1[k] = nums2[j];
                j--;
            } else {
                nums1[k] = nums1[i];
                i--;
            }

            // Move to the next position from the end.
            k--;
        }

        // If nums2 still has remaining elements,
        // copy them into nums1.
        //
        // We don't need a similar loop for nums1 because
        // any remaining elements are already in their
        // correct positions.
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}