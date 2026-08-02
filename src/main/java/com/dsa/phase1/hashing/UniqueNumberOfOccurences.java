package com.dsa.phase1.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ==========================================================
 * Problem    : LeetCode 1207 - Unique Number of Occurrences
 * Difficulty : Easy
 * Pattern    : Hashing
 * <p>
 * Idea:
 * Count the frequency of every distinct number using a HashMap.
 * Then, verify that each frequency is unique by inserting the
 * frequencies into a HashSet.
 * <p>
 * If a frequency already exists in the HashSet, two different
 * numbers share the same occurrence count, so the answer is false.
 * <p>
 * Example:
 * arr = [1,2,2,1,1,3]
 * <p>
 * Frequency Map:
 * 1 -> 3
 * 2 -> 2
 * 3 -> 1
 * <p>
 * Frequency Set:
 * {3, 2, 1}
 * <p>
 * Since every frequency is unique, return true.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 * ==========================================================
 */
public class UniqueNumberOfOccurences {

    public boolean uniqueOccurrences(int[] arr) {
        // Stores the frequency of each number.
        Map<Integer, Integer> map = new HashMap<>();

        // Stores all unique frequencies encountered.
        Set<Integer> set = new HashSet<>();

        // Count the occurrence of every number.
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // If any frequency already exists, occurrences are not unique.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (!set.add(entry.getValue())) {
                return false;
            }
        }

        return true;
    }
}