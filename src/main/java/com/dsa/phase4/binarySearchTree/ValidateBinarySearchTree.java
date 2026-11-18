package com.dsa.phase4.binarySearchTree;

/**
 * Problem: Validate Binary Search Tree
 *
 * <p>
 * LeetCode: https://leetcode.com/problems/validate-binary-search-tree/
 * <p>
 * Difficulty: Medium
 * <p>
 * Topics:
 * - Binary Search Tree (BST)
 * - Binary Tree
 * - Depth First Search (DFS)
 * - Recursion
 * <p>
 * Problem Statement:
 * Given the root of a binary tree, determine whether it is a valid
 * Binary Search Tree (BST).
 *
 * <p>
 * A valid BST must satisfy the following conditions:
 * - All nodes in the left subtree of a node must have values
 * strictly smaller than the node's value.
 * - All nodes in the right subtree of a node must have values
 * strictly greater than the node's value.
 * - Both left and right subtrees must also be valid BSTs.
 *
 * <p>
 * Approach: Recursive DFS using Minimum and Maximum Boundaries
 *
 * <p>
 * We use recursion to validate every node while maintaining
 * the allowed range of values for that node.
 *
 * <p>
 * Instead of checking only the immediate left and right children,
 * we maintain the boundaries imposed by all previous ancestors.
 *
 * <p>
 * The recursive method returns:
 * - true  -> The current subtree is a valid BST.
 * - false -> The current subtree violates the BST property.
 *
 * <p>
 * Important Observations:
 *
 * <p>
 * 1. Checking only the immediate parent is insufficient.
 *
 * <p>
 * Why?
 *
 * <p>
 * A node can satisfy the BST property with respect to its immediate
 * parent but still violate the property imposed by an ancestor.
 *
 * <p>
 * For example:
 * <p>
 * 10
 * /  \
 * 5    15
 * \
 * 12
 * <p>
 * Node 12 is greater than its immediate parent 5, which is correct
 * for a right child.
 * <p>
 * However, node 12 belongs to the left subtree of 10 and must be
 * smaller than 10.
 * <p>
 * Therefore, the tree is not a valid BST.
 *
 * <p>
 * 2. Every node must satisfy an allowed range.
 *
 * <p>
 * We maintain two boundaries:
 * - min -> Minimum allowed value (exclusive).
 * - max -> Maximum allowed value (exclusive).
 *
 * <p>
 * A node is valid only when:
 * <p>
 * min < root.val < max
 *
 * <p>
 * 3. How boundaries are updated:
 *
 * <p>
 * When traversing the left subtree:
 * - The current node becomes the new maximum boundary.
 * - The minimum boundary remains unchanged.
 *
 * <p>
 * When traversing the right subtree:
 * - The current node becomes the new minimum boundary.
 * - The maximum boundary remains unchanged.
 *
 * <p>
 * 4. Why do we use Long instead of Integer?
 *
 * <p>
 * TreeNode values are integers, but a valid BST can contain
 * Integer.MIN_VALUE and Integer.MAX_VALUE.
 *
 * <p>
 * Using Long.MIN_VALUE and Long.MAX_VALUE ensures that these
 * integer boundary values are also accepted as valid node values.
 *
 * <p>
 * 5. Recursive return values must be propagated.
 *
 * <p>
 * If either the left or right subtree returns false, the current
 * node must also return false.
 *
 * <p>
 * This ensures that an invalid node discovered deep inside the tree
 * propagates false all the way back to the original method call.
 *
 * <p>
 * 6. Duplicate values are not allowed.
 *
 * <p>
 * Since the comparison is strict:
 * min < root.val < max
 * <p>
 * Equal values violate the BST property.
 *
 * <p>
 * Algorithm:
 *
 * <p>
 * 1. In isValidBST():
 * - Pass the root node to the recursive method.
 * - Initialize the minimum boundary to Long.MIN_VALUE.
 * - Initialize the maximum boundary to Long.MAX_VALUE.
 *
 * <p>
 * 2. In traverseBST():
 *
 * <p>
 * a. Base Case:
 * - If the current node is null, return true.
 * - An empty subtree is considered a valid BST.
 *
 * <p>
 * b. Validate the current node:
 * - If root.val <= min or root.val >= max, return false.
 *
 * <p>
 * c. Explore the left subtree:
 * - Keep the existing minimum boundary.
 * - Update the maximum boundary to root.val.
 * - Recursively validate the left subtree.
 * - If it returns false, propagate false immediately.
 *
 * <p>
 * d. Explore the right subtree:
 * - Update the minimum boundary to root.val.
 * - Keep the existing maximum boundary.
 * - Recursively validate the right subtree.
 * - If it returns false, propagate false immediately.
 *
 * <p>
 * e. If both subtrees are valid, return true.
 *
 * <p>
 * Execution Walkthrough:
 *
 * <p>
 * Consider the following tree:
 * <p>
 * 10
 * /  \
 * 5    15
 * / \   / \
 * 2   7 12  20
 *
 * <p>
 * Execution:
 *
 * <p>
 * 1. Node 10:
 * - Initial range: (-infinity, +infinity).
 * - 10 satisfies the range.
 * - Explore the left subtree with range (-infinity, 10).
 *
 * <p>
 * 2. Node 5:
 * - Range: (-infinity, 10).
 * - 5 satisfies the range.
 * - Explore the left subtree with range (-infinity, 5).
 *
 * <p>
 * 3. Node 2:
 * - Range: (-infinity, 5).
 * - 2 satisfies the range.
 * - Both children are null, so return true.
 *
 * <p>
 * 4. Back at Node 5:
 * - The left subtree returned true.
 * - Explore the right subtree with range (5, 10).
 *
 * <p>
 * 5. Node 7:
 * - Range: (5, 10).
 * - 7 satisfies the range.
 * - Both children are null, so return true.
 *
 * <p>
 * 6. Back at Node 10:
 * - Both left-side recursive calls were valid.
 * - Explore the right subtree with range (10, +infinity).
 *
 * <p>
 * 7. Node 15:
 * - Range: (10, +infinity).
 * - 15 satisfies the range.
 * - Explore its left subtree with range (10, 15).
 *
 * <p>
 * 8. Node 12:
 * - Range: (10, 15).
 * - 12 satisfies the range.
 * - Both children are null, so return true.
 *
 * <p>
 * 9. Node 20:
 * - Range: (15, +infinity).
 * - 20 satisfies the range.
 * - Both children are null, so return true.
 *
 * <p>
 * 10. Both subtrees of Node 15 return true.
 * - Node 15 returns true.
 *
 * <p>
 * 11. Both subtrees of Node 10 return true.
 * - Node 10 returns true.
 *
 * <p>
 * Final Output:
 * true
 *
 * <p>
 * Recursion and Return Value Propagation:
 *
 * <p>
 * This problem demonstrates how constraints can be passed down
 * through recursive calls while validation results are returned
 * upwards.
 *
 * <p>
 * Information passed from parent to child:
 * - Minimum allowed value.
 * - Maximum allowed value.
 *
 * <p>
 * Information returned from child to parent:
 * - Whether the subtree is a valid BST.
 *
 * <p>
 * If any recursive call discovers an invalid node, it returns false.
 * Every parent checks the child's result and propagates false upwards.
 *
 * <p>
 * Edge Cases:
 *
 * <p>
 * 1. Empty Tree:
 * - An empty tree is a valid BST.
 * - Return true.
 *
 * <p>
 * 2. Single Node:
 * - A single-node tree is always a valid BST.
 *
 * <p>
 * 3. Duplicate Values:
 * - Duplicate values are not allowed.
 * - Return false if a node violates either strict boundary.
 *
 * <p>
 * 4. Invalid Ancestor Relationship:
 * - A node may satisfy its immediate parent's condition but violate
 * a boundary imposed by an ancestor.
 * - The inherited minimum and maximum boundaries detect this.
 *
 * <p>
 * 5. Integer Boundary Values:
 * - Integer.MIN_VALUE and Integer.MAX_VALUE are valid node values.
 * - Long boundaries allow both values to be handled correctly.
 *
 * <p>
 * Complexity Analysis:
 *
 * <p>
 * Let N be the total number of nodes and H be the height of the tree.
 *
 * <p>
 * Time Complexity: O(N)
 * - Every node is visited exactly once.
 * - Each node performs constant-time comparisons and recursive calls.
 *
 * <p>
 * Auxiliary Space Complexity: O(H)
 * - Recursive calls are stored on the call stack.
 * - The maximum number of simultaneous calls depends on tree height.
 * - Balanced tree: O(log N).
 * - Skewed tree: O(N).
 */

public class ValidateBinarySearchTree {

    /*
    // Initial Approach:
    // Perform inorder traversal and store all node values in a list.
    //
    // Inorder traversal of a valid BST produces values in strictly
    // increasing order.
    //
    // We can then iterate through the list and verify that every
    // element is greater than the previous element.
    //
    // This approach works, but requires O(N) additional space
    // to store all node values.

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        List<Integer> list = traverseBST(root, new ArrayList<>());

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) <= list.get(i)) {
                return false;
            }
        }

        return true;
    }

    public List<Integer> traverseBST(TreeNode root, List<Integer> list) {
        if (root.left != null) {
            traverseBST(root.left, list);
        }

        list.add(root.val);

        if (root.right != null) {
            traverseBST(root.right, list);
        }

        return list;
    }
    */

    public boolean isValidBST(TreeNode root) {
        return traverseBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean traverseBST(TreeNode root, long min, long max) {
        if (root == null)
            return true;

        if (root.val <= min || root.val >= max)
            return false;

        boolean leftValid = traverseBST(root.left, min, root.val);

        if (!leftValid)
            return false;

        boolean rightValid = traverseBST(root.right, root.val, max);

        if (!rightValid)
            return false;

        return true;
    }
}
