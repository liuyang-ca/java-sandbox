package leetcode.recursion;

import org.junit.Test;

import java.util.*;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *    1         2
 *     \       /
 *      2     1
 */
public class UniqueBinarySearchTrees2 {
    private List<TreeNode> generateSubtrees(int s, int e) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        if (s > e) {
            res.add(null); // empty tree
            return res;
        }

        for (int i = s; i <= e; ++i) {
            List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);   // (1, 0)   (1, 1)  (1, 2)
            List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);  // (2, 3)   (3, 3)  (4, 3)

            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for(int i=1; i<=n; i++) {
            TreeNode root = new TreeNode(i);
            visited.add(i);
            helper(result, root, root, null, visited, n);
            visited.remove(i);
        }

        return result;
    }

    private void helper(List<TreeNode> result, TreeNode root, TreeNode cur, TreeNode prev, Set<Integer> visited, int n) {
        if(visited.size() == n) {
            TreeNode add = root;
            result.add(add);

            return;
        }

        for(int i=1; i<=n; i++) {
            if(!visited.contains(i)) {
                visited.add(i);
                TreeNode tmp = new TreeNode(i);
                if(i < cur.val) {
                    cur.left = tmp;
                    helper(result, root, cur.left, cur, visited, n);
                } else {
                    cur.right = tmp;
                    helper(result, root, cur.right, cur, visited, n);
                }
                visited.remove(i);
            }
        }
    }

    public List<TreeNode> generateTrees2(int n) {
        return generateSubtrees(1, n);
    }



    @Test
    public void Sandbox() {
        List<TreeNode> list = generateTrees2(3);
        for(TreeNode node : list) {
            printTreeNode(node);
            System.out.println();
        }
    }

    private void printTreeNode(TreeNode node) {
        if(node != null) {
            System.out.print(node.val + ",");
            if(node.left != null || node.right != null) {
                printTreeNode(node.left);
                printTreeNode(node.right);
            }
        } else {
            System.out.print("null,");
        }
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
