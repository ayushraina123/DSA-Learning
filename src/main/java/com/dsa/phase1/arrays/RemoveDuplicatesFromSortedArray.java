package com.dsa.phase1.arrays;

/**
 * ==========================================================
 * Problem    : LeetCode 26 - Remove Duplicates from Sorted Array
 * Difficulty : Easy
 * Pattern    : Two Pointers
 * <p>
 * Idea:
 * Since the array is already sorted, duplicates always appear
 * next to each other.
 * <p>
 * Use:
 * • Read Pointer  -> scans every element.
 * • Write Pointer -> points to the last unique element.
 * <p>
 * Whenever a new unique element is found, overwrite the next
 * position in the array.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 * <p>
 * Learnings:
 * ✔ We never remove elements from an array.
 * ✔ We overwrite the beginning of the array with unique values.
 * ✔ Read Pointer and Write Pointer is a very common pattern.
 * ==========================================================
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {

        // Points to the last unique element written.
        int i = 0;

        // Scans the remaining array.
        int j = 1;

        while (j < nums.length) {

            // Since the array is sorted,
            // a different value means a new unique element.
            if (nums[i] != nums[j]) {

                // Move write pointer.
                i++;

                // Store the newly found unique element.
                nums[i] = nums[j];
            }

            // Continue scanning the array.
            j++;
        }

        // Number of unique elements.
        return i + 1;
    }
}