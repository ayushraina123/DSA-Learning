package com.dsa.phase4.binaryTree;

/**
 * LeetCode: 129. Sum Root to Leaf Numbers
 * <p>
 * Difficulty: Medium
 * Topics: Tree, Depth-First Search, Binary Tree
 * <p>
 * Problem:
 * Each root-to-leaf path in a binary tree represents a number.
 * Every node contains a single digit from 0 to 9.
 * <p>
 * Return the sum of all numbers represented by root-to-leaf paths.
 * <p>
 * Example:
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * Root-to-leaf paths:
 * 1 -> 2 = 12
 * 1 -> 3 = 13
 * <p>
 * Result = 12 + 13 = 25
 * <p>
 * Approach: Top-Down DFS (Recursion)
 * <p>
 * We traverse the tree from root to leaf while maintaining
 * the number formed along the current path.
 * <p>
 * We use two variables:
 * <p>
 * 1. current:
 * Stores the digits accumulated from the root to the
 * parent of the current node.
 * <p>
 * 2. sum:
 * Carries the accumulated value for the current path.
 * In this implementation, it is updated when a leaf
 * node is reached.
 * <p>
 * At every non-leaf node, we append its digit to current:
 * <p>
 * current = (current + root.val) * 10
 * <p>
 * This shifts the accumulated digits one decimal place
 * to the left, leaving the final position ready for the
 * next digit.
 * <p>
 * At a leaf node, we add its digit to current and return
 * the completed root-to-leaf number.
 * <p>
 * The recursive calls explore both subtrees, and their
 * returned values are added together.
 * <p>
 * Algorithm:
 * 1. If the input root is null, return 0.
 * 2. Start traversal with sum = 0 and current = 0.
 * 3. If the current node is null, return 0.
 * 4. If the current node is a leaf:
 * - Add current and the leaf's digit to sum.
 * - Return the completed root-to-leaf number.
 * 5. Otherwise:
 * - Append the current node's digit to current
 * and shift it one decimal place.
 * - Recursively calculate the left subtree's sum.
 * - Recursively calculate the right subtree's sum.
 * 6. Return the sum of both recursive results.
 * <p>
 * Time Complexity: O(n)
 * Every node is visited exactly once.
 * <p>
 * Space Complexity: O(h)
 * The recursion stack requires space proportional to
 * the height of the tree.
 * Worst case: O(n) for a skewed tree.
 * Best case: O(log n) for a balanced tree.
 */
public class SumOfRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {

        // An empty tree has no root-to-leaf paths.
        if (root == null) return 0;

        // Start traversal with no accumulated sum or digits.
        return traverse(root, 0, 0);
    }

    /**
     * Traverses the tree recursively and calculates the sum
     * of all numbers represented by root-to-leaf paths.
     *
     * @param root    Current node being processed.
     * @param sum     Accumulated value for the current path.
     * @param current Number formed by the digits encountered
     *                before the current node.
     * @return Sum of all root-to-leaf numbers in the subtree
     * rooted at the current node.
     */
    public int traverse(TreeNode root, int sum, int current) {

        // Base case: a null node contributes nothing.
        // This handles missing left or right children.
        if (root == null) return 0;

        // Base case: a leaf marks the end of a root-to-leaf path.
        if (root.left == null && root.right == null) {

            // Complete the number by appending the leaf's digit.
            sum += current + root.val;

            // Return the number represented by this path.
            return sum;
        }

        // Append the current node's digit and shift it one
        // decimal place to make room for the next digit.
        //
        // Example:
        // current = 1, root.val = 2
        // current = (1 + 2) * 10 = 30
        //
        // The next node can add its digit to this value.
        current = (current + root.val) * 10;

        // Recursively calculate the sum of numbers in the left subtree.
        int leftSum = traverse(root.left, sum, current);

        // Recursively calculate the sum of numbers in the right subtree.
        int rightSum = traverse(root.right, sum, current);

        // Combine the results returned by both subtrees.
        return leftSum + rightSum;
    }
}
