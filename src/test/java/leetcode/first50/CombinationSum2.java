package leetcode.first50;

import leetcode.MyLogger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        return subCombinationSum(candidates, target, 0);

    }

    private List<List<Integer>> subCombinationSum(int[] candidates, int target, int firstIndex) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i=firstIndex; i<candidates.length; i++) {
            while (i-1 >=firstIndex && i<candidates.length && candidates[i-1] == candidates[i]) {
                i++;
            }
            if (i == candidates.length) {
                break;
            }
            int c = candidates[i];
            MyLogger.LOGGER.debug("firstIndex = {}, c = {}, target = {}", firstIndex, c, target);
            if(c < target) {
                int tmp = target - c;
                if (tmp >= c) {
                    //MyLogger.LOGGER.debug("firstIndex = {}, c = {}, tmp = {}", firstIndex, c, tmp);
                    List<List<Integer>> subList = subCombinationSum(candidates, tmp, i+1);
                    if (!subList.isEmpty()) {
                        for (List<Integer> l : subList) {
                            List<Integer> tmpList = new ArrayList<>();
                            tmpList.add(c);
                            tmpList.addAll(l);
                            list.add(tmpList);
                        }
                    }
                }
            } else if (c == target) {
                list.add(Arrays.asList(c));
            } else {
                break;
            }
        }

        return list;
    }

    @Test
    public void Sandbox() {
        //System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
        System.out.println(combinationSum2(new int[]{2,5,2,1,2}, 5));
        //System.out.println(combinationSum2(new int[]{1, 2, 2, 2}, 4));
        //1 2 2 2 5
        //2 2 2
        //4
    }
}
