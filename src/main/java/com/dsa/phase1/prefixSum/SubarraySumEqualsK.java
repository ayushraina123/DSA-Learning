package com.dsa.phase1.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 560 - Subarray Sum Equals K
 * Difficulty : Medium
 * Pattern    : Prefix Sum + HashMap
 * <p>
 * Idea:
 * We need to find the number of contiguous subarrays whose
 * sum is equal to k.
 * <p>
 * First, build a prefix sum array:
 * <p>
 * prefix[i] = sum of elements from index 0 to i
 * <p>
 * For any subarray from index left + 1 to right:
 * <p>
 * prefix[right] - prefix[left] = k
 * <p>
 * Rearranging:
 * <p>
 * prefix[left] = prefix[right] - k
 * <p>
 * Therefore, while traversing the prefix sums, for every
 * current prefix sum we look for:
 * <p>
 * complement = currentPrefixSum - k
 * <p>
 * Instead of searching for the complement in O(n), we store
 * previously seen prefix sums in a HashMap.
 * <p>
 * The HashMap stores:
 * <p>
 * prefixSum -> frequency
 * <p>
 * Frequency is important because the same prefix sum can
 * occur multiple times, and each occurrence can form a
 * different valid subarray.
 * <p>
 * We initially store:
 * <p>
 * map.put(0, 1)
 * <p>
 * This represents a prefix sum of 0 before the array starts
 * and handles subarrays that begin at index 0.
 * <p>
 * For every prefix sum:
 * <p>
 * 1. Calculate the required complement:
 * currentPrefixSum - k
 * <p>
 * 2. If the complement exists, add its frequency to count.
 * <p>
 * 3. Store/increment the frequency of the current prefix sum.
 * <p>
 * The difference between two prefix sums always represents
 * the elements between their corresponding indices, which
 * guarantees that the identified subarray is contiguous.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 * <p>
 * ==========================================================
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        int count = 0;

        // Build the prefix sum array.
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        // Store prefix sum -> frequency.
        Map<Integer, Integer> map = new HashMap<>();

        // Represents a prefix sum of 0 before the array starts.
        // This handles subarrays that start from index 0.
        map.put(0, 1);

        for (int j : prefix) {

            // prefix[right] - prefix[left] = k
            // Therefore, prefix[left] = prefix[right] - k.
            int complement = j - k;

            // Every occurrence of the complement represents
            // a valid contiguous subarray ending at the current index.
            if (map.containsKey(complement)) {
                count += map.get(complement);
            }

            // Store/increment the frequency of the current prefix sum.
            map.put(j, map.getOrDefault(j, 0) + 1);
        }

        return count;
    }
}