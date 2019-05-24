package leetcode.first50;

import org.junit.Test;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        String result = "";

        if (s.length() == 0) {
            return "";
        }

        for(int i=0; i<s.length()-1; i++) {
            int index1 = extendAroundCenter(s, i, i);
            if (index1 > 0 && index1*2 + 1 > result.length()) {
                result = s.substring(i-index1, i+1+index1);
                System.out.println("result1 = " + result);
            }

            int index2 = extendAroundCenter(s, i, i+1);
            if (index2 > 0 && index2*2 > result.length()) {
                result = s.substring(i+1-index2, i+1+index2);
                System.out.println("result2 = " + result);
            }
        }

        if (result.length() == 0) {
            return String.valueOf(s.charAt(0));
        }
        return result;
    }

    // cbbd 2 cbbd 2
    private int extendAroundCenter(String s, int i, int j) {
        int depth = 0;
        while ((i - depth >= 0) && (j + depth < s.length()) && (s.charAt(i - depth) == s.charAt(j + depth))) {
            System.out.println("i = " + s.charAt(i - depth) + ", j = " + s.charAt(j + depth));
            depth ++;
        }

        if (i == j && depth >= 1) {
            depth --;
        }
        return depth;
    }

    @Test
    public void Sandbox() {
        //System.out.println(extendAroundCenter("cbd", 1,2));
        System.out.println("result=" + longestPalindrome("cccc"));
    }
}
