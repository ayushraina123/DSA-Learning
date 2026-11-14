package com.dsa.phase4.binarySearchTree;

/**
 * Problem: Lowest Common Ancestor of a Binary Tree
 * <p>
 * Given the root of a binary tree and two nodes p and q, find their
 * Lowest Common Ancestor (LCA).
 * <p>
 * The Lowest Common Ancestor of two nodes p and q is the lowest node
 * in the tree that has both p and q as descendants.
 * <p>
 * A node can be considered a descendant of itself.
 * <p>
 * Example:
 * <p>
 * 3
 * / \
 * 5   1
 * / \ / \
 * 6  2 0  8
 * / \
 * 7   4
 * <p>
 * Input:
 * p = 5
 * q = 4
 * <p>
 * Output:
 * 5
 * <p>
 * Explanation:
 * Node 5 is an ancestor of node 4.
 * Since a node can be its own descendant, node 5 is the
 * Lowest Common Ancestor of nodes 5 and 4.
 * <p>
 * <p>
 * APPROACH: RECURSION (BOTTOM-UP)
 * <p>
 * 1. Base Conditions:
 * <p>
 * - If the current node is null, return null because there
 * is nothing to search in that subtree.
 * <p>
 * - If the current node is equal to p or q, return the
 * current node.
 * <p>
 * We return immediately because we have found one of
 * the target nodes.
 * <p>
 * 2. Recursive Calls:
 * <p>
 * Explore both the left and right subtrees recursively.
 * <p>
 * TreeNode left = lowestCommonAncestor(root.left, p, q);
 * TreeNode right = lowestCommonAncestor(root.right, p, q);
 * <p>
 * Each recursive call returns:
 * <p>
 * - null if neither p nor q is found in that subtree.
 * - p or q if one of the target nodes is found.
 * - The LCA if both target nodes were found deeper in
 * that subtree.
 * <p>
 * 3. Combine the Results:
 * <p>
 * After both recursive calls complete, the current node
 * receives the results from its left and right subtrees.
 * <p>
 * CASE 1: Both left and right are non-null.
 * <p>
 * This means one target node was found in the left
 * subtree and the other target node was found in the
 * right subtree.
 * <p>
 * Therefore, the current node is their LCA.
 * <p>
 * return root;
 * <p>
 * CASE 2: Only left is non-null.
 * <p>
 * This means the left subtree has already found either
 * a target node or the LCA.
 * <p>
 * We propagate that result to the parent.
 * <p>
 * return left;
 * <p>
 * CASE 3: Only right is non-null.
 * <p>
 * This means the right subtree has already found either
 * a target node or the LCA.
 * <p>
 * We propagate that result to the parent.
 * <p>
 * return right;
 * <p>
 * CASE 4: Both left and right are null.
 * <p>
 * Neither subtree contains either target node.
 * <p>
 * Therefore, return null.
 * <p>
 * <p>
 * WHY BOTTOM-UP RECURSION?
 * <p>
 * In bottom-up recursion, information flows from children
 * towards their parent.
 * <p>
 * Every recursive call explores its subtree and returns
 * useful information to its parent.
 * <p>
 * The parent combines the results received from its left
 * and right children to determine what it should return.
 * <p>
 * We do not need to maintain separate lists or boolean flags
 * to track whether p and q have been found.
 * <p>
 * <p>
 * IMPORTANT OBSERVATIONS:
 * <p>
 * 1. We return the current node immediately when it matches
 * either p or q.
 * <p>
 * 2. We must explore both subtrees because p and q can exist
 * on opposite sides of the current node.
 * <p>
 * 3. If both recursive calls return non-null results, the
 * current node is the LCA.
 * <p>
 * 4. If only one recursive call returns a non-null result,
 * we propagate that result upwards.
 * <p>
 * 5. Once an LCA is found inside a subtree, its ancestors
 * keep propagating that same LCA instead of replacing it.
 * <p>
 * 6. We compare nodes using reference equality (root == p)
 * rather than comparing their values. This identifies the
 * actual target node.
 * <p>
 * <p>
 * DRY RUN:
 * <p>
 * Input:
 * Tree = [3,5,1,6,2,0,8,null,null,7,4]
 * p = 5
 * q = 4
 * <p>
 * Step 1:
 * Call lowestCommonAncestor(3, 5, 4).
 * <p>
 * Node 3 is neither p nor q.
 * Therefore, explore its left subtree.
 * <p>
 * Step 2:
 * Call lowestCommonAncestor(5, 5, 4).
 * <p>
 * Current node is equal to p.
 * Return node 5 immediately.
 * <p>
 * Node 3 receives:
 * left = node 5
 * <p>
 * Step 3:
 * Node 3 now explores its right subtree.
 * <p>
 * Call lowestCommonAncestor(1, 5, 4).
 * <p>
 * Node 1 is neither p nor q.
 * <p>
 * Step 4:
 * Explore node 1's left subtree.
 * <p>
 * Call lowestCommonAncestor(0, 5, 4).
 * <p>
 * Node 0 is neither p nor q and has no children.
 * <p>
 * Its left and right recursive calls both return null.
 * Therefore, node 0 returns null.
 * <p>
 * Step 5:
 * Explore node 1's right subtree.
 * <p>
 * Call lowestCommonAncestor(8, 5, 4).
 * <p>
 * Node 8 is neither p nor q and has no children.
 * <p>
 * Its left and right recursive calls both return null.
 * Therefore, node 8 returns null.
 * <p>
 * Step 6:
 * Node 1 receives:
 * <p>
 * left = null
 * right = null
 * <p>
 * Neither subtree found a target node.
 * Therefore, node 1 returns null.
 * <p>
 * Step 7:
 * Finally, execution returns to node 3.
 * <p>
 * Node 3 has:
 * <p>
 * left = node 5
 * right = null
 * <p>
 * Since left is non-null, node 3 propagates the left result.
 * <p>
 * return left;
 * <p>
 * Final Answer: Node 5
 * <p>
 * <p>
 * TIME COMPLEXITY:
 * <p>
 * O(N), where N is the number of nodes in the binary tree.
 * <p>
 * Every node is visited at most once.
 * <p>
 * SPACE COMPLEXITY:
 * <p>
 * O(H), where H is the height of the binary tree.
 * <p>
 * This space is used by the recursive call stack.
 * <p>
 * - Balanced binary tree: O(log N)
 * - Skewed binary tree: O(N)
 * <p>
 * ASSUMPTION:
 * <p>
 * Both p and q are guaranteed to exist in the binary tree.
 */
public class LowestCommonAncestorOfBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Base condition: No node to search.
        if (root == null) return null;

        // If the current node matches either target, return it.
        if (root == p || root == q) return root;

        // Recursively search the left subtree.
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // Recursively search the right subtree.
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // Both subtrees found a target or an LCA.
        // Therefore, the current node is the LCA.
        if (left != null && right != null) return root;

        // Only the left subtree returned a result.
        // Propagate that result to the parent.
        if (left != null) return left;

        // Only the right subtree returned a result.
        // Propagate that result to the parent.
        if (right != null) return right;

        // Neither subtree found either target.
        return null;
    }
}
