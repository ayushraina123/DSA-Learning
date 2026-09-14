package com.dsa.phase3.linkedList;

/**
 * ==========================================================
 * Problem    : LeetCode 143 - Reorder List
 * Difficulty : Medium
 * Pattern    : Linked List / Fast & Slow Pointers / Reversal
 * ==========================================================
 *
 * <p>
 * Idea:
 * We are given a linked list:
 * <p>
 * <p>
 * 1 -> 2 -> 3 -> 4 -> 5
 *
 * <p>
 * and need to reorder it as:
 * <p>
 * <p>
 * 1 -> 5 -> 2 -> 4 -> 3
 *
 * <p>
 * The list can be reordered by breaking the problem into
 * three simple steps:
 *
 * <p>
 * 1. Find the middle of the linked list.
 * 2. Reverse the second half.
 * 3. Merge the two halves alternately.
 *
 * <p>
 * ----------------------------------------------------------
 * Step 1: Find the middle
 * ----------------------------------------------------------
 *
 * <p>
 * We use the slow and fast pointer technique.
 *
 * <p>
 * {@code slow} moves one node at a time while {@code fast}
 * moves two nodes at a time.
 *
 * <p>
 * For an odd-length list:
 * <p>
 * <p>
 * 1 -> 2 -> 3 -> 4 -> 5
 * ^
 * slow
 *
 * <p>
 * {@code slow} ends up at the last node of the first half.
 *
 * <p>
 * The condition:
 *
 * <p>
 * {@code fast != null && fast.next != null && fast.next.next != null}
 *
 * <p>
 * is used so that for an odd-length list, {@code slow} stops
 * at the exact node from which the second half should begin.
 *
 * <p>
 * For example:
 *
 * <p>
 * 1 -> 2 -> 3 -> 4 -> 5
 * ^
 * slow
 *
 * <p>
 * Therefore:
 *
 * <p>
 * First half  = 1 -> 2 -> 3
 * Second half = 4 -> 5
 *
 * <p>
 * ----------------------------------------------------------
 * Step 2: Reverse the second half
 * ----------------------------------------------------------
 *
 * <p>
 * {@code slow.next} points to the beginning of the second half.
 * We reverse this portion using the standard linked-list
 * reversal technique.
 *
 * <p>
 * For example:
 *
 * <p>
 * 1 -> 2 -> 3 | 4 -> 5
 *
 * <p>
 * becomes:
 *
 * <p>
 * 1 -> 2 -> 3 | 5 -> 4
 *
 * <p>
 * After reversing, {@code prev} points to the new head of
 * the reversed second half.
 *
 * <p>
 * We then set:
 *
 * <p>
 * {@code slow.next = null}
 *
 * <p>
 * to disconnect the first half from the reversed second half.
 *
 * <p>
 * ----------------------------------------------------------
 * Step 3: Merge the two halves
 * ----------------------------------------------------------
 *
 * <p>
 * We now have two separate lists:
 *
 * <p>
 * First half:
 * 1 -> 2 -> 3
 *
 * <p>
 * Second half:
 * 5 -> 4
 *
 * <p>
 * We merge them by taking one node from the first half,
 * followed by one node from the second half.
 *
 * <p>
 * 1 -> 5 -> 2 -> 4 -> 3
 *
 * <p>
 * Before changing any links, we store the next nodes of both
 * lists in {@code temp1Next} and {@code temp2Next}.
 * This is necessary because changing {@code next} pointers
 * would otherwise cause us to lose access to the remaining
 * nodes.
 *
 * <p>
 * The merge continues until the second half is completely
 * processed.
 *
 * <p>
 * For an odd-length list, the first half contains one more
 * node than the second half. Therefore, the final node of the
 * first half naturally remains at the end of the reordered list.
 *
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 *
 * <p>
 * ==========================================================
 */
public class ReorderList {

    public void reorderList(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        // If the list contains fewer than two nodes,
        // there is nothing to reorder.
        if (head == null || head.next == null)
            return;

        // Step 1: Find the middle of the list.
        //
        // slow moves one step while fast moves two steps.
        // The additional fast.next.next check makes slow
        // stop at the end of the first half.
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half.
        //
        // slow.next is the first node of the second half.
        ListNode nodeToReverse = slow.next;

        ListNode prev = null;
        ListNode curr = nodeToReverse;
        ListNode next;

        // Standard linked-list reversal.
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Disconnect the first half from the reversed second half.
        slow.next = null;

        // Step 3: Merge the two halves alternately.
        //
        // temp1 points to the first half.
        // temp2 points to the reversed second half.
        ListNode temp1 = head;
        ListNode temp2 = prev;

        ListNode temp1Next;
        ListNode temp2Next;

        while (temp1 != null && temp2 != null) {

            // Store the next nodes before changing any links.
            temp1Next = temp1.next;
            temp2Next = temp2.next;

            // Insert the second-half node after the
            // current first-half node.
            temp1.next = temp2;
            temp1 = temp1Next;

            // Connect the second-half node to the next
            // first-half node.
            temp2.next = temp1Next;
            temp2 = temp2Next;
        }
    }
}