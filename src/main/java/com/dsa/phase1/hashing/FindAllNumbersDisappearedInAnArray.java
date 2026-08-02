package com.dsa.phase1.hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================
 * Problem    : LeetCode 448 - Find All Numbers Disappeared in an Array
 * Difficulty : Easy
 * Pattern    : Hashing (In-Place Index Marking)
 * <p>
 * Idea:
 * Since every number lies in the range [1, n], each value can be
 * mapped directly to an array index:
 * <p>
 * value x  ->  index (x - 1)
 * <p>
 * Instead of using an external HashSet to record visited numbers,
 * we use the input array itself.
 * <p>
 * Whenever we encounter a value x, we visit index (x - 1) and make
 * the element at that index negative to indicate that x exists.
 * <p>
 * After processing the entire array:
 * - Negative value  -> corresponding number exists.
 * - Positive value  -> corresponding number is missing.
 * <p>
 * Duplicate values are handled by checking whether the target
 * index is already negative before negating it.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(1) (excluding the output list)
 * ==========================================================
 */
public class FindAllNumbersDisappearedInAnArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        // Mark every encountered number by negating its corresponding index.
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;

            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        // Positive indices represent missing numbers.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        return result;
    }
}