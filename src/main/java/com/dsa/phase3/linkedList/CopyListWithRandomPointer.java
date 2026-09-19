package com.dsa.phase3.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * Problem    : LeetCode 138 - Copy List with Random Pointer
 * Difficulty : Medium
 * Pattern    : Linked List / HashMap / Deep Copy
 * ==========================================================
 *
 * <p>
 * Idea:
 * We need to create a deep copy of a linked list where each node
 * contains both a {@code next} pointer and a {@code random} pointer.
 * The {@code random} pointer can point to any node in the list or
 * {@code null}.
 *
 * <p>
 * <p>
 * We use a {@link HashMap} to maintain a mapping between every
 * original node and its corresponding copied node:
 *
 * <p>
 * {@code Original Node -> Copied Node}
 *
 * <p>
 * We solve the problem in two passes.
 *
 * <p>
 * <p>
 * First pass:
 * We traverse the original list and create a new node for every
 * original node. While creating the copied list, we also store the
 * mapping between the original node and its copy in the HashMap.
 *
 * <p>
 * At the end of the first pass, the complete {@code next} structure
 * has been copied, but the {@code random} pointers have not yet
 * been connected.
 *
 * <p>
 * <p>
 * Second pass:
 * We traverse the original list again. For every original node,
 * we find its corresponding copied node using the HashMap.
 *
 * <p>
 * If the original node's {@code random} pointer is {@code null},
 * the copied node's {@code random} pointer is also {@code null}.
 * Otherwise, we use the HashMap to find the copied version of the
 * node referenced by {@code random}.
 *
 * <p>
 * For example, if:
 *
 * <p>
 * {@code A.random -> C}
 *
 * <p>
 * and the HashMap contains:
 *
 * <p>
 * {@code A -> A'}<br>
 * {@code C -> C'}
 *
 * <p>
 * then we set:
 *
 * <p>
 * {@code A'.random -> C'}
 *
 * <p>
 * This ensures that the copied list only contains references to
 * copied nodes and never references nodes from the original list.
 *
 * <p>
 * Time Complexity  : O(n)
 * Space Complexity : O(n)
 *
 * <p>
 * where {@code n} is the number of nodes in the linked list.
 *
 * <p>
 * ==========================================================
 */
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyListWithRandomPointer {

    public Node copyRandomList(Node node) {

        if (node == null)
            return null;

        Node head;
        Node tail;
        Node temp;

        Map<Node, Node> map = new HashMap<>();

        // Create the copy of the first node.
        head = new Node(node.val);
        tail = head;

        temp = node.next;

        // Map the original first node to its copied node.
        map.put(node, head);

        // First pass: create the copied linked list and
        // store the original -> copied node mapping.
        while (temp != null) {

            tail.next = new Node(temp.val);
            tail = tail.next;

            // Store the mapping between the original node
            // and its corresponding copied node.
            map.put(temp, tail);

            temp = temp.next;
        }

        Node copy = node;
        Node random;

        // Second pass: reconstruct the random pointers
        // using the original -> copied node mapping.
        while (copy != null) {

            random = copy.random;
            temp = map.get(copy);

            if (random == null) {
                temp.random = null;
            } else {
                // Find the copied version of the node pointed
                // to by the original node's random pointer.
                temp.random = map.get(random);
            }

            copy = copy.next;
        }

        return head;
    }
}