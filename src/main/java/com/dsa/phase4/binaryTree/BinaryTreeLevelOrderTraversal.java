package com.dsa.phase4.binaryTree;

import java.util.*;

/**
 * LeetCode Problem 102: Binary Tree Level Order Traversal
 *
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
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
 * Given the root of a binary tree, return the level order
 * traversal of its nodes' values.
 * </p>
 *
 * <p>
 * Level order traversal means visiting all nodes level by
 * level, from left to right.
 * </p>
 *
 * <p>
 * Approach: BFS (Breadth First Search)
 * </p>
 *
 * <p>
 * We use a Queue to perform Breadth First Search.
 * Since a Queue follows FIFO (First In, First Out),
 * nodes are processed in the order they are discovered.
 * </p>
 *
 * <p>
 * Important Observation:
 * </p>
 *
 * <p>
 * At the beginning of each BFS iteration, the queue contains
 * all the remaining nodes of the current level.
 * <p>
 * It may also contain nodes from the next level if children
 * were added while processing the current level.
 * </p>
 *
 * <p>
 * However, the queue size at the beginning of an iteration
 * tells us exactly how many nodes belong to the current level.
 * </p>
 *
 * <p>
 * We capture this size and process exactly that many nodes.
 * Any children added during this iteration will be processed
 * in the next iteration.
 * </p>
 *
 * <p>
 * This allows us to separate levels without maintaining
 * an additional level variable, Map, or node wrapper class.
 * </p>
 *
 * <p>
 * Algorithm:
 * </p>
 *
 * <p>
 * 1. If the root is null, return an empty list.
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
 * 4. At the beginning of each iteration:
 * </p>
 *
 * <p>
 * a. Create a new list to store the values of the current level.
 * </p>
 *
 * <p>
 * b. Capture the current queue size.
 * </p>
 *
 * <p>
 * 5. Process exactly the number of nodes captured in step 4b:
 * </p>
 *
 * <p>
 * a. Remove the front node from the queue.
 * </p>
 *
 * <p>
 * b. Add its value to the current level's list.
 * </p>
 *
 * <p>
 * c. If its left child exists, add it to the queue.
 * </p>
 *
 * <p>
 * d. If its right child exists, add it to the queue.
 * </p>
 *
 * <p>
 * 6. Once all nodes of the current level are processed,
 * add the level's list to the final result.
 * </p>
 *
 * <p>
 * 7. Repeat until the queue becomes empty.
 * </p>
 *
 * <p>
 * Understanding the Queue Size:
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
 *       / \   \
 *      4   5   6
 * </pre>
 *
 * <p>
 * Initial State:
 * </p>
 *
 * <pre>
 * Queue: [1]
 * </pre>
 *
 * <p>
 * Iteration 1 - Level 1:
 * </p>
 *
 * <p>
 * The queue initially contains one node.
 * Therefore, we process exactly one node.
 * </p>
 *
 * <pre>
 * Process: 1
 * Add children: 2, 3
 *
 * Current level: [1]
 * Queue: [2, 3]
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
 * Add children: 4, 5
 *
 * Process: 3
 * Add child: 6
 *
 * Current level: [2, 3]
 * Queue: [4, 5, 6]
 * </pre>
 *
 * <p>
 * Notice that although we added three new nodes while
 * processing this level, we processed only the original
 * two nodes.
 * </p>
 *
 * <p>
 * Iteration 3 - Level 3:
 * </p>
 *
 * <pre>
 * Queue initially: [4, 5, 6]
 * Process: 4, 5, 6
 *
 * Current level: [4, 5, 6]
 * Queue: []
 * </pre>
 *
 * <p>
 * Final Result:
 * </p>
 *
 * <pre>
 * [[1], [2, 3], [4, 5, 6]]
 * </pre>
 *
 * <p>
 * Understanding the For Loop:
 * </p>
 *
 * <pre>
 * for (int i = queue.size(); i > 0; i--)
 * </pre>
 *
 * <p>
 * The initial queue size is evaluated only once when the
 * loop variable i is initialized.
 * </p>
 *
 * <p>
 * Even though we add new nodes to the queue while processing,
 * the number of loop iterations remains fixed.
 * </p>
 *
 * <p>
 * This is the key observation that allows us to process
 * one level at a time.
 * </p>
 *
 * <p>
 * Why We Don't Need an Intermediate Collection:
 * </p>
 *
 * <p>
 * Initially, we can process the current level's nodes into
 * a temporary collection and then iterate over that
 * collection to add their children to the queue.
 * </p>
 *
 * <p>
 * However, this is unnecessary.
 * </p>
 *
 * <p>
 * Since we already know the exact number of nodes belonging
 * to the current level, we can add their children directly
 * to the queue while processing each node.
 * </p>
 *
 * <p>
 * This eliminates the need for an additional collection.
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
 * Every node is inserted into the queue and removed from it
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
 * The final result also stores the values of all n nodes.
 * </p>
 */
public class BinaryTreeLevelOrderTraversal {

    /*
     * Alternative Approach:
     *
     * BFS using a level variable and a Map.
     *
     * We associate every node with its corresponding level
     * and group node values using a Map.
     *
     * This approach works, but requires additional data
     * structures and iterations.
     */

    /*
    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        List<TreeLevel> list = new ArrayList<>();
        Queue<TreeLevel> queue = new LinkedList<>();

        int level = 1;
        queue.add(new TreeLevel(root, level));

        while (!queue.isEmpty()) {
            TreeLevel treeLevel = queue.poll();
            list.add(treeLevel);

            if (treeLevel.node.left != null) {
                queue.add(new TreeLevel(treeLevel.node.left, treeLevel.level + 1));
            }

            if (treeLevel.node.right != null) {
                queue.add(new TreeLevel(treeLevel.node.right, treeLevel.level + 1));
            }
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (TreeLevel treeLevel : list) {
            map.computeIfAbsent(treeLevel.level, k -> new ArrayList<>())
               .add(treeLevel.node.val);
        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }

        return res;
    }

    static class TreeLevel {
        TreeNode node;
        int level;

        public TreeLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    */

    /**
     * Performs level order traversal of a binary tree using BFS.
     *
     * @param root the root node of the binary tree
     * @return a list containing the node values grouped by level
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        /*
         * An empty binary tree has no levels to traverse.
         */
        if (root == null) return new ArrayList<>();

        /*
         * Stores the final result.
         * Each inner list represents one level of the tree.
         */
        List<List<Integer>> res = new ArrayList<>();

        /*
         * Queue used to perform BFS.
         */
        Queue<TreeNode> queue = new LinkedList<>();

        /*
         * Start BFS by inserting the root node.
         */
        queue.add(root);

        /*
         * Continue until all nodes have been processed.
         */
        while (!queue.isEmpty()) {

            /*
             * Stores the values of the current level.
             */
            List<Integer> list = new ArrayList<>();

            /*
             * Capture the number of nodes belonging to
             * the current level.
             *
             * Process exactly these nodes, even if their
             * children are added to the queue during traversal.
             */
            for (int i = queue.size(); i > 0; i--) {

                /*
                 * Remove the front node from the queue.
                 */
                TreeNode node = queue.poll();

                /*
                 * Add the current node's value to this level.
                 */
                list.add(node.val);

                /*
                 * Add existing children to the queue.
                 *
                 * They will be processed in the next iteration,
                 * which represents the next level.
                 */
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            /*
             * All nodes belonging to the current level
             * have been processed.
             *
             * Add this level to the final result.
             */
            res.add(list);
        }

        return res;
    }
}