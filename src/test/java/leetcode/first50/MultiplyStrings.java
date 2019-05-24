package leetcode.first50;

import leetcode.MyLogger;
import org.junit.Test;

import java.util.Arrays;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2,
 * also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int totalLength = num1.length() + num2.length();

        int[] array = new int[totalLength];
        int index;
        for(int i=num1.length()-1; i>=0; i--) {
            index = totalLength - 1 - (num1.length()-1 - i);
            for(int j=num2.length()-1; j>=0; j--) {
                int tmp = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                array[index] = array[index] + tmp%10;
                int carrier = tmp/10;
                if(array[index] >= 10) {
                    int digit = array[index]%10;
                    carrier = carrier +  array[index]/10;
                    array[index] = digit;
                }

                int k = 1;
                while (carrier > 0) {
                    array[index-k] = array[index-k] + carrier;
                    carrier = carrier / 10;
                    if(array[index-k] >= 10) {
                        int digit = array[index-k]%10;
                        carrier = carrier + array[index-k]/10;
                        array[index-k] = digit;
                    }
                    k++;
                }

                MyLogger.LOGGER.debug("num1.charAt(i) = {}, num2.charAt(j) = {}, tmp = {}, index = {}, array[index] = {} array[index-1] = {}",
                        num1.charAt(i), num2.charAt(j), tmp, index, array[index], array[index-1]);
                index--;
            }
            MyLogger.LOGGER.debug("{}", Arrays.toString(array));
        }

        StringBuilder sb = new StringBuilder();
        boolean isLeadingZero = true;
        for(int i : array) {
            if(i == 0 && isLeadingZero) {
                continue;
            } else {
                isLeadingZero = false;
                sb.append(i);
            }
        }

        return sb.toString();
    }

    @Test
    public void Sandbox() {
        System.out.println(multiply("99", "11"));
    }
}
