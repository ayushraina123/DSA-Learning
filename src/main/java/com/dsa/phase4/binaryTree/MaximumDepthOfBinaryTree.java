package com.dsa.phase4.binaryTree;

/**
 * LeetCode Problem 104: Maximum Depth of Binary Tree
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * <p>
 * Difficulty: Easy
 * Topics: Binary Tree, DFS, Recursion
 * <p>
 * Problem Statement:
 * <p>
 * Given the root of a binary tree, return its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 * <p>
 * Approach: Recursive DFS (Depth First Search)
 * <p>
 * We use recursion to calculate the maximum depth of the left
 * and right subtrees independently.
 * <p>
 * Mathematical observation:
 * <p>
 * The maximum depth of a binary tree can be calculated as:
 * <p>
 * maxDepth(root) = 1 + max(maxDepth(root.left), maxDepth(root.right))
 * <p>
 * Here:
 * <p>
 * 1. The depth of an empty tree (null node) is 0.
 * <p>
 * 2. We recursively calculate the maximum depth of the left subtree.
 * <p>
 * 3. We recursively calculate the maximum depth of the right subtree.
 * <p>
 * 4. We take the maximum of the two depths and add 1 to include
 * the current root node.
 * <p>
 * Why is the base condition important?
 * <p>
 * When we reach a null node, we return 0.
 * <p>
 * This represents the end of a particular branch of the tree.
 * The recursive calls then start returning their calculated depths
 * back to their respective parent nodes.
 * <p>
 * Understanding Recursive Returns:
 * <p>
 * Consider the following binary tree:
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * Execution:
 * <p>
 * 1. maxDepth(1) calls maxDepth(2) and maxDepth(3).
 * <p>
 * 2. maxDepth(2) calls maxDepth(4) and maxDepth(5).
 * <p>
 * 3. Nodes 3, 4 and 5 have no children, so their recursive calls
 * eventually reach null and return 0.
 * <p>
 * 4. Node 4 returns max(0, 0) + 1 = 1.
 * Node 5 returns max(0, 0) + 1 = 1.
 * <p>
 * 5. Node 2 receives these returned values:
 * max(1, 1) + 1 = 2.
 * <p>
 * 6. Node 3 returns max(0, 0) + 1 = 1.
 * <p>
 * 7. Finally, node 1 receives:
 * Left subtree depth  = 2
 * Right subtree depth = 1
 * <p>
 * max(2, 1) + 1 = 3.
 * <p>
 * Therefore, the maximum depth is 3.
 * <p>
 * Important:
 * <p>
 * Each recursive call calculates and returns the maximum depth
 * of its own subtree.
 * <p>
 * The parent node uses these returned values to calculate
 * its own depth.
 * <p>
 * Complexity Analysis:
 * <p>
 * Time Complexity: O(n)
 * <p>
 * We visit every node exactly once to calculate its depth.
 * Here, n represents the total number of nodes in the tree.
 * <p>
 * Space Complexity: O(h)
 * <p>
 * The recursive call stack can go as deep as the height of the tree.
 * <p>
 * In a balanced binary tree: O(log n)
 * In a skewed binary tree: O(n)
 */
class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class MaximumDepthOfBinaryTree {

    /**
     * Calculates the maximum depth of a binary tree using recursion.
     *
     * @param root the root node of the binary tree
     * @return the maximum depth of the binary tree
     */
    public int maxDepth(TreeNode root) {

        /*
         * Base condition:
         *
         * If the current node is null, we have reached the end
         * of a particular branch.
         *
         * An empty tree has a depth of 0.
         *
         * This returned value will be used by the parent node
         * to calculate its own subtree depth.
         */
        if (root == null) {
            return 0;
        }

        /*
         * Recursively calculate the maximum depth of the left subtree.
         *
         * The current method call pauses until the left subtree
         * returns its calculated depth.
         */
        int leftDepth = maxDepth(root.left);

        /*
         * Recursively calculate the maximum depth of the right subtree.
         *
         * Similarly, this call returns the maximum depth
         * of the right subtree.
         */
        int rightDepth = maxDepth(root.right);

        /*
         * Once both recursive calls return:
         *
         * 1. Take the maximum depth between the left and right subtrees.
         * 2. Add 1 to include the current root node.
         *
         * Return this calculated depth to the parent recursive call.
         *
         * Eventually, the original call returns the maximum depth
         * of the entire binary tree.
         */
        return Math.max(leftDepth, rightDepth) + 1;
    }
}