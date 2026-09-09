package com.dsa.phase3.linkedList;

/**
 * ==========================================================
 * Problem    : LeetCode 141 - Linked List Cycle
 * Difficulty : Easy
 * Pattern    : Floyd's Cycle Detection Algorithm
 * ==========================================================
 * <p>
 * Idea:
 * To determine whether a linked list contains a cycle, we use
 * two pointers moving at different speeds.
 * <p>
 * {@code slow} moves one node at a time, while {@code fast}
 * moves two nodes at a time.
 * <p>
 * Both pointers initially start at the head of the linked list.
 * Although they initially point to the same node, we do not
 * immediately consider this as a cycle. Instead, both pointers
 * first move according to their respective speeds.
 * <p>
 * During each iteration:
 * <p>
 * 1. {@code slow} moves one step forward.
 * <p>
 * 2. {@code fast} moves two steps forward.
 * <p>
 * If the linked list contains a cycle, {@code fast} will
 * eventually catch up with {@code slow} inside the cycle.
 * When both pointers point to the same node after moving,
 * the linked list contains a cycle.
 * <p>
 * If the linked list does not contain a cycle, {@code fast}
 * will eventually reach {@code null}, or its next node will
 * be {@code null}.
 * <p>
 * Therefore, before moving {@code fast} two steps, we check:
 * <p>
 * - {@code fast != null}
 * <p>
 * - {@code fast.next != null}
 * <p>
 * If either condition fails, the linked list does not contain
 * a cycle.
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 * <p>
 * ==========================================================
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Continue as long as 'fast' can safely move two steps.
        while (fast != null && fast.next != null) {

            // Slow pointer moves one node at a time.
            slow = slow.next;

            // Fast pointer moves two nodes at a time.
            fast = fast.next.next;

            // If both pointers meet, a cycle exists.
            if (slow == fast) {
                return true;
            }
        }

        // If 'fast' reaches the end, no cycle exists.
        return false;
    }
}