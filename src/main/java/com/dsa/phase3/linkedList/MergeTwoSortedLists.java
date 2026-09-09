package com.dsa.phase3.linkedList;

/**
 * ==========================================================
 * Problem    : LeetCode 21 - Merge Two Sorted Lists
 * Difficulty : Easy
 * Pattern    : Two Pointers / Linked List
 * ==========================================================
 * <p>
 * Idea:
 * We are given two sorted linked lists and need to merge them
 * into a single sorted linked list.
 * <p>
 * We maintain two temporary pointers:
 * <p>
 * - {@code temp1} traverses {@code list1}.
 * - {@code temp2} traverses {@code list2}.
 * <p>
 * We also maintain two pointers for the merged list:
 * <p>
 * - {@code head} points to the first node of the merged list.
 * - {@code tail} points to the last node of the merged list.
 * <p>
 * At every iteration, we compare the values of {@code temp1}
 * and {@code temp2}.
 * <p>
 * If {@code temp1.val} is smaller, we attach {@code temp1}
 * to the merged list and move {@code temp1} forward.
 * <p>
 * If {@code temp2.val} is smaller, we attach {@code temp2}
 * to the merged list and move {@code temp2} forward.
 * <p>
 * If both values are equal, we attach both nodes in sequence.
 * <p>
 * A very important detail in the equality case is that we move
 * {@code temp1} forward BEFORE modifying {@code tail.next}.
 * This is necessary because {@code tail} and {@code temp1} can
 * point to the same node. Therefore, modifying {@code tail.next}
 * also modifies the {@code next} field visible through
 * {@code temp1}.
 * <p>
 * Once one of the lists is exhausted, the remaining nodes of
 * the other list are already sorted, so we append all remaining
 * nodes directly to the merged list.
 * <p>
 * The solution reuses the existing nodes rather than creating
 * new nodes.
 * <p>
 * Time Complexity  : O(n + m)
 * Space Complexity : O(1)
 * <p>
 * ==========================================================
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // If either list is empty, return the other list directly.
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode head = null;
        ListNode tail = null;

        // Temporary pointers used to traverse both lists.
        ListNode temp1 = list1;
        ListNode temp2 = list2;

        // Continue until one of the lists is exhausted.
        while (temp1 != null && temp2 != null) {

            // If the current node of list1 is smaller,
            // attach it to the merged list.
            if (temp1.val < temp2.val) {

                if (head == null) {
                    head = temp1;
                    tail = temp1;
                } else {
                    tail.next = temp1;
                    tail = tail.next;
                }

                temp1 = temp1.next;

                // If the current node of list2 is smaller,
                // attach it to the merged list.
            } else if (temp1.val > temp2.val) {

                if (head == null) {
                    head = temp2;
                    tail = temp2;
                } else {
                    tail.next = temp2;
                    tail = tail.next;
                }

                temp2 = temp2.next;

                // If both values are equal, attach both nodes.
            } else {

                if (head == null) {
                    head = temp1;
                    tail = temp1;

                    // Move temp1 BEFORE modifying tail.next.
                    // tail and temp1 currently point to the same node.
                    temp1 = temp1.next;

                    tail.next = temp2;
                    tail = tail.next;

                    temp2 = temp2.next;

                } else {
                    tail.next = temp1;
                    tail = tail.next;

                    // Move temp1 before modifying tail.next,
                    // because tail and temp1 point to the same node.
                    temp1 = temp1.next;

                    tail.next = temp2;
                    tail = tail.next;

                    temp2 = temp2.next;
                }
            }
        }

        // Append all remaining nodes from list1.
        if (temp1 != null) {
            while (temp1 != null) {
                tail.next = temp1;
                tail = tail.next;
                temp1 = temp1.next;
            }
        }

        // Append all remaining nodes from list2.
        if (temp2 != null) {
            while (temp2 != null) {
                tail.next = temp2;
                tail = tail.next;
                temp2 = temp2.next;
            }
        }

        return head;
    }
}