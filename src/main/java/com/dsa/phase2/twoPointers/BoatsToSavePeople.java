package com.dsa.phase2.twoPointers;

import java.util.Arrays;

/**
 * ==========================================================
 * Problem    : LeetCode 881 - Boats to Save People
 * Difficulty : Medium
 * Pattern    : Two Pointers + Greedy
 * <p>
 * Idea:
 * We need to rescue everyone using the minimum number of boats.
 * Each boat can carry at most two people, and the combined weight
 * of the people in a boat must not exceed `limit`.
 * <p>
 * The key observation is that the heaviest person is the hardest
 * person to accommodate.
 * <p>
 * First, sort the array so that we can efficiently find the
 * lightest and heaviest remaining people.
 * <p>
 * Two pointers are used:
 * • i points to the lightest remaining person.
 * • j points to the heaviest remaining person.
 * <p>
 * The basic greedy idea is:
 * • Always consider the heaviest person at j.
 * <p>
 * • If the lightest person and the heaviest person can fit
 * together, put them in the same boat.
 * Move both pointers inward.
 * <p>
 * • If they cannot fit together, the heaviest person must go
 * alone because the lightest person is the smallest possible
 * person. Therefore, nobody else can fit with the heaviest
 * person either.
 * Move only j backward.
 * <p>
 * Example:
 * people = [3, 2, 2, 1]
 * limit = 3
 * <p>
 * After sorting:
 * [1, 2, 2, 3]
 * <p>
 * Initial:
 * i = 0 -> 1
 * j = 3 -> 3
 * <p>
 * people[i] + people[j] = 1 + 3 = 4
 * <p>
 * They cannot fit together, so the heaviest person (3) must
 * go alone.
 * count = 1
 * j--
 * <p>
 * Now:
 * i = 0 -> 1
 * j = 2 -> 2
 * <p>
 * people[i] + people[j] = 1 + 2 = 3
 * <p>
 * They can fit together, so put them in the same boat.
 * count = 2
 * i++
 * j--
 * <p>
 * Now:
 * i = 1 -> 2
 * j = 1 -> 2
 * <p>
 * Only one person remains, so they need one boat.
 * count = 3
 * <p>
 * Result:
 * 3 boats
 * <p>
 * Why the greedy approach works:
 * • If the lightest person cannot fit with the heaviest person,
 * then no other person can fit with the heaviest person.
 * Therefore, the heaviest person must use a boat alone.
 * <p>
 * • If the lightest person can fit with the heaviest person,
 * pairing them is optimal because the lightest person is the
 * easiest person to pair with and there is no benefit in
 * keeping them for another heavier person.
 * <p>
 * Important:
 * Each iteration finalizes exactly one boat.
 * <p>
 * If the two people can share a boat, both pointers move because
 * both people have been rescued.
 * <p>
 * If they cannot share a boat, only j moves because the heaviest
 * person is rescued alone while the lightest person remains
 * available for another person.
 * <p>
 * Time Complexity : O(n log n)
 * Space Complexity: O(log n) auxiliary space
 * <p>
 * Learnings:
 * ✔ Sorting allows us to efficiently identify the lightest and
 * heaviest remaining elements.
 * ✔ When pairing elements under a limit, consider the heaviest
 * element first because it has the fewest possible pairing
 * options.
 * ✔ If the lightest + heaviest cannot fit, the heaviest must go
 * alone.
 * ✔ If they can fit, pairing them is the optimal greedy choice.
 * ✔ Every iteration uses exactly one boat.
 * ✔ The two-pointer technique works efficiently after sorting
 * because both pointers only move inward.
 * ==========================================================
 */
public class BoatsToSavePeople {

    public int numRescueBoats(int[] people, int limit) {

        // Stores the number of boats required.
        int count = 0;

        // Sort the people so that i can point to the lightest
        // person and j can point to the heaviest person.
        Arrays.sort(people);

        // i points to the lightest remaining person.
        int i = 0;

        // j points to the heaviest remaining person.
        int j = people.length - 1;

        while (i <= j) {

            // If the lightest and heaviest people can fit
            // together, put both of them in the same boat.
            if (people[i] + people[j] <= limit) {

                // One boat rescues both people.
                count++;

                // The lightest person has been rescued.
                i++;

                // The heaviest person has been rescued.
                j--;

            } else {

                // The heaviest person cannot fit even with the
                // lightest person, so they must go alone.
                j--;

                // One boat is required for the heaviest person.
                count++;
            }
        }

        // Return the minimum number of boats required to rescue
        // everyone.
        return count;
    }
}