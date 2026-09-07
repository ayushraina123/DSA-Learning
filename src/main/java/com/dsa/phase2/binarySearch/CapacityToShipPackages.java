package com.dsa.phase2.binarySearch;

/**
 * ==========================================================
 * Problem    : LeetCode 1011 - Capacity To Ship Packages
 * Within D Days
 * Difficulty : Medium
 * Pattern    : Binary Search on Answer
 * ==========================================================
 * <p>
 * Idea:
 * Instead of directly trying to calculate the minimum ship
 * capacity required to deliver all packages within the given
 * number of days, we binary search through all possible
 * capacities.
 * <p>
 * For a given ship capacity {@code mid}, we simulate the
 * shipping process and calculate how many days are required
 * to ship all packages in the given order.
 * <p>
 * The packages must be loaded onto the ship in the same order
 * in which they appear in the {@code weights} array. Therefore,
 * for each package, we add its weight to the current day's
 * shipment as long as doing so does not exceed {@code mid}.
 * <p>
 * If adding the next package causes the total weight of the
 * current shipment to exceed {@code mid}, that package cannot
 * be shipped on the current day. Therefore, we start a new day
 * and make that package the first package shipped on that day.
 * <p>
 * {@code maxWeight} represents the minimum possible capacity.
 * A ship must at least be capable of carrying the heaviest
 * individual package.
 * <p>
 * {@code sum} represents the maximum possible capacity. A ship
 * with this capacity can carry all packages in a single day.
 * <p>
 * Therefore, we binary search between:
 * <p>
 * - {@code maxWeight} → Minimum possible ship capacity.
 * <p>
 * - {@code sum} → Maximum possible ship capacity.
 * <p>
 * Once we calculate the number of days required for a given
 * capacity:
 * <p>
 * - If {@code count <= days}, the current capacity is sufficient
 * because all packages can be shipped within the required number
 * of days. However, there may be a smaller capacity that also
 * works, so we continue searching the left half.
 * <p>
 * - If {@code count > days}, the current capacity is too small
 * because it requires more than the allowed number of days.
 * Therefore, we search for a larger capacity in the right half.
 * <p>
 * {@code ans} stores the smallest capacity found so far that
 * allows all packages to be shipped within {@code days}.
 * <p>
 * Time Complexity  : O(n log(sum))
 * Space Complexity : O(1)
 * <p>
 * ==========================================================
 */
public class CapacityToShipPackages {

    public int shipWithinDays(int[] weights, int days) {
        int maxWeight = Integer.MIN_VALUE;
        int sum = 0;

        // Find the heaviest package and the total weight of
        // all packages.
        for (int weight : weights) {
            maxWeight = Math.max(maxWeight, weight);
            sum += weight;
        }

        int count;
        int ans = sum;
        int currentSum;

        // The capacity cannot be smaller than the heaviest
        // individual package.
        int left = maxWeight;

        // The total weight represents the capacity required
        // to ship every package in a single day.
        int right = sum;

        int mid = left + (right - left) / 2;

        while (left <= right) {
            // At least one day is required to ship packages.
            count = 1;
            currentSum = 0;

            // Calculate how many days are required if the
            // ship's capacity is 'mid'.
            for (int weight : weights) {

                // If the current package cannot fit into the
                // current day's shipment, start a new day.
                if (currentSum + weight > mid) {
                    count++;
                    currentSum = weight;

                } else {
                    // Otherwise, ship the package on the
                    // current day.
                    currentSum += weight;
                }
            }

            if (count <= days) {
                // The current capacity works, so store it as
                // a potential answer and check whether a
                // smaller capacity also works.
                ans = mid;
                right = mid - 1;

            } else {
                // The current capacity is too small because
                // more than the allowed number of days are
                // required. Increase the capacity.
                left = mid + 1;
            }

            mid = left + (right - left) / 2;
        }

        return ans;
    }
}