package com.dsa.phase3.linkedList;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * ==========================================================
 * Problem    : LeetCode 206 - Reverse Linked List
 * Difficulty : Easy
 * Pattern    : Iterative Linked List Reversal
 * ==========================================================
 * <p>
 * Idea:
 * To reverse a linked list, we need to change the direction
 * of every node's {@code next} pointer.
 * <p>
 * Initially, {@code prev} is {@code null} because the original
 * head will become the last node of the reversed linked list.
 * Therefore, its {@code next} pointer must eventually point
 * to {@code null}.
 * <p>
 * {@code temp} represents the current node being processed.
 * Before changing {@code temp.next}, we store its original
 * next node in {@code next}. This is necessary because once
 * {@code temp.next} is changed to point backward, we would
 * otherwise lose access to the remaining nodes in the original
 * linked list.
 * <p>
 * During every iteration:
 * <p>
 * 1. Store the next node in {@code next}.
 * <p>
 * 2. Reverse the current node's pointer by making
 * {@code temp.next} point to {@code prev}.
 * <p>
 * 3. Move {@code prev} forward to the current node.
 * <p>
 * 4. Move {@code temp} forward using the previously stored
 * {@code next} reference.
 * <p>
 * For example:
 * <p>
 * Before:
 * <p>
 * {@code 1 -> 2 -> 3 -> null}
 * <p>
 * After processing node {@code 1}:
 * <p>
 * {@code null <- 1    2 -> 3 -> null}
 * <p>
 * After processing node {@code 2}:
 * <p>
 * {@code null <- 1 <- 2    3 -> null}
 * <p>
 * After processing node {@code 3}:
 * <p>
 * {@code null <- 1 <- 2 <- 3}
 * <p>
 * Once {@code temp} becomes {@code null}, {@code prev} points
 * to the new head of the reversed linked list.
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 * <p>
 * ==========================================================
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;
        ListNode next;

        while (temp != null) {
            // Store the next node before changing the current
            // node's next pointer.
            next = temp.next;

            // Reverse the current node's pointer.
            temp.next = prev;

            // Move prev forward to the current node.
            prev = temp;

            // Move to the next node in the original list.
            temp = next;
        }

        // 'prev' points to the new head of the reversed list.
        return prev;
    }
}