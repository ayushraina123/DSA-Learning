package com.dsa.phase4.binarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode Problem 99: Recover Binary Search Tree
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/recover-binary-search-tree/
 * <p>
 * Problem Statement:
 * You are given the root of a Binary Search Tree (BST) where exactly
 * two nodes have been swapped by mistake.
 * <p>
 * Recover the tree without changing its structure.
 * <p>
 * Approach: Inorder Traversal + ArrayList
 * <p>
 * A Binary Search Tree produces sorted values when traversed using
 * inorder traversal (Left -> Root -> Right).
 * <p>
 * We use this property to identify the two incorrectly swapped nodes.
 * <p>
 * Steps:
 * <p>
 * 1. Perform an inorder traversal and store all visited TreeNode
 * references in an ArrayList.
 * <p>
 * 2. Find the first incorrectly positioned node (a).
 * - Find the first position where the current node's value is
 * greater than the next node's value.
 * - Assign the current node to a and break the loop.
 * <p>
 * 3. Find the second incorrectly positioned node (b).
 * - Find positions where the current node's value is smaller
 * than the previous node's value.
 * - Update b every time an inversion is found.
 * - Do not break because we need the last inversion.
 * <p>
 * 4. Swap the values of nodes a and b to recover the BST.
 * <p>
 * Why does the first loop use break but the second loop does not?
 * <p>
 * Consider the inorder sequence:
 * [1, 6, 3, 4, 5, 2, 7]
 * <p>
 * There are two inversions:
 * - 6 > 3
 * - 5 > 2
 * <p>
 * The first incorrectly positioned node is 6, identified by
 * the first inversion.
 * <p>
 * The second incorrectly positioned node is 2, identified by
 * the last inversion.
 * <p>
 * Therefore:
 * - First loop identifies the first node involved in an inversion.
 * - Second loop identifies the last node involved in an inversion.
 * <p>
 * Complexity Analysis:
 * <p>
 * Let n represent the number of nodes in the BST and h represent
 * the height of the tree.
 * <p>
 * Time Complexity: O(n)
 * - Inorder traversal visits every node exactly once: O(n).
 * - First iteration over the list takes O(n).
 * - Second iteration over the list takes O(n).
 * - Swapping the two node values takes O(1).
 * - Overall time complexity is O(n).
 * <p>
 * Space Complexity: O(n)
 * - The ArrayList stores references to all n tree nodes: O(n).
 * - Recursive inorder traversal consumes O(h) call stack space.
 * - Overall auxiliary space complexity is O(n).
 * <p>
 * Note:
 * This solution can be optimized to O(1) auxiliary space using
 * Morris Inorder Traversal, avoiding both the ArrayList and
 * recursion stack.
 */
public class RecoverBinarySearchTree {

    public void recoverTree(TreeNode root) {

        // Perform inorder traversal and store all nodes in a list.
        // Inorder traversal of a valid BST produces sorted values.
        List<TreeNode> list = traverse(root, new ArrayList<>());

        // References to the two incorrectly swapped nodes.
        TreeNode a = null;
        TreeNode b = null;

        // Find the first incorrectly positioned node.
        // The first inversion identifies the first swapped node.
        for (int i = 0; i < list.size() - 1; i++) {

            // An inversion occurs when the current value is greater
            // than the next value in the inorder sequence.
            if (list.get(i).val > list.get(i + 1).val) {

                // The current node is the first incorrectly
                // positioned node.
                a = list.get(i);

                // Stop because we only need the first inversion.
                break;
            }
        }

        // Find the second incorrectly positioned node.
        // We continue searching because we need the last inversion.
        for (int i = 1; i < list.size(); i++) {

            // Check whether the current value is smaller than
            // the previous value in the inorder sequence.
            if (list.get(i).val < list.get(i - 1).val) {

                // Update b every time an inversion is found.
                // After the loop, b will represent the second
                // incorrectly positioned node.
                b = list.get(i);
            }
        }

        // Swap the values of the two incorrectly positioned nodes.
        // The tree structure itself remains unchanged.
        if (a != null && b != null) {

            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }

    public List<TreeNode> traverse(TreeNode root, List<TreeNode> list) {

        // Recursively traverse the left subtree first.
        if (root.left != null) {
            traverse(root.left, list);
        }

        // Visit the current node and store its reference.
        list.add(root);

        // Recursively traverse the right subtree.
        if (root.right != null) {
            traverse(root.right, list);
        }

        // Return the list containing nodes in inorder sequence.
        return list;
    }
}


class MorrisTraversalSolution {

    public void recoverTree(TreeNode root) {

        // Current node used to perform Morris Inorder Traversal.
        // Initially, traversal starts from the root.
        TreeNode current = root;

        // Stores the previously visited node in inorder traversal.
        // Used to detect inversions between consecutive nodes.
        TreeNode prev = null;

        // Stores the first incorrectly positioned node.
        // Identified using the first inversion.
        TreeNode first = null;

        // Stores the second incorrectly positioned node.
        // Updated whenever an inversion is encountered.
        // The last inversion identifies the second swapped node.
        TreeNode second = null;

        // Perform Morris Inorder Traversal.
        // Unlike recursive or iterative inorder traversal,
        // this does not require a stack or recursion.
        while (current != null) {

            // CASE 1:
            // Current node does not have a left child.
            //
            // Since there is no left subtree to process,
            // we can immediately visit the current node.
            if (current.left == null) {

                // Check whether the previously visited node
                // is greater than the current node.
                // If true, an inversion exists in inorder sequence.
                if (prev != null && prev.val > current.val) {

                    // Identify the first incorrectly positioned node.
                    // Only assign it during the first inversion.
                    if (first == null) {
                        first = prev;
                    }

                    // Identify the second incorrectly positioned node.
                    // Keep updating it because we need the last inversion.
                    second = current;
                }

                // Update prev to the current node.
                // It becomes the previously visited node for
                // the next iteration.
                prev = current;

                // Move to the right child.
                // If a temporary thread exists, this may take
                // us back to an ancestor node.
                current = current.right;

            } else {

                // CASE 2 AND CASE 3:
                // Current node has a left subtree.
                //
                // Find its inorder predecessor.
                // The inorder predecessor is the rightmost node
                // in the current node's left subtree.
                TreeNode predecessor = current.left;

                // Keep moving right to find the rightmost node.
                //
                // Stop when:
                // 1. We reach a node whose right pointer is null.
                // 2. We encounter a temporary thread pointing
                //    back to the current node.
                //
                // The second condition prevents us from following
                // the temporary thread indefinitely.
                while (predecessor.right != null &&
                        predecessor.right != current) {

                    predecessor = predecessor.right;
                }

                // CASE 2:
                // No temporary thread exists.
                //
                // predecessor.right == null means we are visiting
                // this current node for the first time.
                //
                // Create a temporary link from the inorder
                // predecessor back to the current node.
                if (predecessor.right == null) {

                    // Create the temporary threaded link.
                    // This allows us to return to current after
                    // completely processing its left subtree.
                    predecessor.right = current;

                    // Move into the left subtree.
                    // Do not visit current yet because inorder
                    // requires processing the left subtree first.
                    current = current.left;

                } else {

                    // CASE 3:
                    // predecessor.right == current.
                    //
                    // This means we have returned to the current
                    // node after processing its entire left subtree.
                    //
                    // The temporary thread is no longer required.
                    // Remove it to restore the original tree structure.
                    predecessor.right = null;

                    // Now we can visit the current node because
                    // its entire left subtree has been processed.
                    //
                    // Check for an inversion between the previously
                    // visited node and the current node.
                    if (prev != null && prev.val > current.val) {

                        // Store the first incorrectly positioned node.
                        // This is assigned only once.
                        if (first == null) {
                            first = prev;
                        }

                        // Keep updating the second incorrectly
                        // positioned node on every inversion.
                        second = current;
                    }

                    // Update the previously visited node.
                    prev = current;

                    // Move to the right subtree to continue
                    // the inorder traversal.
                    current = current.right;
                }
            }
        }

        // Morris traversal has now completed.
        // All temporary threaded links have been removed.
        // The original tree structure is restored.
        //
        // Swap the values of the two incorrectly positioned nodes
        // to recover the Binary Search Tree.
        if (first != null && second != null) {

            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
}