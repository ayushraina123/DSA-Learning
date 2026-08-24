package com.dsa.phase2.twoPointers;

/**
 * ==========================================================
 * Problem    : LeetCode 27 - Remove Element
 * Difficulty : Easy
 * Pattern    : Two Pointers
 * <p>
 * Idea:
 * We need to remove all occurrences of `val` from the array
 * in-place and return the number of elements that are not equal
 * to `val`.
 * <p>
 * The order of the remaining elements does not matter, so instead
 * of shifting elements one position at a time, use two pointers
 * to efficiently replace elements that need to be removed.
 * <p>
 * Two pointers are used:
 * • i points to the current element from the left.
 * • j points to the current element from the right.
 * <p>
 * The basic idea is:
 * • If nums[i] is not `val`, it is already in the correct region,
 * so simply move i forward.
 * <p>
 * • If nums[i] is `val`, this element needs to be removed.
 * Search from the right using j until a value that is not `val`
 * is found.
 * <p>
 * • Once such an element is found, swap it with nums[i].
 * The unwanted `val` at position i is therefore replaced by a
 * valid element.
 * <p>
 * After the swap:
 * • i is moved forward because the newly placed element is valid.
 * • j is moved backward because the element at j was moved to the
 * left and the right side is now part of the removed/ignored
 * region.
 * <p>
 * Important:
 * The inner while loop is necessary because nums[j] itself may
 * also be equal to `val`. We must keep moving j left until we
 * find an element that should remain in the array.
 * <p>
 * Example:
 * nums = [3, 2, 2, 3]
 * val = 3
 * <p>
 * Initial:
 * i = 0, j = 3
 * <p>
 * nums[i] == val and nums[j] == val.
 * Move j left:
 * j = 2
 * <p>
 * nums[j] = 2, which is a valid element.
 * Swap nums[i] and nums[j]:
 * [2, 2, 3, 3]
 * <p>
 * Move both pointers:
 * i = 1, j = 1
 * <p>
 * nums[i] = 2, so it is already valid.
 * Move i:
 * i = 2
 * <p>
 * Now i > j, so processing is complete.
 * <p>
 * The first `i` elements contain all values that are not equal
 * to `val`, and therefore `i` is the required count.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 * <p>
 * Learnings:
 * ✔ When the order of remaining elements does not matter, swapping
 * unwanted elements with valid elements from the opposite end
 * can avoid unnecessary shifting.
 * ✔ Two pointers can divide the array into processed and
 * unprocessed regions.
 * ✔ The inner pointer movement is important when multiple unwanted
 * elements occur consecutively at the end.
 * ✔ The returned value represents the number of valid elements,
 * not the final index of the last valid element.
 * ✔ The key invariant is that everything before i is a valid
 * element, while everything after j can be ignored because it
 * contains elements equal to `val`.
 * ==========================================================
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {

        // i points to the current element from the left.
        int i = 0;

        // j points to the current element from the right.
        int j = nums.length - 1;

        while (i <= j) {

            // nums[i] needs to be removed, so find a valid
            // element from the right that can replace it.
            if (nums[i] == val) {

                // Skip all elements from the right that are also
                // equal to val because they cannot be used for
                // replacement.
                while (i <= j && nums[j] == val) {
                    j--;
                }

                // If a valid element is found, swap it with the
                // unwanted element at i.
                if (i <= j) {
                    swap(nums, i, j);

                    // The element placed at i is valid, so move i.
                    i++;

                    // The element taken from j has already been
                    // processed, so move j as well.
                    j--;
                }

            } else {

                // nums[i] is already valid, so simply move forward.
                i++;
            }
        }

        // i represents the number of elements that are not equal
        // to val.
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}