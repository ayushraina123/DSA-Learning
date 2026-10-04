package com.dsa.phase3.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * ==========================================================
 * Problem    : LeetCode 503 - Next Greater Element II
 * Difficulty : Medium
 * Pattern    : Monotonic Stack
 * ==========================================================
 *
 * <p>
 * Idea:
 * </p>
 *
 * <p>
 * For every element in the array, we need to find the first
 * greater element to its right. Since the array is circular,
 * elements at the end of the array can also find their next
 * greater element at the beginning of the array.
 * </p>
 *
 * <p>
 * A left-to-right monotonic stack is used to keep track of
 * elements for which we have not yet found a greater element.
 * </p>
 *
 * <p>
 * Each element stored in the stack contains:
 * </p>
 *
 * <ul>
 *     <li>Value of the element</li>
 *     <li>Original index of the element</li>
 * </ul>
 *
 * <p>
 * When a new element is greater than the element at the top
 * of the stack, the new element becomes the next greater
 * element for that stack element.
 * </p>
 *
 * <p>
 * Since the array is circular, we process a duplicated version
 * of the array:
 * </p>
 *
 * <pre>
 * nums  = [1, 2, 1]
 *
 * nums2 = [1, 2, 1, 1, 2, 1]
 * </pre>
 *
 * <p>
 * The second copy allows elements near the end of the original
 * array to find greater elements near the beginning.
 * </p>
 *
 * <p>
 * The original index is maintained using:
 * </p>
 *
 * <pre>
 * i % nums.length
 * </pre>
 *
 * <p>
 * This maps indices from the duplicated array back to the
 * corresponding index in the original array.
 * </p>
 *
 * <p>
 * Example:
 * </p>
 *
 * <pre>
 * nums = [1, 2, 1]
 *
 * Process:
 *
 * 1 -> stack = [(1,0)]
 *
 * 2 -> 2 > 1
 *      ans[0] = 2
 *      stack = [(2,1)]
 *
 * 1 -> 1 < 2
 *      stack = [(2,1), (1,2)]
 *
 * Second copy:
 *
 * 1 -> 1 < 1
 *      stack = [(2,1), (1,2), (1,0)]
 *
 * 2 -> 2 > 1
 *      ans[0] = 2
 *
 *      2 > 1
 *      ans[2] = 2
 *
 * Final result:
 *
 * [2, -1, 2]
 * </pre>
 *
 * <p>
 * The answer array is initialized with -1. Therefore, elements
 * that never find a greater element automatically retain -1.
 * </p>
 *
 * <p>
 * The important observation is that elements in the stack are
 * unresolved elements. Once a greater element is encountered,
 * the unresolved element is popped and its answer is determined.
 * </p>
 *
 * <p>
 * Because each element is pushed onto the stack at most twice
 * and can be popped at most once for each occurrence, the total
 * stack operations remain linear with respect to the duplicated
 * array.
 * </p>
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
 * where {@code n} is the length of the original array.
 * </p>
 * <p>
 * ==========================================================
 */
public class NextGreaterElementII {

    public int[] nextGreaterElements(int[] nums) {

        /*
         * Initialize every answer to -1.
         *
         * If an element never finds a greater element,
         * its answer remains -1.
         */
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);

        /*
         * The stack stores unresolved elements.
         *
         * Each element contains:
         *
         * value -> value of the element
         * index -> original index of the element
         *
         * The stack maintains elements in decreasing order
         * of value from bottom to top.
         */
        Stack<KeyValuePair> stack = new Stack<>();

        /*
         * Since the array is circular, create a duplicated
         * version of the array.
         *
         * Example:
         *
         * nums  = [1, 2, 1]
         * nums2 = [1, 2, 1, 1, 2, 1]
         *
         * This allows elements near the end of nums to find
         * greater elements near the beginning of nums.
         */
        int[] nums2 = new int[2 * nums.length];

        for (int i = 0; i < nums.length; i++) {
            nums2[i] = nums[i];
            nums2[i + nums.length] = nums[i];
        }

        /*
         * Process the duplicated array from left to right.
         *
         * The current element can resolve all smaller elements
         * currently waiting in the stack.
         */
        for (int i = 0; i < nums2.length; i++) {

            /*
             * Convert the duplicated-array index back to the
             * corresponding original-array index.
             *
             * Example:
             *
             * i = 5
             * nums.length = 3
             *
             * 5 % 3 = 2
             *
             * Therefore, index 5 in nums2 corresponds to
             * index 2 in the original nums array.
             */
            KeyValuePair keyValuePair =
                    new KeyValuePair(nums2[i], i % nums.length);

            /*
             * If the current element is greater than the element
             * at the top of the stack, the current element is the
             * next greater element for that stack element.
             *
             * Continue popping while the current element can
             * resolve elements waiting in the stack.
             */
            while (!stack.isEmpty()
                    && stack.peek().getValue() < nums2[i]) {

                /*
                 * Remove the unresolved element and store the
                 * current element as its next greater element.
                 */
                ans[stack.pop().getIndex()] = nums2[i];
            }

            /*
             * The current element has not yet found its next
             * greater element, so add it to the stack.
             *
             * It may be resolved by a future element.
             */
            stack.push(keyValuePair);
        }

        /*
         * Return the next greater element for every position.
         */
        return ans;
    }

    /**
     * Stores an element's value and its original array index.
     */
    public static class KeyValuePair {

        int value;
        int index;

        public KeyValuePair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int getValue() {
            return this.value;
        }

        public int getIndex() {
            return this.index;
        }
    }
}