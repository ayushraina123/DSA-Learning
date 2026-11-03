package com.dsa.phase4.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode Problem 199: Binary Tree Right Side View
 *
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * </p>
 *
 * <p>
 * Difficulty: Medium
 * Topics: Binary Tree, BFS, Queue
 * </p>
 *
 * <p>
 * Problem Statement:
 * </p>
 *
 * <p>
 * Given the root of a binary tree, imagine yourself standing
 * on the right side of the tree.
 * <p>
 * Return the values of the nodes that are visible to you
 * from top to bottom.
 * </p>
 *
 * <p>
 * Approach: BFS (Breadth First Search)
 * </p>
 *
 * <p>
 * We use a Queue to perform level order traversal of the
 * binary tree.
 * <p>
 * Since BFS processes nodes level by level and from left
 * to right, the last node processed at every level is
 * the rightmost node of that level.
 * </p>
 *
 * <p>
 * Important Observation:
 * </p>
 *
 * <p>
 * The right-side view does not necessarily consist of nodes
 * that are right children of their respective parents.
 * <p>
 * A node can be visible from the right side even if it is
 * a left child.
 * </p>
 *
 * <p>
 * Therefore, instead of checking whether a node is a right
 * child, we simply select the last node processed at each
 * level during BFS.
 * </p>
 *
 * <p>
 * Since BFS processes nodes from left to right, the last
 * processed node is always the rightmost node at that level.
 * </p>
 *
 * <p>
 * Algorithm:
 * </p>
 *
 * <p>
 * 1. If the root is null, return an empty list because
 * there are no visible nodes.
 * </p>
 *
 * <p>
 * 2. Create a Queue and insert the root node.
 * </p>
 *
 * <p>
 * 3. Continue processing while the queue is not empty.
 * </p>
 *
 * <p>
 * 4. At the beginning of every iteration:
 * </p>
 *
 * <p>
 * a. Capture the current queue size. This represents
 * the number of nodes belonging to the current level.
 * </p>
 *
 * <p>
 * b. Process exactly that many nodes.
 * </p>
 *
 * <p>
 * 5. For every node processed:
 * </p>
 *
 * <p>
 * a. Remove the front node from the queue.
 * </p>
 *
 * <p>
 * b. Store its value in the rightNode variable.
 * <p>
 * Since this variable is overwritten for every node,
 * it will eventually contain the value of the last
 * node processed at the current level.
 * </p>
 *
 * <p>
 * c. If the left child exists, add it to the queue.
 * </p>
 *
 * <p>
 * d. If the right child exists, add it to the queue.
 * </p>
 *
 * <p>
 * 6. After processing all nodes of the current level,
 * add rightNode to the result list.
 * </p>
 *
 * <p>
 * 7. Repeat until the queue becomes empty.
 * </p>
 *
 * <p>
 * Understanding the Execution:
 * </p>
 *
 * <p>
 * Consider the following binary tree:
 * </p>
 *
 * <pre>
 *          1
 *         / \
 *        2   3
 *       /     \
 *      5       4
 * </pre>
 *
 * <p>
 * Initial State:
 * </p>
 *
 * <pre>
 * Queue: [1]
 * Result: []
 * </pre>
 *
 * <p>
 * Iteration 1 - Level 1:
 * </p>
 *
 * <p>
 * The queue contains one node.
 * Therefore, we process exactly one node.
 * </p>
 *
 * <pre>
 * Process: 1
 * rightNode = 1
 *
 * Queue: [2, 3]
 * Result: [1]
 * </pre>
 *
 * <p>
 * Iteration 2 - Level 2:
 * </p>
 *
 * <p>
 * The queue initially contains two nodes.
 * Therefore, we process exactly two nodes.
 * </p>
 *
 * <pre>
 * Process: 2
 * rightNode = 2
 *
 * Process: 3
 * rightNode = 3
 *
 * Queue: [5, 4]
 * Result: [1, 3]
 * </pre>
 *
 * <p>
 * Notice that processing node 3 overwrites the previous
 * value stored in rightNode.
 * Therefore, rightNode contains 3 after this level.
 * </p>
 *
 * <p>
 * Iteration 3 - Level 3:
 * </p>
 *
 * <pre>
 * Process: 5
 * rightNode = 5
 *
 * Process: 4
 * rightNode = 4
 *
 * Queue: []
 * Result: [1, 3, 4]
 * </pre>
 *
 * <p>
 * Final Result:
 * </p>
 *
 * <pre>
 * [1, 3, 4]
 * </pre>
 *
 * <p>
 * Understanding the rightNode Variable:
 * </p>
 *
 * <p>
 * We do not need to explicitly identify the rightmost node
 * of a level.
 * <p>
 * Instead, we keep updating rightNode with every node
 * processed during the current level.
 * </p>
 *
 * <p>
 * The last assignment always belongs to the rightmost
 * node because BFS processes nodes from left to right.
 * </p>
 *
 * <p>
 * This eliminates the need for additional checks or
 * separate logic to identify the rightmost node.
 * </p>
 *
 * <p>
 * Important Edge Case:
 * </p>
 *
 * <p>
 * Consider a tree containing only left children:
 * </p>
 *
 * <pre>
 *       1
 *      /
 *     2
 *    /
 *   3
 * </pre>
 *
 * <p>
 * The right-side view is:
 * </p>
 *
 * <pre>
 * [1, 2, 3]
 * </pre>
 *
 * <p>
 * Even though every node except the root is a left child,
 * each of these nodes is still visible from the right side
 * because there are no nodes to its right at that level.
 * </p>
 *
 * <p>
 * Complexity Analysis:
 * </p>
 *
 * <p>
 * Time Complexity: O(n)
 * </p>
 *
 * <p>
 * Every node is inserted into the queue and processed
 * exactly once.
 * <p>
 * Therefore, the total time complexity is O(n), where n
 * represents the total number of nodes in the binary tree.
 * </p>
 *
 * <p>
 * Space Complexity: O(n)
 * </p>
 *
 * <p>
 * The queue can contain up to O(n) nodes in the worst case.
 * <p>
 * The result list also stores one visible node per level.
 * </p>
 */
public class BinaryTreeRightSideView {

    /**
     * Returns the values of the nodes visible from the
     * right side of a binary tree.
     *
     * @param root the root node of the binary tree
     * @return a list containing the rightmost node value
     * from every level, in top-to-bottom order
     */
    public List<Integer> rightSideView(TreeNode root) {

        /*
         * An empty binary tree has no visible nodes.
         */
        if (root == null) return new ArrayList<>();

        /*
         * Queue used to perform level order traversal.
         */
        Queue<TreeNode> queue = new LinkedList<>();

        /*
         * Stores the rightmost node value of every level.
         */
        List<Integer> res = new ArrayList<>();

        /*
         * Start BFS by inserting the root node.
         */
        queue.add(root);

        /*
         * Stores the value of the last node processed
         * at the current level.
         */
        int rightNode = 0;

        /*
         * Continue until all nodes have been processed.
         */
        while (!queue.isEmpty()) {

            /*
             * Capture the number of nodes belonging
             * to the current level.
             */
            int size = queue.size();

            /*
             * Process exactly the nodes belonging
             * to the current level.
             */
            for (int i = size; i > 0; i--) {

                /*
                 * Remove the front node from the queue.
                 */
                TreeNode node = queue.poll();

                /*
                 * Update rightNode with the current node's value.
                 *
                 * Since BFS processes nodes from left to right,
                 * the final value will belong to the rightmost
                 * node of the current level.
                 */
                rightNode = node.val;

                /*
                 * Add existing children to the queue.
                 *
                 * They will be processed in the next iteration.
                 */
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            /*
             * All nodes of the current level have been processed.
             *
             * Add the rightmost node's value to the result.
             */
            res.add(rightNode);
        }

        return res;
    }
}
