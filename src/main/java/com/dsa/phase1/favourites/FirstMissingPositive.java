package com.dsa.phase1.favourites;

/**
 * ==========================================================
 * Problem    : LeetCode 41 - First Missing Positive
 * Difficulty : Hard
 * Pattern    : Cyclic Placement / In-Place Hashing
 * ==========================================================
 *
 * <p>
 * Idea:
 * We need to find the smallest missing positive integer while using
 * O(1) extra space.
 *
 * <p>
 * For an array of length n, the answer can only be in the range
 * [1, n + 1]. Therefore, values <= 0 or values > n can be ignored.
 *
 * <p>
 * For every valid positive number x, its "correct" index is:
 *
 * <pre>
 * x -> index x - 1
 * </pre>
 *
 * <p>
 * So, instead of using a HashSet to track which numbers exist, we use
 * the array itself as the storage structure. We repeatedly place each
 * valid number at its corresponding index.
 *
 * <p>
 * For example:
 *
 * <pre>
 * Value 1 -> index 0
 * Value 2 -> index 1
 * Value 3 -> index 2
 * ...
 * Value n -> index n - 1
 * </pre>
 *
 * <p>
 * Once the rearrangement is complete, we scan the array. If nums[i]
 * is not equal to i + 1, then i + 1 is the first missing positive.
 *
 * <p>
 * The duplicate check:
 *
 * <pre>
 * nums[i] != nums[nums[i] - 1]
 * </pre>
 *
 * <p>
 * prevents infinite swapping when duplicate values exist.
 *
 * <p>
 * Example:
 *
 * <pre>
 * Input:
 * [3, 4, -1, 1]
 *
 * Rearranged:
 * [-1, 1, 3, 4]
 *
 * Index 0 should contain 1, but contains -1.
 *
 * Therefore, answer = 1.
 * </pre>
 *
 * <p>
 * Why O(n) time despite the nested while loop?
 * Each successful swap places a number into its correct position.
 * Since there are only n positions, the total number of meaningful
 * swaps across the entire algorithm is O(n).
 *
 * <p>
 * Complexity:
 * Time  : O(n)
 * Space : O(1)
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {

        /*
         * Phase 1:
         * Place every valid positive number x at index x - 1.
         *
         * Ignore:
         * - Negative numbers
         * - Zero
         * - Numbers greater than nums.length
         *
         * The duplicate check prevents infinite swapping.
         */
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0
                    && nums[i] <= nums.length
                    && nums[i] != nums[nums[i] - 1]) {

                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        /*
         * Phase 2:
         * After placement, ideally:
         *
         * index 0 -> 1
         * index 1 -> 2
         * index 2 -> 3
         * ...
         *
         * The first index where nums[i] != i + 1
         * gives us the first missing positive.
         */
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        /*
         * If every number from 1 to n is present,
         * the first missing positive is n + 1.
         */
        return nums.length + 1;
    }

    /*
     * ----------------------------------------------------------
     * Alternative Solution:
     * HashSet
     *
     * Time  : O(n) average
     * Space : O(n)
     *
     * This solution is simpler but does not satisfy the
     * O(1) extra-space requirement of the problem.
     * ----------------------------------------------------------
     *
     * public int firstMissingPositive(int[] nums) {
     *     Set<Integer> set = new HashSet<>();
     *
     *     for (int num : nums) {
     *         set.add(num);
     *     }
     *
     *     int answer = 1;
     *
     *     while (true) {
     *         if (set.add(answer)) {
     *             return answer;
     *         }
     *         answer++;
     *     }
     * }
     */
}