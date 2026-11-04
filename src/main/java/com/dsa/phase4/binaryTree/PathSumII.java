package com.dsa.phase4.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Path Sum II
 * <p>
 * LeetCode: https://leetcode.com/problems/path-sum-ii/
 * <p>
 * Difficulty: Medium
 * <p>
 * Topics:
 * - Binary Tree
 * - Depth First Search (DFS)
 * - Recursion
 * - Backtracking
 * <p>
 * Problem Statement:
 * <p>
 * Given the root of a binary tree and an integer targetSum,
 * return all root-to-leaf paths where the sum of the node
 * values in each path equals targetSum.
 * <p>
 * A leaf is a node that does not have any children.
 * <p>
 * Approach: Recursive DFS + Backtracking
 * <p>
 * We use DFS to explore every possible root-to-leaf path.
 * <p>
 * While traversing the tree, we maintain:
 * - currentSum: Accumulated sum of the current path.
 * - path: Stores the nodes belonging to the current path.
 * - res: Stores all root-to-leaf paths whose sum equals targetSum.
 * <p>
 * Whenever we reach a leaf node, we check whether the accumulated
 * sum equals targetSum.
 * <p>
 * If the sum matches, we add a copy of the current path to the result.
 * <p>
 * We use Backtracking to remove the current node from the path
 * after exploring its subtree.
 * <p>
 * Important Observations:
 * <p>
 * 1. We only validate the path when we reach a leaf node.
 * <p>
 * The problem specifically asks for root-to-leaf paths.
 * Reaching targetSum at an intermediate node does not mean
 * that we have found a valid path.
 * <p>
 * 2. We maintain a shared path list across recursive calls.
 * <p>
 * The same ArrayList is passed to every recursive invocation.
 * Therefore, changes made to the path are visible to all calls.
 * <p>
 * We must remove the current node after exploring its subtree
 * so that it does not become part of another path.
 * <p>
 * 3. We must store a copy of the path in the result.
 * <p>
 * We use:
 * res.add(new ArrayList<>(path));
 * <p>
 * If we directly add path, every entry in res would refer
 * to the same mutable ArrayList.
 * <p>
 * Since path keeps changing during backtracking, the previously
 * stored paths would also be affected.
 * <p>
 * 4. currentSum is passed as a primitive int.
 * <p>
 * Every recursive invocation receives its own copy of currentSum.
 * Therefore, updating currentSum in one recursive call does not
 * affect the value maintained by another recursive call.
 * <p>
 * 5. Both subtrees must be explored.
 * <p>
 * Unlike Path Sum I, we cannot stop after finding one valid path.
 * We need to collect all valid root-to-leaf paths.
 * <p>
 * 6. Backtracking must also happen inside the leaf condition.
 * <p>
 * Since the leaf condition returns immediately, the path must
 * be restored before executing that return statement.
 * Otherwise, the leaf node would remain in the shared path list.
 * <p>
 * Algorithm:
 * <p>
 * 1. In pathSum():
 * - If root is null, return an empty list.
 * - Otherwise, call the recursive helper with:
 * - Initial sum = 0
 * - Empty result list
 * - Empty path list
 * <p>
 * 2. In pathSum2():
 * <p>
 * a. Check whether the current node is a leaf.
 * <p>
 * - Add the current node's value to currentSum.
 * - Add the current node's value to path.
 * - If currentSum equals targetSum, add a copy of path to res.
 * - Remove the current node from path before returning.
 * <p>
 * b. If the current node is not a leaf:
 * <p>
 * - Add the current node's value to currentSum.
 * - Add the current node's value to path.
 * <p>
 * c. Explore the left subtree, if it exists.
 * <p>
 * - Pass the updated sum and shared path to the recursive call.
 * <p>
 * d. Explore the right subtree, if it exists.
 * <p>
 * - Pass the updated sum and shared path to the recursive call.
 * <p>
 * e. After both subtrees have been explored:
 * <p>
 * - Remove the current node from path.
 * - Return the result list.
 * <p>
 * Execution Walkthrough:
 * <p>
 * Consider the following binary tree:
 * <pre>
 *              5
 *            /   \
 *           4     8
 *          /     / \
 *         11    13  4
 *        /  \         \
 *       7    2         1
 * </pre>
 * <p>
 * targetSum = 22
 * <p>
 * Initial State:
 * <pre>
 * path = []
 * currentSum = 0
 * res = []
 * </pre>
 * <p>
 * 1. Node 5:
 * <p>
 * currentSum = 0 + 5 = 5
 * <p>
 * path = [5]
 * <p>
 * Explore the left subtree.
 * <p>
 * 2. Node 4:
 * <p>
 * currentSum = 5 + 4 = 9
 * <p>
 * path = [5, 4]
 * <p>
 * Explore the left subtree.
 * <p>
 * 3. Node 11:
 * <p>
 * currentSum = 9 + 11 = 20
 * <p>
 * path = [5, 4, 11]
 * <p>
 * Explore the left subtree first.
 * <p>
 * 4. Node 7:
 * <p>
 * currentSum = 20 + 7 = 27
 * <p>
 * path = [5, 4, 11, 7]
 * <p>
 * Node 7 is a leaf.
 * <p>
 * 27 != 22, so no path is added to res.
 * <p>
 * Backtrack:
 * - Remove 7 from path.
 * - path = [5, 4, 11]
 * <p>
 * 5. Back at Node 11:
 * <p>
 * The left subtree has been explored.
 * Now explore the right subtree.
 * <p>
 * 6. Node 2:
 * <p>
 * currentSum = 20 + 2 = 22
 * <p>
 * path = [5, 4, 11, 2]
 * <p>
 * Node 2 is a leaf.
 * <p>
 * 22 == 22, so add a copy of path to res.
 * <pre>
 * res = [[5, 4, 11, 2]]
 * </pre>
 * <p>
 * Backtrack:
 * - Remove 2 from path.
 * - path = [5, 4, 11]
 * <p>
 * 7. Back at Node 11:
 * <p>
 * Both subtrees have been explored.
 * Remove 11 from path.
 * <p>
 * path = [5, 4]
 * <p>
 * 8. Back at Node 4:
 * <p>
 * Its left subtree has been explored.
 * Since it has no right child, remove 4 from path.
 * <p>
 * path = [5]
 * <p>
 * 9. Back at Node 5:
 * <p>
 * The left subtree has been explored.
 * Now explore the right subtree.
 * <p>
 * 10. Node 8:
 * <p>
 * currentSum = 5 + 8 = 13
 * <p>
 * path = [5, 8]
 * <p>
 * Explore the left subtree.
 * <p>
 * 11. Node 13:
 * <p>
 * currentSum = 13 + 13 = 26
 * <p>
 * path = [5, 8, 13]
 * <p>
 * Node 13 is a leaf.
 * 26 != 22, so no path is added.
 * <p>
 * Backtrack and restore path = [5, 8].
 * <p>
 * 12. Back at Node 8:
 * <p>
 * Explore the right subtree.
 * <p>
 * 13. Node 4:
 * <p>
 * currentSum = 13 + 4 = 17
 * <p>
 * path = [5, 8, 4]
 * <p>
 * Explore its right child.
 * <p>
 * 14. Node 1:
 * <p>
 * currentSum = 17 + 1 = 18
 * <p>
 * path = [5, 8, 4, 1]
 * <p>
 * Node 1 is a leaf.
 * 18 != 22, so no path is added.
 * <p>
 * Final Result:
 * <pre>
 * [[5, 4, 11, 2]]
 * </pre>
 * <p>
 * Understanding Backtracking:
 * <p>
 * The path list is shared across all recursive calls.
 * We add a node when entering its recursive call and remove it
 * after its subtree has been completely explored.
 * <p>
 * For example:
 * <pre>
 * Before exploring node 7:
 * path = [5, 4, 11, 7]
 *
 * After returning from node 7:
 * path = [5, 4, 11]
 * </pre>
 * <p>
 * This ensures that when we explore node 2, node 7 is no longer
 * part of the current path.
 * <p>
 * The same backtracking happens at every level of the tree.
 * <p>
 * Understanding the Result List:
 * <p>
 * The result list is also shared across recursive calls.
 * Whenever a valid path is found, we add a new copy of the
 * current path to res.
 * <p>
 * Recursive calls do not need to return a new result list.
 * They modify the same shared res list, so all collected paths
 * remain available to the original pathSum() call.
 * <p>
 * Edge Cases:
 * <p>
 * 1. Empty Tree:
 * If root is null, return an empty list.
 * <p>
 * 2. Single Node:
 * The root itself is a leaf.
 * Return its value as a path only if it equals targetSum.
 * <p>
 * 3. No Valid Path:
 * If no root-to-leaf path matches targetSum, return an empty list.
 * <p>
 * 4. Multiple Valid Paths:
 * All matching root-to-leaf paths must be added to the result.
 * <p>
 * 5. Negative Node Values:
 * The approach works with negative values because we maintain
 * the actual accumulated sum without making assumptions about
 * whether node values are positive or negative.
 * <p>
 * Complexity Analysis:
 * <p>
 * Let N be the total number of nodes, H be the height of the tree,
 * and K be the number of valid root-to-leaf paths.
 * <p>
 * Time Complexity: O(N * H)
 * <p>
 * - Every node is visited exactly once, taking O(N) time.
 * - Whenever a valid path is found, we copy the current path,
 * which takes O(H) time.
 * - Therefore, the worst-case time complexity is O(N * H).
 * <p>
 * Auxiliary Space Complexity: O(H)
 * <p>
 * - The recursive call stack requires O(H) space.
 * - The current path list also requires O(H) space.
 * <p>
 * Output Space Complexity: O(K * H)
 * <p>
 * - Each of the K valid paths can contain at most H nodes.
 * - Therefore, storing all valid paths requires O(K * H) space.
 */
public class PathSumII {

    /**
     * Finds all root-to-leaf paths whose sum equals targetSum.
     *
     * @param root      root node of the binary tree
     * @param targetSum required sum of each valid path
     * @return list containing all valid root-to-leaf paths
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null)
            return res;

        return pathSum2(root, targetSum, 0, res, new ArrayList<>());
    }

    /**
     * Recursively explores the tree while maintaining the current path
     * and its accumulated sum.
     *
     * @param root       current node being processed
     * @param targetSum  required sum of each valid path
     * @param currentSum accumulated sum from root to current node
     * @param res        shared list storing all valid paths
     * @param path       shared list storing the current root-to-node path
     * @return list containing all valid root-to-leaf paths
     */
    public List<List<Integer>> pathSum2(
            TreeNode root,
            int targetSum,
            int currentSum,
            List<List<Integer>> res,
            List<Integer> path) {

        // Check whether the current node is a leaf.
        if (root.left == null && root.right == null) {

            currentSum += root.val;
            path.add(root.val);

            // Store a copy of the path if its sum matches targetSum.
            if (currentSum == targetSum) {
                res.add(new ArrayList<>(path));
            }

            // Backtrack before returning from the leaf call.
            path.removeLast();
            return res;
        }

        // Include the current node in the path and accumulated sum.
        currentSum += root.val;
        path.add(root.val);

        // Explore the left subtree.
        if (root.left != null)
            pathSum2(root.left, targetSum, currentSum, res, path);

        // Explore the right subtree.
        if (root.right != null)
            pathSum2(root.right, targetSum, currentSum, res, path);

        // Remove the current node after both subtrees are explored.
        path.removeLast();

        return res;
    }
}