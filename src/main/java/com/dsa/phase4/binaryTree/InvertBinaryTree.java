package com.dsa.phase4.binaryTree;

/**
 * LeetCode Problem 226: Invert Binary Tree
 *
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/invert-binary-tree/
 *
 * <p>
 * Difficulty: Easy
 * Topics: Binary Tree, DFS, Recursion, Tree Traversal
 *
 * <p>
 * Problem Statement:
 *
 * <p>
 * Given the root of a binary tree, invert the tree and return
 * its root.
 *
 * <p>
 * Inverting a binary tree means swapping the left and right
 * children of every node in the tree.
 *
 * <p>
 * Approach: Recursive DFS (Depth First Search)
 *
 * <p>
 * We use recursion to invert the left and right subtrees
 * independently before swapping the children of the current node.
 *
 * <p>
 * Mathematical Observation:
 *
 * <p>
 * To invert a binary tree:
 *
 * <p>
 * 1. Invert the left subtree.
 * <p>
 * 2. Invert the right subtree.
 * <p>
 * 3. Swap the left and right subtree references of the
 * current root node.
 *
 * <p>
 * Therefore, the recursive relationship is:
 *
 * <p>
 * invertTree(root):
 * <p>
 * left = invertTree(root.left)
 * right = invertTree(root.right)
 * <p>
 * root.left = right
 * root.right = left
 * <p>
 * return root
 *
 * <p>
 * Algorithm:
 *
 * <p>
 * 1. Base Condition:
 *
 * <p>
 * If the current node is null, return null.
 *
 * <p>
 * This means we have reached the end of a particular
 * branch, and there is nothing left to invert.
 *
 * <p>
 * 2. Recursively invert the left subtree.
 *
 * <p>
 * The recursive call returns the root reference of
 * the inverted left subtree.
 *
 * <p>
 * 3. Recursively invert the right subtree.
 *
 * <p>
 * Similarly, the recursive call returns the root reference
 * of the inverted right subtree.
 *
 * <p>
 * 4. Swap the left and right subtree references.
 *
 * <p>
 * Assign the inverted right subtree to root.left and
 * the inverted left subtree to root.right.
 *
 * <p>
 * 5. Return the current root node.
 *
 * <p>
 * This returns the root of the completely inverted subtree
 * to the parent recursive call.
 *
 * <p>
 * Understanding Recursive Returns:
 *
 * <p>
 * Consider the following binary tree:
 *
 * <p>
 * 4
 * / \
 * 2   7
 * / \ / \
 * 1  3 6  9
 *
 * <p>
 * Execution:
 *
 * <p>
 * 1. The initial call is:
 * <p>
 * invertTree(4)
 * <p>
 * The method makes two recursive calls:
 * <p>
 * invertTree(2)
 * invertTree(7)
 * <p>
 * The current call pauses until both recursive calls return.
 *
 * <p>
 * 2. Consider the left subtree rooted at node 2.
 * <p>
 * The method recursively processes nodes 1 and 3.
 * <p>
 * Both are leaf nodes, so their recursive calls eventually
 * reach null and return null.
 * <p>
 * Now node 2 swaps its children:
 * <p>
 * 2
 * / \
 * 3   1
 * <p>
 * It returns the reference to node 2.
 *
 * <p>
 * 3. Similarly, the right subtree rooted at node 7
 * gets inverted:
 * <p>
 * 7
 * / \
 * 9   6
 * <p>
 * It returns the reference to node 7.
 *
 * <p>
 * 4. Now the original call at node 4 resumes.
 * <p>
 * It receives the returned references:
 * <p>
 * left  = node 2 (inverted subtree)
 * right = node 7 (inverted subtree)
 * <p>
 * It swaps these references:
 * <p>
 * root.left = right
 * root.right = left
 *
 * <p>
 * 5. The final inverted tree becomes:
 * <p>
 * 4
 * / \
 * 7   2
 * / \ / \
 * 9  6 3  1
 * <p>
 * Finally, node 4 is returned as the root of the
 * completely inverted binary tree.
 *
 * <p>
 * Understanding Why We Return root:
 *
 * <p>
 * Each recursive call is responsible for inverting its
 * own subtree.
 *
 * <p>
 * After both children have been inverted and swapped,
 * the current root becomes the root of the inverted subtree.
 *
 * <p>
 * Returning this root reference allows the parent recursive
 * call to attach the inverted subtree at the correct position.
 *
 * <p>
 * Important:
 *
 * <p>
 * The inversion happens in-place.
 * <p>
 * We are not creating new TreeNode objects. Instead, we
 * modify the existing left and right references of each node.
 *
 * <p>
 * This is an example of Post-order DFS traversal because
 * the current node is processed after its left and right
 * subtrees have been recursively processed.
 *
 * <p>
 * Complexity Analysis:
 *
 * <p>
 * Time Complexity: O(n)
 * <p>
 * We visit every node exactly once to invert its children.
 * <p>
 * Here, n represents the total number of nodes in the tree.
 *
 * <p>
 * Space Complexity: O(h)
 * <p>
 * The recursive call stack can go as deep as the height
 * of the tree.
 * <p>
 * In a balanced binary tree: O(log n)
 * In a skewed binary tree: O(n)
 */
public class InvertBinaryTree {

    /**
     * Inverts a binary tree using recursive DFS.
     *
     * @param root the root node of the binary tree
     * @return the root of the inverted binary tree
     */
    public TreeNode invertTree(TreeNode root) {

        /*
         * Base condition:
         *
         * If the current node is null, we have reached
         * the end of a particular branch.
         *
         * There is nothing left to invert, so return null.
         */
        if (root == null) return null;

        /*
         * Recursively invert the left subtree.
         *
         * The current method call pauses until the left
         * subtree has been completely inverted.
         *
         * The recursive call returns the root reference
         * of the inverted left subtree.
         */
        TreeNode left = invertTree(root.left);

        /*
         * Recursively invert the right subtree.
         *
         * Similarly, this call returns the root reference
         * of the inverted right subtree.
         */
        TreeNode right = invertTree(root.right);

        /*
         * Once both recursive calls return:
         *
         * 1. Assign the inverted right subtree to root.left.
         * 2. Assign the inverted left subtree to root.right.
         *
         * This effectively swaps the left and right children
         * of the current node.
         *
         * Since we are modifying existing references,
         * the inversion happens in-place without creating
         * additional TreeNode objects.
         */
        root.left = right;
        root.right = left;

        /*
         * Return the current root node.
         *
         * The current node is now the root of its
         * completely inverted subtree.
         *
         * This returned reference will be used by the
         * parent recursive call.
         *
         * Eventually, the original call returns the root
         * of the completely inverted binary tree.
         */
        return root;
    }
}