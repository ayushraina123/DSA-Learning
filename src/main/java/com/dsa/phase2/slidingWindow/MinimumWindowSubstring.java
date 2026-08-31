package com.dsa.phase2.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 76 - Minimum Window Substring
 * Difficulty : Hard
 * Pattern    : Sliding Window
 * <p>
 * Problem:
 * Given two strings `s` and `t`, find the minimum window
 * substring of `s` that contains all the characters of `t`,
 * including duplicate characters.
 * <p>
 * If no such window exists, return an empty string.
 * ==========================================================
 */
public class MinimumWindowSubstring {

    /**
     * ==========================================================
     * Approach 1 : Sliding Window - User's Approach
     * <p>
     * Idea:
     * We maintain two frequency maps:
     * <p>
     * 1. `tFrequencyMap`
     * Stores the frequency of every character required by `t`.
     * <p>
     * 2. `sFrequencyMap`
     * Stores the frequency of characters currently present
     * in the sliding window.
     * <p>
     * We expand the window by moving `right`.
     * <p>
     * Whenever the current window contains all required
     * characters with their required frequencies, we have
     * found a valid window.
     * <p>
     * We record the window if it is smaller than the previous
     * minimum and then move `left` forward to try finding
     * a smaller valid window.
     * <p>
     * To determine whether the current window is valid, we
     * call `checkMapFrequency()`.
     * <p>
     * `checkMapFrequency()` iterates through every distinct
     * character required by `t` and checks whether the current
     * window contains enough occurrences of that character.
     * <p>
     * Example:
     * <p>
     * s = "ADOBECODEBANC"
     * t = "ABC"
     * <p>
     * When the window becomes:
     * "ADOBEC"
     * <p>
     * the window contains:
     * A -> 1
     * B -> 1
     * C -> 1
     * <p>
     * Therefore the window is valid.
     * We then move `left` forward to try to shrink it.
     * <p>
     * The approach works, but repeatedly calling
     * `checkMapFrequency()` is unnecessary because we keep
     * scanning the same frequency map again and again.
     * <p>
     * Time Complexity:
     * O(n * k)
     * where:
     * n = length of `s`
     * k = number of distinct characters in `t`
     * <p>
     * Space Complexity:
     * O(k)
     * ==========================================================
     */
    public String minWindow(String s, String t) {

        String result = "";
        int minLen = Integer.MAX_VALUE;

        // Stores the frequency of every character required by t.
        Map<Character, Integer> tFrequencyMap = new HashMap<>();

        // Stores the frequency of characters in the current window.
        Map<Character, Integer> sFrequencyMap = new HashMap<>();

        // Left boundary of the sliding window.
        int left = 0;

        // Right boundary of the sliding window.
        int right = 0;

        // Build the frequency map for t.
        for (int i = 0; i < t.length(); i++) {
            tFrequencyMap.put(
                    t.charAt(i),
                    tFrequencyMap.getOrDefault(t.charAt(i), 0) + 1
            );
        }

        // Expand the window using right.
        while (right < s.length() && left < s.length()) {

            // Add the rightmost character to the window.
            sFrequencyMap.put(
                    s.charAt(right),
                    sFrequencyMap.getOrDefault(s.charAt(right), 0) + 1
            );

            /*
             * Check whether the current window contains all
             * required characters with the required frequencies.
             */
            if (checkMapFrequency(tFrequencyMap, sFrequencyMap)) {

                // Update the minimum window if the current
                // window is smaller.
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    result = s.substring(left, right + 1);
                }

                /*
                 * Remove the leftmost character because we want
                 * to shrink the current valid window.
                 */
                sFrequencyMap.put(
                        s.charAt(left),
                        sFrequencyMap.get(s.charAt(left)) - 1
                );

                /*
                 * Remove the rightmost character as well.
                 *
                 * The next iteration adds it back, allowing us
                 * to test the smaller window with the same
                 * right boundary.
                 */
                sFrequencyMap.put(
                        s.charAt(right),
                        sFrequencyMap.get(s.charAt(right)) - 1
                );

                // Remove the character completely if its
                // frequency becomes zero.
                if (sFrequencyMap.get(s.charAt(left)) == 0) {
                    sFrequencyMap.remove(s.charAt(left));
                }

                // Move the left boundary forward.
                left++;

            } else {

                // Current window is not valid, so expand it.
                right++;
            }
        }

        // Return the smallest valid window found.
        return result;
    }

    /**
     * Checks whether the current sliding window contains every
     * required character with at least the required frequency.
     */
    public boolean checkMapFrequency(
            Map<Character, Integer> tFreqMap,
            Map<Character, Integer> sFreqMap) {

        // Check every character required by t.
        for (Map.Entry<Character, Integer> entry : tFreqMap.entrySet()) {

            // Required character is missing from the window.
            if (!sFreqMap.containsKey(entry.getKey())) {
                return false;

            } else {

                // Window does not contain enough occurrences.
                if (sFreqMap.get(entry.getKey()) < entry.getValue()) {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * ==========================================================
     * Approach 2 : Optimized Sliding Window
     * <p>
     * Idea:
     * Instead of repeatedly calling `checkMapFrequency()` and
     * scanning the entire frequency map, we maintain the validity
     * of the window using two variables:
     * <p>
     * `required`:
     * Number of distinct characters that must be satisfied.
     * <p>
     * `formed`:
     * Number of distinct required characters whose frequency
     * requirement is currently satisfied.
     * <p>
     * Example:
     * <p>
     * t = "AABC"
     * <p>
     * tFrequencyMap:
     * A -> 2
     * B -> 1
     * C -> 1
     * <p>
     * required = 3
     * <p>
     * When the window contains:
     * A -> 1
     * B -> 1
     * C -> 1
     * <p>
     * `formed` is only 2 because A still needs one more
     * occurrence.
     * <p>
     * Once the window contains:
     * A -> 2
     * B -> 1
     * C -> 1
     * <p>
     * formed = 3
     * required = 3
     * <p>
     * Therefore:
     * formed == required
     * <p>
     * means the current window is valid.
     * <p>
     * Once the window becomes valid, we keep `right` fixed
     * and repeatedly move `left` forward to find the smallest
     * valid window ending at the current `right`.
     * <p>
     * Example:
     * <p>
     * s = "ADOBECODEBANC"
     * t = "ABC"
     * <p>
     * A valid window is:
     * "ADOBEC"
     * <p>
     * We then shrink it from the left until removing a character
     * makes the window invalid.
     * <p>
     * Later, we find:
     * "BANC"
     * <p>
     * which becomes the minimum window.
     * <p>
     * The major optimization is that we no longer scan the
     * entire `tFrequencyMap` to determine whether the window
     * is valid.
     * <p>
     * Time Complexity:
     * O(n)
     * <p>
     * Space Complexity:
     * O(k)
     * where:
     * n = length of `s`
     * k = number of distinct characters in `t`
     * ==========================================================
     */
    public String minWindowOptimized(String s, String t) {

        // If t is longer than s, no valid window is possible.
        if (t.length() > s.length()) {
            return "";
        }

        // Stores the frequency of every character required by t.
        Map<Character, Integer> tFrequencyMap = new HashMap<>();

        // Stores the frequency of characters in the current window.
        Map<Character, Integer> windowFrequencyMap = new HashMap<>();

        // Build the frequency map for t.
        for (char ch : t.toCharArray()) {
            tFrequencyMap.put(
                    ch,
                    tFrequencyMap.getOrDefault(ch, 0) + 1
            );
        }

        // Left boundary of the sliding window.
        int left = 0;

        // Right boundary of the sliding window.
        int right = 0;

        /*
         * Number of distinct characters that need to be
         * satisfied.
         *
         * Example:
         * t = "AABC"
         * required = 3
         *
         * because A, B and C are the distinct characters.
         */
        int required = tFrequencyMap.size();

        /*
         * Number of distinct required characters whose
         * frequency requirement is currently satisfied.
         */
        int formed = 0;

        // Length of the smallest valid window found so far.
        int minLen = Integer.MAX_VALUE;

        // Starting index of the smallest valid window.
        int minLeft = 0;

        // Expand the window using right.
        while (right < s.length()) {

            // Character entering the window.
            char rightChar = s.charAt(right);

            // Add the character to the current window.
            windowFrequencyMap.put(
                    rightChar,
                    windowFrequencyMap.getOrDefault(rightChar, 0) + 1
            );

            /*
             * If the character is required by t and its frequency
             * has now reached the exact required frequency,
             * one required character has been satisfied.
             *
             * We use == instead of >= because `formed` should
             * increase only once for each distinct character.
             */
            if (tFrequencyMap.containsKey(rightChar)
                    && windowFrequencyMap.get(rightChar).intValue()
                    == tFrequencyMap.get(rightChar).intValue()) {

                formed++;
            }

            /*
             * The current window contains all required characters
             * with the required frequencies.
             *
             * Keep shrinking the window from the left while it
             * remains valid.
             */
            while (formed == required) {

                // Update the minimum valid window.
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                // Character leaving the window.
                char leftChar = s.charAt(left);

                // Remove the leftmost character.
                windowFrequencyMap.put(
                        leftChar,
                        windowFrequencyMap.get(leftChar) - 1
                );

                /*
                 * If the removed character was required and its
                 * frequency has now fallen below the required
                 * frequency, the window is no longer satisfying
                 * that character.
                 */
                if (tFrequencyMap.containsKey(leftChar)
                        && windowFrequencyMap.get(leftChar)
                        < tFrequencyMap.get(leftChar)) {

                    formed--;
                }

                // Shrink the window from the left.
                left++;
            }

            // Expand the window from the right.
            right++;
        }

        // No valid window was found.
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }

        // Return the smallest valid window.
        return s.substring(minLeft, minLeft + minLen);
    }
}