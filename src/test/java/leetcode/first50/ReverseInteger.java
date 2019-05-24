package leetcode.first50;

import org.junit.Test;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {
    public int reverse(int x) {
        int result = 0;
        while(x != 0) {
           int pop = x % 10;                //  3 = 123 % 10        2 = 12 % 10
           x = x/10;                        //  12 = 123 / 10       1 = 12 / 10
           if (result > Integer.MAX_VALUE/10 || result == Integer.MAX_VALUE/10 && pop > 7) {
               return 0;
           }

            if (result < Integer.MIN_VALUE/10 || result == Integer.MIN_VALUE/10 && pop < -8){
                return 0;
            }

           result = result * 10 + pop;      //  3 = 0*10 + 3        32  = 3 * 10 + 2
        }
        return result;
    }
//2147483647
    @Test
    public void Sandbox() {
        //System.out.println(reverse(123));
        System.out.println(-123%10);
    }
}
