package com.dsa.phase1.arrays;

/**
 * ==========================================================
 * Problem    : LeetCode 121 - Best Time to Buy and Sell Stock
 * Difficulty : Easy
 * Pattern    : Running Minimum
 * <p>
 * Idea:
 * Assume every day is the selling day.
 * <p>
 * While traversing the array:
 * • Keep track of the cheapest buying price seen so far.
 * • Calculate the profit if we sell today.
 * • Update the maximum profit whenever a better one is found.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 * <p>
 * Learnings:
 * ✔ Maintain the best value seen so far while traversing.
 * ✔ Don't compare every pair of days.
 * ✔ Running minimum is a very common interview pattern.
 * ==========================================================
 */
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {

        // Lowest buying price encountered till the current day.
        int minPrice = Integer.MAX_VALUE;

        // Best profit found so far.
        int maxProfit = 0;

        for (int price : prices) {

            // Update the cheapest buying opportunity.
            minPrice = Math.min(minPrice, price);

            // If we sell today, compute the profit.
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }
}