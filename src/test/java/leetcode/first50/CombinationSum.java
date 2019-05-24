package leetcode.first50;

import leetcode.MyLogger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return subCombinationSum(candidates, target, 0);

    }

    private List<List<Integer>> subCombinationSum(int[] candidates, int target, int firstIndex) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i=firstIndex; i<candidates.length; i++) {
            int c = candidates[i];
            if(c < target) {
                int tmp = target - c;
                if (tmp >= c) {
                    MyLogger.LOGGER.debug("firstIndex = {}, tmp = {}, c = {}", firstIndex, tmp, c);
                    List<List<Integer>> subList = subCombinationSum(candidates, tmp, i);
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


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        for (int c : candidates) {
            if (c == target) {
                list.add(Arrays.asList(c));
            } else if (c < target) {
                int tmp = target - c;
                if (tmp >= c) {
                    List<List<Integer>> subList = combinationSum(candidates, tmp);
                    if (!subList.isEmpty()) {
                        for (List<Integer> l : subList) {
                            if(l.get(0) >= c) {
                                List<Integer> tmpList = new ArrayList<>();
                                tmpList.add(c);
                                tmpList.addAll(l);
                                list.add(tmpList);
                            }
                        }
                    }
                }
            }
        }

        return list;
    }

    @Test
    public void Sandbox() {
        System.out.println(combinationSum(new int[]{2, 3, 5}, 8));
    }
}
