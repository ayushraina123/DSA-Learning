package com.dsa.phase2.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ==========================================================
 * Problem    : LeetCode 438 - Find All Anagrams in a String
 * Difficulty : Medium
 * Pattern    : Sliding Window
 * <p>
 * Idea:
 * We need to find all starting indices in `s` where an anagram
 * of `p` exists.
 * <p>
 * Since an anagram contains the exact same characters with
 * the exact same frequencies, we maintain:
 * <ul>
 *     <li>`freq1` - frequency of characters in `p`</li>
 *     <li>`freq2` - frequency of characters in the current
 *     sliding window of `s`</li>
 * </ul>
 * <p>
 * The window size is always kept equal to `p.length()`.
 * If the frequency arrays are equal, the current window is
 * an anagram of `p`, so we add the window's starting index
 * to the result.
 * <p>
 * We use arrays of size 26 because the input consists of
 * lowercase English letters.
 * ==========================================================
 */
public class FindAllAnagramsInString {

    public List<Integer> findAnagrams(String s, String p) {

        // Stores the frequency of characters in p.
        int[] freq1 = new int[26];

        // Stores the frequency of characters in the current
        // sliding window of s.
        int[] freq2 = new int[26];

        // Stores the starting indices of all anagrams found.
        List<Integer> result = new ArrayList<>();

        int left = 0;

        // Build the frequency array for p.
        for (int i = 0; i < p.length(); i++) {
            freq1[p.charAt(i) - 'a']++;
        }

        // Sliding window over s.
        for (int right = 0; right < s.length(); right++) {

            // Add the current character to the window.
            freq2[s.charAt(right) - 'a']++;

            // Once the window reaches p's length,
            // check whether it is an anagram of p.
            if (right - left + 1 == p.length()) {

                // If both frequency arrays are equal,
                // the current window is an anagram of p.
                if (Arrays.equals(freq1, freq2)) {
                    result.add(left);
                }

                // Remove the leftmost character before
                // moving the left pointer forward.
                freq2[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return result;
    }
}