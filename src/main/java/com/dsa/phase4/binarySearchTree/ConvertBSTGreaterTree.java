package com.dsa.phase4.binarySearchTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode Problem 538: Convert BST to Greater Tree
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 * <p>
 * Problem Statement:
 * Given the root of a Binary Search Tree (BST), convert it into a
 * Greater Tree where each node's new value is the sum of its original
 * value and all values greater than it in the BST.
 * <p>
 * Solution Approach: Inorder Traversal + HashMap + DFS
 * <p>
 * 1. Perform an inorder traversal of the BST and store all node
 * values in a List. Since inorder traversal of a BST produces
 * values in ascending order, the list will be sorted.
 * <p>
 * 2. Iterate over the list in reverse order while maintaining a
 * running sum. Add each element to the running sum and store
 * the mapping of original value to greater tree value in a
 * HashMap.
 * <p>
 * Since we traverse from the largest value to the smallest,
 * the running sum represents the sum of the current value
 * and all greater values.
 * <p>
 * 3. Traverse the original BST using DFS (Left -> Right -> Root)
 * and update each node's value using the corresponding value
 * from the HashMap.
 * <p>
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * <p>
 * where n is the number of nodes in the BST.
 */
public class ConvertBSTGreaterTree {

    /**
     * Converts a BST into a Greater Tree.
     *
     * @param root root node of the original BST
     * @return root node of the modified Greater Tree
     */
    public TreeNode convertBST(TreeNode root) {

        /*
         * If the tree is empty, there is nothing to convert.
         */
        if (root == null) return null;

        /*
         * Step 1:
         *
         * Perform inorder traversal to collect all original
         * node values in ascending order.
         */
        List<Integer> list = traverse(root, new ArrayList<>());

        /*
         * Step 2:
         *
         * Store the mapping between each original node value
         * and its corresponding Greater Tree value.
         */
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;

        for (int i = list.size() - 1; i >= 0; i--) {
            sum += list.get(i);
            map.put(list.get(i), sum);
        }

        /*
         * Step 3:
         *
         * Traverse the original BST and update every node
         * using the values stored in the HashMap.
         */
        convertBST2(root, map);

        return root;
    }

    /**
     * Updates the values of the original BST using the
     * precomputed Greater Tree values in the HashMap.
     *
     * @param root current node of the BST
     * @param map  mapping of original values to new values
     */
    public void convertBST2(TreeNode root, Map<Integer, Integer> map) {

        /*
         * Process the left subtree first.
         */
        if (root.left != null) {
            convertBST2(root.left, map);
        }

        /*
         * Process the right subtree.
         */
        if (root.right != null) {
            convertBST2(root.right, map);
        }

        /*
         * Update the current node using its original value
         * as the key in the HashMap.
         */
        root.val = map.get(root.val);
    }

    /**
     * Performs inorder traversal of the BST and collects
     * all node values into a List.
     *
     * @param root current node of the BST
     * @param list List used to store inorder values
     * @return List containing node values in ascending order
     */
    public List<Integer> traverse(TreeNode root, List<Integer> list) {

        /*
         * Traverse the left subtree.
         */
        if (root.left != null) {
            traverse(root.left, list);
        }

        /*
         * Visit the current node.
         */
        list.add(root.val);

        /*
         * Traverse the right subtree.
         */
        if (root.right != null) {
            traverse(root.right, list);
        }

        return list;
    }
}
