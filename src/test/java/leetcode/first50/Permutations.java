package leetcode.first50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations {

    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0) {
            return result;
        } else if(nums.length == 1) {
            result.add(Arrays.asList(nums[0]));
            return result;
        }

        int[] subNums = Arrays.copyOfRange(nums, 1, nums.length);
        List<List<Integer>> subResults = permute3(subNums);
        for(List<Integer> subResult : subResults) {
            for(int i=0; i<nums.length; i++) {
                List<Integer> tmp = new ArrayList<>(subResult);
                tmp.add(i, nums[0]);
                result.add(tmp);
            }
        }

        return result;
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int num : nums) {
            list.add(num);
        }
        return permuteList(list);
    }

    public List<List<Integer>> permuteList(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.size() == 0) {
            return result;
        } else if(nums.size() == 1) {
            result.add(Arrays.asList(nums.get(0)));
        } else {
            List<Integer> list = new ArrayList<>();
            list.addAll(nums);

            for(int i=0; i<nums.size(); i++) {
                list.remove(i);
                List<List<Integer>> subResults = permuteList(list);
                for(List<Integer> sub : subResults) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums.get(i));
                    tmp.addAll(sub);
                    result.add(tmp);
                }
                list.add(i, nums.get(i));
            }
        }
        return result;
    }

    @Test
    public void Sandbox() {
        System.out.println(permute3(new int[] {1, 2, 3, 4}));
        System.out.println(permute3(new int[] {1, 2, 3, 4}).size());
    }
}
