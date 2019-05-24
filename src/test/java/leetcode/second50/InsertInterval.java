package leetcode.second50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class InsertInterval {
    @Test
    public void Sandbox() {
        int[][] intervals = new int[][]{
                new int[]{1, 2},
//                new int[]{3, 5},
//                new int[]{6, 7},
//                new int[]{8, 10},
//                new int[]{12, 16},
        };

        int[] newInterval = new int[]{4,8};

        int[][] result = insert(intervals, newInterval);

        for(int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }
    public int[][] insert(int[][] intervals, int[] newInterval) {
        sortInterval(newInterval);
        for(int[] interval : intervals) {
            sortInterval(interval);
        }

        List<int[]> result = new ArrayList<>();

        for(int i=0; i<intervals.length; i++) {
            if(mergeTwoSorted(intervals[i], newInterval)) {
                // keep merge to the left
                int low = i-1;
                while(low >=0 && mergeTwoSorted(intervals[low], intervals[i])) {
                    low--;
                }

                for(int j=0; j<=low; j++) {
                    result.add(intervals[j]);
                }

                // keep merge to the right
                int high = i+1;
                while(high <intervals.length && mergeTwoSorted(intervals[i], intervals[high])) {
                    high++;
                }


                for(int j=high-1; j<intervals.length; j++) {
                    result.add(intervals[j]);
                }

                break;
            }
        }

        // no merge
        if(result.size() == 0) {
            if(intervals.length == 0) {
                result.add(newInterval);
            } else {
                result.addAll(Arrays.asList(intervals));


                if(newInterval[0] < result.get(0)[0]) {
                    result.add(0, newInterval);
                } else if(newInterval[0] > result.get(result.size()-1)[0]) {
                    result.add(newInterval);
                } else {
                    int insertIndex = -1;
                }
            }


        }

        int[][] resultArray = new int[result.size()][2];
        return  result.toArray(resultArray);
    }

    private boolean mergeTwoSorted(int[] intervalA, int[] intervalB) {
        int aHigh = intervalA[1];

        int bLow = intervalB[0];
        int bHigh = intervalB[1];

        if(bLow > aHigh) {
            return false;
        }

        // has overlap
        intervalA[1] = Math.max(aHigh, bHigh);
        intervalB[0] = intervalA[0];
        intervalB[1] = intervalA[1];
        return true;
    }

    private void sortInterval(int[] interval) {
        if(interval[1] < interval[0]) {
            int tmp = interval[1];
            interval[1] = interval[0];
            interval[0] = tmp;
        }
    }
}
