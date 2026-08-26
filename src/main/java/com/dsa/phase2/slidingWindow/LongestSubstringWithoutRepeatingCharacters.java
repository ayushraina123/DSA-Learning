package com.dsa.phase2.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 3 - Longest Substring Without
 * Repeating Characters
 * Difficulty : Medium
 * Pattern    : Sliding Window - Variable Size
 * ==========================================================
 *
 * <p>
 * Given a string s, find the length of the longest substring
 * without repeating characters.
 * </p>
 *
 * <p>
 * Example:
 * <p>
 * s = "abcabcbb"
 * <p>
 * Longest substring without repeating characters:
 * "abc"
 * <p>
 * Answer = 3
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Approach: Variable-Size Sliding Window
 * ==========================================================
 *
 * <p>
 * Unlike the previous fixed-size Sliding Window problems,
 * the size of the window is not predetermined.
 * </p>
 *
 * <p>
 * The window is maintained between:
 * </p>
 * <p>
 * left  -> beginning of the current window
 * right -> end of the current window
 *
 * <p>
 * The window must always satisfy the condition:
 * </p>
 * <p>
 * No character appears more than once.
 *
 * <p>
 * The window expands as right moves forward.
 * If the character entering the window has already appeared
 * inside the current window, left must move forward so that
 * the duplicate is removed from the window.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Map
 * ==========================================================
 *
 * <p>
 * We maintain a HashMap where:
 * </p>
 * <p>
 * Character -> Most recent index of that character
 *
 * <p>
 * For example:
 * </p>
 * <p>
 * a -> 5
 * b -> 7
 * c -> 9
 *
 * <p>
 * This allows us to immediately determine where a duplicate
 * character was previously seen.
 * </p>
 *
 * <p>
 * When a duplicate character is encountered, we can move
 * left directly to one position after its previous occurrence
 * instead of moving left one position at a time.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Important Invariant
 * ==========================================================
 *
 * <p>
 * At every iteration, the current window:
 * </p>
 * <p>
 * [left ... right]
 *
 * <p>
 * contains no duplicate characters.
 * </p>
 *
 * <p>
 * The Map stores the most recent position of each character,
 * which allows us to preserve this invariant whenever a
 * duplicate enters the window.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Why Check Previous Index >= left?
 * ==========================================================
 *
 * <p>
 * This is the most important detail of the implementation.
 * </p>
 *
 * <p>
 * A character may exist in the Map even though its previous
 * occurrence is no longer inside the current window.
 * </p>
 *
 * <p>
 * For example, suppose:
 * </p>
 * <p>
 * left = 3
 *
 * <p>
 * and the previous occurrence of the current character was
 * at index 1.
 * </p>
 * <p>
 * previousIndex = 1
 *
 * <p>
 * That occurrence is already outside the current window:
 * </p>
 * <p>
 * [3 ... right]
 *
 * <p>
 * Therefore, it should not cause left to move.
 * </p>
 *
 * <p>
 * This is why we check:
 * </p>
 * <p>
 * previousIndex >= left
 *
 * <p>
 * Only when the previous occurrence lies inside the current
 * window do we move left:
 * </p>
 * <p>
 * left = previousIndex + 1
 * </p>
 *
 * <p>
 * This also guarantees that left never moves backward.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Why Can We Jump left Directly?
 * ==========================================================
 *
 * <p>
 * Suppose the current character appears at:
 * </p>
 * <p>
 * previousIndex
 *
 * <p>
 * and the current window contains that previous occurrence.
 * </p>
 *
 * <p>
 * To remove the duplicate, every position up to and including
 * previousIndex can no longer be part of the valid window.
 * </p>
 *
 * <p>
 * Therefore, instead of:
 * </p>
 * <p>
 * left++
 * left++
 * left++
 * ...
 *
 * <p>
 * we can directly jump to:
 * </p>
 * <p>
 * left = previousIndex + 1
 *
 * <p>
 * This moves the beginning of the window to the first position
 * after the previous occurrence of the duplicate character.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Algorithm
 * ==========================================================
 *
 * <p>
 * 1. Initialize max to 0.
 * </p>
 *
 * <p>
 * 2. Create a HashMap to store the most recent index of every
 * character encountered.
 * </p>
 *
 * <p>
 * 3. Initialize left to 0.
 * </p>
 *
 * <p>
 * 4. Move right through the string.
 * </p>
 *
 * <p>
 * 5. Check whether the current character already exists
 * in the Map.
 * </p>
 *
 * <p>
 * 6. If it exists, check whether its previous occurrence is
 * inside the current window:
 * </p>
 * <p>
 * previousIndex >= left
 *
 * <p>
 * If it is, move left to:
 * </p>
 * <p>
 * previousIndex + 1
 *
 * <p>
 * 7. Update the character's index in the Map to right.
 * </p>
 *
 * <p>
 * 8. Calculate the current window length:
 * </p>
 * <p>
 * right - left + 1
 *
 * <p>
 * 9. Update max with the current window length.
 * </p>
 *
 * <p>
 * 10. Continue until right reaches the end of the string.
 * </p>
 *
 * <p>
 * 11. Return max.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Example Walkthrough
 * ==========================================================
 *
 * <p>
 * Consider:
 * </p>
 * <p>
 * s = "abcabcbb"
 *
 * <p>
 * Initially:
 * </p>
 * <p>
 * left = 0
 *
 * <p>
 * As right moves:
 * </p>
 * <p>
 * a
 * a b
 * a b c
 *
 * <p>
 * The current window contains no duplicates.
 * </p>
 * <p>
 * [a b c]
 * ↑   ↑
 * left right
 *
 * <p>
 * When the next 'a' is encountered, the Map tells us that
 * the previous 'a' occurred at index 0.
 * </p>
 *
 * <p>
 * Since index 0 is inside the current window:
 * </p>
 * <p>
 * left = 0 + 1
 * = 1
 *
 * <p>
 * The valid window becomes:
 * </p>
 * <p>
 * [b c a]
 * ↑   ↑
 * left right
 *
 * <p>
 * The same process continues as right moves through the
 * string.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Complexity
 * ==========================================================
 * <p>
 * Time  : O(n)
 *
 * <p>
 * The right pointer moves through the string once.
 * The left pointer only moves forward and never moves
 * backward.
 * </p>
 *
 * <p>
 * Map operations such as containsKey(), get(), and put()
 * are O(1) on average.
 * </p>
 * <p>
 * Space : O(min(n, character set size))
 *
 * <p>
 * The Map stores the most recent index of characters currently
 * encountered in the string.
 * </p>
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {

        /*
         * Maximum length of a substring without repeating
         * characters found so far.
         */
        int max = 0;

        /*
         * ======================================================
         * Character -> Most recent index
         * ======================================================
         *
         * The Map allows us to determine where a character
         * was previously seen.
         */
        Map<Character, Integer> map = new HashMap<>();

        /*
         * left marks the beginning of the current valid
         * window.
         */
        int left = 0;

        /*
         * right expands the sliding window one character
         * at a time.
         */
        for (int right = 0; right < s.length(); right++) {

            /*
             * ==================================================
             * Check whether the current character was seen
             * before.
             * ==================================================
             */
            if (map.containsKey(s.charAt(right))) {

                /*
                 * Get the previous position of the current
                 * character.
                 */
                if (map.get(s.charAt(right)) >= left) {

                    /*
                     * The previous occurrence is inside the
                     * current window.
                     *
                     * Therefore, move left to the position
                     * immediately after that occurrence.
                     *
                     * This removes the duplicate from the
                     * current window.
                     */
                    left = map.get(s.charAt(right)) + 1;
                }

                /*
                 * Update the character's position to its
                 * most recent occurrence.
                 */
                map.put(s.charAt(right), right);

            } else {

                /*
                 * Character has not been encountered before.
                 *
                 * Store its current index.
                 */
                map.put(s.charAt(right), right);
            }

            /*
             * Calculate the length of the current valid window.
             *
             * Since both boundaries are inclusive:
             *
             *     window length = right - left + 1
             */
            max = Math.max(max, right - left + 1);
        }

        /*
         * Return the longest valid window found.
         */
        return max;
    }
}