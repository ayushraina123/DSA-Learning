package com.dsa.phase4.binaryTree;

public class BinaryTreeMaximumPathSum {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;

        getMaximumPathSum(root);

        return maxSum;
    }

    public int getMaximumPathSum(TreeNode root) {
        if (root == null) return 0;

        int leftSubtreeSum = Math.max(0, getMaximumPathSum(root.left));
        int rightSubtreeSum = Math.max(0, getMaximumPathSum(root.right));

        // Maximum path passing through the current node
        int currentPathSum = root.val + leftSubtreeSum + rightSubtreeSum;

        // Update the overall maximum
        maxSum = Math.max(maxSum, currentPathSum);

        // Return only one branch to the parent
        return root.val + Math.max(leftSubtreeSum, rightSubtreeSum);
    }
}
