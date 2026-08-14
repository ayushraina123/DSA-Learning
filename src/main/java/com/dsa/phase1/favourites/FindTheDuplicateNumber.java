package com.dsa.phase1.favourites;

/**
 * ==========================================================
 * Problem    : LeetCode 287 - Find the Duplicate Number
 * Difficulty : Medium
 * Pattern    : Floyd's Cycle Detection (Tortoise & Hare)
 * ==========================================================
 *
 * <p>
 * Idea:
 * We are given an array containing n + 1 integers where every
 * integer is in the range [1, n]. Exactly one number is repeated.
 * </p>
 *
 * <p>
 * The key observation is that we can treat the array like a
 * linked list:
 * <p>
 * index -> nums[index]
 * <p>
 * For example:
 * <p>
 * nums = [1, 3, 4, 2, 2]
 * <p>
 * 0 -> 1 -> 3 -> 2 -> 4
 * ^    |
 * |____|
 * <p>
 * The duplicate number creates a cycle because two different
 * indices eventually point to the same index.
 * </p>
 *
 * <p>
 * We use Floyd's Cycle Detection Algorithm:
 * <p>
 * Slow pointer -> moves one step at a time
 * Fast pointer -> moves two steps at a time
 * </p>
 *
 * <p>
 * Phase 1:
 * Find a point where the slow and fast pointers meet inside
 * the cycle.
 * <p>
 * Phase 2:
 * Reset slow to the beginning of the array while keeping fast
 * at the meeting point. Move both one step at a time.
 * <p>
 * They will meet at the entrance of the cycle, which is the
 * duplicate number.
 * </p>
 *
 * <p>
 * Why Phase 2 works:
 * After Phase 1, the distance from the start of the array to
 * the cycle entrance is equal (modulo the cycle length) to the
 * distance from the meeting point to the cycle entrance.
 * <p>
 * Therefore, moving both pointers one step at a time makes them
 * meet exactly at the cycle entrance.
 * </p>
 *
 * <p>
 * Complexity:
 * <p>
 * Time  : O(n)
 * Space : O(1)
 * <p>
 * We do not modify the input array and use no additional data
 * structure.
 * </p>
 */
public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {

        /*
         * ======================================================
         * Phase 1: Detect the cycle
         * ======================================================
         *
         * Treat nums[index] as the "next pointer".
         *
         * Slow moves one step:
         *
         *     slow = nums[slow]
         *
         * Fast moves two steps:
         *
         *     fast = nums[nums[fast]]
         *
         * Since a cycle exists, the two pointers will eventually
         * meet somewhere inside the cycle.
         */
        int slow = nums[0];
        int fast = nums[0];

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];

            if (slow == fast) {
                break;
            }
        }

        /*
         * ======================================================
         * Phase 2: Find the entrance of the cycle
         * ======================================================
         *
         * The meeting point from Phase 1 is somewhere inside
         * the cycle, but it is not necessarily the duplicate.
         *
         * Move slow back to the beginning.
         *
         * Then move both pointers one step at a time.
         *
         * They will meet at the cycle entrance.
         *
         * In this problem:
         *
         *     cycle entrance = duplicate number
         */
        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    /*
     * ==========================================================
     * Alternative Approach: HashSet
     * ==========================================================
     *
     * Store every number seen so far.
     *
     * If add() returns false, the number already exists in the
     * set, so that number is the duplicate.
     *
     * Time  : O(n)
     * Space : O(n)
     *
     * This approach is simpler, but it does not satisfy the
     * O(1) extra-space requirement of the problem.
     *
     *
     * public int findDuplicate(int[] nums) {
     *     Set<Integer> set = new HashSet<>();
     *
     *     for (int num : nums) {
     *         if (!set.add(num)) {
     *             return num;
     *         }
     *     }
     *
     *     return -1;
     * }
     */
}