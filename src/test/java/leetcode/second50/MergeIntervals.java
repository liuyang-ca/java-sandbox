package leetcode.second50;

import org.junit.Test;

import java.util.*;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MergeIntervals {
    private boolean mergeTwoSorted(int[] intervalA, int[] intervalB) {
        int aHigh = intervalA[1];

        int bLow = intervalB[0];
        int bHigh = intervalB[1];

        if(bLow > aHigh) {
            return false;
        }

        // has overlap
        intervalA[1] = Math.max(aHigh, bHigh);
        return true;
    }

    private void sortInterval(int[] interval) {
        if(interval[1] < interval[0]) {
            int tmp = interval[1];
            interval[1] = interval[0];
            interval[0] = tmp;
        }
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) {
            return intervals;
        }

        for(int[] interval : intervals) {
            sortInterval(interval);
        }

        // sort 2d array
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] left, int[] right) {
                if(left[0] == right[0]) {
                    return left[1] - right[1];
                }

                return left[0] - right[0];
            }
        });

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for(int i=1; i<intervals.length; i++) {
            int lastIndex = result.size() - 1;
            if (!mergeTwoSorted(result.get(lastIndex), intervals[i])) {
                result.add(intervals[i]);
            }
        }

        int[][] resultArray = new int[result.size()][2];
        return  result.toArray(resultArray);
    }

    @Test
    public void Sandbox() {
        int[][] intervals = new int[][]{
                new int[]{4, 1},
//                new int[]{4, 5},
//                new int[]{18, 15},
//                new int[]{8, 10},
        };

        int[][] result = merge(intervals);

        for(int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
