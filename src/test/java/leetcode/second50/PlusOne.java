package leetcode.second50;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 *
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        boolean carry = true;
        int k = digits.length - 1;
        while (carry && k >= 0) {
            digits[k] = digits[k] + 1;
            if (digits[k] > 9) {
                digits[k] = digits[k] % 10;
            } else {
                carry = false;
            }
            k--;
        }

        if (carry) {
            int[] array = new int[digits.length + 1];
            array[0] = 1;
            for (int i = 1; i < array.length; i++) {
                array[i] = digits[i - 1];
            }
            return array;
        }

        return digits;
    }

    @Test
    public void Sandbox() {
        System.out.println(Arrays.toString(plusOne(new int[] {0})));
    }
}
