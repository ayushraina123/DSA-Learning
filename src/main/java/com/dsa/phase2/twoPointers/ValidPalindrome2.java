package com.dsa.phase2.twoPointers;

/**
 * ==========================================================
 * Problem    : LeetCode 680 - Valid Palindrome II
 * Difficulty : Easy
 * Pattern    : Two Pointers
 * ==========================================================
 *
 * <p>
 * Given a string, determine whether it can become a palindrome
 * after deleting at most one character.
 * </p>
 *
 * <p>
 * Example:
 * <p>
 * s = "abca"
 * <p>
 * a b c a
 * ↑     ↑
 * i     j
 * <p>
 * The first and last characters match.
 * <p>
 * a == a
 * <p>
 * Move both pointers:
 * <p>
 * a b c a
 * ↑ ↑
 * i j
 * <p>
 * Now:
 * <p>
 * b != c
 * <p>
 * We are allowed to delete at most one character, so there are
 * two possibilities:
 * <p>
 * 1. Delete 'b'
 * Check whether "ca" is a palindrome.
 * <p>
 * 2. Delete 'c'
 * Check whether "ba" is a palindrome.
 * <p>
 * If either remaining substring is a palindrome, the original
 * string can become a palindrome after one deletion.
 * </p>
 *
 * <p>
 * Key Observation:
 * <p>
 * Until the first mismatch, both characters must match in any
 * valid palindrome.
 * <p>
 * Therefore, when we encounter the first mismatch:
 * <p>
 * s[left] != s[right]
 * <p>
 * the only useful choices are:
 * <p>
 * delete s[left]
 * OR
 * delete s[right]
 * <p>
 * We then check both possibilities.
 * </p>
 *
 * <p>
 * Why do we only need to consider the first mismatch?
 * <p>
 * Because before the first mismatch, all corresponding
 * characters already match.
 * <p>
 * Since we are allowed to delete only ONE character, that
 * deletion must resolve the first mismatch. There is no reason
 * to delete any character before it because those characters
 * already form matching pairs.
 * </p>
 *
 * <p>
 * Complexity:
 * <p>
 * Time  : O(n)
 * Space : O(1)
 * <p>
 * The main scan takes O(n).
 * At the first mismatch, we may perform one additional
 * palindrome check of O(n).
 * <p>
 * Since there is only one possible mismatch at which we branch,
 * the overall complexity remains O(n).
 * </p>
 */
public class ValidPalindrome2 {

    public boolean validPalindrome(String s) {

        /*
         * ======================================================
         * Initialize the two pointers
         * ======================================================
         *
         * left  -> first character
         * right -> last character
         *
         * We compare characters from both ends and move toward
         * the center.
         */
        int i = 0;
        int j = s.length() - 1;

        /*
         * Continue while the pointers have not crossed.
         */
        while (i < j) {

            /*
             * ==================================================
             * First mismatch
             * ==================================================
             *
             * If the characters do not match, we are allowed
             * to delete at most one character.
             *
             * There are exactly two possibilities:
             *
             * 1. Delete the character at i:
             *
             *        i + 1 ... j
             *
             * 2. Delete the character at j:
             *
             *        i ... j - 1
             *
             * We don't know which character should be deleted,
             * so we check both possibilities.
             *
             * If either substring is a palindrome, the answer
             * is true.
             */
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j)
                        || isPalindrome(s, i, j - 1);
            }

            /*
             * Characters match.
             *
             * No deletion is required for this pair, so move
             * both pointers toward the center.
             */
            i++;
            j--;
        }

        /*
         * If we reached the center without finding a mismatch,
         * the string is already a palindrome.
         */
        return true;
    }

    /**
     * Checks whether the substring between left and right
     * (inclusive) is a palindrome.
     *
     * <p>
     * This is the same basic Two Pointers technique:
     * <p>
     * left  -> moves right
     * right -> moves left
     * <p>
     * Every corresponding pair must match.
     * </p>
     *
     * @param s     input string
     * @param left  starting index of the substring
     * @param right ending index of the substring
     * @return true if the substring is a palindrome
     */
    public boolean isPalindrome(String s, int left, int right) {

        while (left < right) {

            /*
             * If any pair doesn't match, this substring cannot
             * be a palindrome.
             */
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            /*
             * Current pair matched, so continue toward the
             * center.
             */
            left++;
            right--;
        }

        /*
         * Every pair matched, therefore the substring is a
         * palindrome.
         */
        return true;
    }
}