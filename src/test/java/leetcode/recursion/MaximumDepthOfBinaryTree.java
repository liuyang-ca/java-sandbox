package leetcode.recursion;

/**
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given binary tree [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its depth = 3.
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return traverse(root, 0);
    }

    private int traverse(TreeNode node, int depth) {
        if(node == null) {
            return depth - 1;
        } else if (node.left == null && node.right == null) {
            return depth;
        }

        int rightDepth = traverse(node.right, depth + 1);
        int leftDepth = traverse(node.left, depth + 1);

        return Math.max(rightDepth, leftDepth);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
