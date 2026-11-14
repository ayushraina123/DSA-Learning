package com.dsa.phase4.binarySearchTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Problem: Kth Smallest Element in a BST
 * <p>
 * Given the root of a Binary Search Tree (BST) and an integer k,
 * return the kth smallest value in the BST (1-indexed).
 * <p>
 * Example:
 * <p>
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * <p>
 * Input:
 * k = 3
 * <p>
 * Output:
 * 3
 * <p>
 * Explanation:
 * Inorder traversal of a BST produces values in ascending order.
 * <p>
 * Inorder sequence:
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6
 * <p>
 * The 3rd smallest element is 3.
 * <p>
 * <p>
 * IMPORTANT OBSERVATION:
 * <p>
 * Inorder traversal of a Binary Search Tree always visits nodes
 * in ascending order.
 * <p>
 * This is because:
 * <p>
 * 1. Every node in the left subtree is smaller than the current node.
 * 2. The current node is visited after its left subtree.
 * 3. Every node in the right subtree is greater than the current node.
 * <p>
 * Therefore, we can solve this problem using inorder traversal.
 * <p>
 * <p>
 * APPROACH 1: RECURSIVE INORDER TRAVERSAL
 * <p>
 * INTUITION:
 * <p>
 * Since inorder traversal produces elements in ascending order,
 * we can traverse the entire BST and store the values in a list.
 * <p>
 * Once traversal is complete, the kth smallest element will be
 * available at index k - 1 because list indexing is zero-based.
 * <p>
 * <p>
 * ALGORITHM:
 * <p>
 * 1. Call the inorder() method with the root and an empty list.
 * <p>
 * 2. In the inorder() method:
 * <p>
 * - First recursively traverse the left subtree.
 * - Add the current node's value to the list.
 * - Recursively traverse the right subtree.
 * <p>
 * 3. Once traversal is complete, the list contains all BST
 * values in ascending order.
 * <p>
 * 4. Return the element at index k - 1.
 * <p>
 * <p>
 * DRY RUN:
 * <p>
 * Tree:
 * <p>
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * <p>
 * Input:
 * k = 3
 * <p>
 * Recursive inorder execution:
 * <p>
 * inorder(5)
 * inorder(3)
 * inorder(2)
 * inorder(1)
 * Add 1
 * Add 2
 * Add 3
 * inorder(4)
 * Add 4
 * Add 5
 * inorder(6)
 * Add 6
 * <p>
 * Final list:
 * <p>
 * [1, 2, 3, 4, 5, 6]
 * <p>
 * Return:
 * <p>
 * list.get(k - 1)
 * list.get(2)
 * <p>
 * Output: 3
 * <p>
 * <p>
 * TIME COMPLEXITY:
 * <p>
 * O(N)
 * <p>
 * We visit every node in the BST exactly once to construct
 * the inorder list.
 * <p>
 * SPACE COMPLEXITY:
 * <p>
 * O(N)
 * <p>
 * The list stores all N node values.
 * Additionally, recursive calls consume O(H) stack space.
 * <p>
 * <p>
 * APPROACH 2: ITERATIVE INORDER TRAVERSAL USING STACK
 * <p>
 * INTUITION:
 * <p>
 * In the recursive approach, the function call stack internally
 * remembers the nodes whose processing is pending.
 * <p>
 * We can implement the same behaviour explicitly using a Stack.
 * <p>
 * Instead of storing all node values in a list, we process each
 * node as soon as it is popped from the stack.
 * <p>
 * Since inorder traversal visits nodes in ascending order,
 * we simply maintain a counter and stop when we reach the kth node.
 * <p>
 * <p>
 * ALGORITHM:
 * <p>
 * 1. Create an empty stack using Deque.
 * <p>
 * 2. Continue traversal while:
 * <p>
 * root != null OR stack is not empty.
 * <p>
 * 3. INNER WHILE LOOP:
 * <p>
 * Keep moving towards the leftmost node.
 * <p>
 * - Push the current node onto the stack.
 * - Move root to root.left.
 * <p>
 * We do this because inorder traversal processes the left
 * subtree before the current node.
 * <p>
 * 4. Once root becomes null:
 * <p>
 * - Pop the top node from the stack.
 * - Decrement k because we have processed one node.
 * <p>
 * 5. If k becomes zero, we have reached the kth smallest element.
 * <p>
 * Return the current node's value immediately.
 * <p>
 * 6. Otherwise, move to the current node's right subtree.
 * <p>
 * root = root.right;
 * <p>
 * The process continues until the kth element is found.
 * <p>
 * <p>
 * DRY RUN:
 * <p>
 * Tree:
 * <p>
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * <p>
 * Input:
 * k = 3
 * <p>
 * Initially:
 * <p>
 * stack = []
 * root = 5
 * k = 3
 * <p>
 * STEP 1: Keep moving left and push nodes.
 * <p>
 * Push 5.
 * Push 3.
 * Push 2.
 * Push 1.
 * <p>
 * root becomes null.
 * <p>
 * Stack (top to bottom):
 * <p>
 * 1
 * 2
 * 3
 * 5
 * <p>
 * STEP 2: Pop the top node.
 * <p>
 * Pop 1.
 * Decrement k.
 * <p>
 * k = 2
 * <p>
 * Node 1 has no right child.
 * Move back to the outer while loop.
 * <p>
 * STEP 3: Pop the next node.
 * <p>
 * Pop 2.
 * Decrement k.
 * <p>
 * k = 1
 * <p>
 * Node 2 has no right child.
 * Continue traversal.
 * <p>
 * STEP 4: Pop the next node.
 * <p>
 * Pop 3.
 * Decrement k.
 * <p>
 * k = 0
 * <p>
 * Since k is zero, return 3 immediately.
 * <p>
 * We do not need to process nodes 4, 5 or 6.
 * <p>
 * Output: 3
 * <p>
 * <p>
 * WHY DO WE NEED TWO WHILE LOOPS?
 * <p>
 * OUTER WHILE LOOP:
 * <p>
 * while (root != null || !stack.isEmpty())
 * <p>
 * This ensures that traversal continues as long as:
 * <p>
 * - There is a node to explore.
 * - OR there are pending nodes waiting in the stack.
 * <p>
 * INNER WHILE LOOP:
 * <p>
 * while (root != null)
 * <p>
 * This moves as far left as possible, pushing every node
 * encountered onto the stack.
 * <p>
 * Once root becomes null, we pop and process the next node.
 * <p>
 * <p>
 * IMPORTANT OBSERVATIONS:
 * <p>
 * 1. The stack replaces the implicit recursion call stack.
 * <p>
 * 2. We push nodes while moving left because inorder traversal
 * processes the left subtree first.
 * <p>
 * 3. Popping a node means its left subtree has already been
 * completely processed, so we can now process the node itself.
 * <p>
 * 4. After processing the current node, we explore its right
 * subtree.
 * <p>
 * 5. We decrement k every time we process a node because
 * inorder traversal visits nodes in ascending order.
 * <p>
 * 6. We return immediately when k becomes zero, avoiding
 * unnecessary traversal.
 * <p>
 * 7. Unlike the recursive approach, we do not need to store
 * all the values in a separate list.
 * <p>
 * <p>
 * TIME COMPLEXITY:
 * <p>
 * O(H + k)
 * <p>
 * H = Height of the BST.
 * <p>
 * We first travel down the tree to reach the leftmost node,
 * which takes at most O(H).
 * <p>
 * Then we process at most k nodes before finding the answer.
 * <p>
 * In the worst case, this becomes O(N), for example when k = N.
 * <p>
 * SPACE COMPLEXITY:
 * <p>
 * O(H)
 * <p>
 * The stack stores nodes along the current traversal path.
 * <p>
 * - Balanced BST: O(log N)
 * - Skewed BST: O(N)
 * <p>
 * <p>
 * COMPARISON:
 * <p>
 * Recursive Inorder:
 * - Visits the entire tree.
 * - Stores all values in a list.
 * - O(N) time and O(N) auxiliary space.
 * <p>
 * Iterative Inorder:
 * - Uses an explicit stack.
 * - Stops as soon as the kth element is found.
 * - O(H + k) time and O(H) auxiliary space.
 * <p>
 * <p>
 * INTERVIEW INSIGHT:
 * <p>
 * The iterative approach is preferable when we only need
 * the kth smallest element once.
 * <p>
 * If multiple kth-smallest queries are performed on a BST
 * that does not change, we can consider augmenting each node
 * with its subtree size.
 * <p>
 * This allows us to navigate directly towards the kth element
 * in O(H) time per query.
 */
public class KthSmallestElementInBST {

    /**
     * Approach 1: Recursive Inorder Traversal
     * <p>
     * Stores all BST values in ascending order and returns
     * the element at index k - 1.
     */
    public int kthSmallest1(TreeNode root, int k) {

        List<Integer> list = inOrder(root, new ArrayList<>());

        return list.get(k - 1);
    }

    /**
     * Performs recursive inorder traversal.
     * <p>
     * Order:
     * Left -> Root -> Right
     * <p>
     * Returns the list containing values in ascending order.
     */
    public List<Integer> inOrder(TreeNode root, List<Integer> res) {

        if (root == null) return res;

        if (root.left != null) {
            inOrder(root.left, res);
        }

        res.add(root.val);

        if (root.right != null) {
            inOrder(root.right, res);
        }

        return res;
    }

    /**
     * Approach 2: Iterative Inorder Traversal
     * <p>
     * Uses an explicit stack instead of recursion.
     * Stops traversal as soon as the kth smallest element is found.
     */
    public int kthSmallest2(TreeNode root, int k) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {

            // Keep moving left and push nodes onto the stack.
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // Process the next node in inorder sequence.
            root = stack.pop();

            // One node has been processed.
            k--;

            // Return immediately when the kth node is reached.
            if (k == 0) return root.val;

            // Explore the right subtree.
            root = root.right;
        }

        throw new IllegalArgumentException(
                "k exceeds the number of nodes"
        );
    }
}
