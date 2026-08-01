package com.dsa.phase1.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 347 - Top K Frequent Elements
 * Difficulty : Medium
 * Pattern    : Hashing + Sorting
 * <p>
 * Idea:
 * Count the frequency of every element using a HashMap.
 * Since HashMaps cannot be sorted, convert the map entries
 * into a List and sort the entries in descending order of
 * frequency. The first k entries in the sorted list represent
 * the k most frequent elements.
 * <p>
 * Algorithm:
 * 1. Count the frequency of every number.
 * 2. Convert map.entrySet() into a List.
 * 3. Sort the list by frequency (descending).
 * 4. Collect the keys of the first k entries.
 * <p>
 * Time Complexity : O(n + m log m)
 * n -> Total number of elements
 * m -> Number of distinct elements
 * <p>
 * Space Complexity: O(m)
 * HashMap and entry list both store at most one entry
 * for every distinct element.
 * <p>
 * Learnings:
 * ✔ A HashMap itself cannot be sorted because it does not
 * maintain any ordering.
 * ✔ Convert map.entrySet() into a List when sorting by keys
 * or values is required.
 * ✔ Map.Entry stores both the key and its corresponding
 * value, allowing them to remain associated while sorting.
 * ✔ Sorting only the frequencies loses the relationship
 * between an element and its frequency.
 * ✔ This solution runs in O(n + m log m). An even more
 * optimal O(n) solution exists using Bucket Sort, which
 * leverages the fact that a frequency cannot exceed n.
 * ==========================================================
 */
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        // Stores the final answer containing the k most frequent elements.
        int[] result = new int[k];

        // Maps each number to its frequency.
        // Key   -> Number
        // Value -> Number of occurrences
        Map<Integer, Integer> map = new HashMap<>();

        // Count the frequency of every element.
        //
        // Example:
        // nums = [1,1,1,2,2,3]
        //
        // map:
        // 1 -> 3
        // 2 -> 2
        // 3 -> 1
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Convert the map entries into a list so that they can be sorted.
        //
        // Each entry contains both:
        // Key   -> Element
        // Value -> Frequency
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        // Sort the entries in descending order of frequency.
        //
        // Example:
        // Before:
        // 1 -> 3
        // 3 -> 1
        // 2 -> 2
        //
        // After:
        // 1 -> 3
        // 2 -> 2
        // 3 -> 1
        list.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        // Pick the first k entries from the sorted list.
        // These represent the k most frequent elements.
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }

        return result;
    }
}
