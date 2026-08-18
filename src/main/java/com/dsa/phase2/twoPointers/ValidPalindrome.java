package com.dsa.phase2.twoPointers;

/**
 * ==========================================================
 * Problem    : LeetCode 125 - Valid Palindrome
 * Difficulty : Easy
 * Pattern    : Two Pointers
 * ==========================================================
 *
 * <p>
 * Given a string, determine whether it is a palindrome after
 * converting all uppercase letters to lowercase and removing
 * all non-alphanumeric characters.
 * </p>
 *
 * <p>
 * A palindrome is a sequence that reads the same forward and
 * backward.
 * </p>
 *
 * <p>
 * Example:
 * <p>
 * s = "A man, a plan, a canal: Panama"
 * <p>
 * After removing non-alphanumeric characters and converting
 * to lowercase:
 * <p>
 * "amanaplanacanalpanama"
 * <p>
 * This reads the same from both directions, so the answer is:
 * <p>
 * true
 * </p>
 *
 * <p>
 * Approach:
 * <p>
 * 1. Remove all characters that are not letters or digits.
 * 2. Convert the remaining characters to lowercase.
 * 3. Use two pointers:
 * <p>
 * left  -> starts at the beginning
 * right -> starts at the end
 * <p>
 * 4. Compare the characters at both pointers.
 * 5. If they differ, the string is not a palindrome.
 * 6. If they match, move both pointers toward the center.
 * </p>
 *
 * <p>
 * Complexity:
 * <p>
 * Time  : O(n)
 * Space : O(n)
 * <p>
 * The cleaned string requires O(n) additional space.
 * </p>
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {

        /*
         * ======================================================
         * Step 1: Remove non-alphanumeric characters
         * ======================================================
         *
         * [^a-zA-Z0-9] means:
         *
         *     Any character that is NOT:
         *         - lowercase letter (a-z)
         *         - uppercase letter (A-Z)
         *         - digit (0-9)
         *
         * Replace all such characters with "" (nothing),
         * effectively removing them.
         *
         * Then convert everything to lowercase so that:
         *
         *     'A' == 'a'
         *
         * For example:
         *
         *     "A man, a plan!"
         *
         * becomes:
         *
         *     "amanaplan"
         */
        String result = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        /*
         * ======================================================
         * Step 2: Handle an empty string
         * ======================================================
         *
         * An empty string is considered a palindrome.
         *
         * This can happen when the original string contains
         * only non-alphanumeric characters.
         */
        if (result.isEmpty())
            return true;

        /*
         * ======================================================
         * Step 3: Initialize the two pointers
         * ======================================================
         *
         * left  -> first character
         * right -> last character
         *
         * Example:
         *
         *     "racecar"
         *      ↑     ↑
         *     left  right
         */
        int left = 0;
        int right = result.length() - 1;

        /*
         * ======================================================
         * Step 4: Compare characters from both ends
         * ======================================================
         *
         * Move both pointers toward the center.
         *
         * If the characters at the two pointers are different,
         * the string cannot be a palindrome.
         */
        while (left < right) {

            if (result.charAt(left) != result.charAt(right)) {
                return false;
            }

            /*
             * Characters matched, so move both pointers
             * toward the center.
             */
            left++;
            right--;
        }

        /*
         * Every corresponding pair of characters matched,
         * therefore the string is a palindrome.
         */
        return true;
    }
}