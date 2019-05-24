package leetcode.second50;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairs {
    private Map<Integer, Integer> map = new HashMap<>();

    public int climbStairs(int n) {
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);

        return climbStairsInternal(n);
    }

    private int climbStairsInternal(int n) {
        Integer result = map.get(n);
        if (result != null) {
            return result;
        } else {
            result = climbStairsInternal(n-1) + climbStairsInternal(n-2);
            map.put(n, result);
            return result;
        }
    }

    @Test
    public void Sandbox() {
        System.out.println(climbStairs(44));
    }
}
