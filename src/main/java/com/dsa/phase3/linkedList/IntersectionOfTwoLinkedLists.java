package com.dsa.phase3.linkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * ==========================================================
 * Problem    : LeetCode 160 - Intersection of Two Linked Lists
 * Difficulty : Easy
 * Pattern    : Linked List / HashSet / Two Pointers
 * ==========================================================
 *
 * <p>
 * The goal is to determine whether two singly linked lists
 * intersect and, if they do, return the node at which they
 * intersect.
 *
 * <p>
 * Important:
 * Two lists intersect when they contain the exact same
 * {@code ListNode} object, not merely nodes having the same
 * value.
 *
 * <p>
 * Example:
 *
 * <p>
 * A: 1 -> 2 \
 * 7 -> 8
 * B: 3 -> 4 /
 *
 * <p>
 * The node containing {@code 7} is the intersection because
 * both lists point to the exact same node object.
 *
 * <p>
 * We can solve this problem using two different approaches:
 *
 * <p>
 * 1. HashSet approach
 * 2. Two-pointer approach
 *
 * <p>
 * ----------------------------------------------------------
 * Approach 1: HashSet
 * ----------------------------------------------------------
 *
 * <p>
 * Idea:
 * Store every node belonging to the first linked list in a
 * {@link HashSet}.
 *
 * <p>
 * Then traverse the second linked list. For every node, check
 * whether that exact node already exists in the set.
 *
 * <p>
 * Because a {@code HashSet} stores object references, finding
 * the same node means that both linked lists point to the same
 * {@code ListNode}.
 *
 * <p>
 * ----------------------------------------------------------
 * Step 1: Store all nodes of List A
 * ----------------------------------------------------------
 *
 * <p>
 * {@code tempA} traverses the first linked list and adds every
 * node to {@code setA}.
 *
 * <p>
 * After this traversal, {@code setA} contains references to
 * every node belonging to List A.
 *
 * <p>
 * ----------------------------------------------------------
 * Step 2: Traverse List B
 * ----------------------------------------------------------
 *
 * <p>
 * {@code tempB} traverses the second linked list.
 *
 * <p>
 * For every node, we use:
 *
 * <p>
 * {@code setA.add(tempB)}
 *
 * <p>
 * If the node was already present in the set, {@code add()}
 * returns {@code false}.
 *
 * <p>
 * Therefore, {@code !setA.add(tempB)} means that this node
 * already belongs to List A and is the first intersection
 * node encountered while traversing List B.
 *
 * <p>
 * We immediately return this node.
 *
 * <p>
 * If List B is completely traversed without finding a node
 * already present in the set, the lists do not intersect, so
 * we return {@code null}.
 *
 * <p>
 * Time Complexity  : O(m + n)
 * Space Complexity : O(m)
 *
 * <p>
 * where {@code m} is the length of List A and {@code n} is the
 * length of List B.
 *
 * <p>
 * ----------------------------------------------------------
 * Approach 2: Two Pointers - Optimal
 * ----------------------------------------------------------
 *
 * <p>
 * The HashSet solution uses extra space. We can solve the same
 * problem using constant extra space with two pointers.
 *
 * <p>
 * Idea:
 * Use two pointers:
 *
 * <p>
 * {@code a} starts at {@code headA}
 * {@code b} starts at {@code headB}
 *
 * <p>
 * Each pointer traverses its own list first. When it reaches
 * the end, instead of stopping, it switches to the head of
 * the other list.
 *
 * <p>
 * Therefore:
 *
 * <p>
 * {@code a}: A -> B
 *
 * <p>
 * {@code b}: B -> A
 *
 * <p>
 * This causes both pointers to travel exactly:
 *
 * <p>
 * {@code length(A) + length(B)}
 *
 * <p>
 * distance in total.
 *
 * <p>
 * ----------------------------------------------------------
 * Why switching lists works
 * ----------------------------------------------------------
 *
 * <p>
 * Suppose the lists look like this:
 *
 * <p>
 * A: a1 -> a2 -> c1 -> c2 -> c3
 *
 * <p>
 * B: b1 -> b2 -> b3 -> c1 -> c2 -> c3
 *
 * <p>
 * The two lists have different-length prefixes before their
 * common portion.
 *
 * <p>
 * Pointer {@code a} initially traverses A, while pointer
 * {@code b} initially traverses B.
 *
 * <p>
 * Once {@code a} reaches the end of A, it starts traversing B.
 * Once {@code b} reaches the end of B, it starts traversing A.
 *
 * <p>
 * This effectively cancels out the difference between the
 * lengths of the two prefixes.
 *
 * <p>
 * After switching:
 *
 * <p>
 * {@code a}: A -> B
 *
 * <p>
 * {@code b}: B -> A
 *
 * <p>
 * both pointers have travelled the same total distance when
 * they reach the common portion.
 *
 * <p>
 * Therefore, if an intersection exists, they eventually point
 * to the exact same {@code ListNode}.
 *
 * <p>
 * If the lists do not intersect, both pointers eventually become
 * {@code null} at the same time.
 *
 * <p>
 * ----------------------------------------------------------
 * Important Comparison
 * ----------------------------------------------------------
 *
 * <p>
 * We compare:
 *
 * <p>
 * {@code a != b}
 *
 * <p>
 * rather than:
 *
 * <p>
 * {@code a.val != b.val}
 *
 * <p>
 * because intersection is based on node identity.
 *
 * <p>
 * Two different nodes may contain the same value, but they are
 * not considered an intersection.
 *
 * <p>
 * Time Complexity  : O(m + n)
 * Space Complexity : O(1)
 *
 * <p>
 * ==========================================================
 */
public class IntersectionOfTwoLinkedLists {

    /**
     * ----------------------------------------------------------
     * Approach 1: HashSet
     * ----------------------------------------------------------
     *
     * <p>
     * Stores all nodes from List A and then searches for the
     * first node from List B that is already present in the set.
     */
    public ListNode getIntersectionNodeHashSet(ListNode headA, ListNode headB) {

        Set<ListNode> setA = new HashSet<>();

        ListNode tempA = headA;
        ListNode tempB = headB;

        // Step 1: Store every node from List A.
        while (tempA != null) {
            setA.add(tempA);
            tempA = tempA.next;
        }

        // Step 2: Traverse List B and look for a node
        // that already exists in List A.
        while (tempB != null) {

            // add() returns false if the node already exists.
            // Therefore, !add() means we found the intersection.
            if (!setA.add(tempB)) {
                return tempB;
            }

            tempB = tempB.next;
        }

        // No common node was found.
        return null;
    }

    /**
     * ----------------------------------------------------------
     * Approach 2: Two Pointers - Optimal
     * ----------------------------------------------------------
     *
     * <p>
     * Each pointer traverses both linked lists. Switching the
     * lists equalizes the total distance travelled by both
     * pointers.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode a = headA;
        ListNode b = headB;

        // Continue until both pointers point to the same node.
        //
        // They will either meet at the intersection node or
        // both become null if there is no intersection.
        while (a != b) {

            // When a reaches the end of A, move it to headB.
            // Otherwise, move it to the next node.
            a = (a == null) ? headB : a.next;

            // When b reaches the end of B, move it to headA.
            // Otherwise, move it to the next node.
            b = (b == null) ? headA : b.next;
        }

        // Either the intersection node or null.
        return a;
    }
}