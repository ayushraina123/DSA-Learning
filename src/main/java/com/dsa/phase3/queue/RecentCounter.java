package com.dsa.phase3.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ==========================================================
 * Problem    : LeetCode 933 - Number of Recent Calls
 * Difficulty : Easy
 * Pattern    : Queue / Sliding Window / Amortized Analysis
 * ==========================================================
 *
 * <p>
 * Idea:
 * </p>
 *
 * <p>
 * The objective is to count the number of ping requests
 * received within the inclusive time interval [t - 3000, t]
 * whenever ping(t) is called.
 * </p>
 *
 * <p>
 * A queue is suitable because timestamps arrive in increasing
 * order. Therefore, the oldest timestamp is always at the
 * front, and expired timestamps can be removed efficiently
 * without scanning the entire collection.
 * </p>
 *
 * <p>
 * The queue stores timestamps that may still belong to the
 * current 3000-millisecond sliding window.
 * </p>
 *
 * <p>
 * Algorithm:
 * </p>
 *
 * <ol>
 *     <li>
 *         Remove timestamps from the front while the queue
 *         is not empty and the oldest timestamp is more than
 *         3000 milliseconds older than the current timestamp.
 *     </li>
 *     <li>
 *         Add the current timestamp to the rear of the queue.
 *     </li>
 *     <li>
 *         Return the queue size, which represents the number
 *         of requests in the current time window.
 *     </li>
 * </ol>
 *
 * <p>
 * Expiration condition:
 * </p>
 *
 * <pre>
 * t - queue.peek() > 3000
 * </pre>
 *
 * <p>
 * The condition uses > 3000 rather than >= 3000 because
 * the time interval is inclusive.
 * A timestamp exactly 3000 milliseconds old is still valid.
 * </p>
 *
 * <p>
 * Example:
 * </p>
 *
 * <pre>
 * RecentCounter counter = new RecentCounter();
 *
 * counter.ping(1);     // Returns 1
 * counter.ping(100);   // Returns 2
 * counter.ping(3001);  // Returns 3
 * counter.ping(3002);  // Returns 3
 * </pre>
 *
 * <p>
 * Walkthrough:
 * </p>
 *
 * <pre>
 * 1. ping(1)
 *
 *    Queue before: []
 *    No timestamps to remove.
 *    Queue after:  [1]
 *    Return: 1
 *
 * 2. ping(100)
 *
 *    Valid interval: [-2900, 100]
 *    Timestamp 1 is valid.
 *
 *    Queue after: [1, 100]
 *    Return: 2
 *
 * 3. ping(3001)
 *
 *    Valid interval: [1, 3001]
 *    Timestamp 1 is exactly 3000 milliseconds old,
 *    so it remains valid.
 *
 *    Queue after: [1, 100, 3001]
 *    Return: 3
 *
 * 4. ping(3002)
 *
 *    Valid interval: [2, 3002]
 *    Timestamp 1 is now outside the window because
 *    3002 - 1 = 3001 > 3000.
 *
 *    Remove 1 from the front.
 *    Timestamps 100 and 3001 are also expired.
 *    Remove both timestamps.
 *
 *    Queue after: [3002]
 *    Return: 1
 * </pre>
 *
 * <p>
 * Note: The return value for ping(3002) is 1, since
 * timestamps 1, 100, and 3001 are all outside the
 * interval [2, 3002].
 * </p>
 *
 * <p>
 * Important invariant:
 * </p>
 *
 * <p>
 * After each ping(t), the queue contains exactly the
 * timestamps within the inclusive interval [t - 3000, t].
 * Consequently, queue.size() gives the required answer.
 * </p>
 *
 * <p>
 * Why use a queue?
 * </p>
 *
 * <p>
 * Since timestamps arrive in increasing order, if the
 * oldest timestamp is still valid, every newer timestamp
 * is also valid. If a timestamp expires, it can be removed
 * permanently because future time windows only move forward.
 * </p>
 *
 * <p>
 * This avoids repeatedly scanning timestamps that are
 * already known to be valid.
 * </p>
 *
 * <p>
 * Time Complexity:
 * </p>
 *
 * <ul>
 *     <li>
 *         ping(): O(1) amortized
 *     </li>
 *     <li>
 *         Worst case for a single ping(): O(n), when many
 *         expired timestamps must be removed.
 *     </li>
 * </ul>
 *
 * <p>
 * Each timestamp is inserted exactly once and removed at
 * most once. Therefore, across m calls, the total work
 * is O(m), giving O(1) amortized time per call.
 * </p>
 *
 * <p>
 * Space Complexity: O(n)
 * </p>
 *
 * <p>
 * The queue stores the timestamps that remain inside the
 * current 3000-millisecond window, where n is the maximum
 * number of timestamps retained in that window.
 * </p>
 * <p>
 * ==========================================================
 */
public class RecentCounter {

    private final Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }

    /*
     * Counts requests in the inclusive interval [t - 3000, t].
     *
     * Removes expired timestamps from the front, adds the
     * current timestamp, and returns the number of valid calls.
     */
    public int ping(int t) {

        while (!queue.isEmpty() && t - queue.peek() > 3000) {
            queue.poll();
        }

        queue.add(t);
        return queue.size();
    }
}
