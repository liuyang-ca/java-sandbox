package leetcode.first50;

import org.junit.Test;

import java.util.*;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        if (n == 0) {
            list.add("");
        } else {
            for(int i=0; i<n; i++) {
                List<String> lefts = generateParenthesis(i);
                List<String> rights = generateParenthesis(n-i-1);
                for(String left : lefts) {
                    for(String right : rights) {
                        list.add(left + "(" + right + ")");
                    }
                }
            }
        }
        return list;
    }

    public List<String> generateParenthesis2(int n) {
        if (n == 1) {
            return Arrays.asList("()");
        }
        List<String> list = generateParenthesis2(n - 1);
        Set<String> set = new HashSet<String>();
        for (String str : list) {
            for(int i=0; i<str.length(); i++) {
                for(int j=i; j<str.length(); j++) {
                    StringBuilder sb = new StringBuilder(str);
                    sb = sb.insert(i, '(').insert(j+1, ')');
//                    System.out.println(sb.toString());
//                    System.out.println("i, j = " + i + ", " + j);
                    set.add(sb.toString());
                }
            }
        }
        return new ArrayList<String>(set);
    }

    @Test
    public void Sandbox() {
        System.out.println(Arrays.toString(generateParenthesis(3).toArray()));
    }
}
