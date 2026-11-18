
package com.dsa.phase4.binarySearchTree;

/**
 * LeetCode Problem 108: Convert Sorted Array to Binary Search Tree
 *
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * <p>
 * Difficulty: Easy
 * Topics: Binary Search Tree, Binary Tree, Recursion, Divide and Conquer
 *
 * <p>
 * Problem Statement:
 *
 * <p>
 * Given an integer array nums where the elements are sorted
 * in ascending order, convert it into a height-balanced
 * binary search tree (BST).
 *
 * <p>
 * A height-balanced binary tree is a binary tree in which
 * the depth of the two subtrees of every node never differs
 * by more than one.
 *
 * <p>
 * Approach: Recursive Divide and Conquer
 *
 * <p>
 * The key observation is that the middle element of a
 * sorted array can be selected as the root of a BST.
 *
 * <p>
 * Since the array is sorted:
 *
 * <p>
 * 1. All elements before the middle element are smaller.
 * <p>
 * 2. All elements after the middle element are greater.
 *
 * <p>
 * Therefore:
 *
 * <p>
 * - The left half of the array can form the left subtree.
 * - The right half of the array can form the right subtree.
 *
 * <p>
 * By repeatedly selecting the middle element of each
 * subarray, we construct a height-balanced BST.
 *
 * <p>
 * Algorithm:
 *
 * <p>
 * 1. Initially, call createBinaryTree() with the complete
 * array range:
 *
 * <p>
 * createBinaryTree(nums, 0, nums.length - 1)
 *
 * <p>
 * 2. Base Condition:
 *
 * <p>
 * If left > right, the current subarray is empty.
 * Return null because there is no node to construct.
 *
 * <p>
 * 3. Calculate the middle index:
 *
 * <p>
 * mid = (left + right) / 2
 *
 * <p>
 * 4. Create a new TreeNode using nums[mid].
 *
 * <p>
 * This becomes the root of the current subtree.
 *
 * <p>
 * 5. Recursively construct the left subtree:
 *
 * <p>
 * root.left = createBinaryTree(nums, left, mid - 1)
 *
 * <p>
 * This processes all elements smaller than the current root.
 *
 * <p>
 * 6. Recursively construct the right subtree:
 *
 * <p>
 * root.right = createBinaryTree(nums, mid + 1, right)
 *
 * <p>
 * This processes all elements greater than the current root.
 *
 * <p>
 * 7. Return the current root.
 *
 * <p>
 * Understanding Recursive Execution:
 *
 * <p>
 * Consider the following sorted array:
 *
 * <p>
 * nums = [-10, -3, 0, 5, 9]
 *
 * <p>
 * Initial Call:
 *
 * <p>
 * createBinaryTree(nums, 0, 4)
 *
 * <p>
 * 1. Calculate the middle index:
 *
 * <p>
 * mid = (0 + 4) / 2 = 2
 *
 * <p>
 * nums[2] = 0
 *
 * <p>
 * Create node 0 as the root.
 *
 * <p>
 * 2. Construct the left subtree:
 *
 * <p>
 * createBinaryTree(nums, 0, 1)
 *
 * <p>
 * mid = (0 + 1) / 2 = 0
 *
 * <p>
 * nums[0] = -10
 *
 * <p>
 * Create node -10 as the left child of 0.
 *
 * <p>
 * Now construct its left subtree:
 *
 * <p>
 * createBinaryTree(nums, 0, -1)
 *
 * <p>
 * Since left > right, return null.
 *
 * <p>
 * Construct its right subtree:
 *
 * <p>
 * createBinaryTree(nums, 1, 1)
 *
 * <p>
 * mid = (1 + 1) / 2 = 1
 *
 * <p>
 * nums[1] = -3
 *
 * <p>
 * Create node -3 as the right child of -10.
 *
 * <p>
 * Its left and right recursive calls both return null.
 *
 * <p>
 * Therefore, the left subtree is constructed as:
 *
 * <p>
 * -10
 * \
 * -3
 *
 * <p>
 * 3. Construct the right subtree:
 *
 * <p>
 * createBinaryTree(nums, 3, 4)
 *
 * <p>
 * mid = (3 + 4) / 2 = 3
 *
 * <p>
 * nums[3] = 5
 *
 * <p>
 * Create node 5 as the right child of 0.
 *
 * <p>
 * Its left subtree is constructed using:
 *
 * <p>
 * createBinaryTree(nums, 3, 2)
 *
 * <p>
 * Since left > right, return null.
 *
 * <p>
 * Its right subtree is constructed using:
 *
 * <p>
 * createBinaryTree(nums, 4, 4)
 *
 * <p>
 * mid = (4 + 4) / 2 = 4
 *
 * <p>
 * nums[4] = 9
 *
 * <p>
 * Create node 9 as the right child of 5.
 *
 * <p>
 * Its left and right recursive calls both return null.
 *
 * <p>
 * Final Binary Search Tree:
 *
 * <p>
 * 0
 * /   \
 * -10     5
 * \      \
 * -3      9
 *
 * <p>
 * Understanding Recursive Returns:
 *
 * <p>
 * Each recursive call is responsible for constructing
 * and returning the root of its own subtree.
 *
 * <p>
 * Consider the following statement:
 *
 * <p>
 * root.left = createBinaryTree(nums, left, mid - 1);
 *
 * <p>
 * The current recursive call waits for the left recursive
 * call to finish constructing its subtree.
 *
 * <p>
 * Once the recursive call returns its root node, that node
 * is assigned to root.left.
 *
 * <p>
 * Similarly:
 *
 * <p>
 * root.right = createBinaryTree(nums, mid + 1, right);
 *
 * <p>
 * The returned root of the right subtree is assigned
 * to root.right.
 *
 * <p>
 * Finally, the current call returns its own root node,
 * which allows its parent call to attach the entire
 * constructed subtree.
 *
 * <p>
 * Important Observations:
 *
 * <p>
 * 1. We are not physically dividing the array into
 * smaller arrays. Instead, we maintain left and right
 * indices to represent the current subarray.
 *
 * <p>
 * 2. Every recursive call operates on a smaller range
 * of the original sorted array.
 *
 * <p>
 * 3. The middle element becomes the root of the current
 * subtree, ensuring that smaller elements go left and
 * greater elements go right.
 *
 * <p>
 * 4. The recursive calls construct the left and right
 * subtrees independently.
 *
 * <p>
 * 5. Selecting the middle element repeatedly ensures
 * that the resulting BST is height-balanced.
 *
 * <p>
 * Complexity Analysis:
 *
 * <p>
 * Time Complexity: O(n)
 *
 * <p>
 * Every element in the array is processed exactly once
 * and converted into a TreeNode.
 *
 * <p>
 * Therefore, the total time complexity is O(n).
 *
 * <p>
 * Space Complexity: O(log n)
 *
 * <p>
 * The recursive call stack stores calls corresponding
 * to the height of the constructed BST.
 *
 * <p>
 * Since we repeatedly divide the array into approximately
 * two halves, the recursion depth is O(log n).
 *
 * <p>
 * The space required for the constructed tree is O(n),
 * but this is output space and is generally excluded
 * from auxiliary space complexity.
 */
public class ConvertSortedArrayToBinarySearchTree {

    /**
     * Converts a sorted array into a height-balanced BST.
     *
     * @param nums sorted integer array in ascending order
     * @return root node of the constructed BST
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        /*
         * Start constructing the BST using the complete
         * range of the input array.
         */
        return createBinaryTree(nums, 0, nums.length - 1);
    }

    /**
     * Recursively constructs a height-balanced BST
     * from the specified range of the sorted array.
     *
     * @param nums  original sorted array
     * @param left  starting index of the current range
     * @param right ending index of the current range
     * @return root node of the constructed subtree
     */
    public TreeNode createBinaryTree(int[] nums, int left, int right) {

        /*
         * Base condition:
         *
         * If the current range is empty, there is no node
         * to construct. Return null.
         */
        if (left > right) {
            return null;
        }

        /*
         * Select the middle element of the current range.
         *
         * This element becomes the root of the current subtree.
         */
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);

        /*
         * Construct the left subtree using elements
         * before the middle index.
         */
        root.left = createBinaryTree(nums, left, mid - 1);

        /*
         * Construct the right subtree using elements
         * after the middle index.
         */
        root.right = createBinaryTree(nums, mid + 1, right);

        /*
         * Return the root of the current subtree
         * to the parent recursive call.
         */
        return root;
    }
}
