package com.dsa.phase4.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode Problem 100: Same Tree
 *
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/same-tree/
 *
 * <p>
 * Difficulty: Easy
 * Topics: Binary Tree, BFS, DFS, Recursion
 *
 * <p>
 * Problem Statement:
 *
 * <p>
 * Given the roots of two binary trees p and q, determine whether
 * they are structurally identical and have the same node values.
 *
 * <p>
 * Two binary trees are considered the same if:
 *
 * <p>
 * 1. Both trees have the same structure.
 * <p>
 * 2. Corresponding nodes have the same values.
 *
 * <p>
 * Approach 1: Iterative BFS (Breadth First Search)
 *
 * <p>
 * We use two separate queues to traverse both binary trees
 * simultaneously using level-order traversal.
 *
 * <p>
 * The idea is to process corresponding nodes from both trees
 * and compare their values and child structures.
 *
 * <p>
 * Algorithm:
 *
 * <p>
 * 1. If both root nodes are null, return true because both
 * trees are empty.
 *
 * <p>
 * 2. If only one root is null, return false because their
 * structures are different.
 *
 * <p>
 * 3. Create two queues, one for each binary tree, and insert
 * their respective root nodes.
 *
 * <p>
 * 4. While both queues contain nodes:
 *
 * <p>
 * a. Remove the front node from each queue.
 *
 * <p>
 * b. Compare their values. If the values differ,
 * return false.
 *
 * <p>
 * c. Compare their left children. If one is null and
 * the other is non-null, return false.
 *
 * <p>
 * d. Similarly, compare their right children.
 *
 * <p>
 * e. If both left children exist, insert them into
 * their respective queues.
 *
 * <p>
 * f. If both right children exist, insert them into
 * their respective queues.
 *
 * <p>
 * 5. If all corresponding nodes have been processed without
 * finding any difference, return true.
 *
 * <p>
 * Important Observation:
 *
 * <p>
 * We only insert corresponding non-null children into the
 * queues after verifying that their null/non-null structure
 * matches.
 *
 * <p>
 * This ensures that both queues always contain corresponding
 * nodes in the same order.
 *
 * <p>
 * Approach 2: Recursive DFS (Depth First Search)
 *
 * <p>
 * Instead of traversing both trees using queues, we can
 * recursively compare corresponding nodes.
 *
 * <p>
 * Mathematical Observation:
 *
 * <p>
 * Two binary trees are identical if and only if:
 *
 * <p>
 * 1. Their root nodes have the same value.
 * <p>
 * 2. Their left subtrees are identical.
 * <p>
 * 3. Their right subtrees are identical.
 *
 * <p>
 * Therefore, the recursive relationship is:
 *
 * <p>
 * isSameTree(p, q) =
 * p.val == q.val
 * AND isSameTree(p.left, q.left)
 * AND isSameTree(p.right, q.right)
 *
 * <p>
 * Algorithm:
 *
 * <p>
 * 1. Base Condition:
 *
 * <p>
 * If both nodes are null, return true because both
 * corresponding subtrees are empty.
 *
 * <p>
 * If only one node is null, return false because their
 * structures are different.
 *
 * <p>
 * 2. Compare the values of the current nodes.
 *
 * <p>
 * If their values differ, return false.
 *
 * <p>
 * 3. Recursively compare their left subtrees.
 *
 * <p>
 * 4. Recursively compare their right subtrees.
 *
 * <p>
 * 5. Return true only if both recursive calls return true.
 *
 * <p>
 * Understanding Recursive Returns:
 *
 * <p>
 * Consider the following two binary trees:
 *
 * <p>
 * Tree P:              Tree Q:
 * <p>
 * 1                    1
 * / \                  / \
 * 2   3                2   3
 *
 * <p>
 * Execution:
 *
 * <p>
 * 1. The initial call is:
 * <p>
 * isSameTree(1, 1)
 * <p>
 * Both nodes exist and have the same value.
 *
 * <p>
 * 2. The current call makes two recursive calls:
 * <p>
 * isSameTree(2, 2)
 * isSameTree(3, 3)
 *
 * <p>
 * 3. Consider the left subtree:
 * <p>
 * isSameTree(2, 2) compares the values and finds them equal.
 * <p>
 * It then calls:
 * <p>
 * isSameTree(null, null) for the left children.
 * isSameTree(null, null) for the right children.
 * <p>
 * Both calls return true because both corresponding
 * children are null.
 * <p>
 * Therefore, isSameTree(2, 2) returns true.
 *
 * <p>
 * 4. Similarly, isSameTree(3, 3) returns true because
 * both nodes have the same value and both have null children.
 *
 * <p>
 * 5. Finally, the original call receives:
 * <p>
 * Left subtree result  = true
 * Right subtree result = true
 * <p>
 * true && true = true
 *
 * <p>
 * Therefore, both binary trees are identical.
 *
 * <p>
 * Understanding the Base Conditions:
 *
 * <p>
 * Case 1: Both nodes are null.
 * <p>
 * return true;
 * <p>
 * This means both corresponding positions in the trees
 * are empty, so their structures match at this position.
 *
 * <p>
 * Case 2: Only one node is null.
 * <p>
 * return false;
 * <p>
 * This means one tree has a node where the other tree
 * does not, so their structures are different.
 *
 * <p>
 * Case 3: Both nodes exist but have different values.
 * <p>
 * return false;
 * <p>
 * Their corresponding node values do not match.
 *
 * <p>
 * Important:
 *
 * <p>
 * Each recursive call is responsible for determining whether
 * its own pair of subtrees is identical.
 *
 * <p>
 * The parent call simply uses the boolean results returned
 * by its left and right recursive calls.
 *
 * <p>
 * This is an example of solving a larger problem by
 * independently solving smaller subproblems.
 *
 * <p>
 * Complexity Analysis:
 *
 * <p>
 * BFS Approach:
 *
 * <p>
 * Time Complexity: O(n)
 * <p>
 * We visit every node in both trees exactly once.
 * Here, n represents the number of nodes in the trees
 * (assuming both trees have the same number of nodes).
 *
 * <p>
 * Space Complexity: O(w)
 * <p>
 * The queues store nodes belonging to the current level.
 * Here, w represents the maximum width of the tree.
 * <p>
 * In a balanced binary tree: O(n)
 * In a skewed binary tree: O(1)
 *
 * <p>
 * Recursive DFS Approach:
 *
 * <p>
 * Time Complexity: O(n)
 * <p>
 * Each corresponding pair of nodes is visited exactly once.
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
public class SameTree {

    /**
     * Checks whether two binary trees are identical using BFS.
     *
     * @param p root node of the first binary tree
     * @param q root node of the second binary tree
     * @return true if both trees are identical, otherwise false
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        /*
         * If both roots are null, both trees are empty.
         *
         * If only one root is null, their structures differ.
         */
        if (p == null || q == null) {
            return p == q;
        }

        /*
         * Create separate queues for both binary trees.
         *
         * Both queues will contain corresponding nodes
         * in the same level-order traversal sequence.
         */
        Queue<TreeNode> pQueue = new LinkedList<>();
        Queue<TreeNode> qQueue = new LinkedList<>();

        pQueue.offer(p);
        qQueue.offer(q);

        /*
         * Process corresponding nodes from both trees.
         */
        while (!pQueue.isEmpty() && !qQueue.isEmpty()) {

            TreeNode pNode = pQueue.poll();
            TreeNode qNode = qQueue.poll();

            /*
             * Corresponding nodes must have the same value.
             */
            if (pNode.val != qNode.val) return false;

            /*
             * Compare the existence of corresponding children.
             *
             * If one child is null and the other is non-null,
             * the structures of the trees are different.
             */
            if ((pNode.left == null) != (qNode.left == null)) {
                return false;
            }

            if ((pNode.right == null) != (qNode.right == null)) {
                return false;
            }

            /*
             * Both left children exist.
             *
             * Insert them into their respective queues
             * to compare them in subsequent iterations.
             */
            if (pNode.left != null) {
                pQueue.offer(pNode.left);
                qQueue.offer(qNode.left);
            }

            /*
             * Both right children exist.
             *
             * Insert them into their respective queues.
             */
            if (pNode.right != null) {
                pQueue.offer(pNode.right);
                qQueue.offer(qNode.right);
            }
        }

        /*
         * All corresponding nodes have been processed
         * without finding any difference.
         */
        return true;
    }

    /**
     * Checks whether two binary trees are identical using recursion.
     *
     * @param p root node of the first binary tree
     * @param q root node of the second binary tree
     * @return true if both trees are identical, otherwise false
     */
    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {

        /*
         * Base condition 1:
         *
         * If both nodes are null, their corresponding
         * subtrees are identical.
         */
        if (p == null && q == null) return true;

        /*
         * Base condition 2:
         *
         * If only one node is null, their structures differ.
         */
        if (p == null || q == null) return false;

        /*
         * Compare the values of the current nodes.
         *
         * If they differ, there is no need to explore
         * their respective subtrees.
         */
        if (p.val != q.val) return false;

        /*
         * Recursively compare the left and right subtrees.
         *
         * The current recursive call waits for the results
         * returned by both recursive calls.
         *
         * The current pair of trees is identical only if
         * both corresponding subtrees are identical.
         */
        return isSameTreeRecursive(p.left, q.left)
                && isSameTreeRecursive(p.right, q.right);
    }
}