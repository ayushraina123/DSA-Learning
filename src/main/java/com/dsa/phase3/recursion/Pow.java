
package com.dsa.phase3.recursion;

import java.util.Optional;

/**
 * LeetCode Problem 50: Pow(x, n)
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/powx-n/
 * <p>
 * Difficulty: Medium
 * Topics: Math, Recursion, Binary Exponentiation
 * <p>
 * Problem Statement:
 * Implement pow(x, n), which calculates x raised to the power n (x^n).
 * <p>
 * Given a double x and an integer n, return x^n.
 * <p>
 * Important considerations:
 * 1. The exponent n can be positive, negative, or zero.
 * 2. The exponent can be Integer.MIN_VALUE (-2147483648).
 * 3. The solution must be efficient for very large exponents.
 * <p>
 * Approach: Binary Exponentiation (Iterative)
 * <p>
 * Instead of multiplying x by itself n times, we repeatedly square
 * the base and divide the exponent by 2.
 * <p>
 * Mathematical observations:
 * <p>
 * 1. If n is even:
 * x^n = (x * x)^(n / 2)
 * <p>
 * Example:
 * x^8 = (x^2)^4 = (x^4)^2
 * <p>
 * 2. If n is odd:
 * x^n = x * x^(n - 1)
 * <p>
 * We multiply the current result by x and continue processing
 * the remaining exponent.
 * <p>
 * 3. If n is negative:
 * x^(-n) = 1 / x^n
 * <p>
 * We calculate the positive power first and take its reciprocal
 * at the end.
 * <p>
 * 4. If n is zero:
 * x^0 = 1, so the result remains 1.
 * <p>
 * Why do we use long for the exponent?
 * <p>
 * Integer.MIN_VALUE is -2147483648.
 * Its positive counterpart, 2147483648, cannot fit inside an int.
 * <p>
 * Therefore, we first convert n to long and only then negate it.
 * This prevents integer overflow.
 * <p>
 * Example:
 * <p>
 * Input:
 * x = 2.0, n = 10
 * <p>
 * Expected output:
 * 1024.0
 * <p>
 * Calculation:
 * 2^10 = 1024
 * <p>
 * Complexity Analysis:
 * <p>
 * Time Complexity: O(log |n|)
 * The exponent is divided by 2 during every iteration.
 * <p>
 * Space Complexity: O(1)
 * Only a constant number of variables are used.
 */
public class Pow {

    /**
     * Calculates x raised to the power n using binary exponentiation.
     *
     * @param x the base value
     * @param n the integer exponent
     * @return the value of x^n
     */
    public double myPow(double x, int n) {

        /*
         * Convert n to long before performing any negation.
         *
         * This is important when n is Integer.MIN_VALUE,
         * because its positive counterpart does not fit in an int.
         */
        long power = n;

        /*
         * Remember whether the original exponent was negative.
         *
         * We will calculate the positive power first and take
         * its reciprocal at the end if necessary.
         */
        boolean isNegative = power < 0;

        if (power < 0) {
            power = -power;
        }

        /*
         * The result starts at 1 because multiplying by 1
         * does not change the value.
         *
         * It also correctly handles an exponent of zero.
         */
        double res = 1.0;

        /*
         * Binary exponentiation:
         *
         * Each iteration processes one part of the exponent.
         * We repeatedly square x and divide power by 2.
         */
        while (power > 0) {

            /*
             * If the exponent is odd, the current base
             * contributes to the final result.
             */
            if (power % 2 == 1) {
                res *= x;
            }

            /*
             * Square the base to prepare for the next exponent.
             *
             * For example:
             * x^8 = (x^2)^4
             */
            x *= x;

            /*
             * Integer division by 2 reduces the exponent.
             */
            power /= 2;
        }

        /*
         * If the original exponent was negative,
         * return the reciprocal of the calculated positive power.
         *
         * Otherwise, return the result directly.
         */
        return isNegative ? 1.0 / res : res;
    }
}
