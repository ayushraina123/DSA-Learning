package com.dsa.phase2.slidingWindow;

/**
 * ==========================================================
 * Problem    : LeetCode 1456 - Maximum Number of Vowels in a
 * Substring of Given Length
 * Difficulty : Medium
 * Pattern    : Sliding Window - Fixed Size
 * ==========================================================
 *
 * <p>
 * Given a string s and an integer k, find the maximum number
 * of vowels contained in any substring of length k.
 * </p>
 *
 * <p>
 * Example:
 * <p>
 * s = "abciiidef"
 * k = 3
 * <p>
 * Possible windows:
 * <p>
 * "abc" -> 1 vowel
 * "bci" -> 1 vowel
 * "cii" -> 2 vowels
 * "iii" -> 3 vowels
 * "iid" -> 2 vowels
 * "ide" -> 2 vowels
 * "def" -> 1 vowel
 * <p>
 * Answer = 3
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Approach: Fixed-Size Sliding Window
 * ==========================================================
 *
 * <p>
 * Every substring we need to consider has exactly k
 * characters.
 * Therefore, this is a Fixed-Size Sliding Window problem.
 * </p>
 *
 * <p>
 * We maintain a window using two pointers:
 * </p>
 * <p>
 * left  -> beginning of the current window
 * right -> end of the current window
 *
 * <p>
 * We also maintain:
 * </p>
 * <p>
 * count -> number of vowels currently present
 * inside the window
 *
 * <p>
 * As right moves forward, we check whether the newly added
 * character is a vowel. If it is, we increment count.
 * </p>
 *
 * <p>
 * Once the window reaches size k, we have a complete
 * substring that can be evaluated.
 * </p>
 *
 * <p>
 * After processing the window, we remove the contribution
 * of s[left] from count if it is a vowel, and then move
 * left forward.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Window State
 * ==========================================================
 *
 * <p>
 * The important state maintained by the algorithm is:
 * </p>
 * <p>
 * count
 *
 * <p>
 * count represents the number of vowels inside the current
 * window.
 * </p>
 *
 * <p>
 * The current window size is:
 * </p>
 * <p>
 * right - left + 1
 *
 * <p>
 * We process the window only when:
 * </p>
 * <p>
 * right - left + 1 == k
 *
 * <p>
 * Therefore, every processed window contains exactly k
 * characters.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * How the Window Slides
 * ==========================================================
 *
 * <p>
 * Suppose:
 * </p>
 * <p>
 * s = "abciiidef"
 * k = 3
 *
 * <p>
 * The first window is:
 * </p>
 * <p>
 * "abc"
 * ↑ ↑
 * left right
 *
 * <p>
 * After processing it, the window slides forward:
 * </p>
 * <p>
 * "bci"
 * ↑ ↑
 * left right
 *
 * <p>
 * Instead of counting all vowels in the new window again,
 * we reuse the existing count.
 * </p>
 *
 * <p>
 * When the window moves:
 * </p>
 * <p>
 * 1. Add the new character entering from the right.
 * <p>
 * 2. Remove the character leaving from the left.
 *
 * <p>
 * Therefore, only the elements entering and leaving the
 * window need to be considered.
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
 * 2. Initialize count to 0.
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
 * 5. If s[right] is a vowel, increment count.
 * </p>
 *
 * <p>
 * 6. When the window reaches size k:
 * </p>
 * <p>
 * - Update max using the current vowel count.
 * - Check whether s[left] is a vowel.
 * - If it is, decrement count.
 * - Move left forward.
 *
 * <p>
 * 7. Continue until right reaches the end of the string.
 * </p>
 *
 * <p>
 * 8. Return max.
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
 * The right pointer traverses the string once.
 * The left pointer also only moves forward and never moves
 * backward.
 * </p>
 *
 * <p>
 * Each character is therefore processed a constant number
 * of times.
 * </p>
 * <p>
 * Space : O(1)
 *
 * <p>
 * Only a constant number of variables are used.
 * No additional data structure grows with the size of the
 * input.
 * </p>
 */
public class MaximumNumberOfVowelsInASubstringOfGivenLength {

    public int maxVowels(String s, int k) {

        /*
         * ======================================================
         * Track the maximum number of vowels found so far.
         * ======================================================
         */
        int max = 0;

        /*
         * Number of vowels currently present inside
         * the sliding window.
         */
        int count = 0;

        /*
         * left marks the beginning of the current window.
         */
        int left = 0;

        /*
         * right expands the window one character at a time.
         */
        for (int right = 0; right < s.length(); right++) {

            /*
             * ==================================================
             * Add the new character to the window.
             * ==================================================
             *
             * If the character entering the window is a vowel,
             * increase the current vowel count.
             */
            if (s.charAt(right) == 'a'
                    || s.charAt(right) == 'e'
                    || s.charAt(right) == 'i'
                    || s.charAt(right) == 'o'
                    || s.charAt(right) == 'u') {

                count++;
            }

            /*
             * ==================================================
             * Check whether the window has reached size k.
             * ==================================================
             *
             * Window size:
             *
             *     right - left + 1
             *
             * Once it becomes k, we have a complete substring
             * that can be evaluated.
             */
            if (right - left + 1 == k) {

                /*
                 * Update the maximum number of vowels found
                 * in any window so far.
                 */
                max = Math.max(max, count);

                /*
                 * ==================================================
                 * Slide the window forward.
                 * ==================================================
                 *
                 * s[left] is about to leave the current window.
                 *
                 * If it is a vowel, remove its contribution
                 * from count.
                 */
                if (s.charAt(left) == 'a'
                        || s.charAt(left) == 'e'
                        || s.charAt(left) == 'i'
                        || s.charAt(left) == 'o'
                        || s.charAt(left) == 'u') {

                    count--;
                }

                /*
                 * Move left forward so that the next window
                 * starts at the following character.
                 */
                left++;
            }
        }

        /*
         * Return the maximum number of vowels found in any
         * substring of length k.
         */
        return max;
    }
}