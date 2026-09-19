package com.dsa.phase3.linkedList;

/**
 * ==========================================================
 * Problem    : LeetCode 92 - Reverse Linked List II
 * Difficulty : Medium
 * Pattern    : Linked List / Pointer Manipulation / Reversal
 * ==========================================================
 *
 * <p>
 * Idea:
 * We are given a linked list and two positions {@code left} and
 * {@code right}. We need to reverse only the portion of the list
 * between these two positions.
 *
 * <p>
 * First, we traverse the list to find:
 *
 * <p>
 * {@code leftNode}  - the first node of the section to reverse
 *
 * <p>
 * {@code rightNode} - the last node of the section to reverse
 *
 * <p>
 * We then make another traversal to find:
 *
 * <p>
 * {@code temp} - the node immediately before {@code leftNode}
 *
 * <p>
 * {@code temp2} - the node immediately after {@code rightNode}
 *
 * <p>
 * The section from {@code leftNode} to {@code rightNode} is then
 * reversed using the {@code reverse()} method.
 *
 * <p>
 * After reversal, {@code rightNode} becomes the first node of the
 * reversed section and {@code leftNode} becomes the last node.
 *
 * <p>
 * If {@code temp} exists, we reconnect it to {@code rightNode}.
 * Otherwise, the reversed section started at the head, so
 * {@code rightNode} becomes the new head.
 *
 * <p>
 * Finally, {@code leftNode} is connected to {@code temp2}, which
 * reconnects the reversed section to the remaining part of the list.
 *
 * <p>
 * If {@code left == right}, no reversal is required and the
 * original list is returned.
 *
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 *
 * <p>
 * ==========================================================
 */
public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null) {
            return head;
        }

        // No reversal is required when both positions are the same.
        if (left == right) {
            return head;
        }

        ListNode temp = null;
        ListNode temp2 = null;
        ListNode curr = head;
        ListNode leftNode = null;
        ListNode rightNode = null;
        int count = 0;

        // First pass: find the nodes at the left and right positions.
        while (curr != null) {

            count++;

            if (count == left) {
                leftNode = curr;
            }

            if (count == right) {
                rightNode = curr;
            }

            curr = curr.next;
        }

        curr = head;

        // Second pass: find the node before the reversed section
        // and the node after the reversed section.
        while (curr != null) {

            if (curr.next == leftNode) {
                temp = curr;
            }

            if (curr == rightNode) {
                temp2 = curr.next;
            }

            curr = curr.next;
        }

        // Reverse the section from leftNode to rightNode.
        reverse(temp, leftNode, rightNode);

        if (temp != null) {

            // Reconnect the node before the reversed section
            // to the new first node of the reversed section.
            temp.next = rightNode;

        } else {

            // If there is no previous node, the reversed section
            // started at the head.
            head = rightNode;
        }

        // leftNode becomes the last node of the reversed section.
        // Reconnect it to the remaining part of the list.
        leftNode.next = temp2;

        return head;
    }

    public void reverse(ListNode temp, ListNode left, ListNode right) {

        ListNode prev = temp;
        ListNode next = left.next;
        ListNode limit = right.next;

        // Reverse the section until the node after rightNode.
        while (next != limit) {

            next = left.next;

            left.next = prev;

            prev = left;
            left = next;
        }
    }
}