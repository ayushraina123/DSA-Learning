package com.dsa.phase4.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem: Binary Tree Zigzag Level Order Traversal
 * <p>
 * LeetCode: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * Difficulty: Medium
 * <p>
 * Topics:
 * - Binary Tree
 * - Breadth First Search (BFS)
 * - Queue
 * - LinkedList
 * <p>
 * Problem Statement:
 * Given the root of a binary tree, return the zigzag level order traversal
 * of its nodes' values.
 * <p>
 * Zigzag traversal means:
 * - Level 0: Left to Right
 * - Level 1: Right to Left
 * - Level 2: Left to Right
 * - Level 3: Right to Left
 * - Continue alternating the direction for every level.
 * <p>
 * Approach: Breadth First Search (BFS) + LinkedList
 * <p>
 * We use BFS to traverse the binary tree level by level.
 * <p>
 * A Queue is used to maintain the order in which nodes are visited.
 * At the beginning of every level, we capture the current queue size.
 * This ensures that we process only the nodes belonging to that level.
 * <p>
 * To achieve zigzag ordering, we use a LinkedList to store the values
 * of the current level.
 * <p>
 * Instead of reversing the list after every alternate level, we use:
 * <p>
 * 1. addLast():
 * Inserts an element at the end of the LinkedList.
 * Used when the current level should be traversed Left to Right.
 * <p>
 * 2. addFirst():
 * Inserts an element at the beginning of the LinkedList.
 * Used when the current level should be traversed Right to Left.
 * <p>
 * Important Observations:
 * <p>
 * 1. The BFS traversal order always remains Left to Right.
 * We do not change the order in which nodes are added to the queue.
 * <p>
 * 2. Only the order in which values are stored in the current level
 * changes depending on the traversal direction.
 * <p>
 * 3. A boolean flag is used to maintain the traversal direction:
 * - true  -> Left to Right
 * - false -> Right to Left
 * <p>
 * 4. The flag is toggled after processing every level using:
 * flag = !flag;
 * <p>
 * 5. LinkedList allows insertion at both ends in O(1) time.
 * Therefore, we can avoid explicitly reversing alternate levels.
 * <p>
 * Algorithm:
 * <p>
 * 1. If the root is null, return an empty list.
 * <p>
 * 2. Initialize:
 * - A result list to store all levels.
 * - A Queue to perform BFS.
 * - A boolean flag initialized to true, indicating Left to Right.
 * <p>
 * 3. Add the root node to the queue.
 * <p>
 * 4. While the queue is not empty:
 * <p>
 * a. Capture the current queue size. This represents the number
 * of nodes belonging to the current level.
 * <p>
 * b. Create a new LinkedList to store the current level's values.
 * <p>
 * c. Process exactly 'size' nodes:
 * <p>
 * - Remove the front node from the queue.
 * - If flag is true, insert its value using addLast().
 * - Otherwise, insert its value using addFirst().
 * - Add its left and right children to the queue, if they exist.
 * <p>
 * d. Toggle the flag to change the traversal direction for
 * the next level.
 * <p>
 * e. Add the current level's list to the result.
 * <p>
 * 5. Return the result.
 * <p>
 * Execution Walkthrough:
 * <p>
 * Consider the following binary tree:
 * <p>
 * 1
 * /   \
 * 2     3
 * / \   / \
 * 4   5 6   7
 * <p>
 * Level 0:
 * - Direction: Left to Right
 * - flag = true
 * - Values are inserted using addLast().
 * - Result: [1]
 * <p>
 * Level 1:
 * - Direction: Right to Left
 * - flag = false
 * - BFS visits nodes in the order 2, 3.
 * - addFirst(2) -> [2]
 * - addFirst(3) -> [3, 2]
 * - Result: [3, 2]
 * <p>
 * Level 2:
 * - Direction: Left to Right
 * - flag = true
 * - BFS visits nodes in the order 4, 5, 6, 7.
 * - Values are inserted using addLast().
 * - Result: [4, 5, 6, 7]
 * <p>
 * Final Output:
 * [[1], [3, 2], [4, 5, 6, 7]]
 * <p>
 * Edge Cases:
 * <p>
 * 1. Empty Tree:
 * If root is null, return an empty list.
 * <p>
 * 2. Single Node:
 * The result contains only one level with the root value.
 * <p>
 * 3. Left-Skewed or Right-Skewed Tree:
 * Every level contains only one node, so the result remains valid
 * regardless of the traversal direction.
 * <p>
 * Complexity Analysis:
 * <p>
 * Let N be the total number of nodes in the binary tree.
 * <p>
 * Time Complexity: O(N)
 * - Every node is visited exactly once.
 * - Each node is inserted into the LinkedList in O(1) time.
 * - Each node is added to and removed from the queue exactly once.
 * - Therefore, the overall time complexity is O(N).
 * <p>
 * Space Complexity: O(N)
 * - The BFS queue can contain up to O(N) nodes in the worst case.
 * - The result stores all N node values.
 * - The current level's LinkedList requires at most O(N) space.
 * - Therefore, the overall auxiliary space complexity is O(N),
 * including the result.
 */
public class BinaryTreeZigZagLevelOrderTraversal {

    /**
     * Performs zigzag level order traversal of a binary tree.
     *
     * @param root root node of the binary tree
     * @return list containing node values in zigzag level order
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // true -> Left to Right
        // false -> Right to Left
        boolean flag = true;

        while (!queue.isEmpty()) {

            // Capture the number of nodes at the current level.
            int size = queue.size();

            // LinkedList allows insertion at both ends in O(1) time.
            LinkedList<Integer> list = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Store values according to the current traversal direction.
                if (flag) {
                    list.addLast(node.val);
                } else {
                    list.addFirst(node.val);
                }

                // Always maintain normal BFS order while adding children.
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            // Alternate the traversal direction for the next level.
            flag = !flag;

            // Add the completed level to the final result.
            res.add(list);
        }

        return res;
    }
}
