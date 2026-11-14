package com.dsa.phase4.binaryTree;

/**
 * Problem: Diameter of Binary Tree
 * <p>
 * LeetCode: https://leetcode.com/problems/diameter-of-binary-tree/
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
 * Given the root of a binary tree, return the length of the
 * diameter of the tree.
 * <p>
 * The diameter of a binary tree is the length of the longest
 * path between any two nodes in the tree.
 * <p>
 * The path may or may not pass through the root.
 * <p>
 * The length of a path is measured by the number of edges
 * between the nodes, not the number of nodes.
 * <p>
 * Approach: Recursive DFS (Postorder Traversal)
 * <p>
 * We use recursion to calculate the height of every subtree
 * and simultaneously calculate the maximum diameter.
 * <p>
 * Instead of calculating the diameter separately for every
 * possible node, we calculate it during the height traversal.
 * <p>
 * We maintain a global variable called diameter to store
 * the maximum diameter found so far.
 * <p>
 * The recursive method returns the height of the current subtree.
 * <p>
 * Important Observations:
 * <p>
 * 1. The diameter of a binary tree does not necessarily pass
 * through the root.
 * <p>
 * Why?
 * <p>
 * The longest path might exist entirely inside the left
 * or right subtree.
 * <p>
 * Therefore, we must consider every node as a potential
 * turning point of the diameter.
 * <p>
 * 2. Every node can be considered as a potential turning point.
 * <p>
 * A path passing through the current node can extend:
 * <p>
 * - From the deepest node in its left subtree.
 * - Through the current node.
 * - To the deepest node in its right subtree.
 * <p>
 * Therefore:
 * <p>
 * Diameter passing through current node =
 * leftSubtreeHeight + rightSubtreeHeight
 * <p>
 * We add the two heights because each height represents
 * the number of edges from the current node's child
 * to the deepest node in that subtree.
 * <p>
 * 3. We use a global diameter variable.
 * <p>
 * Why?
 * <p>
 * The recursive method returns the height of the subtree,
 * not its diameter.
 * <p>
 * However, we also need to retain the maximum diameter
 * calculated at every node.
 * <p>
 * Therefore, we maintain diameter separately.
 * <p>
 * 4. We calculate the diameter before returning the height.
 * <p>
 * Why?
 * <p>
 * Once we have calculated the left and right subtree heights,
 * we have enough information to calculate the diameter
 * passing through the current node.
 * <p>
 * We update the global diameter using:
 * <p>
 * diameter = max(diameter, leftSubtree + rightSubtree)
 * <p>
 * After updating the diameter, we return the height
 * of the current subtree to its parent.
 * <p>
 * 5. The recursive method returns height, not diameter.
 * <p>
 * Why?
 * <p>
 * The parent node needs the height of its left and right
 * subtrees to calculate its own diameter.
 * <p>
 * Returning the diameter instead would not provide the
 * information needed by the parent.
 * <p>
 * 6. We use postorder traversal (Left -> Right -> Root).
 * <p>
 * Why?
 * <p>
 * To calculate the diameter passing through the current
 * node, we first need the heights of both child subtrees.
 * <p>
 * Therefore, we process the children before processing
 * the current node.
 * <p>
 * Algorithm:
 * <p>
 * 1. In diameterOfBinaryTree():
 * <p>
 * - Call maximumPathSum(root).
 * - The recursive method calculates the height of each
 * subtree and updates the global diameter.
 * - Return the final diameter.
 * <p>
 * 2. In maximumPathSum():
 * <p>
 * a. Base Case:
 * <p>
 * - If root is null, return 0.
 * - An empty subtree has height 0.
 * <p>
 * b. Calculate the left subtree height.
 * <p>
 * - Recursively call maximumPathSum(root.left).
 * <p>
 * c. Calculate the right subtree height.
 * <p>
 * - Recursively call maximumPathSum(root.right).
 * <p>
 * d. Calculate the diameter passing through the current node.
 * <p>
 * - Add the left and right subtree heights.
 * - Compare the result with the global diameter.
 * - Update diameter if a larger value is found.
 * <p>
 * e. Return the height of the current subtree.
 * <p>
 * - Find the maximum of the left and right subtree heights.
 * - Add 1 to include the current node.
 * <p>
 * return max(leftSubtree, rightSubtree) + 1
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
 * Initially:
 * <p>
 * diameter = 0
 * <p>
 * Execution:
 * <p>
 * 1. Node 6:
 * <p>
 * Both children are null.
 * <p>
 * leftSubtree = 0
 * rightSubtree = 0
 * <p>
 * Diameter passing through node 6:
 * <p>
 * 0 + 0 = 0
 * <p>
 * diameter = max(0, 0) = 0
 * <p>
 * Return max(0, 0) + 1 = 1
 * <p>
 * 2. Node 4:
 * <p>
 * leftSubtree = 1
 * rightSubtree = 0
 * <p>
 * Diameter passing through node 4:
 * <p>
 * 1 + 0 = 1
 * <p>
 * diameter = max(0, 1) = 1
 * <p>
 * Return max(1, 0) + 1 = 2
 * <p>
 * 3. Node 5:
 * <p>
 * Both children are null.
 * <p>
 * leftSubtree = 0
 * rightSubtree = 0
 * <p>
 * Diameter passing through node 5:
 * <p>
 * 0 + 0 = 0
 * <p>
 * diameter = max(1, 0) = 1
 * <p>
 * Return 1
 * <p>
 * 4. Node 2:
 * <p>
 * leftSubtree = 2
 * rightSubtree = 1
 * <p>
 * Diameter passing through node 2:
 * <p>
 * 2 + 1 = 3
 * <p>
 * diameter = max(1, 3) = 3
 * <p>
 * Return max(2, 1) + 1 = 3
 * <p>
 * 5. Node 3:
 * <p>
 * Both children are null.
 * <p>
 * leftSubtree = 0
 * rightSubtree = 0
 * <p>
 * Diameter passing through node 3:
 * <p>
 * 0 + 0 = 0
 * <p>
 * diameter = max(3, 0) = 3
 * <p>
 * Return 1
 * <p>
 * 6. Back at Node 1:
 * <p>
 * leftSubtree = 3
 * rightSubtree = 1
 * <p>
 * Diameter passing through node 1:
 * <p>
 * 3 + 1 = 4
 * <p>
 * diameter = max(3, 4) = 4
 * <p>
 * Return max(3, 1) + 1 = 4
 * <p>
 * 7. Back in diameterOfBinaryTree():
 * <p>
 * The recursive traversal is complete.
 * <p>
 * The global diameter contains the maximum value found
 * during the traversal.
 * <p>
 * Return diameter = 4.
 * <p>
 * Final Output:
 * <p>
 * 4
 * <p>
 * Why Do We Calculate Diameter Before Returning Height?
 * <p>
 * This is an important concept in this problem.
 * <p>
 * Consider the following code:
 * <p>
 * int leftSubtree = maximumPathSum(root.left);
 * int rightSubtree = maximumPathSum(root.right);
 * <p>
 * diameter = Math.max(diameter, leftSubtree + rightSubtree);
 * <p>
 * return Math.max(leftSubtree, rightSubtree) + 1;
 * <p>
 * There are two separate responsibilities here:
 * <p>
 * 1. Diameter Calculation:
 * <p>
 * We use both subtree heights to calculate the longest
 * path passing through the current node.
 * <p>
 * leftSubtree + rightSubtree
 * <p>
 * This value updates the global diameter.
 * <p>
 * 2. Height Calculation:
 * <p>
 * We return only the height of the current subtree.
 * <p>
 * max(leftSubtree, rightSubtree) + 1
 * <p>
 * Why only the maximum?
 * <p>
 * The parent node can extend its path through only one
 * child subtree at a time.
 * <p>
 * Therefore, we return the taller subtree's height,
 * along with the current node.
 * <p>
 * Important:
 * <p>
 * The diameter is calculated using both subtrees,
 * but the height is returned using only the taller subtree.
 * <p>
 * The recursive call stack ensures that every node gets
 * an opportunity to calculate its own diameter before
 * returning its height to its parent.
 * <p>
 * Edge Cases:
 * <p>
 * 1. Empty Tree:
 * <p>
 * If root is null, maximumPathSum() returns 0.
 * <p>
 * The diameter remains 0.
 * <p>
 * 2. Single Node:
 * <p>
 * Both children are null.
 * <p>
 * Diameter = 0.
 * <p>
 * A single node has no edges.
 * <p>
 * 3. Two Nodes:
 * <p>
 * The diameter is 1 because there is only one edge
 * connecting the two nodes.
 * <p>
 * 4. Left-Skewed Tree:
 * <p>
 * The diameter is the number of edges in the longest
 * chain of nodes.
 * <p>
 * 5. Right-Skewed Tree:
 * <p>
 * The diameter is also the number of edges in the
 * longest chain of nodes.
 * <p>
 * 6. Diameter Does Not Pass Through Root:
 * <p>
 * The maximum diameter may be entirely inside a subtree.
 * <p>
 * Since we calculate the diameter at every node and
 * maintain the global maximum, this case is handled.
 * <p>
 * Complexity Analysis:
 * <p>
 * Let N be the total number of nodes and H be the height
 * of the binary tree.
 * <p>
 * Time Complexity: O(N)
 * <p>
 * - Every node is visited exactly once.
 * - Each node performs constant-time operations:
 * addition, comparison, and maximum calculation.
 * - Therefore, the time complexity is O(N).
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
 * Key Takeaway:
 * <p>
 * This problem demonstrates an important binary tree
 * recursion pattern:
 * <p>
 * Calculate some information using both child subtrees,
 * update a global result, and return only the information
 * required by the parent.
 * <p>
 * Here:
 * <p>
 * - Global result: Maximum diameter.
 * - Recursive return value: Height of the subtree.
 * <p>
 * This allows us to calculate the diameter in a single
 * postorder traversal without repeatedly calculating
 * subtree heights.
 */
public class DiameterOfBinaryTree {

    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maximumPathSum(root);
        return diameter;
    }

    public int maximumPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSubtree = maximumPathSum(root.left);
        int rightSubtree = maximumPathSum(root.right);

        diameter = Math.max(diameter, leftSubtree + rightSubtree);

        return Math.max(leftSubtree, rightSubtree) + 1;
    }
}