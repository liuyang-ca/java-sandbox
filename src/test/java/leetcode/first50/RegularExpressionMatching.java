package leetcode.first50;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 * <p>
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 * <p>
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 * Example 5:
 * <p>
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class RegularExpressionMatching {
    public boolean isMatch(String str, String pattern) {
        int s = 0;
        int p = 0;
        while(s < str.length() && p < pattern.length()) {
            if(pattern.charAt(p) != '*') {
                if(pattern.charAt(p) == str.charAt(s) || pattern.charAt(p) == '.') {
                    p++;
                    s++;
                } else if(p+1 < pattern.length() && pattern.charAt(p+1) == '*') {
                    p=p+2;
                } else {
                    return false;
                }
            } else {
                // "a", "a*c*a"));
                char previousPChar = pattern.charAt(p-1);
                p++;
                String subP = pattern.substring(p);

                while(s < str.length() && (previousPChar == str.charAt(s) || previousPChar == '.')) {
                    if (isMatch(str.substring(s), subP)) {
                        return true;
                    }
                    s++;
                }
            }
        }

        if(p < pattern.length() && pattern.charAt(p) == '*') {
            p++;
        }

        while(p+1 < pattern.length() && pattern.charAt(p) != '*'  && pattern.charAt(p+1) == '*') {
            p=p+2;
        }

        return p == pattern.length() && s == str.length();
    }

    public boolean isMatch3(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        } else if (s.length() == 0 || p.length() == 0) {
            if (s.length() == 0 && p.length() >= 2) {
                if (p.charAt(1) == '*') {
                    return isMatch3(s, p.substring(2));
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) {
                return isMatch3(s.substring(1), p.substring(1));
            } else if (p.length() >= 2 && p.charAt(1) == '*') {
                return isMatch3(s, p.substring(2));
            } else {
                return false;
            }
        }
    }

    public boolean isMatchOnlyDot(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        } else if (s.length() == 0 || p.length() == 0) {
            return false;
        } else {
            if (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) {
                return isMatchOnlyDot(s.substring(1), p.substring(1));
            } else {
                return false;
            }
        }
    }

    public boolean isMatch2(String text, String pattern) {
        if (pattern.length() == 0)
            return text.length() == 0;

        boolean first_match = (text.length() != 0 &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch2(text, pattern.substring(2)) ||
                    (first_match && isMatch2(text.substring(1), pattern)));
        } else {
            return first_match && isMatch2(text.substring(1), pattern.substring(1));
        }
    }


    @Test
    public void Sandbox() {
        Assert.assertFalse(isMatch3("aa", "a"));
        Assert.assertTrue(isMatch3("aa", "a*"));
        Assert.assertTrue(isMatch3("ab", ".*"));
        Assert.assertTrue(isMatch3("aab", "c*a*b"));
        Assert.assertFalse(isMatch3("mississippi", "mis*is*p*."));
        Assert.assertTrue(isMatch3("aaa", "ab*a*c*a"));
        Assert.assertTrue(isMatch3("asdfasdfasdfas", ".*asdf.*asdf.*asdf.*s"));
    }
}
