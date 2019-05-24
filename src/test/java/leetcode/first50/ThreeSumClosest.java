package leetcode.first50;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 Return the sum of the three integers. You may assume that each input would have exactly one solution.

 Example:

 Given array nums = [-1, 2, 1, -4], and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int closest = nums[0] + nums[1] + nums[2];

        // -4 -2 0 1 2
        for(int i=0; i<nums.length-2; i++) {

            int smallest = nums[i] + nums[i+1] + nums[i+2];
            if(target < smallest) {
                closest = findClosest(target, smallest, closest);
                continue;
            }

            int largest = nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3];
            if(target > largest) {
                closest = findClosest(target, largest, closest);
                continue;
            }

            int low = i+1, high = nums.length - 1;
            while(low < high) {
                System.out.println(nums[i] + " " + nums[low] + " " + nums[high]);

                int sum = nums[i] + nums[low] + nums[high];
                if (sum == target) {
                    return sum;
                } else if (sum > target) {
                    high--;
                } else {
                    low ++;
                }

                closest = findClosest(target, sum, closest);
            }
        }

        return closest;
    }

    private int findClosest(int target, int sum, int closest) {
        if(Math.abs(target - sum) < Math.abs(target - closest)) {
            System.out.println("ABS(" + (target - sum) + ") < ABS(" + (target - closest) + ")");
            return sum;
        }
        return closest;
    }

    @Test
    public void Sandbox() {
        System.out.println(threeSumClosest(new int[]{1,2,4,8,16,32,64,128}, 82));
    }
}
