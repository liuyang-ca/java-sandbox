package leetcode.second50;

import com.sun.tools.javac.util.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i : nums) {
            list.add(i);
        }
        return subsets(list);
    }

    private List<List<Integer>> subsets(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.isEmpty()) {
            result.add(new ArrayList<>());
            return result;
        }

        int first = nums.remove(0);
        List<List<Integer>> sub = subsets(nums);

        for(int i=0; i<sub.size(); i++) {
            result.add(new ArrayList<>(sub.get(i)));
            sub.get(i).add(first);
            result.add(sub.get(i));
        }

        return result;
    }

    public List<List<Integer>> subsets2(int[] nums) {

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList<Integer>();
        subsets(nums, ret, curr, 0);
        return ret;
    }

    public void subsets(int[] nums, List<List<Integer>> ret, List<Integer> curr, int index) {
        if(index == nums.length) {
            ret.add(curr);
            return;
        }


        subsets(nums,ret,new ArrayList<Integer>(curr),index+1);
        curr.add(nums[index]);
        subsets(nums,ret,new ArrayList<Integer>(curr),index+1);
    }

    @Test
    public void Sandbox() {
        System.out.println(subsets2(new int[]{1, 2, 3}));
    }
}
