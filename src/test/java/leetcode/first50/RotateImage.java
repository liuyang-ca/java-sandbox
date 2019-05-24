package leetcode.first50;

import org.junit.Test;

import java.util.Arrays;

/**
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * Example 2:
 *
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int lenMinus1 = len - 1;
        int lenHalf = len/2;
        for(int j=0; j<lenHalf; j++) {
            for (int i = j; i < lenMinus1; i++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[lenMinus1 - i + j][j];
                matrix[lenMinus1 - i + j][j] = matrix[lenMinus1][lenMinus1 - i + j];
                matrix[lenMinus1][lenMinus1 - i + j] = matrix[i][lenMinus1];
                matrix[i][lenMinus1] = tmp;

            }
            lenMinus1--;
        }
    }

    @Test
    public void Sandbox() {
        int[][] matrix1 = new int[][]{
                new int[]{1},
        };
        int[][] matrix2 = new int[][]{
                new int[]{1, 2},
                new int[]{3, 4},
        };
        int[][] matrix3 = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9},
        };
        int[][] matrix4 = new int[][]{
                new int[]{1, 2, 3, 4},
                new int[]{5, 6, 7, 8},
                new int[]{9, 10, 11, 12},
                new int[]{13, 14, 15, 16},
        };
        int[][] matrix5 = new int[][]{
                new int[]{1, 2, 3, 4, 5},
                new int[]{6, 7, 8, 9, 10},
                new int[]{11, 12, 13, 14, 15},
                new int[]{16, 17, 18, 19, 20},
                new int[]{21, 22, 23, 24, 25},
        };

        int[][] matrix = matrix2;
        rotate(matrix);
        for(int[] array : matrix) {
            System.out.println(Arrays.toString(array));
        }
    }
}
