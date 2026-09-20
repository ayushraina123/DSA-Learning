package com.dsa.phase3.linkedList;

/**
 * ==========================================================
 * Problem    : LeetCode 25 - Reverse Nodes in K-Group
 * Difficulty : Hard
 * Pattern    : Linked List / Pointer Manipulation / Reversal
 * ==========================================================
 *
 * <p>
 * Idea:
 * We are given a linked list and an integer {@code k}. We need to
 * reverse the nodes of the list in groups of {@code k}.
 * </p>
 *
 * <p>
 * The list is processed one group at a time. During traversal,
 * {@code leftNode} marks the first node of the current group and
 * {@code temp} eventually becomes the last node of the group.
 * </p>
 *
 * <p>
 * The pointer {@code start} represents the node immediately before
 * the current group. It is {@code null} for the first group.
 * After a group is reversed, {@code start} is moved to the original
 * first node of that group, which becomes the last node after reversal.
 * </p>
 *
 * <p>
 * When {@code count == k}, the current group is complete. We save
 * {@code end}, which is the node immediately after the group, and
 * reverse the section from {@code leftNode} up to {@code rightNode}.
 * </p>
 *
 * <p>
 * The {@code reverse()} method uses {@code start} as the initial
 * previous node and {@code rightNode.next} as the stopping boundary.
 * This allows the exact group to be reversed without affecting the
 * remaining part of the list.
 * </p>
 *
 * <p>
 * After reversal, {@code rightNode} becomes the first node of the
 * reversed group and {@code leftNode} becomes the last node.
 * </p>
 *
 * <p>
 * If {@code start} is {@code null}, the reversed group started at
 * the head, so {@code rightNode} becomes the new head. Otherwise,
 * {@code start.next} is connected to {@code rightNode}.
 * </p>
 *
 * <p>
 * Finally, {@code leftNode} is connected to {@code end} to reconnect
 * the reversed group with the remaining portion of the list.
 * {@code start} is then moved to {@code leftNode} so it can serve as
 * the node before the next group.
 * </p>
 *
 * <p>
 * If fewer than {@code k} nodes remain at the end of the list,
 * {@code count} never reaches {@code k}, so those nodes remain
 * unchanged.
 * </p>
 *
 * <p>
 * Time Complexity  : O(n)
 * </p>
 *
 * <p>
 * Space Complexity : O(1)
 * </p>
 *
 * <p>
 * ==========================================================
 */
public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }

        int count = 0;
        ListNode temp = head;
        ListNode start = null;
        ListNode next;
        ListNode end;
        ListNode leftNode = null;
        ListNode rightNode;

        while (temp != null) {

            count++;

            next = temp.next;

            if (leftNode == null) {
                leftNode = temp;
            }

            if (count == k) {

                rightNode = temp;
                end = temp.next;

                // Reverse the current group from leftNode to rightNode.
                reverse(start, leftNode, rightNode.next);

                if (start == null) {

                    // The first group starts at the head,
                    // so rightNode becomes the new head.
                    head = rightNode;

                } else {

                    // Connect the previous group to the
                    // newly reversed current group.
                    start.next = rightNode;
                }

                // leftNode becomes the last node of the reversed group.
                // Connect it to the remaining part of the list.
                leftNode.next = end;

                // leftNode is now the node before the next group.
                start = leftNode;

                // Reset the first node of the next group.
                leftNode = null;

                count = 0;
            }

            temp = next;
        }

        return head;
    }

    public void reverse(ListNode start, ListNode leftNode, ListNode limit) {

        ListNode prev = start;
        ListNode next = leftNode;

        // Reverse nodes until the boundary of the current group.
        while (next != limit) {

            next = leftNode.next;

            leftNode.next = prev;

            prev = leftNode;

            leftNode = next;
        }
    }
}