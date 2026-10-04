package com.dsa.phase3.stack;

import java.util.Stack;

public class DecodeString {

    public String decodeString(String s) {

        Stack<String> characters = new Stack<>();
        Stack<Integer> numbers = new Stack<>();

        int number = 0;
        String current = "";

        for (char c : s.toCharArray()) {

            /*
             * Build the complete number.
             *
             * Example:
             *
             * 123[
             *
             * 1 -> 1
             * 2 -> 12
             * 3 -> 123
             *
             * ch - '0' converts the digit character
             * into its corresponding integer value.
             */
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            }

            /*
             * '[' means we are entering a new
             * nested expression.
             *
             * Before entering it, save the state
             * of the outer expression:
             *
             * 1. How many times should the inner
             *    expression be repeated?
             * 2. What string had we already built?
             *
             * Example:
             *
             * 3[a2[c]]
             *  ^
             *
             * At '[':
             *
             * numbers    -> [3]
             * characters -> [""]
             *
             * Then start building the inner string
             * from scratch.
             */
            else if (c == '[') {

                numbers.push(number);
                characters.push(current);

                number = 0;
                current = "";
            }

            /*
             * ']' means the current nested expression
             * is complete.
             *
             * Retrieve the state that was saved when
             * we encountered the corresponding '['.
             *
             * Example:
             *
             * 3[a2[c]]
             *
             * At the inner ']':
             *
             * previous = "a"
             * current  = "c"
             * repeat   = 2
             *
             * Therefore:
             *
             * current = "a" + "cc"
             *         = "acc"
             *
             * This reconstructed string then becomes
             * the current string of the outer level.
             */
            else if (c == ']') {

                int repeat = numbers.pop();
                String previous = characters.pop();

                current = previous + current.repeat(repeat);
            }

            /*
             * Normal character.
             *
             * Add it to the string currently being built.
             *
             * Example:
             *
             * 3[abc
             *
             * current = "abc"
             */
            else {
                current += c;
            }
        }

        /*
         * Once the entire string has been processed,
         * current contains the fully decoded string.
         */
        return current;
    }
}