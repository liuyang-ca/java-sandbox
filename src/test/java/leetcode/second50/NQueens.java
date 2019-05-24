package leetcode.second50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 *
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> results = new ArrayList<>();

        for(int k=0; k<n; k++) {
            int queenCounter = 0;
            List<String> tmpResult = new ArrayList<>();
            boolean[][] notAllowed = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                tmpResult.add("");
                for (int j = 0; j < n; j++) {
                    String row = tmpResult.get(i);
                    if (notAllowed[i][j] || (i==0 && j!= k)) {
                        tmpResult.set(i, row + ".");
                        continue;
                    }
                    queenCounter++;
                    tmpResult.set(i, row + "Q");
                    // update notAllowed array row and col
                    for(int x=0; x<n; x++) {
                        notAllowed[i][x] = true;
                        notAllowed[x][j] = true;
                    }
                    // update dia
                    int x=i, y=j;
                    while(x >=0 && y >=0) {
                        notAllowed[x--][y--] = true;
                    }

                    x=i;
                    y=j;
                    while(x >= 0 && y < n) {
                        notAllowed[x--][y++] = true;
                    }

                    x=i;
                    y=j;
                    while(x < n && y >= 0) {
                        notAllowed[x++][y--] = true;
                    }

                    x=i;
                    y=j;
                    while(x < n && y < n) {
                        notAllowed[x++][y++] = true;
                    }
                }
            }
            if (queenCounter == n) {
                results.add(tmpResult);
            }
        }
        return results;
    }


    @Test
    public void Sandbox() {
        System.out.println(solveNQueens(5));
    }
}
