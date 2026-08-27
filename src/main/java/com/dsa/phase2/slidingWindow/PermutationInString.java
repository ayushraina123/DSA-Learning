package com.dsa.phase2.slidingWindow;

import java.util.Arrays;

/**
 * ==========================================================
 * Problem    : LeetCode 567 - Permutation in String
 * Difficulty : Medium
 * Pattern    : Sliding Window
 * <p>
 * Idea:
 * We need to determine whether any permutation of `s1` exists
 * as a substring of `s2`.
 * <p>
 * Since a permutation contains the exact same characters with
 * the exact same frequencies, we maintain:
 * <ul>
 *     <li>`freq1` - frequency of characters in `s1`</li>
 *     <li>`freq2` - frequency of characters in the current
 *     sliding window of `s2`</li>
 * </ul>
 * <p>
 * The window size is always kept equal to `s1.length()`.
 * If the frequency arrays are equal, the current window is a
 * permutation of `s1`.
 * <p>
 * We use arrays of size 26 because the input consists of
 * lowercase English letters.
 * ==========================================================
 */
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {

        // Frequency array for characters in s1.
        int[] freq1 = new int[26];

        // Frequency array for the current window in s2.
        int[] freq2 = new int[26];

        // Build the frequency array for s1.
        for (int i = 0; i < s1.length(); i++) {
            freq1[s1.charAt(i) - 'a']++;
        }

        int left = 0;

        // Sliding window over s2.
        for (int right = left; right < s2.length(); right++) {

            // Add the current character to the window.
            freq2[s2.charAt(right) - 'a']++;

            // If both frequency arrays are equal,
            // the current window is a permutation of s1.
            if (Arrays.equals(freq1, freq2)) {
                return true;
            }

            // Once the window reaches s1's length,
            // remove the leftmost character before moving left.
            if (right - left + 1 == s1.length()) {
                freq2[s2.charAt(left) - 'a']--;
                left++;
            }
        }

        return false;
    }
}