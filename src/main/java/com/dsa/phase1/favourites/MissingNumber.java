package com.dsa.phase1.favourites;

/**
 * ==========================================================
 * Problem    : LeetCode 268 - Missing Number
 * Difficulty : Easy
 * Pattern    : XOR / Bit Manipulation
 * ==========================================================
 * <p>
 * Problem:
 * Given an array containing n distinct numbers taken from the
 * range [0, n], find the one number that is missing.
 * <p>
 * Example:
 * <p>
 * nums = [3, 0, 1]
 * <p>
 * Expected range = [0, 1, 2, 3]
 * Missing number = 2
 *
 * <p>
 * ----------------------------------------------------------
 * Approach 1: XOR
 * ----------------------------------------------------------
 * <p>
 * We know two important properties of XOR:
 * <p>
 * x ^ x = 0
 * x ^ 0 = x
 * <p>
 * Therefore, if we XOR all numbers from 0 to n together with
 * all numbers present in the array, every number that exists
 * in both sets will cancel itself out.
 * <p>
 * For example:
 * <p>
 * Expected: 0 ^ 1 ^ 2 ^ 3
 * Actual  : 3 ^ 0 ^ 1
 * <p>
 * Combining them:
 * <p>
 * 0 ^ 1 ^ 2 ^ 3 ^ 3 ^ 0 ^ 1
 * <p>
 * The duplicate numbers cancel:
 * <p>
 * 0 ^ 0 = 0
 * 1 ^ 1 = 0
 * 3 ^ 3 = 0
 * <p>
 * Leaving:
 * <p>
 * 2
 * <p>
 * which is the missing number.
 *
 * <p>
 * We don't need to create a separate array containing
 * [0, 1, ..., n]. Since the expected numbers are predictable,
 * we can generate them using the loop index itself.
 * <p>
 * We initialize result with nums.length because the loop only
 * visits indices from 0 to n - 1, while the expected range also
 * contains n.
 * <p>
 * result = n
 * <p>
 * Then for every index:
 * <p>
 * result ^= i;
 * result ^= nums[i];
 * <p>
 * After all XOR operations, only the missing number remains.
 *
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 *
 * <p>
 * ----------------------------------------------------------
 * Approach 2: HashSet
 * ----------------------------------------------------------
 * <p>
 * The alternative approach is to store every number from the
 * array in a HashSet and then iterate from 0 to n to find the
 * number that does not exist in the set.
 * <p>
 * This approach is also O(n) in time, but requires O(n) extra
 * space because of the HashSet.
 * <p>
 * Therefore, the XOR approach is preferred because it achieves
 * constant extra space.
 * <p>
 * ==========================================================
 */
public class MissingNumber {

    /**
     * Finds the missing number using XOR.
     *
     * @param nums array containing n distinct numbers from [0, n]
     * @return the missing number
     */
    public int missingNumber(int[] nums) {
        int result = nums.length;

        for (int i = 0; i < nums.length; i++) {
            result ^= i;
            result ^= nums[i];
        }

        return result;
    }

    /*
     * ----------------------------------------------------------
     * Alternative Approach: HashSet
     * ----------------------------------------------------------
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     *
     * public int missingNumber(int[] nums) {
     *     Set<Integer> set = new HashSet<>();
     *
     *     for (int num : nums) {
     *         set.add(num);
     *     }
     *
     *     int count = 0;
     *
     *     while (count <= nums.length) {
     *         if (!set.contains(count)) {
     *             return count;
     *         }
     *
     *         count++;
     *     }
     *
     *     return count;
     * }
     *
     * ----------------------------------------------------------
     */
}