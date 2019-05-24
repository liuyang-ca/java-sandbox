package leetcode.first50;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.*;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 *
 *
 * A sudoku puzzle...
 *
 *
 * ...and its solution numbers marked in red.
 *
 * Note:
 *
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 */
public class SudokuSolver {
    private ValidSudoku vs = new ValidSudoku();


    public void solveSudoku(char[][] board) {
        Map<Integer, char[]> col = new HashMap<>();
        Map<Integer, char[]> row = new HashMap<>();
        Map<Integer, char[]> box = new HashMap<>();
        for(int i=0; i<9; i++) {
            row.put(i, board[i]);
            for(int j=0; j<9; j++) {
                char[] colVal = col.get(j);
                if(colVal == null) {
                    colVal = new char[9];
                }
                colVal[i] = board[i][j];
                col.put(j, colVal);

                int boxIndex = 3*(i/3) + j/3;
                char[] boxVal = box.get(boxIndex);
                if(boxVal == null) {
                    boxVal = new char[9];
                }
                boxVal[(i%3)*3 + j%3] = board[i][j];
                box.put(boxIndex, boxVal);
            }
        }

        solveSudokuInternal(row, col, box);
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                board[i][j] = row.get(i)[j];
            }
        }
    }

    private boolean solveSudokuInternal(Map<Integer, char[]> row, Map<Integer, char[]> col, Map<Integer, char[]> box) {
        for(int i : row.keySet()) {
            for(int j=0; j<9; j++) {
                char c = row.get(i)[j];
                if(c == '.') {
                    for(char k='1'; k<='9'; k++) {
                        if(setSudoku(row, col, box, i, j, k)) {
                            if (solveSudokuInternal(row, col, box)) {
                                return true;
                            }
                        }
                    }

                    int boxIndex = 3*(i/3) + j/3;
                    row.get(i)[j] = '.';
                    col.get(j)[i] = '.';
                    box.get(boxIndex)[(i%3)*3 + j%3] = '.';
                    return false;
                }
            }
        }

        return true;
    }

    private boolean setSudoku(Map<Integer, char[]> row, Map<Integer, char[]> col, Map<Integer, char[]> box, int i, int j, char value) {
        int boxIndex = 3*(i/3) + j/3;

        for(int k=0; k<9; k++) {
            //MyLogger.LOGGER.debug("i = {}, k = {}, row = {}, col = {}, box = {}", i, k, row.get(i)[k], col.get(j)[k], box.get(boxIndex)[k]);
            if (row.get(i)[k] == value || col.get(j)[k] == value || box.get(boxIndex)[k] == value) {
                return false;
            }
        }

        row.get(i)[j] = value;
        col.get(j)[i] = value;
        box.get(boxIndex)[(i%3)*3 + j%3] = value;

        return true;
    }

    @Test
    public void Sandbox() {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };


        solveSudoku(board);
        //setSudoku(row, col, box, 0, 2, '0');
        Gson gson = new Gson();
        System.out.println(gson.toJson(board));
    }
}
