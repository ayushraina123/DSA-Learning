package com.dsa.phase3.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ==========================================================
 * Problem    : LeetCode 496 - Next Greater Element I
 * Difficulty : Easy
 * Pattern    : Monotonic Stack + HashMap
 * ==========================================================
 *
 * <p>
 * Idea:
 * </p>
 *
 * <p>
 * For every element in nums1, we need to find the first element
 * greater than it that appears to its right in nums2.
 * </p>
 *
 * <p>
 * Instead of searching nums2 separately for every element in nums1,
 * we first calculate the next greater element for every element in
 * nums2.
 * </p>
 *
 * <p>
 * A monotonic decreasing stack is used to efficiently find the
 * next greater element while processing nums2 from right to left.
 * </p>
 *
 * <p>
 * The stack stores elements that are still potential candidates
 * for being the next greater element of an element to their left.
 * </p>
 *
 * <p>
 * For every current element:
 * </p>
 *
 * <ul>
 *     <li>
 *         Remove elements from the stack that are smaller than the
 *         current element because they can never be the next greater
 *         element of the current element.
 *     </li>
 *
 *     <li>
 *         If the stack is empty, there is no greater element to the
 *         right, so the answer is -1.
 *     </li>
 *
 *     <li>
 *         Otherwise, the element at the top of the stack is the
 *         next greater element.
 *     </li>
 *
 *     <li>
 *         Push the current element onto the stack because it may be
 *         the next greater element for an element further to its left.
 *     </li>
 * </ul>
 *
 * <p>
 * A HashMap is used to store the calculated result:
 * </p>
 *
 * <pre>
 * element -> next greater element
 *
 * Example:
 *
 * 1 -> 3
 * 3 -> 4
 * 4 -> -1
 * 2 -> -1
 * </pre>
 *
 * <p>
 * Once the map has been built, every element in nums1 can be answered
 * with a constant-time HashMap lookup.
 * </p>
 *
 * <p>
 * Example:
 * </p>
 *
 * <pre>
 * nums1 = [1, 2]
 * nums2 = [1, 3, 4, 2]
 *
 * Process nums2 from right to left:
 *
 * 2 -> stack = []
 *      map = {2=-1}
 *      push 2
 *
 * 4 -> 4 > 2
 *      pop 2 because 2 cannot be greater than 4
 *      map = {2=-1, 4=-1}
 *      push 4
 *
 * 3 -> 3 < 4
 *      map = {2=-1, 4=-1, 3=4}
 *      push 3
 *
 * 1 -> 1 < 3
 *      map = {2=-1, 4=-1, 3=4, 1=3}
 *      push 1
 *
 * Final map:
 *
 * 1 -> 3
 * 2 -> -1
 * 3 -> 4
 * 4 -> -1
 *
 * nums1 = [1, 2]
 *
 * ans = [3, -1]
 * </pre>
 *
 * <p>
 * The important observation is that every element is pushed onto
 * the stack once and can be popped at most once.
 * Therefore, although there is a while loop, the total work done
 * by the stack operations is O(n).
 * </p>
 *
 * <p>
 * The HashMap allows us to avoid searching nums2 again for every
 * element of nums1.
 * </p>
 *
 * <p>
 * Time Complexity: O(n + m)
 * </p>
 *
 * <p>
 * Space Complexity: O(n)
 * </p>
 *
 * <p>
 * where {@code n} is the length of nums2 and {@code m} is the
 * length of nums1.
 * </p>
 * <p>
 * ==========================================================
 */
public class NextGreaterElement {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        /*
         * The answer array contains one result for every element
         * in nums1.
         */
        int[] ans = new int[nums1.length];

        /*
         * The stack is used as a monotonic decreasing stack.
         *
         * It stores elements from nums2 that are still potential
         * candidates for being the next greater element of an
         * element to their left.
         */
        Stack<Integer> stack = new Stack<>();

        /*
         * Stores the already calculated next greater element
         * for every number in nums2.
         *
         * key   -> current number
         * value -> next greater element
         */
        Map<Integer, Integer> map = new HashMap<>();

        /*
         * Process nums2 from right to left.
         *
         * Processing from right to left allows the stack to contain
         * elements that appear to the right of the current element.
         */
        for (int i = nums2.length - 1; i >= 0; i--) {

            /*
             * Remove elements that are smaller than the current
             * element.
             *
             * These elements can never be the next greater element
             * for the current number because the current number
             * itself is already greater than them.
             *
             * They are therefore no longer useful candidates.
             */
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                stack.pop();
            }

            /*
             * If the stack is empty, there is no greater element
             * to the right of the current number.
             */
            if (stack.isEmpty()) {
                map.put(nums2[i], -1);
            }

            /*
             * Otherwise, the element at the top of the stack is
             * the next greater element for the current number.
             */
            else {
                map.put(nums2[i], stack.peek());
            }

            /*
             * The current number may be the next greater element
             * for an element that appears further to its left.
             *
             * Therefore, push it onto the stack.
             */
            stack.push(nums2[i]);
        }

        /*
         * nums2 has already been processed completely.
         *
         * We can now find the answer for every nums1 element
         * using a constant-time HashMap lookup.
         */
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}
