package leetcode.first50;

import org.junit.Test;

/**
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.isEmpty()) {
            return 0;
        }

        char[] array = needle.toCharArray();
        int i = 0;
        for(char c : haystack.toCharArray()) {
           // MyLogger.LOGGER.debug("haystack[{}] = {}, needle[{}] = {}", i, c, needleIndex, array[needleIndex]);
            if(c == array[0]) {
                int needleIndex = 0;
                while(i+needleIndex < haystack.length() && needleIndex < needle.length() && haystack.charAt(i+needleIndex) == array[needleIndex]) {
                    needleIndex ++;
                }
                if (needleIndex == needle.length()) {
                    return i;
                }
            }

            i++;
        }

        return -1;
    }

    @Test
    public void Sandbox() {
        System.out.println(strStr("hello", "ll"));
    }
}
