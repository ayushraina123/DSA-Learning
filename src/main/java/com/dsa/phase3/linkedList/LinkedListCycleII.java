package com.dsa.phase3.linkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * ==========================================================
 * Problem    : LeetCode 142 - Linked List Cycle II
 * Difficulty : Medium
 * Pattern    : HashSet
 * ==========================================================
 * <p>
 * Idea:
 * To find the node where a cycle begins, we keep track of
 * every node that has already been visited using a HashSet.
 * <p>
 * We start traversing the linked list from the head.
 * <p>
 * During each iteration, we attempt to add the current node
 * to the HashSet.
 * <p>
 * {@code Set.add()} returns:
 * <p>
 * - {@code true} if the node does not already exist in the set.
 * <p>
 * - {@code false} if the node has already been visited.
 * <p>
 * If we encounter a node that already exists in the set, it
 * means that we have reached that node before.
 * <p>
 * The first node encountered for the second time is exactly
 * the node where the cycle begins, so we return that node.
 * <p>
 * If {@code temp} eventually becomes {@code null}, the linked
 * list does not contain a cycle. Therefore, we return
 * {@code null}.
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(n)
 * <p>
 * ==========================================================
 */
public class LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = head;

        // Traverse the linked list until we either encounter
        // a previously visited node or reach the end.
        while (temp != null) {

            // If the current node already exists in the set,
            // it is the starting node of the cycle.
            if (!set.add(temp)) {
                return temp;
            }

            // Move to the next node.
            temp = temp.next;
        }

        // No cycle exists in the linked list.
        return null;
    }
}