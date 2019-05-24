package leetcode.first50;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 * Input: [1,2,0]
 * Output: 3
 *
 * Example 2:
 * Input: [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 * Input: [7,8,9,11,12]
 * Output: 1
 *
 * Note:
 *
 * Your algorithm should run in O(n) time and uses constant extra space.
 */
public class FirstMissingPositive {
    public int firstMissingPositive2(int[] nums) {
        if(nums.length == 0) {
            return 1;
        }

        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(num > 0) {
                set.add(num);
            }
        }

        int smallest = 1;
        for(Integer num : nums) {
            if(!set.contains(smallest)) {
                return smallest;
            }
            smallest++;
        }

        return smallest;
    }

    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Integer smallest = Integer.MAX_VALUE;
        Integer largest = 0;
        for(int num : nums) {
            if(num > 0) {
                smallest = Math.min(smallest, num);
                largest = Math.max(largest, num);
                set.add(num);
            }
        }

        if(largest == 0 || smallest > 1) {
            return 1;
        }

        for(Integer num : set) {
            if(!set.contains(smallest)) {
                return smallest;
            }
            smallest++;
        }

        return largest + 1;
    }

    @Test
    public void Sandbox() {
        System.out.println(firstMissingPositive2(new int[] {1,2,0}));
        System.out.println(firstMissingPositive2(new int[] {3,4,-1,1}));
        System.out.println(firstMissingPositive2(new int[] {7,8,9,11,12}));
        System.out.println(firstMissingPositive2(new int[] {-1,-2,-3}));

    }
}
