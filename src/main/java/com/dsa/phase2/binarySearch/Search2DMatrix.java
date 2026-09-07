package com.dsa.phase2.binarySearch;

/**
 * ==========================================================
 * Problem    : LeetCode 74 - Search a 2D Matrix
 * Difficulty : Medium
 * Pattern    : Binary Search
 * <p>
 * Idea:
 * The matrix follows two important ordering rules:
 *
 * <ul>
 *     <li>Each row is sorted in ascending order.</li>
 *     <li>
 *         The first element of a row is greater than the last
 *         element of the previous row.
 *     </li>
 * </ul>
 * <p>
 * Because of this ordering, we can use Binary Search twice.
 * <p>
 * First, we perform Binary Search on the rows to determine
 * which row could contain the target.
 * <p>
 * A row can contain the target only when:
 *
 * <pre>
 * matrix[mid][0] <= target <= matrix[mid][n - 1]
 * </pre>
 * <p>
 * If the target is greater than the last element of the
 * current row, we search the rows below it.
 * <p>
 * If the target is smaller than the first element of the
 * current row, we search the rows above it.
 * <p>
 * Once the potential row is found, we perform a second
 * Binary Search within that row.
 * <p>
 * If no possible row exists, the target does not exist in
 * the matrix.
 * <p>
 * Time Complexity  : O(log m + log n)
 * Space Complexity : O(1)
 * ==========================================================
 */
public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        // Store the number of rows and columns.
        int m = matrix.length;
        int n = matrix[0].length;

        // Stores the row that may contain the target.
        int midArray = -1;

        // Search boundaries for locating the correct row.
        int left = 0;
        int right = m - 1;

        // Calculate the middle row.
        int mid = left + (right - left) / 2;

        // Search boundaries for Binary Search within the row.
        int innerLeft = 0;
        int innerRight = n - 1;

        // Calculate the middle column.
        int innerMid = innerLeft + (innerRight - innerLeft) / 2;

        // Find the row that could contain the target.
        while (left <= right) {

            // Target falls within the range of the current row.
            if (target >= matrix[mid][0] && target <= matrix[mid][n - 1]) {
                midArray = mid;
                break;
            }

            // Target must be in a row below the current row.
            else if (target > matrix[mid][n - 1]) {
                left = mid + 1;
            }

            // Target must be in a row above the current row.
            else {
                right = mid - 1;
            }

            // Recalculate the middle row.
            mid = left + (right - left) / 2;
        }

        // No row can contain the target.
        if (midArray == -1) {
            return false;
        }

        // Perform Binary Search within the identified row.
        while (innerLeft <= innerRight) {

            // Target found.
            if (target == matrix[midArray][innerMid]) {
                return true;
            }

            // Target must be in the right half of the row.
            else if (target > matrix[midArray][innerMid]) {
                innerLeft = innerMid + 1;
            }

            // Target must be in the left half of the row.
            else {
                innerRight = innerMid - 1;
            }

            // Recalculate the middle column.
            innerMid = innerLeft + (innerRight - innerLeft) / 2;
        }

        // Target does not exist in the matrix.
        return false;
    }
}