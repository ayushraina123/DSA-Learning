package com.dsa.phase3.stack;

import java.util.Stack;

/**
 * ==========================================================
 * Problem    : LeetCode 20 - Valid Parentheses
 * Difficulty : Easy
 * Pattern    : Stack / Matching Parentheses
 * ==========================================================
 *
 * <p>
 * Idea:
 * We are given a string containing the characters '(', ')',
 * '{', '}', '[' and ']'. The string is valid if every opening
 * bracket is closed by the correct closing bracket and the
 * brackets are closed in the correct order.
 * </p>
 *
 * <p>
 * We use a {@code Stack} to keep track of the unmatched
 * brackets encountered so far.
 * </p>
 *
 * <p>
 * For every character:
 * </p>
 *
 * <ul>
 *     <li>
 *         If the stack is empty, there is no opening bracket
 *         available to match the current character, so we
 *         push the character onto the stack.
 *     </li>
 *
 *     <li>
 *         Otherwise, we compare the current character with
 *         the bracket at the top of the stack.
 *     </li>
 *
 *     <li>
 *         If the top of the stack and the current character
 *         form a valid pair such as {@code ()}, {@code {}},
 *         or {@code []}, we remove the opening bracket using
 *         {@code pop()}.
 *     </li>
 *
 *     <li>
 *         If they do not form a valid pair, we push the
 *         current character onto the stack.
 *     </li>
 * </ul>
 *
 * <p>
 * The stack therefore contains all brackets that have not
 * yet been matched.
 * </p>
 *
 * <p>
 * At the end, the string is valid only if the stack is empty.
 * </p>
 *
 * <p>
 * Example:
 * </p>
 *
 * <p>
 * {@code s = "([{}])"}
 * </p>
 *
 * <p>
 * Processing:
 * </p>
 *
 * <pre>
 * ( → push
 * [ → push
 * { → push
 * } → matches { → pop
 * ] → matches [ → pop
 * ) → matches ( → pop
 *
 * Stack is empty → valid
 * </pre>
 *
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(n)
 * </p>
 *
 * <p>
 * where {@code n} is the length of the input string. In the
 * worst case, all characters can be stored in the stack.
 * </p>
 * <p>
 * ==========================================================
 */
public class ValidParentheses {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            // If the stack is empty, push the current character.
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
                continue;
            }

            // If the current character closes the bracket at the
            // top of the stack, remove the matched opening bracket.
            if ((stack.peek() == '(' && s.charAt(i) == ')') ||
                    (stack.peek() == '{' && s.charAt(i) == '}') ||
                    (stack.peek() == '[' && s.charAt(i) == ']')) {

                stack.pop();

            } else {

                // Current character does not match the top bracket,
                // so keep it for a future match.
                stack.push(s.charAt(i));
            }
        }

        // The string is valid only when every bracket was matched.
        return stack.isEmpty();
    }
}