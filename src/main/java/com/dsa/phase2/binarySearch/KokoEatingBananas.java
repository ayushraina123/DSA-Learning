package com.dsa.phase2.binarySearch;

/**
 * ==========================================================
 * Problem    : LeetCode 875 - Koko Eating Bananas
 * Difficulty : Medium
 * Pattern    : Binary Search on Answer
 * ==========================================================
 * <p>
 * Idea:
 * Instead of directly trying to calculate Koko's minimum
 * eating speed, we binary search through all possible eating
 * speeds.
 * <p>
 * For a given eating speed {@code mid}, we calculate how many
 * hours Koko would need to finish all the piles.
 * <p>
 * For each pile:
 * <p>
 * - {@code pile / mid} gives the number of complete hours
 * required at that eating speed.
 * <p>
 * - If {@code pile % mid != 0}, some bananas are still left
 * after the complete hours, so Koko needs one additional
 * hour to finish that pile.
 * <p>
 * Therefore, the total hours required are equivalent to:
 * <p>
 * {@code ceil(pile[0] / mid) + ceil(pile[1] / mid) + ...}
 * <p>
 * Once we know the total number of hours:
 * <p>
 * - If {@code totalHours <= h}, the current eating speed is
 * sufficient. However, there may be a smaller valid speed,
 * so we continue searching the left half.
 * <p>
 * - If {@code totalHours > h}, the current eating speed is
 * too slow, so we search for a larger speed in the right half.
 * <p>
 * The {@code min} variable stores the smallest eating speed
 * that allows Koko to finish all piles within {@code h} hours.
 * <p>
 * {@code sum} is declared as {@code long} because the total
 * number of hours can exceed the maximum value of an
 * {@code int} when the piles contain very large values.
 * <p>
 * Time Complexity  : O(n log(maxPile))
 * Space Complexity : O(1)
 * <p>
 * ==========================================================
 */
public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        long sum = 0;

        // Find the largest pile, which represents the maximum
        // possible eating speed we need to consider.
        for (int pile : piles) {
            max = Math.max(max, pile);
            sum += pile;
        }

        // Eating speed cannot be less than 1 banana per hour.
        int left = 1;
        int right = max;
        int mid = left + (right - left) / 2;

        while (left <= right) {
            sum = 0;

            // Calculate the total hours required if Koko eats
            // 'mid' bananas per hour.
            for (int pile : piles) {
                sum += pile / mid;

                // If the pile is not evenly divisible by mid,
                // one additional hour is required for the
                // remaining bananas.
                if (pile % mid != 0) {
                    sum += 1;
                }
            }

            if (sum <= h) {
                // The current speed works, so store it as a
                // potential answer and check whether a smaller
                // eating speed also works.
                min = mid;
                right = mid - 1;

            } else {
                // The current speed is too slow, so Koko needs
                // a larger eating speed.
                left = mid + 1;
            }

            mid = left + (right - left) / 2;
        }

        return min;
    }
}