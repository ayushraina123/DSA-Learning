package com.dsa.phase2.twoPointers;

/**
 * ==========================================================
 * Problem    : LeetCode 283 - Move Zeroes
 * Difficulty : Easy
 * Pattern    : Two Pointers
 * <p>
 * Idea:
 * We need to move all zeroes to the end of the array while
 * maintaining the relative order of all non-zero elements.
 * <p>
 * Use two pointers:
 * • i scans through the entire array.
 * • j points to the position where the next non-zero element
 * should be placed.
 * <p>
 * Whenever nums[i] is non-zero, swap nums[i] with nums[j].
 * Then increment j because the next non-zero element should
 * be placed at the next position.
 * <p>
 * This effectively partitions the array into two regions:
 * <p>
 * [processed non-zero elements] [unprocessed elements]
 * ^
 * j
 * <p>
 * Pointer i continues scanning the array, while j only moves
 * when a non-zero element is found.
 * <p>
 * Example:
 * nums = [0, 1, 0, 3, 12]
 * <p>
 * i scans the array:
 * • i = 0 → nums[i] is 0 → do nothing
 * • i = 1 → nums[i] is 1 → swap with nums[j]
 * [1, 0, 0, 3, 12]
 * j++
 * • i = 2 → nums[i] is 0 → do nothing
 * • i = 3 → nums[i] is 3 → swap with nums[j]
 * [1, 3, 0, 0, 12]
 * j++
 * • i = 4 → nums[i] is 12 → swap with nums[j]
 * [1, 3, 12, 0, 0]
 * <p>
 * The relative order of non-zero elements is preserved.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 * <p>
 * Learnings:
 * ✔ One pointer can scan the array while another tracks the
 * position where the next valid element should be placed.
 * ✔ The slow pointer j only moves when a non-zero element
 * is encountered.
 * ✔ Swapping allows us to perform the operation in-place
 * without requiring an additional array.
 * ✔ This is a variation of the Two Pointers pattern where
 * the two pointers have different responsibilities.
 * ==========================================================
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {

        // i scans through the entire array.
        int i = 0;

        // j points to the position where the next non-zero
        // element should be placed.
        int j = 0;

        int temp;

        while (i < nums.length) {

            // If the current element is non-zero, place it
            // at the next available position tracked by j.
            if (nums[i] != 0) {

                // Swap nums[i] with nums[j].
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;

                // Move j to the next position for a non-zero element.
                j++;
            }

            // Continue scanning the array.
            i++;
        }
    }
}