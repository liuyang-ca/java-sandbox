package leetcode.first50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(nums.length < 4) {
            return list;
        }

        Arrays.sort(nums);

        for(int i=0; i < nums.length-3; i++) {
            if(nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) {
                continue;
            }
            if(nums[nums.length-1] + nums[nums.length-2] + nums[nums.length-3] + nums[nums.length-4] < target) {
                continue;
            }

            if(i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for(int j=i+1; j < nums.length-2; j++) {
                if(j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }
                int sum2 = nums[i] + nums[j];
                int low = j+1, high = nums.length - 1;
                while(low < high) {
                    int sum = sum2 + nums[low] + nums[high];
                    if (sum == target) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        while(low < high && nums[low] == nums[low+1]) {
                            low++;
                        }
                        while(low < high && nums[high] == nums[high-1]) {
                            high--;
                        }
                        low++;
                        high--;
                    } else if (sum > target) {
                        high--;
                    } else {
                        low++;
                    }
                }
            }
        }
        return list;
    }

    @Test
    public void Sandbox() {
        System.out.println(Arrays.toString(fourSum( new int[]{0, 0, 0, 0}, 0).toArray()));
    }
}
