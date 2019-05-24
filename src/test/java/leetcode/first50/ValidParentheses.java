package leetcode.first50;

import java.util.List;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        StringBuilder stack = null;
        for(char c : s.toCharArray()) {
            if (stack == null) {
                stack = new StringBuilder();
                stack.append(c);
            } else {
                int index = stack.length() - 1;
                if (index < 0) {
                    stack.append(c);
                } else {
                    char peek = stack.charAt(index);
                    if ((peek == '(' && c == ')') || (peek == '{' && c == '}') || (peek == '[' && c == ']')) {
                        stack.deleteCharAt(index);
                    } else {
                        stack.append(c);
                    }
                }
            }
        }
        return stack == null || stack.length() == 0;
    }

    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char c : s.toCharArray()) {
            if (stack.empty()) {
                stack.push(c);
            } else {
                char peek = stack.peek();
                if ((peek == '(' && c == ')') || (peek == '{' && c == '}') || (peek == '[' && c == ']')) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.size() == 0;
    }
}
