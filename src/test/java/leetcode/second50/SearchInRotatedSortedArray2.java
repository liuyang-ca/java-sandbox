package leetcode.second50;

import org.junit.Test;

/**
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

 You are given a target value to search. If found in the array return true, otherwise return false.

 Example 1:

 Input: nums = [2,5,6,0,0,1,2], target = 0
 Output: true
 Example 2:

 Input: nums = [2,5,6,0,0,1,2], target = 3
 Output: false
 Follow up:

 This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 Would this affect the run-time complexity? How and why?
 */
public class SearchInRotatedSortedArray2 {
    public boolean search(int[] nums, int target) {
        if(nums.length == 0) {
            return false;
        } else if(nums.length == 1) {
            return nums[0] == target;
        }

        int low = 0;
        int high = nums.length - 1;

        while(low < high-1) {
            int mid = low + (high - low)/2;
            if(nums[mid] == target) {
                return true;
            } else if(nums[mid] < target) {
                if(nums[high] == nums[mid]) {
                    high--;
                } else {
                    if (nums[high] > nums[mid] && target > nums[high]) {
                        high = mid;
                    } else {
                        low = mid;
                    }
                }
            } else { // nums[mid] > target
                if(nums[low] == nums[mid]) {
                    low++;
                } else {
                    if (nums[low] < nums[mid] && target < nums[low]) {
                        low = mid;
                    } else {
                        high = mid;
                    }
                }
            }
        }

        return nums[low] == target || nums[high] == target;
    }


    @Test
    public void Sandbox() {
//        System.out.println(search(new int[]{2, 2, 2, 2, 3, 3, 3}, 9));
        System.out.println(search(new int[]{3,1,1}, 3));
        System.out.println(search(new int[]{1,1,3}, 3));
        System.out.println(search(new int[]{1,1,3,1}, 3));
    }
}
