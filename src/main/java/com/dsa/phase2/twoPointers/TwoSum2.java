package com.dsa.phase2.twoPointers;

/**
 * ==========================================================
 * Problem    : LeetCode 167 - Two Sum II - Input Array Is Sorted
 * Difficulty : Medium
 * Pattern    : Two Pointers
 * ==========================================================
 *
 * <p>
 * Given a 1-indexed array of integers {@code numbers} that is
 * already sorted in non-decreasing order, find two numbers such
 * that they add up to {@code target}.
 * </p>
 *
 * <p>
 * Return the 1-based indices of the two numbers.
 * </p>
 *
 * <p>
 * The problem guarantees that exactly one solution exists and
 * the same element cannot be used twice.
 * </p>
 *
 * <p>
 * Example:
 *
 * <pre>
 * numbers = [2, 7, 11, 15]
 * target  = 9
 *
 *              i           j
 *              ↓           ↓
 * [2, 7, 11, 15]
 *
 * 2 + 15 = 17 > 9
 * → Sum is too large, so move j left.
 *
 *          i       j
 *          ↓       ↓
 * [2, 7, 11, 15]
 *
 * 2 + 11 = 13 > 9
 * → Sum is still too large, so move j left.
 *
 *          i   j
 *          ↓   ↓
 * [2, 7, 11, 15]
 *
 * 2 + 7 = 9
 * → Found the answer.
 *
 * Return [1, 2]
 * </pre>
 *
 * <p>
 * Key Observation:
 * <p>
 * Since the array is sorted, we can use two pointers:
 *
 * <ul>
 *     <li>{@code i} starts at the beginning of the array.</li>
 *     <li>{@code j} starts at the end of the array.</li>
 * </ul>
 *
 * <p>
 * At every step, calculate:
 *
 * <pre>
 * numbers[i] + numbers[j]
 * </pre>
 *
 * <p>
 * There are three possibilities:
 *
 * <ol>
 *     <li>
 *         If the sum equals {@code target}, we found the answer.
 *     </li>
 *     <li>
 *         If the sum is smaller than {@code target}, we need a
 *         larger value. Since the array is sorted, move
 *         {@code i} to the right.
 *     </li>
 *     <li>
 *         If the sum is greater than {@code target}, we need a
 *         smaller value. Since the array is sorted, move
 *         {@code j} to the left.
 *     </li>
 * </ol>
 *
 * <p>
 * Why does moving the pointers work?
 * <p>
 * Suppose:
 *
 * <pre>
 * numbers[i] + numbers[j] < target
 * </pre>
 * <p>
 * Since {@code numbers[j]} is already the largest value available
 * on the right, keeping {@code i} unchanged while moving
 * {@code j} left would only make the sum smaller.
 * <p>
 * Therefore, the only useful move is:
 *
 * <pre>
 * i++
 * </pre>
 *
 * <p>
 * Similarly, if:
 *
 * <pre>
 * numbers[i] + numbers[j] > target
 * </pre>
 * <p>
 * moving {@code i} right would only make the sum larger.
 * Therefore, the only useful move is:
 *
 * <pre>
 * j--
 * </pre>
 *
 * <p>
 * This allows us to eliminate impossible pairs without checking
 * every possible combination.
 *
 * <p>
 * Complexity:
 *
 * <pre>
 * Time  : O(n)
 * Space : O(1)
 * </pre>
 *
 * <p>
 * Each pointer moves only in one direction and each element is
 * visited at most once by the two-pointer scan.
 * No additional data structure is required.
 * </p>
 */
public class TwoSum2 {

    /**
     * Finds two numbers in the sorted array whose sum equals
     * the given target.
     *
     * <p>
     * Uses the Two Pointers technique:
     *
     * <pre>
     * i -> starts from the beginning
     * j -> starts from the end
     * </pre>
     *
     * <p>
     * Since the array is sorted:
     *
     * <ul>
     *     <li>
     *         Sum too small → move {@code i} right to increase
     *         the sum.
     *     </li>
     *     <li>
     *         Sum too large → move {@code j} left to decrease
     *         the sum.
     *     </li>
     *     <li>
     *         Sum equals target → answer found.
     *     </li>
     * </ul>
     *
     * @param numbers sorted array of integers
     * @param target  target sum
     * @return 1-based indices of the two numbers whose sum equals
     * {@code target}
     */
    public int[] twoSum(int[] numbers, int target) {

        /*
         * Result array stores the answer.
         *
         * The problem expects 1-based indices, so when we find
         * the answer we will store i + 1 and j + 1.
         */
        int[] result = new int[2];

        /*
         * ======================================================
         * Initialize the two pointers
         * ======================================================
         *
         * i -> smallest available element
         * j -> largest available element
         *
         * Starting from both ends allows us to use the sorted
         * property of the array.
         */
        int i = 0;
        int j = numbers.length - 1;

        /*
         * Continue until the pointers meet.
         *
         * i < j ensures that we never use the same element twice.
         */
        while (i < j) {

            /*
             * ==================================================
             * Target found
             * ==================================================
             *
             * The current pair adds up exactly to the target.
             *
             * The problem requires 1-based indices, while Java
             * arrays use 0-based indices.
             *
             * Therefore:
             *
             * array index i -> problem index i + 1
             * array index j -> problem index j + 1
             */
            if (numbers[i] + numbers[j] == target) {
                result[0] = i + 1;
                result[1] = j + 1;

                /*
                 * Exactly one solution is guaranteed, so there
                 * is no need to continue searching.
                 */
                break;

                /*
                 * ==================================================
                 * Sum is too small
                 * ==================================================
                 *
                 * numbers[i] + numbers[j] < target
                 *
                 * Because the array is sorted, moving j to the left
                 * would make the sum even smaller.
                 *
                 * Therefore, we need a larger value and move i right.
                 */
            } else if (numbers[i] + numbers[j] < target) {
                i++;

                /*
                 * ==================================================
                 * Sum is too large
                 * ==================================================
                 *
                 * numbers[i] + numbers[j] > target
                 *
                 * Because the array is sorted, moving i to the right
                 * would make the sum even larger.
                 *
                 * Therefore, we need a smaller value and move j left.
                 */
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            }
        }

        /*
         * Return the 1-based indices of the required pair.
         */
        return result;
    }
}