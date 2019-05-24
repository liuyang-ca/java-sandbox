package leetcode.first50;

import leetcode.MyLogger;
import org.junit.Test;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if(nums.length == 0) {
            return -1;
        } else if(nums.length <= 2) {
            if (nums[0] == target) {
                return 0;
            }

            if (nums.length == 2 && nums[1] == target) {
                return 1;
            }
        }

        int low=0, high=nums.length-1;
        while(low < high-1) {
            int index = (low + high) / 2;
            MyLogger.LOGGER.debug("nums[low] = {}, nums[high] = {}, nums[index] = {}", nums[low], nums[high], nums[index]);
            if (nums[index] == target) {
                return index;
            } else if (index+1<nums.length && nums[index+1] == target) {
                return index+1;
            } else if (index-1>=0 && nums[index-1] == target) {
                return index-1;
            } else if (target < nums[index]){
                if((nums[index] > nums[low] && target < nums[low])) {
                    low = index;
                } else {
                    high = index;
                }
            } else {
                if((nums[index] < nums[high] && target > nums[high])) {
                    high = index;
                } else {
                    low = index;
                }
            }
        }

        return -1;
    }

    @Test
    public void Sandbox() {
        System.out.println(search(new int[]{2,3,4,5,6,7,8,9,1}, 9));
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 1));
    }
}
