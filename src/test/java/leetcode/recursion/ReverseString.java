package leetcode.recursion;

import leetcode.MyLogger;
import org.junit.Test;

import java.util.Arrays;

/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 *
 *
 * Example 1:
 *
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
public class ReverseString {
    public void reverseString(char[] s) {
        helper(0, s.length-1, s);
    }

    private void helper(int low, int high, char[] s) {
        MyLogger.LOGGER.debug("low = {}, high = {}", low, high);
        if(low > high) {
            return;
        }
        helper(low+1, high-1, s);
        char tmp = s[low];
        s[low] = s[high];
        s[high] = tmp;
    }

    public String reverseString2(char[] s) {
        return helper2(s, s.length-1);
    }

    private String helper2(char[] s, int index) {
        if(index < 0) {
            return "";
        }
        return s[index] + helper2(s, index-1);
    }

    @Test
    public void Sandbox() {
        char[] array = "".toCharArray();
        reverseString(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void Sandbox2() {

        System.out.println(reverseString2("abcdef".toCharArray()));
    }
}
