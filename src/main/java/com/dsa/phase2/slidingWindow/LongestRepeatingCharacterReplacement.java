package com.dsa.phase2.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 424 - Longest Repeating Character Replacement
 * Difficulty : Medium
 * Pattern    : Sliding Window
 * <p>
 * Idea:
 * We need to find the longest substring that can be converted
 * into a substring containing only one repeating character by
 * replacing at most `k` characters.
 * <p>
 * We maintain a sliding window using `left` and `right`.
 * The frequency of every character inside the current window
 * is stored in a HashMap.
 * <p>
 * `maxFreq` stores the highest frequency of any single character
 * seen in the current/expanded window.
 * <p>
 * Suppose the current window has length `windowSize` and the
 * most frequent character occurs `maxFreq` times.
 * <p>
 * We can keep all occurrences of that most frequent character
 * and replace every other character. Therefore:
 * <p>
 * replacementsNeeded = windowSize - maxFreq
 * <p>
 * If `replacementsNeeded > k`, the current window cannot be
 * converted into a repeating-character substring using at most
 * `k` replacements, so we shrink the window by moving `left`.
 * <p>
 * Otherwise, the window is valid and we update the maximum
 * length found so far.
 * <p>
 * Example:
 * <p>
 * s = "AABABBA", k = 1
 * <p>
 * Window: "AABA"
 * <p>
 * A occurs 3 times and B occurs once.
 * Window length = 4
 * Replacements needed = 4 - 3 = 1
 * <p>
 * Replace B -> A:
 * <p>
 * A A B A
 * A A A A
 * <p>
 * Therefore, this window is valid.
 * <p>
 * ==========================================================
 * <p>
 * Approach:
 * <p>
 * 1. Expand the window by moving `right`.
 * 2. Add the current character to the frequency map.
 * 3. Update `maxFreq` using the frequency of the newly added
 * character.
 * 4. Calculate whether the current window requires more than
 * `k` replacements:
 * <p>
 * windowSize - maxFreq > k
 * <p>
 * 5. If the window is invalid:
 * - Decrease the frequency of `s.charAt(left)`.
 * - Move `left` forward.
 * <p>
 * 6. Update `answer` with the largest valid window length.
 * <p>
 * ==========================================================
 * <p>
 * Important:
 * <p>
 * We do NOT care which character is at `left`.
 * The character we keep is whichever character has the highest
 * frequency in the current window.
 * <p>
 * For example:
 * <p>
 * A B B B B
 * ^
 * left
 * <p>
 * Even though `left` points to A, B is the character we want
 * to keep because B occurs four times.
 * <p>
 * Therefore, only the A needs to be replaced.
 * <p>
 * ==========================================================
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 * <p>
 * Space is O(1) because the input contains only uppercase
 * English letters, giving us at most 26 distinct characters.
 * <p>
 * ==========================================================
 */
public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        int answer = 0;
        int maxFreq = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            map.put(
                    s.charAt(right),
                    map.getOrDefault(s.charAt(right), 0) + 1
            );

            maxFreq = Math.max(
                    maxFreq,
                    map.get(s.charAt(right))
            );

            if (right - left + 1 - maxFreq > k) {
                map.put(
                        s.charAt(left),
                        map.get(s.charAt(left)) - 1
                );
                left++;
            }

            answer = Math.max(
                    answer,
                    right - left + 1
            );
        }

        return answer;
    }
}