package com.dsa.phase4.binaryTree;

/**
 * LeetCode Problem 101: Symmetric Tree
 *
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/symmetric-tree/
 *
 * <p>
 * Difficulty: Easy
 * Topics: Binary Tree, DFS, Recursion
 *
 * <p>
 * Problem Statement:
 *
 * <p>
 * Given the root of a binary tree, determine whether the
 * tree is symmetric around its centre.
 *
 * <p>
 * A binary tree is symmetric if its left and right subtrees
 * are mirror images of each other.
 *
 * <p>
 * Approach: Recursive DFS (Depth First Search)
 *
 * <p>
 * We use recursion to compare the left and right subtrees
 * as mirror images of each other.
 *
 * <p>
 * Important Observation:
 *
 * <p>
 * A binary tree is symmetric if its inverted version is
 * identical to the original tree.
 *
 * <p>
 * Instead of actually inverting the tree and comparing
 * both versions, we can directly compare the left and
 * right subtrees using mirror comparisons.
 *
 * <p>
 * Mirror Comparison:
 *
 * <p>
 * Consider two subtrees rooted at nodes p and q.
 *
 * <p>
 * For these subtrees to be mirror images:
 *
 * <p>
 * 1. Both nodes must be null, or both must exist.
 *
 * <p>
 * 2. Their values must be equal.
 *
 * <p>
 * 3. The left subtree of p must be a mirror image of
 * the right subtree of q.
 *
 * <p>
 * 4. The right subtree of p must be a mirror image of
 * the left subtree of q.
 *
 * <p>
 * Therefore, the recursive relationship is:
 *
 * <p>
 * isSymmetric(p, q) =
 * <p>
 * p.val == q.val
 * AND isSymmetric(p.left, q.right)
 * AND isSymmetric(p.right, q.left)
 *
 * <p>
 * Algorithm:
 *
 * <p>
 * 1. Check whether the root is null.
 *
 * <p>
 * An empty binary tree is symmetric, so return true.
 *
 * <p>
 * 2. Start the recursive comparison by passing the left
 * and right children of the root.
 *
 * <p>
 * 3. Base Conditions:
 *
 * <p>
 * a. If both nodes are null, return true because
 * both corresponding mirror positions are empty.
 *
 * <p>
 * b. If only one node is null, return false because
 * the structures do not mirror each other.
 *
 * <p>
 * 4. Compare the values of the current nodes.
 *
 * <p>
 * If their values differ, return false.
 *
 * <p>
 * 5. Recursively compare the outer children:
 * <p>
 * isSymmetric(p.left, q.right)
 *
 * <p>
 * 6. Recursively compare the inner children:
 * <p>
 * isSymmetric(p.right, q.left)
 *
 * <p>
 * 7. Return true only if both recursive comparisons
 * return true.
 *
 * <p>
 * Understanding Recursive Returns:
 *
 * <p>
 * Consider the following symmetric binary tree:
 *
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 *
 * <p>
 * Execution:
 *
 * <p>
 * 1. The initial call is:
 * <p>
 * isSymmetric(1)
 * <p>
 * Since the root exists, we compare its left and
 * right children:
 * <p>
 * isSymmetric(2, 2)
 *
 * <p>
 * 2. Both nodes have the same value.
 * <p>
 * The current call makes two recursive calls:
 * <p>
 * isSymmetric(3, 3)
 * isSymmetric(4, 4)
 * <p>
 * Notice that we compare opposite children rather
 * than corresponding children.
 *
 * <p>
 * 3. Consider the outer comparison:
 * <p>
 * isSymmetric(3, 3)
 * <p>
 * Both nodes have the same value.
 * <p>
 * Their left and right children are null, so the
 * recursive calls return true.
 * <p>
 * Therefore, this comparison returns true.
 *
 * <p>
 * 4. Similarly, the inner comparison:
 * <p>
 * isSymmetric(4, 4)
 * <p>
 * Also returns true because both nodes have equal
 * values and matching null children.
 *
 * <p>
 * 5. The original comparison at node 2 receives:
 * <p>
 * Outer comparison = true
 * Inner comparison = true
 * <p>
 * true && true = true
 *
 * <p>
 * 6. Therefore, the original call returns true,
 * indicating that the binary tree is symmetric.
 *
 * <p>
 * Understanding the Base Conditions:
 *
 * <p>
 * Case 1: Both nodes are null.
 * <p>
 * return true;
 * <p>
 * Both corresponding mirror positions are empty.
 * Therefore, their structures match.
 *
 * <p>
 * Case 2: Only one node is null.
 * <p>
 * return false;
 * <p>
 * One subtree contains a node where the other
 * subtree does not.
 *
 * <p>
 * Case 3: Both nodes exist but have different values.
 * <p>
 * return false;
 * <p>
 * Their corresponding mirror node values differ.
 *
 * <p>
 * Important:
 *
 * <p>
 * Each recursive call determines whether its two input
 * subtrees are mirror images of each other.
 *
 * <p>
 * The parent call uses the boolean results returned
 * by its two recursive calls.
 *
 * <p>
 * Unlike the Same Tree problem, where we compare:
 * <p>
 * left with left
 * right with right
 * <p>
 * Here, we compare:
 * <p>
 * left with right
 * right with left
 *
 * <p>
 * This opposite-child comparison is what allows us
 * to verify mirror symmetry.
 *
 * <p>
 * Complexity Analysis:
 *
 * <p>
 * Time Complexity: O(n)
 * <p>
 * Each node is visited at most once during the
 * recursive comparison.
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
public class SymmetricTree {

    /**
     * Checks whether a binary tree is symmetric.
     *
     * @param root the root node of the binary tree
     * @return true if the tree is symmetric, otherwise false
     */
    public boolean isSymmetric(TreeNode root) {

        /*
         * An empty binary tree is symmetric.
         */
        if (root == null) return true;

        /*
         * Compare the left and right subtrees as
         * mirror images of each other.
         */
        return isSymmetric(root.left, root.right);
    }

    /**
     * Recursively checks whether two subtrees are
     * mirror images of each other.
     *
     * @param p root node of the first subtree
     * @param q root node of the second subtree
     * @return true if both subtrees are mirror images,
     * otherwise false
     */
    public boolean isSymmetric(TreeNode p, TreeNode q) {

        /*
         * Base condition 1:
         *
         * If both nodes are null, their corresponding
         * mirror positions are empty.
         */
        if (p == null && q == null) return true;

        /*
         * Base condition 2:
         *
         * If only one node is null, their structures
         * are not symmetric.
         */
        if (p == null || q == null) return false;

        /*
         * Compare the values of the current nodes.
         *
         * Mirror nodes must have identical values.
         */
        if (p.val != q.val) return false;

        /*
         * Recursively compare the outer children:
         *
         * Left child of p with right child of q.
         */
        return isSymmetric(p.left, q.right)

                /*
                 * Recursively compare the inner children:
                 *
                 * Right child of p with left child of q.
                 *
                 * Both comparisons must return true
                 * for the current subtrees to be symmetric.
                 */
                && isSymmetric(p.right, q.left);
    }
}