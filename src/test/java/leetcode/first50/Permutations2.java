package leetcode.first50;

import leetcode.MyLogger;
import org.junit.Test;

import java.util.*;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * <p>
 * Example:
 * <p>
 * Input: [1,1,2]
 * Output:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class Permutations2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        boolean[] isVisit = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            isVisit[i] = false;
        }

        dfs(result, new ArrayList<>(), isVisit, nums);
        return result;
    }

    private static void dfs(List<List<Integer>> result, List<Integer> cur, boolean[] isVisit, int[] nums) {
        if (cur.size() == nums.length) {
            result.add(new ArrayList(cur));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (isVisit[i]) {
                continue;
            }
            if(i > 0 && nums[i] == nums[i - 1] && !isVisit[i - 1]) {
                continue;
            }
            isVisit[i] = true;
            cur.add(nums[i]);

            //MyLogger.LOGGER.debug("befor cur = {}, visited = {}", cur, Arrays.toString(isVisit));
            dfs(result, cur, isVisit, nums);
            //MyLogger.LOGGER.debug("after cur = {}, visited = {}", cur, Arrays.toString(isVisit));
            isVisit[i] = false;
            cur.remove(cur.size() - 1);
        }

    }

    public List<List<Integer>> permute2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> visited = new ArrayList<>();
        List<Integer> unVisited = new ArrayList<>();
        for(int i : nums) {
            unVisited.add(i);
        }

        traverseTree(result, visited, unVisited, nums.length);
        return result;
    }

    private void traverseTree(List<List<Integer>> result, List<Integer> visited, List<Integer> unVisited, int size) {
        MyLogger.LOGGER.debug("visited = {}", visited);
        if (visited.size() == size) {
            result.add(new ArrayList<>(visited));
            return;
        }
        List<Integer> tmpUnVisited = new ArrayList<>(unVisited);
        for(int i=0; i<tmpUnVisited.size(); i++) {
            if(i >0 && tmpUnVisited.get(i) == tmpUnVisited.get(i-1)) {
                continue;
            }

            Integer removed = unVisited.remove(i);
            visited.add(removed);
            //MyLogger.LOGGER.debug("before i = {}, visited = {}, unVisited = {}", i, visited, unVisited);
            traverseTree(result, visited, unVisited, size);
            //MyLogger.LOGGER.debug("after  i = {}, visited = {}, unVisited = {}", i, visited, unVisited);
            unVisited.add(i, removed);
            visited.remove(visited.size() - 1);
        }
    }

    @Test
        public void Sandbox() {
        System.out.println(permuteUnique(new int[]{1, 1, 3}));
        System.out.println(permute2(new int[]{1, 1, 3}));
    }
}
