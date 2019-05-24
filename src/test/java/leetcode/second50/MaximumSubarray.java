package leetcode.second50;

import org.junit.Test;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int max = nums[0];
        for(int num : nums) {
            if(num > max) {
                max = num;
            }
        }

        if (max <= 0) {
            return max;
        }

        int tmp = 0;
        boolean startAdd = false;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > 0 && !startAdd) {
                startAdd = true;
                tmp = 0;
            }

            if(startAdd) {
                tmp = tmp + nums[i];
                if(tmp <= 0) {
                    startAdd = false;
                } else if (tmp > max) {
                    max = tmp;
                }
            }

        }
        return max;
    }

    //  0, -2,  1,-2,  4, 3,
    // [-2, 1, -3, 4, -1, 2, 1,-5,4],
    public int maxSubArray2(int[] nums) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = Math.max(sum, max);
        }
        return max;
    }
    @Test
    public void Sandbox() {
        System.out.println(maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
