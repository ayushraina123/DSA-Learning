package com.dsa.phase3.linkedList;

/**
 * ==========================================================
 * Problem    : LeetCode 234 - Palindrome Linked List
 * Difficulty : Easy
 * Pattern    : Linked List / Fast & Slow Pointers / Reversal
 * ==========================================================
 *
 * <p>
 * Idea:
 * We need to determine whether the linked list reads the same
 * from left to right and right to left.
 *
 * <p>
 * Instead of using extra space such as an array or stack, we
 * can solve the problem in three steps:
 *
 * <p>
 * 1. Find the middle of the linked list.
 * 2. Reverse the second half of the linked list.
 * 3. Compare the first half with the reversed second half.
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
 * The loop condition also checks {@code fast.next.next} so that
 * for both even and odd length lists, {@code slow} stops at the
 * node immediately before the portion that will be reversed.
 *
 * <p>
 * For example, for:
 *
 * <p>
 * 1 -> 0 -> 1
 *
 * <p>
 * {@code slow} stops at the middle node {@code 0}.
 * Therefore, {@code slow.next} points to the second half:
 *
 * <p>
 * First half:  1 -> 0
 * Second half: 1
 *
 * <p>
 * For:
 *
 * <p>
 * 1 -> 2 -> 2 -> 1
 *
 * <p>
 * {@code slow} stops at the second node:
 *
 * <p>
 * First half:  1 -> 2
 * Second half: 2 -> 1
 *
 * <p>
 * ----------------------------------------------------------
 * Step 2: Reverse the second half
 * ----------------------------------------------------------
 *
 * <p>
 * {@code slow.next} gives us the first node of the second half.
 * We reverse this portion using the standard linked-list
 * reversal technique.
 *
 * <p>
 * Before reversing, we disconnect the two halves:
 *
 * <p>
 * {@code slow.next = null}
 *
 * <p>
 * This ensures that the first and second halves become
 * independent lists.
 *
 * <p>
 * ----------------------------------------------------------
 * Step 3: Compare both halves
 * ----------------------------------------------------------
 *
 * <p>
 * {@code temp1} traverses the first half while {@code temp2}
 * traverses the reversed second half.
 *
 * <p>
 * For every node, we compare their values.
 *
 * <p>
 * If any pair of values is different, the list cannot be a
 * palindrome, so we immediately return {@code false}.
 *
 * <p>
 * For an odd-length list, the first half contains one extra
 * node because it also contains the middle node.
 * This does not matter because the second half is the shorter
 * portion, and we only need to compare all nodes in the second
 * half with their corresponding nodes from the first half.
 *
 * <p>
 * If the entire second half is successfully compared without
 * finding a mismatch, the list is a palindrome.
 *
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 *
 * <p>
 * ==========================================================
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {

        // A list with zero or one node is always a palindrome.
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Find the middle of the list.
        //
        // slow moves one step while fast moves two steps.
        // The additional fast.next.next check makes slow stop
        // at the node immediately before the second half.
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half.
        ListNode nodeToReverse = slow.next;

        // Disconnect the first half from the second half.
        slow.next = null;

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

        // Step 3: Compare the first half with the reversed
        // second half.
        ListNode temp1 = head;
        ListNode temp2 = prev;

        while (temp1 != null && temp2 != null) {

            // A mismatch means the list is not a palindrome.
            if (temp1.val != temp2.val) {
                return false;
            }

            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        // If no mismatch was found, the list is a palindrome.
        //
        // For odd-length lists, temp1 may still contain the
        // middle node. That node does not need to be compared.
        return true;
    }
}