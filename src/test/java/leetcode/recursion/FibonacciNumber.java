package leetcode.recursion;

import org.junit.Test;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), for N > 1.
 * Given N, calculate F(N).
 *
 *
 *
 * Example 1:
 *
 * Input: 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 * Example 2:
 *
 * Input: 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 * Example 3:
 *
 * Input: 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 *
 * Note:
 *
 * 0 ≤ N ≤ 30.
 */
public class FibonacciNumber {

    public int fib(int n) {
        int[] array = new int[31];
        array[1] = 1;
        return helper(array, n);
    }

    private int helper(int[] array, int n) {
        if(array[n] != 0) {
            return array[n];
        } else if (n != 0) {
            array[n] = helper(array, n-1) + helper(array, n-2);
        }

        return array[n];
    }

    @Test
    public void Sandbox() {
        System.out.println(fib(30));
    }
}
