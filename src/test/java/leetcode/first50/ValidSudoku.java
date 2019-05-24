package leetcode.first50;

import org.junit.Test;

import java.util.Arrays;

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * A partially filled sudoku which is valid.
 *
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 * Example 1:
 *
 * Input:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * Example 2:
 *
 * Input:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 *     modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[] array1 = new boolean[9];
        boolean[] array2 = new boolean[9];

        for(int i=0; i<9; i++) {
            Arrays.fill(array1, false);
            Arrays.fill(array2, false);
            for(int j=0; j<9; j++) {
                // check row
                if(board[i][j] != '.') {
                    int tmp = board[i][j] - '1';
                    if(!array1[tmp]) {
                        array1[tmp] = true;
                    } else {
                        return false;
                    }
                }
                // check column
                if(board[j][i] != '.') {
                    int tmp = board[j][i] - '1';
                    if(!array2[tmp]) {
                        array2[tmp] = true;
                    } else {
                        return false;
                    }
                }
            }
        }

        int[] intArray = {0, 3, 6};
        for(int i : intArray) {
            for(int j : intArray) {
                Arrays.fill(array1, false);
                for(int k=0; k<3; k++) {
                    for(int l=0; l<3; l++) {
                        int a = i+k;
                        int b = j+l;
                        System.out.println(a + "," + b);
                        if (board[a][b] != '.') {
                            int tmp = board[a][b] - '1';
                            if (!array1[tmp]) {
                                array1[tmp] = true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }


        return true;
    }

    @Test
    public void Sandbox() {
        System.out.println('1'-'0');
    }
}
