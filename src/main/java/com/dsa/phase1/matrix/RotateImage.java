package com.dsa.phase1.matrix;

/**
 * ==========================================================
 * Problem    : LeetCode 48 - Rotate Image
 * Difficulty : Medium
 * Pattern    : Matrix / 2D Array
 * <p>
 * Idea:
 * We need to rotate an n x n matrix 90 degrees clockwise.
 * <p>
 * Instead of modifying the original matrix directly, we create
 * a temporary matrix and place each element at its rotated
 * position.
 * <p>
 * For a 90-degree clockwise rotation:
 * <p>
 * new[i][j] = old[n - 1 - j][i]
 * <p>
 * Therefore, for every element matrix[i][j], we place it in
 * the temporary matrix using:
 * <p>
 * temp[i][j] = matrix[n - 1 - j][i]
 * <p>
 * Example:
 * <p>
 * Original matrix:
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * <p>
 * Rotated matrix:
 * 7 4 1
 * 8 5 2
 * 9 6 3
 * <p>
 * Once the rotated matrix is constructed, we copy all of its
 * elements back into the original matrix.
 * <p>
 * Note:
 * This approach uses an additional matrix, so it requires
 * O(n^2) extra space.
 * <p>
 * Time Complexity : O(n^2)
 * Space Complexity: O(n^2)
 * ==========================================================
 */
public class RotateImage {

    public void rotate(int[][] matrix) {

        // Create a temporary matrix with the same dimensions
        // as the original matrix.
        int[][] temp = new int[matrix.length][matrix[0].length];

        // Place every element at its position after a
        // 90-degree clockwise rotation.
        // new[i][j] = old[n - 1 - j][i]
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[i][j] = matrix[matrix[0].length - j - 1][i];
            }
        }

        // Copy the rotated matrix back into the original matrix.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }
}