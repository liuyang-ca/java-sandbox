package leetcode.first50;

import org.junit.Test;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FindFirstAndLastPositionOfElementISortedArray {
    public int[] searchRange(int[] nums, int target) {
        int start = -1, end = -1;
        int index = Arrays.binarySearch(nums, target);
        if (index >= 0 ) {
            // find start index
            start = index;
            while(start > 0) {
                int tmp = Arrays.binarySearch(nums, 0, start, target);
                if (tmp < 0) {
                    break;
                } else {
                    start = tmp;
                }
            }

            // find end index
            end = index + 1;
            while(end < nums.length) {
                int tmp = Arrays.binarySearch(nums, end, nums.length, target);
                if (tmp < 0) {
                    break;
                } else {
                    end = tmp + 1;
                }
            }
            end = end - 1;

        }

        return new int[] {start, end};
    }

    @Test
    public void Sandbox() {
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10},6)));
    }
}
