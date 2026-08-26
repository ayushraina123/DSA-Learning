package com.dsa.phase2.twoPointers;

/**
 * ==========================================================
 * Problem    : LeetCode 42 - Trapping Rain Water
 * Difficulty : Hard
 * Pattern    : Two Pointers
 * <p>
 * Idea:
 * For any position i, the amount of water it can trap depends
 * on the smaller of the maximum heights to its left and right:
 * <p>
 * water[i] = min(leftMax, rightMax) - height[i]
 * <p>
 * The challenge is to calculate this without finding the
 * maximum on both sides separately for every position.
 * <p>
 * Use two pointers:
 * • j starts from the left.
 * • k starts from the right.
 * • leftMax stores the maximum height encountered from the left.
 * • rightMax stores the maximum height encountered from the right.
 * <p>
 * At every step, compare leftMax and rightMax.
 * <p>
 * If leftMax < rightMax:
 * • leftMax is the smaller boundary.
 * • Therefore, the water level for position j is determined
 * by leftMax.
 * • If height[j] is below leftMax, it traps:
 * leftMax - height[j]
 * • Otherwise, update leftMax.
 * • Move j forward.
 * <p>
 * Otherwise:
 * • rightMax is the smaller boundary (or both are equal).
 * • Therefore, the water level for position k is determined
 * by rightMax.
 * • If height[k] is below rightMax, it traps:
 * rightMax - height[k]
 * • Otherwise, update rightMax.
 * • Move k backward.
 * <p>
 * The condition j <= k is important because when j == k,
 * there is still one position left to process. Using j < k
 * would skip that final position.
 * <p>
 * Example:
 * height = [4, 2, 0, 3, 2, 5]
 * <p>
 * Initially:
 * leftMax = 4
 * rightMax = 5
 * <p>
 * Since leftMax < rightMax, the minimum boundary is leftMax,
 * so the left pointer can be safely processed.
 * <p>
 * At height 2:
 * water = 4 - 2 = 2
 * <p>
 * At height 0:
 * water = 4 - 0 = 4
 * <p>
 * At height 3:
 * water = 4 - 3 = 1
 * <p>
 * When the rightMax becomes the smaller boundary, the same
 * logic is applied from the right side.
 * <p>
 * The two pointers therefore allow us to calculate the same
 * formula:
 * <p>
 * water[i] = min(leftMax, rightMax) - height[i]
 * <p>
 * without repeatedly scanning the array to find the maximum
 * height on each side.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 * <p>
 * Learnings:
 * ✔ The amount of trapped water at a position is determined
 * by the smaller of the maximum heights on its two sides.
 * ✔ leftMax and rightMax allow us to maintain those maximum
 * values while scanning from both directions.
 * ✔ When leftMax is smaller, it is the limiting boundary,
 * so the left position can be resolved safely.
 * ✔ When rightMax is smaller or equal, it is the limiting
 * boundary, so the right position can be resolved safely.
 * ✔ The pointer whose side has the smaller maximum is moved
 * because that side's water level is already determined.
 * ✔ The two pointers move toward each other, giving an O(n)
 * solution with O(1) extra space.
 * ==========================================================
 */
public class TrappingRainWater {

    public int trap(int[] height) {

        // Stores the total amount of trapped rain water.
        int count = 0;

        // j scans from the left.
        int j = 0;

        // k scans from the right.
        int k = height.length - 1;

        // Maximum height encountered from the left.
        int leftMax = height[0];

        // Maximum height encountered from the right.
        int rightMax = height[height.length - 1];

        // <= is required because when j == k, there is still
        // one position that needs to be processed.
        while (j <= k) {

            // leftMax is the smaller boundary, so the amount
            // of water at j is determined by leftMax.
            if (leftMax < rightMax) {

                // If the current height is below leftMax,
                // the difference is trapped water.
                if (height[j] < leftMax) {
                    count += leftMax - height[j];

                    // Otherwise, the current height becomes the
                    // new maximum boundary from the left.
                } else {
                    leftMax = Math.max(leftMax, height[j]);
                }

                // Move the left pointer forward.
                j++;

            } else {

                // rightMax is the smaller boundary (or equal),
                // so the amount of water at k is determined
                // by rightMax.
                if (height[k] < rightMax) {
                    count += rightMax - height[k];

                    // Otherwise, the current height becomes the
                    // new maximum boundary from the right.
                } else {
                    rightMax = Math.max(rightMax, height[k]);
                }

                // Move the right pointer backward.
                k--;
            }
        }

        return count;
    }
}