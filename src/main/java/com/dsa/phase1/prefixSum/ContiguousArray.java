package com.dsa.phase1.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 525 - Contiguous Array
 * Difficulty : Medium
 * Pattern    : Prefix Sum + HashMap
 * <p>
 * Idea:
 * We need to find the maximum length of a contiguous subarray
 * containing an equal number of 0s and 1s.
 * <p>
 * Since the array contains only 0 and 1, we treat:
 * 0 -> -1
 * 1 -> +1
 * <p>
 * Now the problem becomes finding the longest subarray whose
 * sum is equal to 0.
 * <p>
 * We build a prefix sum array where:
 * 0 decreases the sum by 1
 * 1 increases the sum by 1
 * <p>
 * If the same prefix sum occurs at two different indices,
 * the elements between those indices have a sum of 0.
 * <p>
 * Example:
 * nums = [0, 1, 0, 1]
 * <p>
 * Converted values:
 * [-1, +1, -1, +1]
 * <p>
 * Prefix sum:
 * [-1, 0, -1, 0]
 * <p>
 * The prefix sum -1 occurs at indices 0 and 2.
 * Therefore, the subarray between them has sum 0:
 * [1, 2] -> [1, 0]
 * <p>
 * Similarly, prefix sum 0 occurs at index 1 and 3, giving
 * the subarray [0, 3] with length 4.
 * <p>
 * HashMap:
 * We store the FIRST occurrence of every prefix sum.
 * We do not overwrite an existing index because the earliest
 * occurrence gives us the longest possible subarray when the
 * same prefix sum is encountered again.
 * <p>
 * Special Case:
 * If prefix[i] == 0, it means the subarray from index 0 to i
 * itself contains an equal number of 0s and 1s.
 * Its length is therefore i + 1.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 * ==========================================================
 */
public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        int result = 0;

        // Build prefix sum.
        // Treat 0 as -1 and 1 as +1.
        int[] prefix = new int[nums.length];

        if (nums[0] == 0) {
            prefix[0] = -1;
        } else {
            prefix[0] = 1;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                prefix[i] = prefix[i - 1] - 1;
            } else {
                prefix[i] = prefix[i - 1] + 1;
            }
        }

        // Store the first occurrence of each prefix sum.
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < prefix.length; i++) {

            // Same prefix sum means the elements between the
            // two indices have a sum of 0, meaning equal 0s and 1s.
            if (map.containsKey(prefix[i])) {
                result = Math.max(result, i - map.get(prefix[i]));
            } else {
                // Store only the first occurrence so that we
                // always get the longest possible subarray.
                map.put(prefix[i], i);
            }

            // Prefix sum 0 means the subarray from index 0
            // through i has equal 0s and 1s.
            if (prefix[i] == 0) {
                result = Math.max(result, i + 1);
            }
        }

        return result;
    }
}