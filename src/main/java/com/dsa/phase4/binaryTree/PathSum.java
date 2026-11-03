package com.dsa.phase4.binaryTree;

/**
 * Problem: Path Sum
 * <p>
 * LeetCode: https://leetcode.com/problems/path-sum/
 * <p>
 * Difficulty: Easy
 * <p>
 * Topics:
 * - Binary Tree
 * - Depth First Search (DFS)
 * - Recursion
 * <p>
 * Problem Statement:
 * Given the root of a binary tree and an integer targetSum,
 * return true if the tree has a root-to-leaf path such that
 * adding up all the values along that path equals targetSum.
 * <p>
 * A leaf is a node that does not have any children.
 * <p>
 * Approach: Recursive DFS (Depth First Search)
 * <p>
 * We use recursion to explore every possible root-to-leaf path
 * while maintaining the accumulated sum along the current path.
 * <p>
 * Instead of collecting all possible path sums in a list,
 * we directly return a boolean indicating whether a valid path exists.
 * <p>
 * The recursive method returns:
 * - true  -> A valid root-to-leaf path has been found.
 * - false -> No valid root-to-leaf path has been found in this subtree.
 * <p>
 * Important Observations:
 * <p>
 * 1. We only need to check the accumulated sum when we reach a leaf.
 * <p>
 * Why?
 * The problem specifically asks for a root-to-leaf path.
 * Reaching the target sum at an intermediate node does not mean
 * we have found a valid path because that node may have children.
 * <p>
 * 2. Each recursive call receives the accumulated sum from its parent.
 * <p>
 * Since sum is a primitive int, Java passes its value.
 * Each recursive invocation has its own local copy.
 * Therefore, modifying sum in one recursive call does not
 * affect the sum maintained by another call.
 * <p>
 * 3. Recursive return values must be propagated to the parent.
 * <p>
 * If either the left or right subtree returns true,
 * the current node should also return true.
 * <p>
 * 4. We do not need to explore the right subtree if the left
 * subtree has already found a valid path.
 * <p>
 * This is achieved using an early return.
 * <p>
 * 5. If neither subtree finds a valid path, return false.
 * <p>
 * Algorithm:
 * <p>
 * 1. In hasPathSum():
 * - If root is null, return false.
 * - Otherwise, call the recursive helper with an initial sum of 0.
 * <p>
 * 2. In pathSum():
 * <p>
 * a. Check whether the current node is a leaf.
 * <p>
 * - Add the current node's value to the accumulated sum.
 * - Compare the sum with targetSum.
 * - Return the comparison result.
 * <p>
 * b. If the current node is not a leaf:
 * <p>
 * - Add the current node's value to the accumulated sum.
 * <p>
 * c. Explore the left subtree, if it exists.
 * <p>
 * - If the recursive call returns true, immediately return true.
 * <p>
 * d. Explore the right subtree, if it exists.
 * <p>
 * - If the recursive call returns true, immediately return true.
 * <p>
 * e. If neither subtree finds a valid path, return false.
 * <p>
 * Execution Walkthrough:
 * <p>
 * Consider the following tree:
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * / \       \
 * 7   2       1
 * <p>
 * targetSum = 22
 * <p>
 * Consider the path:
 * 5 -> 4 -> 11 -> 2
 * <p>
 * Execution:
 * <p>
 * 1. Node 5:
 * sum = 0 + 5 = 5
 * Explore the left subtree.
 * <p>
 * 2. Node 4:
 * sum = 5 + 4 = 9
 * Explore the left subtree.
 * <p>
 * 3. Node 11:
 * sum = 9 + 11 = 20
 * Explore its left subtree first.
 * <p>
 * 4. Node 7:
 * sum = 20 + 7 = 27
 * Node 7 is a leaf.
 * 27 != 22, so return false.
 * <p>
 * 5. Back at Node 11:
 * The left subtree returned false.
 * Explore the right subtree.
 * <p>
 * 6. Node 2:
 * sum = 20 + 2 = 22
 * Node 2 is a leaf.
 * 22 == 22, so return true.
 * <p>
 * 7. Back at Node 11:
 * The right subtree returned true.
 * Immediately return true.
 * <p>
 * 8. Back at Node 4:
 * Its left subtree returned true.
 * Immediately return true.
 * <p>
 * 9. Back at Node 5:
 * Its left subtree returned true.
 * Immediately return true.
 * <p>
 * Final Output:
 * true
 * <p>
 * Recursion and Return Value Propagation:
 * <p>
 * This problem demonstrates how a recursive method can return
 * information to its parent.
 * <p>
 * If a child subtree finds a valid path, it returns true.
 * The parent receives that true and propagates it further upward.
 * <p>
 * This continues until the original hasPathSum() call receives true.
 * <p>
 * The logical condition is:
 * <p>
 * result = leftSubtreeResult OR rightSubtreeResult
 * <p>
 * We implement this using early returns:
 * <p>
 * if (leftSubtreeReturnsTrue) return true;
 * if (rightSubtreeReturnsTrue) return true;
 * <p>
 * Edge Cases:
 * <p>
 * 1. Empty Tree:
 * If root is null, return false.
 * <p>
 * 2. Single Node:
 * The root itself is a leaf.
 * Return whether its value equals targetSum.
 * <p>
 * 3. Target Sum Reached at an Intermediate Node:
 * Do not return true immediately.
 * The path must continue until a leaf is reached.
 * <p>
 * 4. Negative Node Values:
 * The approach works with negative values because we maintain
 * the actual accumulated sum without assuming values are positive.
 * <p>
 * Complexity Analysis:
 * <p>
 * Let N be the total number of nodes and H be the height of the tree.
 * <p>
 * Time Complexity: O(N)
 * - In the worst case, every node may need to be visited.
 * - Each node is processed at most once.
 * - Therefore, the time complexity is O(N).
 * <p>
 * Auxiliary Space Complexity: O(H)
 * - Recursive calls are stored on the call stack.
 * - The maximum number of simultaneous recursive calls depends
 * on the height of the tree.
 * - Balanced tree: O(log N)
 * - Skewed tree: O(N)
 *
 */
public class PathSum {

    /*
    // Initial Approach:
    // Collect all root-to-leaf path sums in a shared list
    // and check whether the target sum exists in that list.
    //
    // This approach works, but requires storing all path sums
    // even though we only need to know whether one valid path exists.

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        List<Integer> list = pathSum(root, new ArrayList<>(), 0);
        return list.contains(targetSum);
    }

    public List<Integer> pathSum(TreeNode root, List<Integer> list, int sum) {
        if (root.left == null && root.right == null) {
            sum += root.val;
            list.add(sum);
            return list;
        }

        sum += root.val;

        if (root.left != null) pathSum(root.left, list, sum);
        if (root.right != null) pathSum(root.right, list, sum);

        return list;
    }
    */

    /**
     * Checks whether the binary tree contains a root-to-leaf path
     * whose node values add up to targetSum.
     *
     * @param root      root node of the binary tree
     * @param targetSum required sum of the root-to-leaf path
     * @return true if a valid path exists, otherwise false
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        return pathSum(root, 0, targetSum);
    }

    /**
     * Recursively explores the tree while maintaining the accumulated
     * sum of the current root-to-node path.
     *
     * @param root      current node being processed
     * @param sum       accumulated sum from the root to the parent of the current node
     * @param targetSum required sum of the root-to-leaf path
     * @return true if a valid root-to-leaf path is found, otherwise false
     */
    public boolean pathSum(TreeNode root, int sum, int targetSum) {

        // A path is validated only when we reach a leaf node.
        if (root.left == null && root.right == null) {
            sum += root.val;
            return sum == targetSum;
        }

        // Include the current node's value in the accumulated sum.
        sum += root.val;

        // Explore the left subtree.
        // If a valid path is found, propagate true immediately.
        if (root.left != null) {
            if (pathSum(root.left, sum, targetSum)) return true;
        }

        // Explore the right subtree only if the left subtree
        // did not find a valid path.
        if (root.right != null) {
            if (pathSum(root.right, sum, targetSum)) return true;
        }

        // Neither subtree contains a valid root-to-leaf path.
        return false;
    }
}