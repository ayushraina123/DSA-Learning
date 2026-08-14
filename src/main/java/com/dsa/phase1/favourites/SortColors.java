package com.dsa.phase1.favourites;

/**
 * ==========================================================
 * Problem    : LeetCode 75 - Sort Colors
 * Difficulty : Medium
 * Pattern    : Dutch National Flag Algorithm
 * ==========================================================
 *
 * <p>
 * Idea:
 * We are given an array containing only 0, 1 and 2.
 * The goal is to sort the array in-place without using
 * any extra space.
 * </p>
 *
 * <p>
 * We divide the array into four regions:
 * <p>
 * [0 ... low-1]       -> all 0s
 * [low ... mid-1]     -> all 1s
 * [mid ... high]      -> unknown elements
 * [high+1 ... n-1]    -> all 2s
 * <p>
 * The middle region [mid ... high] is the only region
 * that we need to process.
 * </p>
 *
 * <p>
 * We use three pointers:
 * <p>
 * low  -> position where the next 0 should go
 * mid  -> current element being examined
 * high -> position where the next 2 should go
 * </p>
 *
 * <p>
 * For every element at nums[mid]:
 * <p>
 * nums[mid] == 0
 * -> Swap with nums[low]
 * -> Increment both low and mid
 * <p>
 * nums[mid] == 1
 * -> Already in the correct region
 * -> Increment mid
 * <p>
 * nums[mid] == 2
 * -> Swap with nums[high]
 * -> Decrement high
 * -> Do NOT increment mid
 * </p>
 *
 * <p>
 * Why don't we increment mid after finding a 2?
 * <p>
 * Because the element swapped from nums[high] into nums[mid]
 * has not been examined yet. It could be 0, 1 or 2.
 * Therefore, we must process nums[mid] again.
 * </p>
 *
 * <p>
 * The algorithm terminates when mid > high, meaning that
 * there are no unknown elements left.
 * </p>
 *
 * <p>
 * Complexity:
 * <p>
 * Time  : O(n)
 * Space : O(1)
 * <p>
 * The array is sorted in-place and no additional data
 * structure is used.
 * </p>
 */
public class SortColors {

    public void sortColors(int[] nums) {

        /*
         * ======================================================
         * Dutch National Flag Algorithm
         * ======================================================
         *
         * Maintain four regions:
         *
         *     [0 ... low-1]       -> 0s
         *     [low ... mid-1]     -> 1s
         *     [mid ... high]      -> unknown
         *     [high+1 ... n-1]    -> 2s
         *
         * Initially the entire array is unknown.
         */
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        /*
         * Continue processing while there are still
         * unknown elements.
         */
        while (mid <= high) {

            /*
             * ==================================================
             * Case 1: Current element is 0
             * ==================================================
             *
             * 0 belongs to the left region.
             *
             * Swap nums[mid] with nums[low] to move the 0
             * into its correct position.
             *
             * After the swap:
             *
             *     low  -> next position for a 0
             *     mid  -> next unknown element
             */
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;

                /*
                 * ==================================================
                 * Case 2: Current element is 1
                 * ==================================================
                 *
                 * 1 already belongs to the middle region.
                 *
                 * No swap is required.
                 *
                 * Simply move mid forward.
                 */
            } else if (nums[mid] == 1) {
                mid++;

                /*
                 * ==================================================
                 * Case 3: Current element is 2
                 * ==================================================
                 *
                 * 2 belongs to the right region.
                 *
                 * Swap nums[mid] with nums[high] to move the 2
                 * to the correct side.
                 *
                 * Decrease high because that position is now
                 * correctly occupied by a 2.
                 *
                 * IMPORTANT:
                 *
                 * We do NOT increment mid here.
                 *
                 * The element that came from nums[high] into
                 * nums[mid] is still unknown and must be processed.
                 */
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    /*
     * ==========================================================
     * Helper Method: Swap
     * ==========================================================
     *
     * Swaps two elements in the array.
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /*
     * ==========================================================
     * Alternative Approach: Counting
     * ==========================================================
     *
     * Count the number of 0s, 1s and 2s.
     *
     * Then overwrite the array:
     *
     *     First  -> all 0s
     *     Next   -> all 1s
     *     Last   -> all 2s
     *
     * Time  : O(n)
     * Space : O(1)
     *
     * This approach is also valid because there are only three
     * possible values.
     *
     * However, the Dutch National Flag approach is preferable
     * because it sorts the array in a single pass while directly
     * partitioning the elements into their respective regions.
     *
     *
     * public void sortColors(int[] nums) {
     *
     *     int zeroCounter = 0;
     *     int oneCounter = 0;
     *     int twoCounter = 0;
     *
     *     for (int num : nums) {
     *         if (num == 0) {
     *             zeroCounter++;
     *         } else if (num == 1) {
     *             oneCounter++;
     *         } else {
     *             twoCounter++;
     *         }
     *     }
     *
     *     int index = 0;
     *
     *     while (zeroCounter > 0) {
     *         nums[index++] = 0;
     *         zeroCounter--;
     *     }
     *
     *     while (oneCounter > 0) {
     *         nums[index++] = 1;
     *         oneCounter--;
     *     }
     *
     *     while (twoCounter > 0) {
     *         nums[index++] = 2;
     *         twoCounter--;
     *     }
     * }
     */
}