package com.dsa.phase2.binarySearch;

/**
 * ==========================================================
 * Pattern    : Binary Search
 * ==========================================================
 * <p>
 * Binary Search is not just "searching in a sorted array".
 * <p>
 * The real idea is:
 * <p>
 * Define a search space containing possible answers
 * ↓
 * Evaluate mid
 * ↓
 * Use the result to eliminate part of the search space
 * ↓
 * Repeat
 * <p>
 * The important question is:
 * <p>
 * Can evaluating mid safely tell me which part of the search
 * space cannot contain the answer?
 * <p>
 * <p>
 * ==========================================================
 * The Core Mental Model
 * ==========================================================
 * <p>
 * For every Binary Search problem, identify:
 * <p>
 * 1. What is my search space?
 * <p>
 * 2. What does mid represent?
 * <p>
 * 3. What condition tells me which side to eliminate?
 * <p>
 * 4. What invariant am I maintaining?
 * <p>
 * Binary Search works when the information obtained from mid
 * allows us to safely reduce the remaining possibilities.
 * <p>
 * <p>
 * ==========================================================
 * 1. Classic Binary Search
 * ==========================================================
 * <p>
 * Used when searching for a specific target.
 * <p>
 * Typical condition:
 * <p>
 * nums[mid] == target
 * → Found
 * <p>
 * nums[mid] < target
 * → Search right
 * <p>
 * nums[mid] > target
 * → Search left
 * <p>
 * <p>
 * ==========================================================
 * 2. Boundary Binary Search
 * ==========================================================
 * <p>
 * Here, we are NOT necessarily looking for an exact element.
 * <p>
 * We are looking for a transition point.
 * <p>
 * Example:
 * <p>
 * F F F F T T T T
 * ↑
 * First True
 * <p>
 * Common applications:
 * <p>
 * - First occurrence
 * - Last occurrence
 * - First True
 * - Last False
 * - First valid position
 * - Insert position
 * <p>
 * Important difference:
 * <p>
 * Finding a valid value does not always mean we are finished.
 * <p>
 * For example, when finding First True:
 * <p>
 * mid = True
 * → mid can be the answer
 * → but an earlier True may exist
 * → continue searching left
 * <p>
 * mid = False
 * → answer must be on the right
 * <p>
 * So the key question is:
 * <p>
 * Is mid a valid answer?
 * <p>
 * If yes, ask:
 * <p>
 * Could a better boundary still exist?
 * <p>
 * <p>
 * ==========================================================
 * 3. Binary Search in a Rotated Sorted Array
 * ==========================================================
 * <p>
 * Example:
 * <p>
 * [5, 6, 7, 1, 2, 3, 4]
 * <p>
 * The entire array is not sorted.
 * <p>
 * However:
 * <p>
 * At least one half is always sorted.
 * <p>
 * Mental process:
 * <p>
 * 1. Find mid
 * 2. Identify which half is sorted
 * 3. Check whether target lies within that half's range
 * 4. Eliminate the impossible half
 * <p>
 * Left half is sorted if:
 * <p>
 * nums[low] <= nums[mid]
 * <p>
 * Then check:
 * <p>
 * nums[low] <= target < nums[mid]
 * <p>
 * Right half is sorted otherwise.
 * <p>
 * Then check:
 * <p>
 * nums[mid] < target <= nums[high]
 * <p>
 * The important mental model:
 * <p>
 * Do NOT ask:
 * <p>
 * "Is the array sorted?"
 * <p>
 * Ask:
 * <p>
 * "Which half is currently sorted?"
 * <p>
 * <p>
 * ==========================================================
 * 4. Binary Search on the Answer
 * ==========================================================
 * <p>
 * Sometimes the search space is not an array.
 * <p>
 * Instead, it is a range of possible answers.
 * <p>
 * Typical problem statements:
 * <p>
 * Find the minimum X such that...
 * <p>
 * Find the maximum X such that...
 * <p>
 * Example:
 * <p>
 * Possible answers:
 * 1 2 3 4 5 6 7 8 9
 * <p>
 * Works?
 * F F F F F F T T T
 * ↑
 * First valid answer
 * <p>
 * Binary Search on the Answer is essentially:
 * <p>
 * Boundary Binary Search
 * over a range of possible answers.
 * <p>
 * Three things to identify:
 * <p>
 * 1. Search Space
 * <p>
 * What is the minimum and maximum possible answer?
 * <p>
 * 2. Validation Function
 * <p>
 * Can I test whether a particular answer works?
 * <p>
 * Example:
 * <p>
 * canShip(capacity)
 * <p>
 * 3. Monotonic Condition
 * <p>
 * The result must behave consistently:
 * <p>
 * F F F F T T T T
 * <p>
 * or:
 * <p>
 * T T T T F F F F
 * <p>
 * Once the condition changes direction, Binary Search
 * can find the transition point.
 * <p>
 * <p>
 * ==========================================================
 * How to Recognize Binary Search
 * ==========================================================
 * <p>
 * Do NOT only look for:
 * <p>
 * "Is the array sorted?"
 * <p>
 * Instead ask:
 * <p>
 * 1. What are all possible answers?
 * <p>
 * 2. Can I define them as a search space?
 * <p>
 * 3. Can evaluating mid eliminate part of that space?
 * <p>
 * 4. Is there a boundary or monotonic transition?
 * <p>
 * Common patterns:
 * <p>
 * 1. Find an exact target
 * 2. Find a boundary / transition point
 * 3. Search in a rotated sorted array
 * 4. Find the minimum/maximum valid answer
 * <p>
 * <p>
 * ==========================================================
 * Important Distinction
 * ==========================================================
 * <p>
 * Classic Binary Search:
 * <p>
 * Find the target.
 * <p>
 * Boundary Binary Search:
 * <p>
 * Find where a condition changes.
 * <p>
 * Binary Search on the Answer:
 * <p>
 * Define possible answers
 * + check whether an answer works
 * + find the transition point.
 * <p>
 * <p>
 * ==========================================================
 * Final Problem-Solving Checklist
 * ==========================================================
 * <p>
 * Before writing Binary Search, ask:
 * <p>
 * 1. What exactly is my search space?
 * <p>
 * 2. What does mid represent?
 * <p>
 * 3. What information does evaluating mid give me?
 * <p>
 * 4. Which part can I safely eliminate?
 * <p>
 * 5. Am I searching for:
 * - an exact target?
 * - a boundary?
 * - a valid minimum/maximum answer?
 * <p>
 * 6. If this is Binary Search on the Answer:
 * - What is the answer range?
 * - What function checks validity?
 * - Is the condition monotonic?
 * <p>
 * <p>
 * ==========================================================
 * Complexity
 * ==========================================================
 * <p>
 * Time  : O(log n)
 * Space : O(1) for iterative Binary Search
 */
public class BinarySearchAlgorithm {

}