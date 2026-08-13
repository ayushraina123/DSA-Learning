package com.dsa.phase1.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================
 * Problem    : LeetCode 54 - Spiral Matrix
 * Difficulty : Medium
 * Pattern    : Matrix / Boundary Traversal
 * <p>
 * Idea:
 * We need to traverse all elements of the matrix in spiral
 * order, starting from the top-left corner and moving:
 * <p>
 * 1. Left -> Right across the top boundary.
 * 2. Top -> Bottom along the right boundary.
 * 3. Right -> Left across the bottom boundary.
 * 4. Bottom -> Top along the left boundary.
 * <p>
 * After completing one complete layer, we move all four
 * boundaries inward and repeat the same process for the
 * remaining inner matrix.
 * <p>
 * We maintain four boundaries:
 * <p>
 * top    -> current top row
 * bottom -> current bottom row
 * left   -> current left column
 * right  -> current right column
 * <p>
 * Initially, the boundaries cover the entire matrix.
 * <p>
 * Example:
 * <p>
 * 1   2   3   4
 * 5   6   7   8
 * 9  10  11  12
 * 13 14  15  16
 * <p>
 * First layer:
 * <p>
 * 1 -> 2 -> 3 -> 4
 * |
 * 8
 * |
 * 12
 * |
 * 13 <- 14 <- 15 <- 16
 * ^
 * |
 * 9 <- 5
 * <p>
 * After consuming the outer layer, the boundaries move
 * inward and the remaining matrix is:
 * <p>
 * 6   7
 * 10  11
 * <p>
 * The same process is repeated until all elements have
 * been visited.
 * <p>
 * The outer while loop represents processing one complete
 * layer of the matrix.
 * <p>
 * Within each iteration, four traversals process the four
 * boundaries:
 * <p>
 * 1. Top boundary:
 * Traverse from left -> right.
 * <p>
 * 2. Right boundary:
 * Traverse from top -> bottom.
 * <p>
 * 3. Bottom boundary:
 * Traverse from right -> left.
 * <p>
 * 4. Left boundary:
 * Traverse from bottom -> top.
 * <p>
 * The bottom and left traversals require additional boundary
 * checks to avoid processing the same elements again when
 * only a single row or a single column remains.
 * <p>
 * After processing a layer:
 * <p>
 * top++;
 * left++;
 * right--;
 * bottom--;
 * <p>
 * This effectively shrinks the current rectangle and moves
 * the traversal inward.
 * <p>
 * The process continues while:
 * <p>
 * top <= bottom && left <= right
 * <p>
 * which means that there is still a valid rectangular region
 * remaining to process.
 * <p>
 * Time Complexity : O(m * n)
 * Space Complexity: O(m * n)
 * ==========================================================
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> list = new ArrayList<>();

        // Define the four boundaries of the current matrix layer.
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        // Process one layer of the matrix at a time.
        while (top <= bottom && left <= right) {

            // Traverse the top boundary from left -> right.
            //
            // Example:
            // [1  2  3  4]
            //  ^
            //  Traverse all elements of this row.
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }

            // Traverse the right boundary from top -> bottom.
            //
            // The top element is skipped using top + 1 because
            // it was already added during the top-boundary traversal.
            //
            // Example:
            // 1  2  3  4
            //          |
            //          8
            //          |
            //          12
            for (int i = top + 1; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }

            // Traverse the bottom boundary from right -> left.
            //
            // Only perform this traversal if there is more than
            // one row remaining.
            //
            // Otherwise, the bottom row would be the same row
            // that was already processed as the top boundary.
            if (top < bottom) {
                for (int i = right - 1; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
            }

            // Traverse the left boundary from bottom -> top.
            //
            // Only perform this traversal if there is more than
            // one column remaining.
            //
            // The bottom element is skipped using bottom - 1
            // because it was already added during the bottom-
            // boundary traversal.
            if (left < right) {
                for (int i = bottom - 1; i > top; i--) {
                    list.add(matrix[i][left]);
                }
            }

            // Move all four boundaries inward.
            //
            // This removes the current outer layer and makes
            // the next iteration process the inner matrix.
            top++;
            left++;
            right--;
            bottom--;
        }

        return list;
    }
}