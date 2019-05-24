package leetcode.second50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Combinations {
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(result, cur, 1, k);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> cur, int n, int k) {
        if(cur.size() == k) {
            result.add(new ArrayList<>(cur));
            return;
        }

        //dfs(result, new ArrayList<>(cur), n+1, k);
        cur.add(n);
        dfs(result, cur, n+1, k);
    }
    // k = 2
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < k || k == 0) {
            // do nothing
        } else if (k == 1) {
            for(int i=1; i<=n; i++) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                result.add(tmp);
            }
        } else {
            result.addAll(combine(n-1, k));
            List<List<Integer>> subResult = combine(n-1, k-1);
            for(List<Integer> tmpSub : subResult) {
                List<Integer> tmp = new ArrayList<>(tmpSub);
                tmp.add(n);
                result.add(tmp);
            }
        }

        return result;
    }


    @Test
    public void Sandbox() {
        System.out.println(combine2(2,1));
    }
}
