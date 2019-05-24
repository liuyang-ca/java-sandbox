package leetcode.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalsTriangle2 {
    public List<Integer> getRow(int rowIndex) {
        int[][] array = new int[rowIndex+1][rowIndex+1];
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<=rowIndex; i++) {
            list.add(getRowNumber(array, rowIndex, i));
        }
        return list;
    }

    private int getRowNumber(int[][] array, int rowIndex, int colIndex) {

        if(array[rowIndex][colIndex] != 0) {

        } else if (array[rowIndex][rowIndex-colIndex] != 0) {
            array[rowIndex][colIndex] = array[rowIndex][rowIndex-colIndex];
        } else {
            if(colIndex == 0 || colIndex == rowIndex) {
                array[rowIndex][colIndex] = 1;
            } else if (colIndex == 1 || colIndex == rowIndex - 1) {
                array[rowIndex][colIndex] = rowIndex;
            } else {
                array[rowIndex][colIndex] = getRowNumber(array, rowIndex-1, colIndex-1) + getRowNumber(array, rowIndex-1, colIndex);
            }
        }

        return array[rowIndex][colIndex];
    }

    @Test
    public void Sandbox() {
        System.out.println(getRow(5));
    }
}
