package leetcode.first50;

import org.junit.Test;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 *
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 *
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer
 * range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 2^31 − 1 when
 * the division result overflows.
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        boolean isDividendMin = false;
        if(dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
            dividend = dividend + 1;
            isDividendMin = true;
        }

        if(divisor == Integer.MIN_VALUE) {
            if (isDividendMin) {
                return 1;
            }
            return 0;
        }

        boolean isNegative = false;
        if((dividend < 0 && divisor >=0) || dividend >= 0 && divisor < 0) {
            isNegative = true;
        }

        if (dividend < 0) {
            dividend = 0 - dividend;
        }
        if (divisor < 0) {
            divisor = 0 - divisor;
        }

        int i = 0;
        int sum = divisor;
        int tmp = 0;
        if (isDividendMin) {
            tmp = 1;
        }
        if(sum - tmp == dividend) {
            i = 1;
        } else {
            while (sum - tmp <= dividend) {
                i++;
                if (sum - tmp > Integer.MAX_VALUE - divisor) {
                    //MyLogger.LOGGER.debug("sum = {}, tmp = {}, divisor = {}, i = {}", sum, tmp, divisor, i);
                    break;
                }
                sum = sum + divisor;
            }
        }

        return isNegative ? 0-i : i;
    }

    @Test
    public void Sandbox() {
        System.out.println(divide(Integer.MIN_VALUE, 1));
//        System.out.println(0-Integer.MIN_VALUE);
//        System.out.println(0-Integer.MAX_VALUE);
    }
}
