package leetcode.second50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 *
 * n=2
 * "12"
 * "21"
 *
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 *
 * Note:
 *
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * Example 1:
 *
 * Input: n = 3, k = 3
 * Output: "213"
 * Example 2:
 *
 * Input: n = 4, k = 9
 * Output: "2314"
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        List<Integer> unVisited = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            unVisited.add(i);
        }

        List<Integer> counter = new ArrayList<>();
        counter.add(0);
        return findNthPermutation(counter, "", unVisited, n, k);
    }

    private String findNthPermutation(List<Integer> counter, String cur, List<Integer> unVisited, int n, int k) {
        if(cur.length() == n) {
            counter.set(0, counter.get(0)+1);
            if(counter.get(0) == k) {
                return cur;
            }
            return null;
        }

        List<Integer> tmpList = new ArrayList<>(unVisited);
        for(int i=0; i<tmpList.size(); i++) {
            Integer removed = unVisited.remove(i);
            String tmp = findNthPermutation(counter, cur + removed, unVisited, n, k);
            if (tmp != null) {
                return tmp;
            }
            unVisited.add(i, removed);
        }

        return null;
    }

    private List<String> findPermutation(int n) {
        List<Integer> unVisited = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            unVisited.add(i);
        }

        List<String> result = new ArrayList<>();
        findPermutation(result, "", unVisited, n);

        return result;
    }

    private void findPermutation(List<String> result, String cur, List<Integer> unVisited, int n) {
        if(cur.length() == n) {
            result.add(cur);
            return;
        }

        List<Integer> tmpList = new ArrayList<>(unVisited);
        for(int i=0; i<tmpList.size(); i++) {
            Integer removed = unVisited.remove(i);
            findPermutation(result, cur + removed, unVisited, n);
            unVisited.add(i, removed);
        }
    }

    @Test
    public void Sandbox() {
        System.out.println(findPermutation(3));
        System.out.println(findPermutation(4));
        //System.out.println(getPermutation(4, 9));
    }
}
