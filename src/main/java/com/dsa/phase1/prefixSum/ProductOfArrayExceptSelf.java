package com.dsa.phase1.prefixSum;

/**
 * ==========================================================
 * Problem    : LeetCode 238 - Product of Array Except Self
 * Difficulty : Medium
 * Pattern    : Prefix / Suffix Product
 * <p>
 * Idea:
 * For every index i, we need the product of all elements
 * except nums[i].
 * <p>
 * We can split this into two parts:
 * <p>
 * result[i] = product of elements to the LEFT of i
 * * product of elements to the RIGHT of i
 * <p>
 * First pass:
 * Store the product of all elements to the left of each
 * index directly in result[].
 * <p>
 * Second pass:
 * Maintain a running rightProduct and multiply it with
 * result[i] to include the product of all elements to
 * the right.
 * <p>
 * This avoids using separate left[] and right[] arrays.
 * <p>
 * Example:
 * nums   = [1, 2, 3, 4]
 * <p>
 * After left pass:
 * result = [1, 1, 2, 6]
 * <p>
 * After right pass:
 * result = [24, 12, 8, 6]
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(1) extra space
 * (excluding the output array)
 * <p>
 * ==========================================================
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        // result[i] stores the product of all elements
        // to the LEFT of index i.
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        // Running product of all elements to the RIGHT.
        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            // Combine left product and right product.
            result[i] *= rightProduct;
            // Include nums[i] for the next index on the left.
            rightProduct *= nums[i];
        }
        return result;
    }
}

    /*
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int productWithoutZero = 1;
        int product = 1;
        int zeroCount = 0;

        for (int num : nums) {

            if (num == 0) {
                zeroCount++;
            }

            if (num != 0) {
                productWithoutZero *= num;
            }

            product *= num;
        }

        if (zeroCount == nums.length || zeroCount > 1) {
            Arrays.fill(nums, 0);
            return nums;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                result[i] = product / nums[i];
            } else {
                result[i] = productWithoutZero;
            }
        }

        return result;
    }
     */
