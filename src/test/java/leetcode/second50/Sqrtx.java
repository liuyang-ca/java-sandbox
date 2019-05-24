package leetcode.second50;

import org.junit.Test;

/**
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 *
 * Example 1:
 *
 * Input: 4
 * Output: 2
 * Example 2:
 *
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 *              the decimal part is truncated, 2 is returned.
 *
 * Input: 1000
 * Output: 100
 */
public class Sqrtx {
    public int mySqrt(int x) {
        if(x == 1 || x == 0) {
            return x;
        }

        int tmp = x;
        int length = 1;
        while(tmp/10 != 0) {
            length++;
            tmp = tmp/10;
        }

        if (length % 2 == 0) {
            length = length / 2;
        } else {
            length = 1 + length / 2;
        }

        // sqrt between [length/10 - length]

        int high = 1;
        while(length > 0) {
            high = high * 10;
            length--;
        }

        int low = high/10;

        while(low < high - 1) {
            int mid = low + (high - low) / 2;
            if (mid * mid == x) {
                return mid;
            } else if (mid > x / mid) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return low;
    }

    public int mySqrt2(int x) {
        if (x == 0) {
            return x;
        }
        int i = 1;
        int j = x;
        int ans = 0;
        while (i <= j) {
            int m = i + (j - i) / 2;
            if (m * m == x) {
                return m;
            }
            if (m >= x / m) {
                j = m - 1;
            }
            if (m <= x / m) {
                i = m + 1;
                ans = m;
            }
        }
        return ans;
    }

    @Test
    public void Sandbox() {
        //System.out.println(Math.sqrt(Integer.MAX_VALUE-10));
        System.out.println(mySqrt(Integer.MAX_VALUE));
    }
}
