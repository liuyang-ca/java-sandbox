package leetcode.first50;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).
 The matching should cover the entire input string (not partial).

 Note:

 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 Example 1:

 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".
 Example 2:

 Input:
 s = "aa"
 p = "*"
 Output: true
 Explanation: '*' matches any sequence.
 Example 3:

 Input:
 s = "cb"
 p = "?a"
 Output: false
 Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 Example 4:

 Input:
 s = "adceb"
 p = "*a*b"
 Output: true
 Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 Example 5:

 Input:
 s = "acdcb"
 p = "a*c?b"
 Output: false
 */
public class WildcardMatching {
    private Map<String, Set<String>>  map = new HashMap<>();

    public boolean isMatch2(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }

    public boolean isMatch(String s, String p) {
        if(map.get(s) != null && map.get(s).contains(p)) {
            return false;
        }

        int j=0;
        for(int i=0; i<p.length(); i++) {
           // MyLogger.LOGGER.debug("i = {}, j = {}, chatAt = {}, s = {}, p = {}", i, j, p.charAt(i), s, p);
            char c = p.charAt(i);
            switch(c) {
                case '?':
                    if(j >= s.length()) {
                        Set<String> m = map.get(p);
                        if (m == null) {
                            m = new HashSet<>();
                        }
                        m.add(p);
                        map.put(s, m);

                        return false;
                    } else {
                        j++;
                    }
                    break;
                case '*':
                    int tmp = j;
                    while(i+1 < p.length() && p.charAt(i+1) == '*') {
                        i ++;
                    }

                    while(tmp <= s.length()) {
                        if (isMatch(s.substring(tmp), p.substring(i+1))) {
                            return true;
                        }
                        tmp++;
                    }

                    break;
                default: //a-z
                    if(j >= s.length() || s.charAt(j) != c) {
                        Set<String> m = map.get(p);
                        if (m == null) {
                            m = new HashSet<>();
                        }
                        m.add(p);
                        map.put(s, m);
                        return false;
                    } else {
                        j++;
                    }
                    break;
            }

            if (j > s.length()) {
                Set<String> m = map.get(p);
                if (m == null) {
                    m = new HashSet<>();
                }
                m.add(p);
                map.put(s, m);
                return false;
            }
        }

        if(j != s.length()) {
            Set<String> m = map.get(p);
            if (m == null) {
                m = new HashSet<>();
            }
            m.add(p);
            map.put(s, m);
            return false;
        }

        return true;
    }

    @Test
    public void Sandbox() {
        //System.out.println("aa".substring(2));
//        System.out.println(isMatch("aa", "a"));
//        System.out.println(isMatch("aa", "*"));
//        System.out.println(isMatch("cb", "?a"));
 //       System.out.println(isMatch("adceb", "*e****b"));
//        System.out.println(isMatch("acdcb", "a*c?b"));
        System.out.println(isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
        //System.out.println(isMatch("aabaaababaaaabb", "a*c"));
        //"**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"
    }
}
