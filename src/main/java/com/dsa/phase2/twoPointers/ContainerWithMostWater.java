package com.dsa.phase2.twoPointers;

/**
 * ==========================================================
 * Problem    : LeetCode 11 - Container With Most Water
 * Difficulty : Medium
 * Pattern    : Two Pointers
 * <p>
 * Idea:
 * We need to find two vertical lines that form a container
 * holding the maximum amount of water.
 * <p>
 * The area of water between two lines is determined by:
 * <p>
 * area = min(height[left], height[right]) * (right - left)
 * <p>
 * Start with the two pointers at the extreme ends of the
 * array. This gives us the maximum possible width.
 * <p>
 * At every step:
 * • Calculate the area using the current left and right lines.
 * • Update the maximum area found so far.
 * • Move the pointer pointing to the shorter line.
 * <p>
 * Why move the shorter line?
 * <p>
 * The area is limited by the shorter line. Moving the taller
 * line cannot increase the area because the width decreases
 * while the limiting height remains the same or becomes
 * smaller.
 * <p>
 * Therefore, to potentially find a larger area, we must move
 * the shorter pointer in the hope of finding a taller line.
 * <p>
 * When both heights are equal, either pointer can be moved.
 * This implementation chooses to move the right pointer because
 * the equality case falls into the else block.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 * <p>
 * Learnings:
 * ✔ The area is limited by the shorter of the two lines.
 * ✔ Start with the widest possible container using two pointers.
 * ✔ Always move the pointer at the shorter height because
 * moving the taller pointer cannot improve the current area.
 * ✔ When both heights are equal, either pointer can be moved.
 * ✔ Two Pointers can reduce the brute-force O(n²) solution to O(n).
 * ==========================================================
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {

        // Stores the maximum container area found so far.
        int max = Integer.MIN_VALUE;

        int left = 0;
        int right = height.length - 1;

        while (left < right) {

            // Calculate the area formed by the current two lines.
            // The shorter line determines the water level.
            int product = Math.min(height[left], height[right])
                    * (right - left);

            // Update the maximum area.
            max = Math.max(max, product);

            /*
             * Move the pointer at the shorter line.
             *
             * If both heights are equal, the condition is false,
             * so right-- is executed. This is arbitrary because
             * either pointer can be moved when both heights are equal.
             */
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}