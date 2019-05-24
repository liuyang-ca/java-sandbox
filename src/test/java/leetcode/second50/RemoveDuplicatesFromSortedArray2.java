package leetcode.second50;

import org.junit.Test;

import java.util.Arrays;

/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 *
 * Given nums = [1,1,1,2,2,3],
 *
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 *
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 *
 * Given nums = [0,0,1,1,1,1,2,3,3],
 *
 * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 *
 * It doesn't matter what values are set beyond the returned length.
 * Clarification:
 *
 * Confused why the returned value is an integer but your answer is an array?
 *
 * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
 *
 * Internally you can think of this:
 *
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeDuplicates(nums);
 *
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 */
public class RemoveDuplicatesFromSortedArray2 {
    public int removeDuplicates2(int[] nums) {
        int i=0;
        for(int n : nums) {
            if(i < 2 || nums[i-2] < n) {
                nums[i++] = n;
            }
        }

        return i;
    }

    public int removeDuplicates(int[] nums) {
        if(nums.length <= 2) {
            return nums.length;
        }

        int i=0;
        boolean isStartSwap = false;
        for(int j=2; j<nums.length; j++) {
            if (isStartSwap) {
                if(nums[j] != nums[i-1] || nums[j] != nums[i-2]) {
                    swap(nums, i, j);
                    i++;
                }
            } else {
                i = j;
            }

            if(nums[j-1] == nums[j] && nums[j-2] == nums[j] && !isStartSwap) {
                isStartSwap = true;
            }
        }

        return isStartSwap ? i:i+1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void Sandbox() {
        int[] array = new int[]{1,1,1,2,2,2};
        System.out.println(removeDuplicates2(array));
        System.out.println(Arrays.toString(array));
     }
}
