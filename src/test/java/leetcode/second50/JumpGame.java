package leetcode.second50;

import org.junit.Test;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int len = nums.length-1;
        for(int i=0; i<len; i++) {
            if(nums[i] == 0) {
                boolean canJump = false;
                for(int j=i-1; j>=0; j--) {
                    int delta = i-j;
                    if(nums[j] > delta) {
                        canJump = true;
                        break;
                    }
                }
                if(!canJump) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean canJump2(int[] nums) {
        int len = nums.length-1;
        int lastPos = len;
        for(int j=len; j>=0; j--) {
            int delta = lastPos-j;
            //MyLogger.LOGGER.debug("lastPos = {}, j = {}, delta = {}, nums[j] = {}", lastPos, j, delta, nums[j]);
            if(nums[j] >= delta) {
                lastPos = j;
            }
        }

        return lastPos == 0;
    }

    @Test
    public void Sandbox() {
        System.out.println(canJump2(new int[]{2,3,1,1,4}));
        System.out.println(canJump2(new int[]{3,2,1,0,4}));
        System.out.println(canJump2(new int[]{3,2,2,0,4}));
        System.out.println(canJump2(new int[]{3,2,2,0,0}));
        System.out.println(canJump2(new int[]{0}));
    }
}
