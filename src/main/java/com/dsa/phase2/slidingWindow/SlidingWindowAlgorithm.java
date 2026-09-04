package com.dsa.phase2.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ==========================================================
 * Pattern    : Sliding Window
 * ==========================================================
 *
 * <p>
 * Sliding Window is a technique used to efficiently process
 * contiguous portions of an array or string.
 * </p>
 *
 * <p>
 * The idea is to maintain a "window" representing a contiguous
 * range of elements between two pointers:
 * </p>
 * <p>
 * left  -> beginning of the window
 * right -> end of the window
 *
 * <p>
 * Instead of repeatedly calculating information for every
 * possible subarray or substring from scratch, we maintain
 * information about the current window and move the window
 * across the input.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Core Idea
 * ==========================================================
 *
 * <p>
 * Consider:
 * </p>
 * <p>
 * [1, 2, 3, 4, 5, 6]
 * ↑        ↑
 * left    right
 *
 * <p>
 * The elements between left and right form the current window.
 * </p>
 *
 * <p>
 * As the window moves, elements leave from the left side and
 * new elements enter from the right side.
 * </p>
 *
 * <p>
 * The important optimization is that we do NOT recompute the
 * entire window every time it moves.
 * </p>
 *
 * <p>
 * Instead:
 * </p>
 * <p>
 * Remove the element leaving the window
 * Add the element entering the window
 *
 * <p>
 * This allows us to reuse the work already performed for the
 * previous window.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Why Sliding Window?
 * ==========================================================
 *
 * <p>
 * Suppose we need to process every contiguous subarray of
 * size k.
 * </p>
 *
 * <p>
 * A brute-force approach may calculate the result of every
 * window independently.
 * </p>
 * <p>
 * [1, 2, 3]
 * [2, 3, 4]
 * [3, 4, 5]
 *
 * <p>
 * The windows overlap heavily, so recalculating everything
 * repeatedly wastes work.
 * </p>
 *
 * <p>
 * Sliding Window takes advantage of this overlap.
 * When moving from:
 * </p>
 * <p>
 * [1, 2, 3]
 *
 * <p>
 * to:
 * </p>
 * <p>
 * [2, 3, 4]
 *
 * <p>
 * we can remove 1 and add 4 instead of processing 2 and 3
 * again.
 * </p>
 *
 * <p>
 * This is the fundamental reason Sliding Window can reduce
 * many brute-force solutions from O(n * k) or O(n²) to O(n).
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Two Types of Sliding Window
 * ==========================================================
 *
 * <p>
 * Sliding Window is primarily divided into two categories:
 * </p>
 * <p>
 * 1. Fixed-Size Sliding Window
 * 2. Variable-Size Sliding Window
 * <p>
 * <p>
 * ==========================================================
 * 1. Fixed-Size Sliding Window
 * ==========================================================
 *
 * <p>
 * In a fixed-size window, the window always contains exactly
 * k elements.
 * </p>
 *
 * <p>
 * Therefore:
 * </p>
 * <p>
 * window size = right - left + 1
 *
 * <p>
 * must always remain equal to k.
 * </p>
 * <p>
 * <p>
 * Example:
 * <p>
 * [1, 2, 3, 4, 5, 6]
 * ↑     ↑
 * left  right
 *
 * <p>
 * If k = 3, the current window contains:
 * </p>
 * <p>
 * [1, 2, 3]
 *
 * <p>
 * When the window moves one position:
 * </p>
 * <p>
 * [1, 2, 3]
 * ↓
 * [2, 3, 4]
 *
 * <p>
 * The element at the left leaves the window and the new
 * element at the right enters the window.
 * </p>
 * <p>
 * <p>
 * Fixed-Size Algorithm:
 * <p>
 * 1. Initialize the window state.
 * <p>
 * 2. Expand the window by moving right.
 * <p>
 * 3. Once the window reaches size k, process the window.
 * <p>
 * 4. Remove the element that is leaving the window.
 * <p>
 * 5. Move the window forward.
 * <p>
 * 6. Continue until the entire array/string is processed.
 * <p>
 * <p>
 * Generic structure:
 * <p>
 * for (int right = 0; right < n; right++) {
 * <p>
 * // Add nums[right] to the window
 * <p>
 * if (right - left + 1 == k) {
 * <p>
 * // Process current window
 * <p>
 * // Remove nums[left]
 * left++;
 * }
 * }
 *
 *
 * <p>
 * The important invariant is:
 * </p>
 * <p>
 * window size == k
 *
 * <p>
 * Once the window reaches size k, every movement of right
 * must be accompanied by removing the element at left.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * 2. Variable-Size Sliding Window
 * ==========================================================
 *
 * <p>
 * In a variable-size window, the window does NOT have a
 * predetermined fixed size.
 * </p>
 *
 * <p>
 * Instead, the size of the window changes according to a
 * condition defined by the problem.
 * </p>
 *
 * <p>
 * The general behavior is:
 * </p>
 * <p>
 * Expand the window
 * ↓
 * Check the condition
 * ↓
 * If necessary, shrink the window
 * ↓
 * Continue expanding
 *
 *
 * <p>
 * The right pointer normally expands the window:
 * </p>
 * <p>
 * right++
 *
 * <p>
 * The left pointer shrinks the window:
 * </p>
 * <p>
 * left++
 * <p>
 * <p>
 * Example conceptual flow:
 * <p>
 * [................]
 * ↑
 * left
 *
 * <p>
 * Expand:
 * </p>
 * <p>
 * [................]
 * ↑          ↑
 * left      right
 *
 * <p>
 * Continue expanding until the window reaches some problem-
 * specific condition.
 * </p>
 *
 * <p>
 * Once the condition requires the window to shrink, move left
 * forward:
 * </p>
 * <p>
 * [................]
 * ↑       ↑
 * left   right
 *
 * <p>
 * Keep shrinking while appropriate, and then continue
 * expanding with right.
 * </p>
 * <p>
 * <p>
 * Variable-Size Algorithm:
 * <p>
 * int left = 0;
 * <p>
 * for (int right = 0; right < n; right++) {
 * <p>
 * // Add the new element to the window
 * <p>
 * while (window violates the condition) {
 * <p>
 * // Remove nums[left] from the window
 * left++;
 * }
 * <p>
 * // Process the current valid window
 * }
 *
 *
 * <p>
 * Unlike the fixed-size version, the window size here is
 * determined dynamically.
 * </p>
 *
 * <p>
 * The key invariant is usually:
 * </p>
 * <p>
 * The current window satisfies the required condition.
 *
 * <p>
 * The exact condition depends on the problem.
 * It could involve:
 * </p>
 * <p>
 * - sum
 * - number of distinct elements
 * - character frequencies
 * - number of occurrences
 * - number of allowed/disallowed elements
 * - some other property of the current window
 * <p>
 * <p>
 * ==========================================================
 * Fixed vs Variable Window
 * ==========================================================
 *
 * <p>
 * Fixed-Size:
 * </p>
 * <p>
 * Window size is predetermined.
 * <p>
 * Example:
 * <p>
 * k = 3
 * <p>
 * [1, 2, 3]
 * [2, 3, 4]
 * [3, 4, 5]
 *
 *
 * <p>
 * Variable-Size:
 * </p>
 * <p>
 * Window size changes according to a condition.
 * <p>
 * [1, 2]
 * [1, 2, 3]
 * [1, 2, 3, 4]
 * ↓
 * shrink
 * ↓
 * [2, 3, 4]
 * <p>
 * <p>
 * ==========================================================
 * The Most Important Mental Model
 * ==========================================================
 *
 * <p>
 * Do NOT think of Sliding Window as simply:
 * </p>
 * <p>
 * "Two pointers moving through an array."
 *
 * <p>
 * Instead, think:
 * </p>
 * <p>
 * "I maintain a contiguous window and maintain some
 * useful information about that window while moving
 * its boundaries."
 *
 * <p>
 * Every Sliding Window problem requires us to answer three
 * important questions:
 * </p>
 * <p>
 * 1. What does my current window represent?
 * <p>
 * 2. What information/state do I need to maintain
 * about the window?
 * <p>
 * 3. When should I expand or shrink the window?
 *
 * <p>
 * Once these three questions are clear, the implementation
 * usually follows naturally.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Why Can Variable Sliding Window Still Be O(n)?
 * ==========================================================
 *
 * <p>
 * A common source of confusion is seeing a for-loop containing
 * a while-loop and assuming the complexity must be O(n²).
 * </p>
 *
 * <p>
 * However, in the typical Sliding Window implementation:
 * </p>
 * <p>
 * right moves forward at most n times.
 * <p>
 * left also moves forward at most n times.
 *
 * <p>
 * Neither pointer moves backward.
 * </p>
 *
 * <p>
 * Therefore, although the while-loop is nested inside the
 * for-loop, the total number of left-pointer movements across
 * the entire algorithm is at most n.
 * </p>
 * <p>
 * right movements -> O(n)
 * left movements  -> O(n)
 * <p>
 * total            -> O(n)
 * <p>
 * <p>
 * ==========================================================
 * Complexity
 * ==========================================================
 *
 * <p>
 * Typical Sliding Window:
 * </p>
 * <p>
 * Time  : O(n)
 *
 * <p>
 * Additional space depends on what information must be
 * maintained for the window.
 * </p>
 * <p>
 * Space : O(1)
 * or
 * O(k)
 * or
 * O(characters / distinct elements)
 *
 * <p>
 * The exact space complexity depends on the data structure
 * used to maintain the window's state.
 * </p>
 * <p>
 * <p>
 * ==========================================================
 * Summary
 * ==========================================================
 *
 * <p>
 * Sliding Window efficiently processes contiguous subarrays
 * or substrings by maintaining a window between left and right
 * pointers.
 * </p>
 *
 * <p>
 * Instead of recomputing information for every possible
 * window, we reuse the information from the previous window.
 * </p>
 *
 * <p>
 * There are two primary types:
 * </p>
 * <p>
 * 1. Fixed-Size Window
 * - Window size remains exactly k.
 * - Expand until size k.
 * - Process the window.
 * - Remove the outgoing element.
 * - Continue sliding.
 * <p>
 * 2. Variable-Size Window
 * - Window size changes dynamically.
 * - right expands the window.
 * - left shrinks the window.
 * - A problem-specific condition determines when
 * the window must shrink.
 *
 * <p>
 * The most important skill is not memorizing a template.
 * It is identifying:
 * </p>
 * <p>
 * What is my window?
 * What state does it maintain?
 * What makes the window valid or invalid?
 * When should I expand?
 * When should I shrink?
 * </p>
 */
public class SlidingWindowAlgorithm {
}