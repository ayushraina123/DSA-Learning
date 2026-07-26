package com.dsa.phase1.arrays;

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minElement = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < minElement) {
                minElement = prices[i];
            }

            maxProfit = Math.max(maxProfit, prices[i + 1] - minElement);
        }

        return maxProfit;
    }
}
