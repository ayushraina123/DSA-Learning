package com.dsa.phase1.hashing;

import java.util.*;

/**
 * ==========================================================
 * Problem    : LeetCode 49 - Group Anagrams
 * Difficulty : Medium
 * Pattern    : Hashing
 * <p>
 * Idea:
 * Two strings are anagrams if they contain the same characters
 * with the exact same frequencies.
 * <p>
 * A straightforward solution is to sort every string and use
 * the sorted string as the key inside a HashMap.
 * <p>
 * Example:
 * <p>
 * "eat" -> "aet"
 * "tea" -> "aet"
 * "ate" -> "aet"
 * <p>
 * Although this works well, sorting every string requires
 * O(k log k) time, where k is the length of the string.
 * <p>
 * Instead, we can represent every string by its character
 * frequency.
 * <p>
 * Example:
 * <p>
 * "eat"
 * <p>
 * a -> 1
 * b -> 0
 * c -> 0
 * ...
 * e -> 1
 * ...
 * t -> 1
 * <p>
 * Every anagram will produce exactly the same frequency array.
 * <p>
 * Since arrays cannot be used directly as HashMap keys
 * (they compare by reference rather than contents), we convert
 * the frequency array into a String using Arrays.toString().
 * <p>
 * Example key:
 * <p>
 * [1, 0, 0, 0, 1, ..., 1]
 * <p>
 * This key uniquely represents all anagrams containing the
 * same character frequencies.
 * <p>
 * The algorithm becomes:
 * <p>
 * 1. Compute the frequency array for every word.
 * 2. Convert the frequency array into a String key.
 * 3. Store the original word against that key.
 * 4. Return all grouped values from the HashMap.
 * <p>
 * Why does this work?
 * <p>
 * Two words are anagrams if and only if every character
 * appears the same number of times in both words.
 * <p>
 * Therefore, identical frequency arrays always belong to the
 * same anagram group.
 * <p>
 * Time Complexity : O(n × k)
 * n -> Number of strings
 * k -> Average length of each string
 * <p>
 * Space Complexity: O(n × k)
 * HashMap stores all strings along with one key per
 * anagram group.
 * <p>
 * Learnings:
 * ✔ HashMap keys don't have to be the original data—they can be
 * any unique representation of that data.
 * ✔ Arrays cannot be used directly as HashMap keys because
 * equals() and hashCode() are inherited from Object and
 * compare references instead of contents.
 * ✔ Counting character frequencies avoids sorting and improves
 * the algorithm from O(k log k) to O(k) per string.
 * ✔ Sometimes preprocessing data into a canonical form makes
 * grouping problems much easier.
 * ==========================================================
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        // Maps a unique frequency representation of a word
        // to all words having the same character frequencies.
        Map<String, List<String>> valueMap = new HashMap<>();

        for (String str : strs) {

            // Stores the frequency of each lowercase letter.
            int[] freq = new int[26];

            // Count the occurrence of every character.
            for (char ch : str.toCharArray()) {
                freq[ch - 'a']++;
            }

            // Arrays cannot be used directly as HashMap keys.
            // Convert the frequency array into a String that
            // uniquely represents this anagram group.
            String key = Arrays.toString(freq);

            // Insert the current word into its corresponding group.
            valueMap.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        // Collect all grouped anagrams.
        result.addAll(valueMap.values());

        return result;
    }
}