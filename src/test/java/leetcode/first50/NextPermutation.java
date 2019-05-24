package leetcode.first50;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * <p>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {
    private boolean hasPermutation(List<Integer> nums) {
        if(nums.size() == 1) {
            return false;
        }

        Integer first = nums.get(0);
        nums.remove(0);
        if(hasPermutation(nums)) {
            nums.add(0, first);
            return true;
        }

        //nums.add(0, first);
        for(int i=nums.size()-1; i>=0; i--) {
            Integer current = nums.get(i);
            if(current > first) {
                //MyLogger.LOGGER.debug("first = {}, nums = {}", first, nums);
                nums.remove(i);
                nums.add(i, first);
                Collections.reverse(nums);
                nums.add(0, current);
                //MyLogger.LOGGER.debug("nums = {}", nums);
                return true;
            }
        }
        nums.add(0, first);
        return false;
    }

    public void nextPermutation(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        if (!hasPermutation(list)) {
            Collections.reverse(list);
        }
       // MyLogger.LOGGER.debug("list = {}", list);
        for(int i=0; i<list.size(); i++) {
            nums[i] = list.get(i);
        }
    }

    @Test
    public void Sandbox() {
        int[] nums = new int[]{3, 4, 3, 2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
