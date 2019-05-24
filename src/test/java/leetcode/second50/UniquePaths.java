package leetcode.second50;

import org.junit.Test;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 *
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 *
 * Note: m and n will be at most 100.
 *
 * Example 1:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * Example 2:
 *
 * Input: m = 7, n = 3
 * Output: 28
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int k = Math.max(m, n);
        int[][] array = new int[k+1][k+1];
        return uniquePathsInternal(array, m, n);
    }

    public int uniquePathsInternal(int[][] array, int m, int n) {
        //MyLogger.LOGGER.debug("[{},{}]", m, n);
        if(array[m][n] != 0) {
            return array[m][n];
        } else if(array[n][m] != 0) {
            return array[n][m];
        }
        //MyLogger.LOGGER.debug("[{},{}]", m, n);
        if(m == 1 || n == 1) {
            array[m][n] = 1;
            return 1;
        }

        int result1 = uniquePathsInternal(array, m-1, n);
        array[m-1][n] = result1;

        int result2 = uniquePathsInternal(array, m, n-1);
        array[m][n-1] = result2;
        int result = result1 + result2;

        array[m][n] = result;
        return result;
    }

    @Test
    public void Sandbox() {
        System.out.println(uniquePaths(3,2));
    }
}
