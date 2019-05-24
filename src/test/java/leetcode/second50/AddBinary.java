package leetcode.second50;

import org.junit.Test;

import java.util.Arrays;

/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;

        char carrier = '0';
        while(i >= 0 || j >= 0) {
            char aChar = i < 0 ? '0' : a.charAt(i);
            char bChar = j < 0 ? '0' : b.charAt(j);

            if (aChar == '1' && bChar == '1') {
                sb = sb.insert(0, carrier);
                carrier = '1';
            } else if (aChar == '0' && bChar == '0') {
                sb = sb.insert(0, carrier);
                carrier = '0';
            } else {
                if (carrier == '1') {
                    sb = sb.insert(0, '0');
                    carrier = '1';
                } else {
                    sb = sb.insert(0, '1');
                    carrier = '0';
                }
            }

            i--;
            j--;
        }

        if (carrier == '1') {
            sb = sb.insert(0, '1');
        }

        return sb.toString();
    }

    @Test
    public void Sandbox() {
        System.out.println(addBinary("0", "1"));
    }
}
