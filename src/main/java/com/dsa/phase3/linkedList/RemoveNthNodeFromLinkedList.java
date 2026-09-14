package com.dsa.phase3.linkedList;

/**
 * ==========================================================
 * Problem    : LeetCode 19 - Remove Nth Node From End of List
 * Difficulty : Medium
 * Pattern    : Linked List / Two Passes
 * ==========================================================
 *
 * <p>
 * Idea:
 * We are given a linked list and an integer {@code n}, and need
 * to remove the nth node from the end of the list.
 * <p>
 * <p>
 * Instead of using the two-pointer approach, we first traverse
 * the entire list to calculate its length.
 * <p>
 * <p>
 * Once we know the total number of nodes {@code count}, the
 * position of the nth node from the end can be converted into
 * its position from the beginning:
 * <p>
 * Position from beginning = count - n
 * <p>
 * For example, if the list contains 5 nodes and {@code n = 2},
 * the node to remove is at position:
 * <p>
 * 5 - 2 = 3
 * <p>
 * So the 3rd node from the beginning needs to be removed.
 * <p>
 * <p>
 * We handle three cases separately:
 * <p>
 * 1. {@code n > count}
 * The requested position does not exist, so we return null.
 * <p>
 * 2. {@code n == count}
 * The nth node from the end is the first node.
 * Therefore, we remove the head and make {@code head.next}
 * the new head.
 * <p>
 * 3. {@code n < count}
 * The node to remove is somewhere after the head.
 * We move {@code temp} to the node immediately before the
 * node that needs to be removed.
 *
 * <p>
 * Once {@code temp} is at the previous node, we store the node
 * to remove in {@code nodeToRemove}.
 * <p>
 * nodeToRemove = temp.next
 * <p>
 * Then we bypass it:
 * <p>
 * temp.next = nodeToRemove.next
 * <p>
 * Finally, we disconnect the removed node from the list:
 * <p>
 * nodeToRemove.next = null
 * <p>
 * This explicitly removes the connection from the removed node
 * to the remaining list.
 *
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 *
 * <p>
 * ==========================================================
 */
public class RemoveNthNodeFromLinkedList {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode temp = head;
        int count = 0;

        // First pass: calculate the total number of nodes.
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // If n is greater than the list size, the requested
        // node does not exist.
        if (n > count) return null;

        // If n equals the list size, the node to remove is
        // the head node.
        if (n == count) {

            // Move head to the second node.
            temp = head.next;

            // Disconnect the old head from the list.
            head.next = null;

            // Make the second node the new head.
            head = temp;

            return head;
        }

        // Second pass: move temp to the node immediately
        // before the node that needs to be removed.
        //
        // count - n gives the position of the node to remove
        // from the beginning.
        //
        // Therefore, we need to stop one position before it.
        for (int i = 0; i < count - n - 1; i++) {
            temp = temp.next;
        }

        // temp.next is the node that needs to be removed.
        ListNode nodeToRemove = temp.next;

        // Bypass the node being removed.
        temp.next = nodeToRemove.next;

        // Disconnect the removed node from the remaining list.
        nodeToRemove.next = null;

        return head;
    }
}