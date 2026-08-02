package com.dsa.phase1.hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * ==========================================================
 * Problem    : LeetCode 128 - Longest Consecutive Sequence
 * Difficulty : Medium
 * Pattern    : Hashing
 * <p>
 * Idea:
 * Instead of sorting the array (O(n log n)), store all unique
 * elements in a HashSet to enable O(1) average lookups.
 * <p>
 * The key observation is that we only begin counting from the
 * first element of a consecutive sequence.
 * <p>
 * A number is considered the start of a sequence if its
 * previous consecutive number (num - 1) does not exist in the set.
 * <p>
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * <p>
 * HashSet:
 * {100, 4, 200, 1, 3, 2}
 * <p>
 * Iteration:
 * 100 -> 99 doesn't exist -> sequence length = 1
 * 4   -> 3 exists         -> skip
 * 200 -> 199 doesn't exist -> sequence length = 1
 * 1   -> 0 doesn't exist  -> 1 -> 2 -> 3 -> 4
 * 3   -> 2 exists         -> skip
 * 2   -> 1 exists         -> skip
 * <p>
 * Longest sequence = 4
 * <p>
 * Why this works:
 * Every consecutive sequence is expanded exactly once—from its
 * smallest element. Numbers in the middle of a sequence are skipped,
 * preventing redundant work.
 * <p>
 * Time Complexity:
 * O(n)
 * - Building the HashSet takes O(n).
 * - Each number is processed at most twice:
 * 1. Once in the outer loop.
 * 2. At most once while expanding a sequence.
 * Therefore, the total work remains linear.
 * <p>
 * Space Complexity:
 * O(n)
 * - HashSet stores all unique elements.
 * ==========================================================
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {

        // Edge case: Empty array has no consecutive sequence.
        if (nums.length == 0) {
            return 0;
        }

        int max = 1;

        // Store all unique numbers for O(1) average lookups.
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Iterate through unique elements only.
        for (int num : set) {

            // A sequence can only start if the previous number is absent.
            if (!set.contains(num - 1)) {

                int count = 1;
                int current = num;

                // Count the length of the consecutive sequence.
                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }

                max = Math.max(max, count);
            }
        }

        return max;
    }
}