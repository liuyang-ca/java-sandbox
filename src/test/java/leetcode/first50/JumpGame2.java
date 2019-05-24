package leetcode.first50;

import org.junit.Test;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 *
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 *
 * You can assume that you can always reach the last index.
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        if(nums.length == 1) {
            return 0;
        } else if(nums.length == 2) {
            return 1;
        }

        int j=0;
        int i=0;
        while(i < nums.length) {
            int tmp = nums[i] + i;
            int max = 0;
            int maxIndex = tmp;
            while(tmp > i) {
                if(tmp >= nums.length-1){
                    return j+1;
                }
                if(max < nums[tmp] + tmp) {
                    max = nums[tmp] + tmp;
                    maxIndex = tmp;
                }
                tmp--;
            }
            i = maxIndex;
            j++;
        }

        return j;
    }

    public int jump2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int jumps = 0, pos = 0, max = 0;
        for (int i = 0; i < nums.length-1; i++) {
            max = Math.max(max, i+nums[i]);
            if (i == pos) {
                jumps++;
                pos = max;
            }
        }
        return jumps;
    }

    @Test
    public void Sandbox() {
        System.out.println(jump(new int[]{3,1,1,1,4}));
//        System.out.println(jump(new int[]{1, 1, 1}));
        System.out.println(jump(new int[]{10,9,8,7,6,5,4,3,2,1,1,0}));
    }
}
