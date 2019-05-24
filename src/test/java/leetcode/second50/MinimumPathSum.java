package leetcode.second50;

import leetcode.MyLogger;
import org.junit.Test;

import java.util.*;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinimumPathSum {
    public int minPathSum2(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) {
            return 0;
        }


        int maxH = grid.length;
        int maxW = grid[0].length;

        for(int i=0; i<maxH; i++) {
            for(int j=0; j<maxW; j++) {
                int fromTop = i-1 >= 0 ? grid[i-1][j] + grid[i][j] : -1;
                int fromLeft = j-1 >= 0 ? grid[i][j-1] + grid[i][j] : -1;
                if(fromTop != -1 || fromLeft != -1) {
                    if(fromTop == -1) {
                        grid[i][j] = fromLeft;
                    } else if(fromLeft == -1) {
                        grid[i][j] = fromTop;
                    } else {
                        grid[i][j] = Math.min(fromLeft, fromTop);
                    }
                }
            }
        }

        return grid[maxH-1][maxW-1];
    }

    class Index {
        public int i,j;
        public Index(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public String toString() {
            return "[" + i + "," + j + "]";
        }

        @Override
        public boolean equals(Object obj) {
            Index other = (Index)obj;
            return other.i == i && other.j == j;
        }
    }

    public int minPathSum(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) {
            return 0;
        }


        int maxH = grid.length-1;
        int maxW = grid[0].length-1;

        Map<Index, Integer> map = new HashMap<>();
        Index first = new Index(0, 0);
        map.put(first, grid[0][0]);

        while(true) {
            MyLogger.LOGGER.debug("{}", map);
            int firstValue = map.get(first);
            map.remove(first);
            if(first.i < maxH) {
                Index down = new Index(first.i + 1, first.j);
                int downValue = firstValue + grid[first.i+1][first.j];
                if(down.i == maxH && down.j == maxW) {
                    return downValue;
                }
                if (map.get(down) == null || downValue < map.get(down)) {
                    map.put(down, downValue);
                }
            }
            if(first.j < maxW) {
                Index right = new Index(first.i, first.j + 1);
                int rightValue = firstValue + grid[first.i][first.j + 1];
                if(right.i == maxH && right.j == maxW) {
                    return rightValue;
                }
                if (map.get(right) == null || rightValue < map.get(right)) {
                    map.put(right, rightValue);
                }
            }

            if(map.size() == 0) {
                return firstValue;
            }

            first = (Index)map.keySet().toArray()[0];
            firstValue = map.get(first);
            for(Index tmp : map.keySet()) {
                if(firstValue > map.get(tmp)) {
                    first = tmp;
                }
            }
        }

    }
// *   [1,3,1],
// *   [1,5,1],
// *   [4,2,1]
    @Test
    public void Sandbox() {
        int[][] array = new int[][]{
                new int[]{7,0,8,8,0,3,5,8,5,4}

        };
        System.out.println(minPathSum2(array));
    }
}
