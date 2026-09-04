package com.dsa.phase2.binarySearch;

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int midArray = -1;
        int left = 0;
        int right = m - 1;
        int mid = left + (right - left) / 2;

        int innerLeft = 0;
        int innerRight = n - 1;
        int innerMid = innerLeft + (innerRight - innerLeft) / 2;

        while (left <= right) {
            if (target >= matrix[mid][0] && target <= matrix[mid][n - 1]) {
                midArray = mid;
                break;
            } else if (target > matrix[mid][n - 1]) {
                left = mid + 1;
            } else if (target < matrix[mid][0]) {
                right = mid - 1;
            }
            mid = left + (right - left) / 2;
        }

        if (midArray == -1) {
            return false;
        }

        while (innerLeft <= innerRight) {
            if (target == matrix[midArray][innerMid]) {
                return true;
            } else if (target > matrix[midArray][innerMid]) {
                innerLeft = innerMid + 1;
            } else if (target < matrix[midArray][innerMid]) {
                innerRight = innerMid - 1;
            }
            innerMid = innerLeft + (innerRight - innerLeft) / 2;
        }

        return false;
    }
}
