package com.dsa.phase2.slidingWindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * ==========================================================
 * Problem    : LeetCode 239 - Sliding Window Maximum
 * Difficulty : Hard
 * Pattern    : Sliding Window - Fixed Size + Monotonic Deque
 * ==========================================================
 *
 * <p>
 * Given an integer array nums and an integer k, find the
 * maximum value in every contiguous subarray of size k.
 * </p>
 *
 * <p>
 * Example:
 * <p>
 * nums = [1, 3, -1, -3, 5, 3, 6, 7]
 * k = 3
 *
 * <p>
 * The windows are:
 * <p>
 * [1, 3, -1] -> 3
 * [3, -1, -3] -> 3
 * [-1, -3, 5] -> 5
 * [-3, 5, 3] -> 5
 * [5, 3, 6] -> 6
 * [3, 6, 7] -> 7
 *
 * <p>
 * Answer = [3, 3, 5, 5, 6, 7]
 * </p>
 *
 * <p>
 * ==========================================================
 * Approach: Fixed-Size Sliding Window + Monotonic Deque
 * ==========================================================
 *
 * <p>
 * Every window we need to consider has exactly k elements.
 * Therefore, this is a Fixed-Size Sliding Window problem.
 * </p>
 *
 * <p>
 * The challenge is efficiently finding the maximum value
 * inside every window.
 * </p>
 *
 * <p>
 * A simple approach would be to scan all k elements for
 * every window. However, that would take O(n * k) time.
 * </p>
 *
 * <p>
 * Instead, we maintain a Deque that stores indices of
 * elements that are still possible candidates for being
 * the maximum of the current or future windows.
 * </p>
 *
 * <p>
 * The deque is maintained in decreasing order of the
 * values represented by its indices.
 * </p>
 *
 * <p>
 * Therefore:
 * </p>
 *
 * <p>
 * - The FRONT of the deque always contains the index of
 * the maximum value in the current window.
 *
 * <p>
 * - Elements smaller than or equal to a newly added element
 * are removed from the BACK because they can never become
 * the maximum while the new element remains in the window.
 * </p>
 *
 * <p>
 * ==========================================================
 * Why Does the Deque Store Indices?
 * ==========================================================
 *
 * <p>
 * The deque stores indices rather than the actual values.
 * </p>
 *
 * <p>
 * For example:
 * </p>
 *
 * <p>
 * nums = [1, 3, -1]
 *
 * <p>
 * Instead of:
 *
 * <p>
 * deque = [3, -1]
 *
 * <p>
 * we store:
 *
 * <p>
 * deque = [1, 2]
 *
 * <p>
 * where:
 *
 * <p>
 * nums[1] = 3
 * nums[2] = -1
 *
 * <p>
 * Storing indices is important because we need to know
 * whether an element has left the sliding window.
 * </p>
 *
 * <p>
 * For example, if left = 2 and the deque front contains
 * index 1, then index 1 is outside the current window and
 * must be removed.
 * </p>
 *
 * <p>
 * Therefore, storing indices allows us to determine whether
 * an element is still inside the current window.
 * </p>
 *
 * <p>
 * ==========================================================
 * Monotonic Deque
 * ==========================================================
 *
 * <p>
 * The deque maintains indices whose corresponding values
 * are in decreasing order.
 * </p>
 *
 * <p>
 * For example:
 *
 * <p>
 * deque indices:
 * <p>
 * [1, 2, 4]
 *
 * <p>
 * Corresponding values:
 *
 * <p>
 * nums[1] = 5
 * nums[2] = 3
 * nums[4] = 1
 *
 * <p>
 * Therefore:
 *
 * <p>
 * 5 > 3 > 1
 *
 * <p>
 * This is called a Monotonic Deque because the values
 * represented by the indices are maintained in decreasing
 * order.
 * </p>
 *
 * <p>
 * Because of this ordering, the largest value is always
 * represented by the index at the FRONT of the deque.
 * </p>
 *
 * <p>
 * ==========================================================
 * Why Remove Elements From the Back?
 * ==========================================================
 *
 * <p>
 * Suppose the deque currently represents:
 *
 * <p>
 * [3, 1]
 *
 * <p>
 * and a new value 2 arrives.
 * </p>
 *
 * <p>
 * The value 1 can never become the maximum while 2 is
 * inside the window.
 * </p>
 *
 * <p>
 * Therefore, we remove 1 from the BACK:
 *
 * <p>
 * [3, 1]
 * ^
 * remove
 *
 * <p>
 * Then add 2:
 *
 * <p>
 * [3, 2]
 *
 * <p>
 * The values are still maintained in decreasing order.
 * </p>
 *
 * <p>
 * The same logic applies to every smaller or equal element
 * at the back of the deque.
 * </p>
 *
 * <p>
 * ==========================================================
 * Why Can Smaller Elements Be Removed?
 * ==========================================================
 *
 * <p>
 * Suppose we have:
 *
 * <p>
 * [3, 1, 2]
 *
 * <p>
 * When 2 arrives, the 1 can be removed.
 * </p>
 *
 * <p>
 * Why?
 * </p>
 *
 * <p>
 * If both 1 and 2 are present in a future window, 2 will
 * always be larger than 1.
 * Therefore, 1 can never be the maximum of that window.
 * </p>
 *
 * <p>
 * Also, 2 entered the array later than 1, meaning 2 will
 * remain inside the sliding window longer than 1.
 * </p>
 *
 * <p>
 * Therefore, once a larger element appears to the right,
 * the smaller element becomes useless as a maximum candidate.
 * </p>
 *
 * <p>
 * ==========================================================
 * Window State
 * ==========================================================
 *
 * <p>
 * The important state maintained by the algorithm is:
 * </p>
 *
 * <p>
 * left
 * </p>
 *
 * <p>
 * Beginning of the current window.
 * </p>
 *
 * <p>
 * right
 * </p>
 *
 * <p>
 * End of the current window.
 * </p>
 *
 * <p>
 * deque
 * </p>
 *
 * <p>
 * Stores indices of possible maximum elements.
 * The values represented by those indices are maintained
 * in decreasing order.
 * </p>
 *
 * <p>
 * The current window size is:
 * </p>
 *
 * <p>
 * right - left + 1
 *
 * <p>
 * We process the window when:
 * </p>
 *
 * <p>
 * right - left + 1 == k
 * </p>
 *
 * <p>
 * ==========================================================
 * How the Window Slides
 * ==========================================================
 *
 * <p>
 * Suppose:
 *
 * <p>
 * nums = [1, 3, -1]
 * k = 3
 *
 * <p>
 * The deque eventually becomes:
 *
 * <p>
 * [1, 2]
 *
 * <p>
 * because:
 *
 * <p>
 * nums[1] = 3
 * nums[2] = -1
 *
 * <p>
 * The front contains index 1, so:
 *
 * <p>
 * nums[deque.peekFirst()] = nums[1] = 3
 *
 * <p>
 * Therefore, 3 is the maximum of the current window.
 * </p>
 *
 * <p>
 * After processing the window, left moves forward.
 * </p>
 *
 * <p>
 * If the index at the front of the deque is equal to left,
 * that element is leaving the window and must be removed
 * from the front of the deque.
 * </p>
 *
 * <p>
 * ==========================================================
 * Algorithm
 * ==========================================================
 *
 * <p>
 * 1. Initialize an empty deque.
 * </p>
 *
 * <p>
 * 2. Initialize left and right to 0.
 * </p>
 *
 * <p>
 * 3. Move right through the array.
 * </p>
 *
 * <p>
 * 4. Before adding right to the deque:
 * </p>
 *
 * <p>
 * - Remove indices from the BACK while their corresponding
 * values are smaller than or equal to nums[right].
 *
 * <p>
 * These elements can never become the maximum while
 * nums[right] remains in the window.
 * </p>
 *
 * <p>
 * 5. Add right to the BACK of the deque.
 * </p>
 *
 * <p>
 * 6. Once the window reaches size k:
 * </p>
 *
 * <p>
 * - The FRONT of the deque contains the index of the
 * maximum element.
 *
 * <p>
 * - Add nums[deque.peekFirst()] to the result.
 *
 * <p>
 * - If deque.peekFirst() == left, remove it because the
 * maximum element is leaving the window.
 *
 * <p>
 * - Move left forward.
 *
 * <p>
 * 7. Continue until right reaches the end of the array.
 * </p>
 *
 * <p>
 * 8. Return the result.
 * </p>
 *
 * <p>
 * ==========================================================
 * Complexity
 * ==========================================================
 *
 * <p>
 * Time : O(n)
 * </p>
 *
 * <p>
 * Each index is added to the deque once and removed from
 * the deque at most once.
 * </p>
 *
 * <p>
 * Therefore, although there are while loops inside the main
 * loop, the total number of deque operations is O(n).
 * </p>
 *
 * <p>
 * Space : O(k)
 * </p>
 *
 * <p>
 * The deque can contain at most k indices from the current
 * window.
 * </p>
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {

        /*
         * ======================================================
         * Store the maximum value from each sliding window.
         * ======================================================
         */
        List<Integer> list = new ArrayList<>();

        /*
         * ======================================================
         * Monotonic Deque
         * ======================================================
         *
         * The deque stores INDICES, not values.
         *
         * The values represented by these indices are kept
         * in decreasing order.
         *
         * Therefore:
         *
         *     deque.peekFirst()
         *
         * always gives us the index of the maximum element
         * in the current window.
         */
        Deque<Integer> deque = new ArrayDeque<>();

        /*
         * left marks the beginning of the current window.
         */
        int left = 0;

        /*
         * right expands the window one element at a time.
         */
        int right = 0;

        /*
         * Expand the sliding window until right reaches
         * the end of the array.
         */
        while (right < nums.length) {

            /*
             * ==================================================
             * Remove smaller elements from the BACK.
             * ==================================================
             *
             * The deque maintains decreasing values.
             *
             * If the value at the back is smaller than or
             * equal to nums[right], that value can never become
             * the maximum while nums[right] is inside the window.
             *
             * Therefore, remove it.
             */
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]) {

                deque.pollLast();
            }

            /*
             * Add the current index to the BACK.
             *
             * We store the index rather than nums[right]
             * because we need to know when an element leaves
             * the sliding window.
             */
            deque.addLast(right);

            /*
             * ==================================================
             * Check whether the window has reached size k.
             * ==================================================
             *
             * Window size:
             *
             *     right - left + 1
             *
             * Once it becomes k, we have a complete window
             * whose maximum can be added to the result.
             */
            if (right - left + 1 == k) {

                /*
                 * ==================================================
                 * Get the maximum of the current window.
                 * ==================================================
                 *
                 * The deque is maintained in decreasing order
                 * of values.
                 *
                 * Therefore, the FRONT always contains the
                 * index of the largest value in the window.
                 */
                list.add(nums[deque.peekFirst()]);

                /*
                 * ==================================================
                 * Remove an element leaving the window.
                 * ==================================================
                 *
                 * left is about to move forward.
                 *
                 * If the index at the front of the deque is
                 * equal to left, that element is leaving the
                 * current window.
                 *
                 * Therefore, remove it from the deque.
                 */
                if (deque.peekFirst() == left) {
                    deque.pollFirst();
                }

                /*
                 * Move the beginning of the window forward.
                 */
                left++;
            }

            /*
             * Expand the window by moving right forward.
             */
            right++;
        }

        /*
         * Convert the List<Integer> into an int[] and return
         * the maximum value from every sliding window.
         */
        return list.stream().mapToInt(i -> i).toArray();
    }
}