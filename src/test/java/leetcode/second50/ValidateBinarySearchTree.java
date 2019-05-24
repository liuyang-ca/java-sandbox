package leetcode.second50;

import leetcode.recursion.UniqueBinarySearchTrees2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        travaseBST(root, list);
        int prev = list.get(0);
        for(int i=1; i<list.size(); i++) {
            if(prev >= list.get(i)) {
                return false;
            }
            prev = list.get(i);
        }

        return true;
    }

    private void travaseBST(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }
        travaseBST(root.left, list);
        list.add(root.val);
        travaseBST(root.right, list);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    @Test
    public void Sandbox() {
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(30);
        root.right.left = new TreeNode(10);
        root.right.left.right = new TreeNode(15);
        root.right.left.right.right = new TreeNode(45);

        List<Integer> list = new ArrayList<>();
        travaseBST(root, list);
        System.out.println(list);
        System.out.println(isValidBST(root));
    }
}
