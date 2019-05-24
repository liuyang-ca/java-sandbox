package leetcode.recursion;

import org.junit.Test;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *    f(1, 1) = 1
 *    g(1) = 1
 *    f(1, 2) = 1
 *    f(2, 2) = 1
 *    g(2) = g(1) * 2 = 2
 *    f(1, 3) = g(2)
 *    f(2, 3) = 1
 *    f(3, 3) = g(2)
 *    g(3) = g(2) * 2 + g(1) = 5
 *    f(1, 4) = g(3)
 *    f(2, 4) = g(2)
 *    f(3, 4) = g(2)
 *    f(4, 4) = g(3)
 *    g(4) = g(3)*2 + g(2)*2 = 14
 *    g(5) = g(4)*2 + g(3)*2 + g(2)*2 = 28 + 10 + g(2)*2 = 42
 *    g(6) = g(5)*2 + g(4)*2 + g(2)*g(3)*2  = 132
 *    g(7) = g(6)*2 + g(5)*2 + g(4)*2 + g(3)*2
 */
public class UniqueBinarySearchTrees {

    public int numTrees(int n) {
        int[] array = new int[n+1];
        array[0] = 1;
        array[1] = 1;
        return numTreesHelper(array, n);
    }

    private int numTreesHelper(int[] array, int n) {
        if(array[n] != 0) {
            return array[n];
        }

        int result = 0;
        for(int i=n-1; i>=0; i--) {
            result = result + numTreesHelper(array, i)*numTreesHelper(array, n-1-i);
        }
        array[n] = result;
        return result;
    }

    @Test
    public void Sandbox() {
        for(int i=1; i<10; i++) {
            System.out.println(numTrees(i));
        }
    }
}
