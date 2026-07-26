package com.dsa.phase1.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * ==========================================================
 * Problem    : LeetCode 217 - Contains Duplicate
 * Difficulty : Easy
 * Pattern    : HashSet
 * <p>
 * Idea:
 * A HashSet only stores unique elements.
 * While traversing the array, try inserting every element.
 * <p>
 * If insertion fails, it means the element already exists,
 * therefore a duplicate has been found.
 * <p>
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 * <p>
 * Learnings:
 * ✔ HashSet.add() returns false if the element already exists.
 * ✔ Stop immediately when the answer is found.
 * ✔ No need to traverse the entire array once a duplicate is detected.
 * ==========================================================
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {

        // Stores every unique element encountered so far.
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {

            // add() returns false if the element is already present.
            if (!set.add(num)) {
                return true;
            }
        }

        // No duplicates found.
        return false;
    }
}