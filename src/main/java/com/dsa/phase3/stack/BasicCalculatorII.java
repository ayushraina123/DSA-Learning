package com.dsa.phase3.stack;

import java.util.Stack;

public class BasicCalculatorII {

    public int calculate(String s) {

        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int number = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');

            } else if (c == '+' || c == '-' || c == '*' || c == '/') {

                numbers.push(number);
                number = 0;

                /*
                 * Resolve the previous operator if:
                 *
                 * 1. It has higher precedence (* or /)
                 * 2. It has equal precedence (+ or -)
                 *
                 * This preserves:
                 *
                 * 10 - 5 - 2
                 * -> (10 - 5) - 2
                 *
                 * 10 - 2 * 3
                 * -> 10 - (2 * 3)
                 */
                while (!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/' || (c == '+' || c == '-'))) {

                    char operator = operators.pop();

                    int operand2 = numbers.pop();
                    int operand1 = numbers.pop();

                    if (operator == '+') {
                        numbers.push(operand1 + operand2);
                    } else if (operator == '-') {
                        numbers.push(operand1 - operand2);
                    } else if (operator == '*') {
                        numbers.push(operand1 * operand2);
                    } else {
                        numbers.push(operand1 / operand2);
                    }
                }

                operators.push(c);
            }
        }

        // Push the final number.
        numbers.push(number);

        // Evaluate remaining operators.
        while (!operators.isEmpty()) {

            char operator = operators.pop();

            int operand2 = numbers.pop();
            int operand1 = numbers.pop();

            if (operator == '+') {
                numbers.push(operand1 + operand2);
            } else if (operator == '-') {
                numbers.push(operand1 - operand2);
            } else if (operator == '*') {
                numbers.push(operand1 * operand2);
            } else {
                numbers.push(operand1 / operand2);
            }
        }

        return numbers.pop();
    }
}