package com.dsa.phase3.stack;

import java.util.Stack;

/**
 * ==========================================================
 * Problem    : LeetCode 150 - Evaluate Reverse Polish Notation
 * Difficulty : Medium
 * Pattern    : Stack
 * ==========================================================
 *
 * <p>
 * Idea:
 * </p>
 *
 * <p>
 * Reverse Polish Notation (RPN) places the operator after its
 * operands. Therefore, whenever an operator is encountered,
 * the two most recently available values in the stack are the
 * operands for that operation.
 * </p>
 *
 * <p>
 * The stack stores both:
 * </p>
 *
 * <ul>
 *     <li>Original operands</li>
 *     <li>Intermediate results of previous operations</li>
 * </ul>
 *
 * <p>
 * For every token:
 * </p>
 *
 * <ul>
 *     <li>
 *         If the token is an operand, push it onto the stack.
 *     </li>
 *     <li>
 *         If the token is an operator, pop the two operands,
 *         perform the operation, and push the result back.
 *     </li>
 * </ul>
 *
 * <p>
 * The first value popped is the right operand and the second
 * value popped is the left operand.
 * </p>
 *
 * <pre>
 * Example:
 *
 * tokens = ["4", "13", "5", "/", "+"]
 *
 * 4   -> [4]
 * 13  -> [4, 13]
 * 5   -> [4, 13, 5]
 *
 * /   -> 13 / 5
 *        [4, 2]
 *
 * +   -> 4 + 2
 *        [6]
 *
 * Final result = 6
 * </pre>
 *
 * <p>
 * Operand ordering is important for subtraction and division:
 * </p>
 *
 * <pre>
 * operand1 = first value popped  = right operand
 * operand2 = second value popped = left operand
 *
 * operand2 - operand1
 * operand2 / operand1
 * </pre>
 *
 * <p>
 * Time Complexity: O(n)
 * </p>
 *
 * <p>
 * Space Complexity: O(n)
 * </p>
 *
 * <p>
 * where {@code n} is the number of tokens.
 * </p>
 * <p>
 * ==========================================================
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {

        /*
         * The stack stores operands as well as intermediate
         * results produced while evaluating the expression.
         */
        Stack<String> stack = new Stack<>();

        int result;
        int operand1 = 0;
        int operand2 = 0;

        for (String token : tokens) {

            /*
             * Check whether the current token is an operator.
             */
            if (token.equals("+")
                    || token.equals("-")
                    || token.equals("*")
                    || token.equals("/")) {

                /*
                 * The first value popped is the right operand.
                 *
                 * Example:
                 *
                 * 4 13 5 /
                 *
                 * operand1 = 5
                 * operand2 = 13
                 */
                if (!stack.isEmpty()) {
                    operand1 = Integer.parseInt(stack.pop());
                }

                /*
                 * The second value popped is the left operand.
                 */
                if (!stack.isEmpty()) {
                    operand2 = Integer.parseInt(stack.pop());
                }

                /*
                 * Perform the operation and push the intermediate
                 * result back onto the stack.
                 */
                switch (token) {

                    case "/":
                        result = operand2 / operand1;
                        stack.push(String.valueOf(result));
                        break;

                    case "*":
                        result = operand2 * operand1;
                        stack.push(String.valueOf(result));
                        break;

                    case "+":
                        result = operand1 + operand2;
                        stack.push(String.valueOf(result));
                        break;

                    case "-":
                        result = operand2 - operand1;
                        stack.push(String.valueOf(result));
                        break;
                }

            } else {

                /*
                 * Current token is an operand.
                 * Push it onto the stack until an operator
                 * requires it.
                 */
                stack.push(token);
            }
        }

        /*
         * After processing all tokens, only the final result
         * remains on the stack.
         */
        return Integer.parseInt(stack.pop());
    }
}