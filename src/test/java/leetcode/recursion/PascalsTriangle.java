package leetcode.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * [1,5,10,10,5,1]
 *[1,6,15,20,15,6,1]
 *[1, 7, 21, 35, 35, 21, 7, 1],
 * [1, 8, 28, 56, 70, 56, 28, 8, 1]
 * ]
 */
public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> previousRow = null;
            if (i>1) {
                previousRow = result.get(i-1);
            }
            for(int j=0; j<=i; j++) {
                if(j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(previousRow.get(j-1) + previousRow.get(j));
                }
            }
            result.add(row);
        }
        return result;
    }

    @Test
    public void Sandbox() {
        //System.out.println(generate(40));
    }
}
