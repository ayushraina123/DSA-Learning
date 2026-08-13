package com.dsa.phase1.matrix;

/**
 * ==========================================================
 * Problem    : LeetCode 73 - Set Matrix Zeroes
 * Difficulty : Medium
 * Pattern    : Matrix / In-place Matrix Manipulation
 * <p>
 * Idea:
 * We need to set an entire row and column to zero whenever
 * an element in the matrix is zero.
 * <p>
 * The challenge is to do this in-place using O(1) extra space.
 * <p>
 * Instead of using separate sets to store the rows and columns
 * that need to be zeroed, we use the first row and first column
 * of the matrix itself as marker storage.
 * <p>
 * For a zero at matrix[i][j]:
 * <p>
 * matrix[i][0] = 0  -> Mark row i
 * matrix[0][j] = 0  -> Mark column j
 * <p>
 * However, the first row and first column are also being used
 * as marker storage. Therefore, we separately store whether
 * the original first row and first column contained a zero.
 * <p>
 * The process is:
 * <p>
 * 1. Check whether the first column originally contains a zero.
 * 2. Check whether the first row originally contains a zero.
 * 3. Use the first row and first column as markers.
 * 4. Zero the marked rows and columns, excluding the first
 * row and first column.
 * 5. Finally, zero the first row and/or first column if their
 * corresponding flags were set.
 * <p>
 * Example:
 * <p>
 * Original matrix:
 * 1 1 1
 * 1 0 1
 * 1 1 1
 * <p>
 * When matrix[1][1] is zero, we mark:
 * <p>
 * matrix[1][0] = 0  -> Row 1 must be zeroed.
 * matrix[0][1] = 0  -> Column 1 must be zeroed.
 * <p>
 * The matrix then becomes:
 * 0 0 1
 * 0 0 1
 * 1 1 1
 * <p>
 * The first row and first column are acting as our marker
 * storage. We then use those markers to zero the remaining
 * cells.
 * <p>
 * Note:
 * The first row and first column cannot independently represent
 * whether they themselves originally contained a zero because
 * matrix[0][0] belongs to both. Therefore, firstRowZero and
 * firstColumnZero are used to preserve this information.
 * <p>
 * Time Complexity : O(m * n)
 * Space Complexity: O(1)
 * ==========================================================
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {

        boolean firstRowZero = false;
        boolean firstColumnZero = false;

        int rows = matrix.length;
        int columns = matrix[0].length;

        // Check whether the first column originally contains a zero.
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstColumnZero = true;
                break;
            }
        }

        // Check whether the first row originally contains a zero.
        for (int i = 0; i < columns; i++) {
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Use the first column and first row as markers.
        //
        // If matrix[i][j] is zero:
        // matrix[i][0] = 0 -> mark row i
        // matrix[0][j] = 0 -> mark column j
        //
        // We intentionally use the entire matrix here to create
        // the markers. The first row and column's original state
        // has already been saved in the boolean flags above.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Use the markers to zero the matrix.
        //
        // Start from index 1 because the first row and first column
        // are being used as marker storage.
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // If the original first column contained a zero,
        // zero the entire first column.
        if (firstColumnZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }

        // If the original first row contained a zero,
        // zero the entire first row.
        if (firstRowZero) {
            for (int i = 0; i < columns; i++) {
                matrix[0][i] = 0;
            }
        }
    }
}