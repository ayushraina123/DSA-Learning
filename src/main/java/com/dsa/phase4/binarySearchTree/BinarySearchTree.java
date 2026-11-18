package com.dsa.phase4.binarySearchTree;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BinarySearchTree {

    TreeNode root;

    public void insert(int val) {
        TreeNode newNode = new TreeNode(val);

        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode temp = root;

        while (true) {
            if (val < temp.val) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return;
                }
                temp = temp.right;
            }
        }
    }

    public void recursiveInsert(int val) {
        if (root == null) {
            root = new TreeNode(val);
            return;
        }
        insert(root, val);
    }

    public TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        return root;
    }

    public List<Integer> breadthFirstSearch(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return list;
    }

    // Depth First Search

    public List<Integer> preOrderSearch(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
        return preOrder(root, list);
    }

    public List<Integer> preOrder(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (root.left != null) {
            preOrder(root.left, list);
        }
        if (root.right != null) {
            preOrder(root.right, list);
        }
        return list;
    }

    public List<Integer> inOrderSearch(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        return inOrder(root, list);
    }

    public List<Integer> inOrder(TreeNode root, List<Integer> list) {
        if (root.left != null) {
            inOrder(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            inOrder(root.right, list);
        }
        return list;
    }

    public List<Integer> postOrderSearch(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        return postOrder(root, list);
    }

    public List<Integer> postOrder(TreeNode root, List<Integer> list) {
        if (root.left != null) {
            postOrder(root.left, list);
        }
        if (root.right != null) {
            postOrder(root.right, list);
        }
        list.add(root.val);
        return list;
    }

    public List<Integer> preOrderIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return list;
    }

    public List<Integer> inOrderIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode node = stack.pop();
                list.add(node.val);
                current = node.right;
            }
        }

        return list;
    }

    public List<Integer> postOrderIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) return list;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();

            stack2.push(node);

            if (node.left != null) {
                stack1.push(node.left);
            }

            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }

        return list;
    }
}
