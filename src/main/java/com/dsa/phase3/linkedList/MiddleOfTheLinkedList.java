package com.dsa.phase3.linkedList;

/**
 * ==========================================================
 * Problem    : LeetCode 876 - Middle of the Linked List
 * Difficulty : Easy
 * Pattern    : Two-Pass Linked List Traversal
 * ==========================================================
 * <p>
 * Idea:
 * To find the middle node of a linked list, we first need to
 * know the total number of nodes present in the list.
 * <p>
 * In the first traversal, {@code temp} starts from the head
 * and moves through every node until it reaches {@code null}.
 * During this traversal, {@code count} keeps track of the
 * total number of nodes.
 * <p>
 * Once the total number of nodes is known, we start another
 * traversal from the head using {@code ans}.
 * <p>
 * For a linked list containing {@code count} nodes, the middle
 * node is located at index {@code count / 2} when using
 * zero-based indexing.
 * <p>
 * For example:
 * <p>
 * - 1 → 2 → 3
 * count = 3, count / 2 = 1
 * Middle node = 2
 * <p>
 * - 1 → 2 → 3 → 4
 * count = 4, count / 2 = 2
 * Middle node = 3
 * <p>
 * The problem requires us to return the second middle node
 * when the linked list contains an even number of nodes.
 * Using {@code count / 2} naturally gives us the correct
 * zero-based index for both odd and even length lists.
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 * <p>
 * ==========================================================
 */
public class MiddleOfTheLinkedList {

    public ListNode middleNode(ListNode head) {
        int count = 0;

        // An empty linked list does not have a middle node.
        if (head == null) {
            return null;
        }

        ListNode temp = head;
        ListNode ans = head;

        // First traversal: Count the total number of nodes.
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // Move 'ans' to the middle node.
        // The middle node is located at index count / 2.
        for (int i = 1; i < count / 2 + 1; i++) {
            ans = ans.next;
        }

        return ans;
    }
}