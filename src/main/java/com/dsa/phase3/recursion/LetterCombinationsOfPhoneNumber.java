package com.dsa.phase3.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode Problem 17: Letter Combinations of a Phone Number
 *
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * </p>
 *
 * <p>
 * Difficulty: Medium
 * </p>
 *
 * <p>
 * Topics: Strings, Recursion, Backtracking
 * </p>
 *
 * <p>
 * Problem Statement:
 * Given a string containing digits from 2 to 9, return all possible
 * letter combinations that the digits could represent.
 * </p>
 *
 * <p>
 * Each digit maps to a set of letters, just like the keys on a
 * traditional phone keypad.
 * </p>
 *
 * <p>
 * Important considerations:
 * 1. Each combination must contain one character for every input digit.
 * 2. Every possible combination must be generated.
 * 3. If the input is null or empty, return an empty list.
 * </p>
 *
 * <p>
 * Approach: Recursion
 * </p>
 *
 * <p>
 * First, map each digit to its corresponding letters.
 * Then, create a list of character lists based on the input digits.
 * </p>
 *
 * <p>
 * Use a recursive helper method to build the combinations.
 * The helper method processes one list at a time and selects each
 * character from the current list.
 * </p>
 *
 * <p>
 * For every selected character, recursively process the next list
 * and append the selected character to the current string.
 * </p>
 *
 * <p>
 * Base Case:
 * When the current index reaches the number of lists, we have
 * selected one character from every list. The current string is
 * therefore a complete combination, so add it to the result.
 * </p>
 *
 * <p>
 * Example:
 * Input: digits = "23"
 * </p>
 *
 * <p>
 * Digit mappings:
 * 2 -> [a, b, c]
 * 3 -> [d, e, f]
 * </p>
 *
 * <p>
 * Expected output:
 * ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 * </p>
 *
 * <p>
 * Complexity Analysis:
 * </p>
 *
 * <p>
 * Let n be the number of input digits.
 * Each digit has at most 4 possible letters.
 * </p>
 *
 * <p>
 * Time Complexity: O(n * 4^n)
 * There can be up to 4^n combinations, and creating each combination
 * takes O(n) time.
 * </p>
 *
 * <p>
 * Auxiliary Space Complexity: O(n)
 * The recursion depth is n, and the current strings have a maximum
 * length of n. The lists created from the digit mappings also require
 * O(n) space for a fixed-size keypad mapping.
 * </p>
 *
 * <p>
 * Output Space Complexity: O(n * 4^n)
 * In the worst case, the result contains 4^n strings, each of length n.
 * </p>
 */
public class LetterCombinationsOfPhoneNumber {

    /*
     * Stores the letters corresponding to each phone keypad digit.
     *
     * Each digit has its own list so that modifying one list
     * does not affect the mappings of other digits.
     */
    private static final Map<Character, List<Character>> letterCombinations =
            new HashMap<>();

    /*
     * Initialize the phone keypad mapping.
     *
     * A new list is created for every digit because the map stores
     * references to the lists rather than copies of the lists.
     */ {
        List<Character> list = new ArrayList<>();
        list.add('a');
        list.add('b');
        list.add('c');

        letterCombinations.put('2', list);

        list = new ArrayList<>();
        list.add('d');
        list.add('e');
        list.add('f');

        letterCombinations.put('3', list);

        list = new ArrayList<>();
        list.add('g');
        list.add('h');
        list.add('i');

        letterCombinations.put('4', list);

        list = new ArrayList<>();
        list.add('j');
        list.add('k');
        list.add('l');

        letterCombinations.put('5', list);

        list = new ArrayList<>();
        list.add('m');
        list.add('n');
        list.add('o');

        letterCombinations.put('6', list);

        list = new ArrayList<>();
        list.add('p');
        list.add('q');
        list.add('r');
        list.add('s');

        letterCombinations.put('7', list);

        list = new ArrayList<>();
        list.add('t');
        list.add('u');
        list.add('v');

        letterCombinations.put('8', list);

        list = new ArrayList<>();
        list.add('w');
        list.add('x');
        list.add('y');
        list.add('z');

        letterCombinations.put('9', list);
    }

    /**
     * Generates all possible letter combinations for the given digits.
     *
     * @param digits the input string containing digits from 2 to 9
     * @return a list containing all possible letter combinations
     */
    public List<String> letterCombinations(String digits) {

        /*
         * If the input is null or empty, there are no combinations
         * to generate, so return an empty list.
         */
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        /*
         * Convert the input digits into a list of character lists.
         *
         * For example, if digits = "23":
         *
         * lists = [
         *     [a, b, c],
         *     [d, e, f]
         * ]
         */
        List<List<Character>> lists = new ArrayList<>();

        for (Character c : digits.toCharArray()) {
            lists.add(letterCombinations.get(c));
        }

        /*
         * Stores the completed combinations.
         */
        List<String> result = new ArrayList<>();

        /*
         * Start the recursive process.
         *
         * index = 0:
         * Start processing the first list.
         *
         * current = "":
         * No characters have been selected yet.
         */
        generate(lists, 0, "", result);

        return result;
    }

    /**
     * Recursively generates combinations by selecting one character
     * from each list.
     *
     * @param lists   the character lists corresponding to the digits
     * @param index   the index of the list currently being processed
     * @param current the combination built so far
     * @param result  the list containing completed combinations
     */
    private void generate(
            List<List<Character>> lists,
            int index,
            String current,
            List<String> result) {

        /*
         * Base Case:
         *
         * If index reaches lists.size(), every list has been processed.
         * We have selected one character from every input digit.
         *
         * Therefore, current is a complete combination.
         * Add it to the result and return to the previous recursive call.
         */
        if (index == lists.size()) {
            result.add(current);
            return;
        }

        /*
         * Get the list corresponding to the current digit.
         *
         * For digits = "23":
         *
         * index = 0 -> [a, b, c]
         * index = 1 -> [d, e, f]
         */
        List<Character> list = lists.get(index);

        /*
         * Try every character in the current list.
         *
         * For each character:
         * 1. Append the character to the current combination.
         * 2. Move to the next list by incrementing index.
         * 3. Continue generating combinations recursively.
         *
         * Once the recursive call returns, the loop continues
         * with the next character in the current list.
         */
        for (Character c : list) {
            generate(lists, index + 1, current + c, result);
        }
    }
}
