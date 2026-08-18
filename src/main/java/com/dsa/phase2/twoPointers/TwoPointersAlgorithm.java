package com.dsa.phase2.twoPointers;

/**
 * ==========================================================
 * Problem    : LeetCode 167 - Two Sum II - Input Array Is Sorted
 * Difficulty : Medium
 * Pattern    : Two Pointers
 * ==========================================================
 *
 * <p>
 * Given a 1-indexed array of integers that is sorted in
 * non-decreasing order, find two numbers whose sum equals
 * the given target.
 * </p>
 *
 * <p>
 * Example:
 * <p>
 * numbers = [2, 7, 11, 15]
 * target  = 9
 * <p>
 * 2 + 7 = 9
 * <p>
 * Answer = [1, 2]
 * </p>
 *
 * <p>
 * Key Observation:
 * The array is sorted, so we can use two pointers:
 * <p>
 * left  -> starts at the beginning
 * right -> starts at the end
 * <p>
 * We compare numbers[left] + numbers[right] with the target
 * and use the sorted order to eliminate impossible pairs.
 * </p>
 *
 * <p>
 * Pointer movement:
 * <p>
 * If sum == target:
 * We found the required pair.
 * <p>
 * If sum < target:
 * The sum is too small.
 * Move left forward to obtain a larger value.
 * <p>
 * If sum > target:
 * The sum is too large.
 * Move right backward to obtain a smaller value.
 * </p>
 *
 * <p>
 * Why can we safely move the pointers?
 * <p>
 * Suppose:
 * <p>
 * numbers[left] + numbers[right] > target
 * <p>
 * Since the array is sorted, numbers[left] is the smallest
 * value currently available.
 * <p>
 * Therefore, replacing numbers[left] with any larger value
 * would only make the sum even larger.
 * <p>
 * So numbers[right] cannot form a valid pair with left or
 * any value to its right. We can safely discard right and
 * move it backward.
 * <p>
 * Similarly, if:
 * <p>
 * numbers[left] + numbers[right] < target
 * <p>
 * numbers[right] is the largest value currently available.
 * Therefore, replacing numbers[right] with a smaller value
 * would only make the sum even smaller.
 * <p>
 * So numbers[left] cannot form a valid pair with right or
 * any value to its left. We can safely discard left and
 * move it forward.
 * </p>
 *
 * <p>
 * This allows us to eliminate an entire range of impossible
 * pairs with every pointer movement instead of checking every
 * possible pair using nested loops.
 * </p>
 *
 * <p>
 * Complexity:
 * <p>
 * Time  : O(n)
 * Space : O(1)
 * <p>
 * Each pointer moves only in one direction and each element
 * is visited at most once.
 * </p>
 */
public class TwoPointersAlgorithm {

    public int[] twoSum(int[] numbers, int target) {

        /*
         * ======================================================
         * Initialize the two pointers
         * ======================================================
         *
         * left starts at the smallest element.
         *
         * right starts at the largest element.
         *
         * Example:
         *
         *     [2, 7, 11, 15]
         *      ↑          ↑
         *     left      right
         */
        int left = 0;
        int right = numbers.length - 1;

        /*
         * Continue until the two pointers cross.
         *
         * We need two different elements, therefore:
         *
         *     left < right
         */
        while (left < right) {

            /*
             * Calculate the sum of the values currently
             * pointed to by the two pointers.
             */
            int sum = numbers[left] + numbers[right];

            /*
             * ==================================================
             * Case 1: Found the target
             * ==================================================
             *
             * Since the problem requires 1-based indices,
             * convert Java's 0-based indices by adding 1.
             */
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }

            /*
             * ==================================================
             * Case 2: Sum is too small
             * ==================================================
             *
             *     sum < target
             *
             * We need to increase the sum.
             *
             * Since the array is sorted, moving left forward
             * gives us a larger value.
             *
             * Therefore:
             *
             *     left++
             */
            if (sum < target) {
                left++;
            }

            /*
             * ==================================================
             * Case 3: Sum is too large
             * ==================================================
             *
             *     sum > target
             *
             * We need to decrease the sum.
             *
             * Since the array is sorted, moving right backward
             * gives us a smaller value.
             *
             * Therefore:
             *
             *     right--
             */
            else {
                right--;
            }
        }

        /*
         * No valid pair was found.
         *
         * The problem guarantees a solution, but returning
         * [-1, -1] makes the method safe for inputs where no
         * such pair exists.
         */
        return new int[]{-1, -1};
    }

    /*
     * ==========================================================
     * Alternative Approach: Brute Force
     * ==========================================================
     *
     * Check every possible pair using nested loops.
     *
     * Time  : O(n²)
     * Space : O(1)
     *
     * This works, but it completely ignores the fact that the
     * input array is sorted.
     *
     *
     * public int[] twoSum(int[] numbers, int target) {
     *
     *     for (int i = 0; i < numbers.length; i++) {
     *
     *         for (int j = i + 1; j < numbers.length; j++) {
     *
     *             if (numbers[i] + numbers[j] == target) {
     *                 return new int[]{i + 1, j + 1};
     *             }
     *         }
     *     }
     *
     *     return new int[]{-1, -1};
     * }
     *
     * The Two Pointers approach improves this from O(n²) to O(n)
     * by exploiting the sorted order of the array.
     */
}