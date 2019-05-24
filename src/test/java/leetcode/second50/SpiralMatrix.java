package leetcode.second50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * <p>
 * Input:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int height = matrix.length;
        if (height == 0) {
            return result;
        }
        int width = matrix[0].length;
        int maxSize = height * width;

        int d = 0;
        while (d == 0 || ((d + 1) < width && (d + 1) < height)) {
            int x = d, y = d;
            int maxW = width - d;
            int maxH = height - d;
            boolean changed = false;
            while (x < maxW) {
                result.add(matrix[y][x]);
                if(result.size() == maxSize) {
                    return result;
                }
                x++;
                changed = true;
            }
            if (changed) {
                x--;
            }
            y++;

            changed = false;
            while (y < maxH) {
                result.add(matrix[y][x]);
                if(result.size() == maxSize) {
                    return result;
                }
                y++;
                changed = true;
            }
            if (changed) {
                y--;
            }
            x--;

            changed = false;
            while (x >= d) {
                result.add(matrix[y][x]);
                if(result.size() == maxSize) {
                    return result;
                }
                x--;
                changed = true;
            }
            if (changed) {
                x++;
            }
            y--;

            while (y > d) {
                result.add(matrix[y][x]);
                if(result.size() == maxSize) {
                    return result;
                }
                y--;
            }

            d++;
        }

        return result;
    }


    @Test
    public void Sandbox() {
        int[][] array = new int[][]{
                new int[]{1,  2,  3,  4,  5,  6,  7},
                new int[]{8,  9,  10, 11, 12, 13, 14},
                new int[]{15, 16, 17, 18, 19, 20, 21},
                new int[]{22, 23, 24, 25, 26, 27, 28},
               // new int[]{15, 16, 17, 18, 19, 20, 21},

        };
        System.out.println(spiralOrder(array));
    }
}
