package com.dsa.phase3.linkedList;

/**
 * ==========================================================
 * Problem    : LeetCode 2 - Add Two Numbers
 * Difficulty : Medium
 * Pattern    : Linked List / Carry
 * ==========================================================
 *
 * <p>
 * Idea:
 * We are given two linked lists representing two non-negative
 * integers. The digits are stored in reverse order, so the
 * least significant digit comes first.
 *
 * <p>
 * We add the corresponding digits of both linked lists while
 * maintaining a {@code carry} for the next position.
 *
 * <p>
 * For every pair of digits:
 *
 * <p>
 * {@code sum = digit1 + digit2 + carry}
 *
 * <p>
 * The digit stored in the result is:
 *
 * <p>
 * {@code sum % 10}
 *
 * <p>
 * and the carry for the next position is:
 *
 * <p>
 * {@code sum / 10}
 *
 * <p>
 * We first process both lists while both contain nodes.
 *
 * <p>
 * If one list is longer, we continue processing the remaining
 * nodes while adding the current {@code carry}.
 *
 * <p>
 * Finally, if a carry still remains after both lists have been
 * processed, we create one additional node for it.
 *
 * <p>
 * Example:
 *
 * <p>
 * {@code l1 = 2 -> 4 -> 3} represents 342
 *
 * <p>
 * {@code l2 = 5 -> 6 -> 4} represents 465
 *
 * <p>
 * The result is:
 *
 * <p>
 * {@code 7 -> 0 -> 8} representing 807.
 *
 * <p>
 * Time Complexity  : O(max(m, n))
 * Space Complexity : O(max(m, n))
 *
 * <p>
 * where {@code m} and {@code n} are the lengths of the two
 * linked lists. The space complexity accounts for the result
 * linked list.
 *
 * <p>
 * ==========================================================
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = null;
        ListNode temp;
        ListNode tail = null;

        ListNode temp1 = l1, temp2 = l2;

        int carry = 0;

        // Process both lists while both contain nodes.
        while (temp1 != null && temp2 != null) {

            temp = new ListNode(carry);

            // Calculate the carry for the next position.
            carry = (temp.val + temp1.val + temp2.val) / 10;

            // Store the current digit in the result node.
            temp.val = (temp.val + temp1.val + temp2.val) % 10;

            temp1 = temp1.next;
            temp2 = temp2.next;

            // Add the new node to the result list.
            if (head == null) {
                head = temp;
                tail = temp;
            } else {
                tail.next = temp;
                tail = tail.next;
            }
        }

        // Process remaining nodes of the first list.
        if (temp1 != null) {

            while (temp1 != null) {

                temp = new ListNode(carry);

                carry = (temp.val + temp1.val) / 10;
                temp.val = (temp.val + temp1.val) % 10;

                temp1 = temp1.next;

                if (head == null) {
                    head = temp;
                    tail = temp;
                } else {
                    tail.next = temp;
                    tail = tail.next;
                }
            }
        }

        // Process remaining nodes of the second list.
        if (temp2 != null) {

            while (temp2 != null) {

                temp = new ListNode(carry);

                carry = (temp.val + temp2.val) / 10;
                temp.val = (temp.val + temp2.val) % 10;

                temp2 = temp2.next;

                if (head == null) {
                    head = temp;
                    tail = temp;
                } else {
                    tail.next = temp;
                    tail = tail.next;
                }
            }
        }

        // If a carry remains, add it as the final node.
        if (carry != 0) {
            tail.next = new ListNode(carry);
        }

        return head;
    }
}