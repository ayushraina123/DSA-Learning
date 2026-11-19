package com.dsa.phase4.binarySearchTree;

/**
 * LeetCode Problem 450: Delete Node in a BST
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/delete-node-in-a-bst/
 * <p>
 * Problem Statement:
 * Given the root of a Binary Search Tree and an integer key,
 * delete the node with the given key and return the updated BST.
 * <p>
 * Approach: Recursive BST Search + Inorder Successor
 * <p>
 * There are three cases when deleting a node:
 * <p>
 * 1. Node has no left child:
 * Return its right child. This also handles leaf nodes,
 * where both children are null.
 * <p>
 * 2. Node has no right child:
 * Return its left child.
 * <p>
 * 3. Node has both children:
 * Find the inorder successor (smallest node in the right subtree).
 * Copy its value into the node being deleted.
 * Recursively delete the successor from the right subtree.
 * <p>
 * Complexity Analysis:
 * <p>
 * Let h represent the height of the BST.
 * <p>
 * Time Complexity: O(h)
 * - Searching for the node takes O(h).
 * - Finding the inorder successor takes O(h).
 * - Deleting the successor takes O(h).
 * <p>
 * Space Complexity: O(h)
 * - Recursive calls consume stack space proportional to tree height.
 * - Finding the inorder successor iteratively requires O(1) space.
 * <p>
 * Balanced BST: O(log n) time and space.
 * Skewed BST: O(n) time and space.
 */
public class DeleteNodeInBST {

    public TreeNode deleteNode(TreeNode root, int key) {
        // If the tree is empty, there is nothing to delete.
        if (root == null) return null;

        return deleteNodeRecursive(root, key);
    }

    public TreeNode deleteNodeRecursive(TreeNode root, int key) {
        // Base case: the key does not exist in this subtree.
        if (root == null) return null;

        // Found the node that needs to be deleted.
        if (root.val == key) {

            // Case 1: No left child.
            // Replace the current node with its right child.
            if (root.left == null) return root.right;

            // Case 2: No right child.
            // Replace the current node with its left child.
            if (root.right == null) return root.left;

            // Case 3: Node has both left and right children.
            // Find the smallest node in the right subtree.
            // This is the inorder successor.
            TreeNode inOrderSuccessor = findInorderSuccessor(root.right);

            // Copy the successor's value into the node being deleted.
            // We retain the original node instead of physically
            // moving the successor node.
            root.val = inOrderSuccessor.val;

            // Delete the inorder successor from its original position.
            // Since the successor is in the right subtree, recursively
            // update the right child reference.
            root.right = deleteNodeRecursive(root.right, root.val);

            // Return the original node with its updated value and subtree.
            return root;
        }

        // If the current value is smaller than the key,
        // the key can only exist in the right subtree.
        if (root.val < key) {
            root.right = deleteNodeRecursive(root.right, key);
        } else {
            // If the current value is greater than the key,
            // search in the left subtree.
            root.left = deleteNodeRecursive(root.left, key);
        }

        // Return the current node after updating the appropriate subtree.
        return root;
    }

    public TreeNode findInorderSuccessor(TreeNode root) {

        // The inorder successor is the smallest value in the right subtree.
        // Keep moving left until we reach the leftmost node.
        while (root.left != null) {
            root = root.left;
        }

        // The leftmost node is the inorder successor.
        return root;
    }
}
