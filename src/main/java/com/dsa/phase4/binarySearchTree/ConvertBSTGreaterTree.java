
package com.dsa.phase4.binarySearchTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode Problem 538: Convert BST to Greater Tree
 *
 * <p>
 * Problem Link:
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 *
 * <p>
 * Difficulty: Medium
 * Topics: Binary Search Tree, DFS, Inorder Traversal, HashMap
 *
 * <p>
 * Problem Statement:
 *
 * <p>
 * Given the root of a Binary Search Tree (BST), convert it
 * into a Greater Tree such that every key of the original BST
 * is replaced with the original key plus the sum of all keys
 * greater than the original key in the BST.
 *
 * <p>
 * In other words, each node's new value should be the sum
 * of all original node values greater than or equal to
 * its original value.
 *
 * <p>
 * Approach: Inorder Traversal + HashMap + DFS
 *
 * <p>
 * The approach consists of three steps:
 *
 * <p>
 * Step 1: Perform Inorder Traversal
 *
 * <p>
 * Since an inorder traversal of a BST produces values
 * in ascending order, we can collect all node values
 * into a List.
 *
 * <p>
 * For example, consider the following BST:
 *
 * <p>
 * 4
 * /   \
 * 1     6
 * / \
 * 5   7
 *
 * <p>
 * Its inorder traversal produces:
 *
 * <p>
 * [1, 4, 5, 6, 7]
 *
 * <p>
 * Step 2: Calculate the Greater Tree Values
 *
 * <p>
 * Once we have the sorted list, we calculate the new value
 * for every element using nested loops.
 *
 * <p>
 * For each index i:
 *
 * <p>
 * Calculate the sum of all elements starting from index i
 * until the end of the list.
 *
 * <p>
 * Mathematically:
 *
 * <p>
 * newValue[i] = list[i] + list[i+1] + ... + list[n-1]
 *
 * <p>
 * This gives us the original node value plus the sum
 * of all greater values.
 *
 * <p>
 * Store the result in a HashMap:
 *
 * <p>
 * Key   = Original node value
 * Value = Corresponding greater tree value
 *
 * <p>
 * For the example:
 *
 * <p>
 * Original inorder list:
 * [1, 4, 5, 6, 7]
 *
 * <p>
 * Calculations:
 *
 * <p>
 * 1 -> 1 + 4 + 5 + 6 + 7 = 23
 * <p>
 * 4 -> 4 + 5 + 6 + 7 = 22
 * <p>
 * 5 -> 5 + 6 + 7 = 18
 * <p>
 * 6 -> 6 + 7 = 13
 * <p>
 * 7 -> 7 = 7
 *
 * <p>
 * Resulting HashMap:
 *
 * <p>
 * 1 -> 23
 * <p>
 * 4 -> 22
 * <p>
 * 5 -> 18
 * <p>
 * 6 -> 13
 * <p>
 * 7 -> 7
 *
 * <p>
 * Step 3: Update the Original BST
 *
 * <p>
 * Once the HashMap is ready, traverse the original BST
 * using DFS.
 *
 * <p>
 * For every node:
 *
 * <p>
 * 1. Recursively visit its left subtree.
 * <p>
 * 2. Recursively visit its right subtree.
 * <p>
 * 3. Replace its original value with the corresponding
 * value from the HashMap.
 *
 * <p>
 * This updates every node in the original BST.
 *
 * <p>
 * Algorithm:
 *
 * <p>
 * 1. If the root is null, return null.
 *
 * <p>
 * 2. Perform inorder traversal using traverse() and
 * collect all original node values into a List.
 *
 * <p>
 * 3. Create a HashMap to store the mapping between
 * original node values and their greater tree values.
 *
 * <p>
 * 4. Iterate over the list using two nested loops.
 *
 * <p>
 * For each element at index i, calculate the sum of
 * all elements from index i to the end.
 *
 * <p>
 * 5. Store the original value as the key and its
 * calculated greater tree value as the value in the map.
 *
 * <p>
 * 6. Call convertBST2() to traverse the original BST
 * and update its node values using the HashMap.
 *
 * <p>
 * 7. Return the modified root.
 *
 * <p>
 * Understanding Recursive Execution:
 *
 * <p>
 * Consider the following BST:
 *
 * <p>
 * 4
 * /   \
 * 1     6
 * / \
 * 5   7
 *
 * <p>
 * Step 1: Inorder Traversal
 *
 * <p>
 * The initial call is:
 *
 * <p>
 * traverse(root, new ArrayList<>())
 *
 * <p>
 * The traversal follows Left -> Root -> Right.
 *
 * <p>
 * Execution:
 *
 * <p>
 * 1. Visit node 1 and add it to the list.
 * <p>
 * 2. Visit node 4 and add it to the list.
 * <p>
 * 3. Visit node 5 and add it to the list.
 * <p>
 * 4. Visit node 6 and add it to the list.
 * <p>
 * 5. Visit node 7 and add it to the list.
 *
 * <p>
 * Result:
 *
 * <p>
 * list = [1, 4, 5, 6, 7]
 *
 * <p>
 * Step 2: Build the HashMap
 *
 * <p>
 * Initially:
 *
 * <p>
 * sum = 0
 *
 * <p>
 * First iteration (i = 0):
 *
 * <p>
 * The inner loop calculates:
 *
 * <p>
 * 1 + 4 + 5 + 6 + 7 = 23
 *
 * <p>
 * Store:
 *
 * <p>
 * map.put(1, 23)
 *
 * <p>
 * Reset sum to 0.
 *
 * <p>
 * Second iteration (i = 1):
 *
 * <p>
 * Calculate:
 *
 * <p>
 * 4 + 5 + 6 + 7 = 22
 *
 * <p>
 * Store:
 *
 * <p>
 * map.put(4, 22)
 *
 * <p>
 * Similarly, the remaining entries are calculated:
 *
 * <p>
 * map.put(5, 18)
 * <p>
 * map.put(6, 13)
 * <p>
 * map.put(7, 7)
 *
 * <p>
 * Final Map:
 *
 * <p>
 * {
 * 1=23,
 * 4=22,
 * 5=18,
 * 6=13,
 * 7=7
 * }
 *
 * <p>
 * Step 3: Update the Original BST
 *
 * <p>
 * The call is:
 *
 * <p>
 * convertBST2(root, map)
 *
 * <p>
 * This method performs a postorder-style traversal:
 *
 * <p>
 * Left -> Right -> Root
 *
 * <p>
 * Execution:
 *
 * <p>
 * 1. Visit node 1.
 *
 * <p>
 * Both its children are null.
 * Replace its value using the map:
 *
 * <p>
 * root.val = map.get(1) = 23
 *
 * <p>
 * 2. Visit node 5.
 *
 * <p>
 * Both its children are null.
 * Replace its value:
 *
 * <p>
 * root.val = map.get(5) = 18
 *
 * <p>
 * 3. Visit node 7.
 *
 * <p>
 * Both its children are null.
 * Replace its value:
 *
 * <p>
 * root.val = map.get(7) = 7
 *
 * <p>
 * 4. Visit node 6.
 *
 * <p>
 * Its left and right subtrees have already been processed.
 * Replace its value:
 *
 * <p>
 * root.val = map.get(6) = 13
 *
 * <p>
 * 5. Finally, visit the root node 4.
 *
 * <p>
 * Replace its value:
 *
 * <p>
 * root.val = map.get(4) = 22
 *
 * <p>
 * Final Greater Tree:
 *
 * <p>
 * 22
 * /    \
 * 23     13
 * /  \
 * 18   7
 *
 * <p>
 * Understanding the Recursive Returns:
 *
 * <p>
 * The traverse() method uses inorder traversal to collect
 * node values into a shared List.
 *
 * <p>
 * Each recursive call receives the same List object.
 * Therefore, changes made by one recursive call are
 * available to all subsequent recursive calls.
 *
 * <p>
 * The convertBST2() method does not return a TreeNode.
 * Instead, it modifies the existing TreeNode objects
 * directly by updating their val properties.
 *
 * <p>
 * Since Java passes object references by value, every
 * recursive call operates on the same underlying tree.
 *
 * <p>
 * Important Observations:
 *
 * <p>
 * 1. Inorder traversal of a BST produces values
 * in ascending order.
 *
 * <p>
 * 2. For every element in the sorted list, the required
 * greater tree value is the suffix sum starting from
 * that element's index.
 *
 * <p>
 * 3. The HashMap preserves the relationship between
 * original node values and their calculated new values.
 *
 * <p>
 * 4. We must calculate all new values before modifying
 * the original BST because the calculations depend on
 * the original node values.
 *
 * <p>
 * 5. The convertBST2() method updates the original tree
 * rather than creating a new tree.
 *
 * <p>
 * Complexity Analysis:
 *
 * <p>
 * Let n represent the number of nodes in the BST.
 *
 * <p>
 * Inorder Traversal:
 *
 * <p>
 * Time Complexity: O(n)
 *
 * <p>
 * Every node is visited exactly once.
 *
 * <p>
 * Space Complexity: O(n)
 *
 * <p>
 * The List stores all n node values.
 *
 * <p>
 * HashMap Construction:
 *
 * <p>
 * Time Complexity: O(n^2)
 *
 * <p>
 * The outer loop executes n times.
 *
 * <p>
 * For each iteration, the inner loop processes
 * the remaining elements.
 *
 * <p>
 * Total operations:
 *
 * <p>
 * n + (n-1) + (n-2) + ... + 1
 *
 * <p>
 * = n(n+1)/2
 *
 * <p>
 * Therefore, time complexity is O(n^2).
 *
 * <p>
 * Space Complexity: O(n)
 *
 * <p>
 * The HashMap stores one entry for each distinct
 * original node value.
 *
 * <p>
 * Tree Update:
 *
 * <p>
 * Time Complexity: O(n)
 *
 * <p>
 * Every node is visited exactly once.
 * HashMap lookups take O(1) average time.
 *
 * <p>
 * Space Complexity: O(h)
 *
 * <p>
 * The recursive call stack depends on the height
 * of the original BST.
 *
 * <p>
 * Overall Complexity:
 *
 * <p>
 * Time Complexity: O(n^2)
 *
 * <p>
 * Space Complexity: O(n)
 *
 * <p>
 * The List and HashMap require O(n) space.
 * The recursive stack requires O(h) space,
 * where h is the height of the BST.
 *
 * <p>
 * Since h is at most n, the overall auxiliary
 * space complexity is O(n).
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

        /*
         * For every element, calculate the sum of all
         * elements from its index to the end of the list.
         *
         * This is the original value plus all greater values.
         */
        for (int i = 0; i < list.size(); i++) {

            for (int j = i; j < list.size(); j++) {
                sum += list.get(j);
            }

            /*
             * Map the original value to its calculated
             * Greater Tree value.
             */
            map.put(list.get(i), sum);

            /*
             * Reset sum before processing the next element.
             */
            sum = 0;
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
