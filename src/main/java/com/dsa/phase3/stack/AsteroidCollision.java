package com.dsa.phase3.stack;

import java.util.Stack;

public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {

            boolean destroyed = false;

            /*
             * A collision can happen only when:
             *
             * stack top  -> positive
             * current    -> negative
             *
             * Example:
             *
             * 5  ->    <-  3
             */
            while (!stack.isEmpty()
                    && stack.peek() > 0
                    && asteroid < 0) {

                /*
                 * Stack asteroid is smaller.
                 *
                 * Example:
                 *
                 * 5 ->    <- 10
                 *
                 * 5 is destroyed, so keep checking
                 * against the next asteroid in the stack.
                 */
                if (stack.peek() < Math.abs(asteroid)) {
                    stack.pop();
                }

                /*
                 * Both asteroids have the same size.
                 *
                 * Example:
                 *
                 * 5 ->    <- 5
                 *
                 * Both are destroyed.
                 */
                else if (stack.peek() == Math.abs(asteroid)) {
                    stack.pop();
                    destroyed = true;
                    break;
                }

                /*
                 * Stack asteroid is larger.
                 *
                 * Example:
                 *
                 * 10 ->    <- 5
                 *
                 * Current asteroid is destroyed.
                 */
                else {
                    destroyed = true;
                    break;
                }
            }

            /*
             * If the current asteroid survived all collisions,
             * add it to the stack.
             */
            if (!destroyed) {
                stack.push(asteroid);
            }
        }

        int[] ans = new int[stack.size()];

        for (int i = stack.size() - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }

        return ans;
    }
}