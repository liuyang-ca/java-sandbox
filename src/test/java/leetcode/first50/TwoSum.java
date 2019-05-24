package leetcode.first50;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // map of num and count
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++) {
                Integer other = map.get(target - nums[i]);
                if (other != null) {
                    return new int[]{other, i};
                } else {
                    map.put(nums[i], i);
                }
        }

        return new int[]{};
    }

    @Test
    public void Sandbox() {
        System.out.println(Arrays.toString(twoSum(new int[]{-3, 4, 3, 90}, 0)));
    }
}
