package com.dsa.phase4.binaryTree;

/**
 * Problem: Balanced Binary Tree
 * <p>
 * LeetCode: https://leetcode.com/problems/balanced-binary-tree/
 * <p>
 * Difficulty: Easy
 * <p>
 * Topics:
 * - Binary Tree
 * - Depth First Search (DFS)
 * - Recursion
 * - Tree Height
 * <p>
 * Problem Statement:
 * Given the root of a binary tree, determine whether it is height-balanced.
 * <p>
 * A binary tree is height-balanced if, for every node in the tree,
 * the difference between the heights of its left and right subtrees
 * is not greater than 1.
 * <p>
 * Approach: Recursive DFS with Sentinel Return Value
 * <p>
 * We use recursion to calculate the height of every subtree
 * while checking whether the current subtree is balanced.
 * <p>
 * Instead of calculating all subtree heights and storing their
 * differences in a separate list, we directly detect imbalance
 * during the height calculation itself.
 * <p>
 * The recursive method returns:
 * <p>
 * - Positive integer (including 0) -> Actual height of the subtree.
 * - -1 -> The subtree is unbalanced.
 * <p>
 * We use -1 as a special sentinel value to indicate that
 * an imbalance has been detected.
 * <p>
 * Important Observations:
 * <p>
 * 1. A binary tree must satisfy the balance condition at EVERY node.
 * <p>
 * It is not sufficient to check only the root node.
 * <p>
 * For every node:
 * <p>
 * abs(height(left) - height(right)) <= 1
 * <p>
 * 2. We can calculate the height and check the balance condition
 * in the same recursive traversal.
 * <p>
 * We do not need a separate traversal to validate each node.
 * <p>
 * 3. We use -1 as a sentinel return value.
 * <p>
 * Why?
 * <p>
 * The actual height of a binary tree can never be negative.
 * Therefore, -1 can safely represent an unbalanced subtree.
 * <p>
 * 4. An imbalance detected in a child subtree must be propagated
 * to all its ancestors.
 * <p>
 * If either the left or right subtree returns -1,
 * the current node must also return -1.
 * <p>
 * This prevents an ancestor from treating an already
 * unbalanced subtree as if it had a valid height.
 * <p>
 * 5. We perform an early return when an imbalance is detected.
 * <p>
 * Once a subtree is found to be unbalanced, calculating
 * its actual height is unnecessary.
 * <p>
 * We simply propagate -1 to the parent.
 * <p>
 * 6. We use postorder traversal (Left -> Right -> Root).
 * <p>
 * Why?
 * <p>
 * To determine whether the current node is balanced,
 * we first need the heights of its left and right subtrees.
 * <p>
 * Therefore, we process both children before processing
 * the current node.
 * <p>
 * Algorithm:
 * <p>
 * 1. In isBalanced():
 * <p>
 * - If root is null, return true.
 * - Otherwise, call heightOfTree(root).
 * - If the result is -1, return false.
 * - Otherwise, return true.
 * <p>
 * 2. In heightOfTree():
 * <p>
 * a. Base Case:
 * <p>
 * - If root is null, return 0.
 * - An empty subtree has height 0.
 * <p>
 * b. Calculate the left subtree height.
 * <p>
 * - Recursively call heightOfTree(root.left).
 * <p>
 * c. Calculate the right subtree height.
 * <p>
 * - Recursively call heightOfTree(root.right).
 * <p>
 * d. Check whether either subtree is already unbalanced.
 * <p>
 * - If leftHeight == -1 OR rightHeight == -1,
 * immediately return -1.
 * <p>
 * - This propagates the imbalance to the parent.
 * <p>
 * e. Check the balance condition at the current node.
 * <p>
 * - Calculate the absolute difference between leftHeight
 * and rightHeight.
 * <p>
 * - If the difference is greater than 1, return -1.
 * <p>
 * f. If the current subtree is balanced:
 * <p>
 * - Return the maximum of the two subtree heights + 1.
 * <p>
 * Execution Walkthrough:
 * <p>
 * Consider the following tree:
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * /
 * 6
 * <p>
 * Execution:
 * <p>
 * 1. Node 6:
 * <p>
 * Both children are null.
 * <p>
 * leftHeight = 0
 * rightHeight = 0
 * <p>
 * Difference = abs(0 - 0) = 0
 * <p>
 * Return max(0, 0) + 1 = 1
 * <p>
 * 2. Node 4:
 * <p>
 * leftHeight = 1
 * rightHeight = 0
 * <p>
 * Difference = abs(1 - 0) = 1
 * <p>
 * Return max(1, 0) + 1 = 2
 * <p>
 * 3. Node 5:
 * <p>
 * Both children are null.
 * <p>
 * leftHeight = 0
 * rightHeight = 0
 * <p>
 * Difference = 0
 * <p>
 * Return 1
 * <p>
 * 4. Node 2:
 * <p>
 * leftHeight = 2
 * rightHeight = 1
 * <p>
 * Difference = abs(2 - 1) = 1
 * <p>
 * Return max(2, 1) + 1 = 3
 * <p>
 * 5. Node 3:
 * <p>
 * Both children are null.
 * <p>
 * leftHeight = 0
 * rightHeight = 0
 * <p>
 * Difference = 0
 * <p>
 * Return 1
 * <p>
 * 6. Back at Node 1:
 * <p>
 * leftHeight = 3
 * rightHeight = 1
 * <p>
 * Difference = abs(3 - 1) = 2
 * <p>
 * Since the difference is greater than 1,
 * return -1.
 * <p>
 * 7. Back in isBalanced():
 * <p>
 * heightOfTree(root) returns -1.
 * <p>
 * Therefore:
 * <p>
 * -1 != -1 evaluates to false.
 * <p>
 * Final Output:
 * <p>
 * false
 * <p>
 * Sentinel Value Propagation:
 * <p>
 * Consider another tree:
 * <p>
 * 1
 * /
 * 2
 * /
 * 3
 * /
 * 4
 * <p>
 * Execution:
 * <p>
 * 1. Node 4:
 * <p>
 * leftHeight = 0
 * rightHeight = 0
 * <p>
 * Return 1.
 * <p>
 * 2. Node 3:
 * <p>
 * leftHeight = 1
 * rightHeight = 0
 * <p>
 * Difference = 1
 * <p>
 * Return 2.
 * <p>
 * 3. Node 2:
 * <p>
 * leftHeight = 2
 * rightHeight = 0
 * <p>
 * Difference = 2
 * <p>
 * Return -1.
 * <p>
 * 4. Back at Node 1:
 * <p>
 * leftHeight = -1
 * <p>
 * The following condition becomes true:
 * <p>
 * if (leftHeight == -1 || rightHeight == -1)
 * <p>
 * Therefore, return -1 immediately.
 * <p>
 * 5. The original isBalanced() method receives -1
 * and returns false.
 * <p>
 * This demonstrates how an imbalance detected deep inside
 * the tree is propagated all the way to the root.
 * <p>
 * Edge Cases:
 * <p>
 * 1. Empty Tree:
 * <p>
 * If root is null, return true.
 * <p>
 * An empty tree is considered balanced.
 * <p>
 * 2. Single Node:
 * <p>
 * Both children are null.
 * <p>
 * The height difference is 0.
 * <p>
 * Return true.
 * <p>
 * 3. Completely Balanced Tree:
 * <p>
 * Every node has a left and right subtree height difference
 * of at most 1.
 * <p>
 * Return true.
 * <p>
 * 4. Left-Skewed Tree:
 * <p>
 * A sufficiently deep left-skewed tree will violate
 * the balance condition.
 * <p>
 * Return false.
 * <p>
 * 5. Right-Skewed Tree:
 * <p>
 * A sufficiently deep right-skewed tree will violate
 * the balance condition.
 * <p>
 * Return false.
 * <p>
 * 6. Unbalanced Subtree:
 * <p>
 * Even if the root itself appears balanced, an imbalance
 * inside any descendant subtree must result in false.
 * <p>
 * The -1 sentinel value handles this case by propagating
 * the imbalance to the root.
 * <p>
 * Complexity Analysis:
 * <p>
 * Let N be the total number of nodes and H be the height
 * of the binary tree.
 * <p>
 * Time Complexity: O(N)
 * <p>
 * - Every node is visited at most once.
 * - Each node performs constant-time operations:
 * height comparison, absolute difference, and maximum calculation.
 * - Therefore, the worst-case time complexity is O(N).
 * <p>
 * Auxiliary Space Complexity: O(H)
 * <p>
 * - Recursive calls are stored on the call stack.
 * - The maximum number of simultaneous recursive calls
 * depends on the height of the tree.
 * <p>
 * - Balanced tree: O(log N)
 * - Skewed tree: O(N)
 * <p>
 * Optimization:
 * <p>
 * The initial approach used an ArrayList to store the height
 * difference at every node and then iterated over the list
 * to determine whether the tree was balanced.
 * <p>
 * Although that approach works, it requires additional O(N)
 * space for storing the differences.
 * <p>
 * The optimized approach eliminates the list entirely.
 * <p>
 * By using -1 as a sentinel value, we can detect imbalance
 * during the height calculation itself.
 * <p>
 * This gives us:
 * <p>
 * - Time Complexity: O(N)
 * - Auxiliary Space Complexity: O(H)
 * <p>
 * The main idea is to combine height calculation and
 * balance validation into a single postorder traversal.
 */
public class BalancedBinaryTree {

    /*
    // Initial Approach:
    // Calculate the height difference at every node,
    // store the differences in a list, and then check
    // whether any node has a difference greater than 1.
    //
    // This approach works, but requires additional space
    // to store the height differences.

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        List<Integer> list = new ArrayList<>();

        heightOfTree(root, list);

        for (Integer integer : list) {
            if (integer > 1)
                return false;
        }

        return true;
    }

    public int heightOfTree(TreeNode root, List<Integer> list) {
        if (root == null)
            return 0;

        int leftHeight = heightOfTree(root.left, list);
        int rightHeight = heightOfTree(root.right, list);

        list.add(Math.abs(leftHeight - rightHeight));

        return Math.max(leftHeight, rightHeight) + 1;
    }
    */

    /**
     * Checks whether the binary tree is height-balanced.
     *
     * @param root root node of the binary tree
     * @return true if the tree is balanced, otherwise false
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        return heightOfTree(root) != -1;
    }

    /**
     * Recursively calculates the height of a subtree while
     * checking whether the subtree is balanced.
     *
     * @param root current node being processed
     * @return actual subtree height if balanced, otherwise -1
     */
    public int heightOfTree(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = heightOfTree(root.left);
        int rightHeight = heightOfTree(root.right);

        // Propagate an imbalance detected in either child subtree.
        if (leftHeight == -1 || rightHeight == -1) return -1;

        // Check whether the current node satisfies the balance condition.
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        // Return the actual height of the current subtree.
        return Math.max(leftHeight, rightHeight) + 1;
    }
}