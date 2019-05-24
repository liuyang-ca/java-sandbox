package leetcode.first50;

import leetcode.MyLogger;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParentheses {
    public int longestValidParentheses2(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLength = 0;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i-stack.peek());
                }
            }
        }

        return maxLength;
    }

    public int longestValidParentheses(String s) {
        Stack<CharWithIndex> stack = new Stack<>();
        stack.push(new CharWithIndex('b', -1));
        char[] array = s.toCharArray();
        for(int i=0; i<array.length; i++) {
            CharWithIndex peek = stack.peek();
            //MyLogger.LOGGER.debug("array[i] = {}, peek.c = {}", array[i], peek.c);
            if(array[i] == ')' && peek.c == '(') {
                stack.pop();
                array[i] = 'a';
                array[peek.index] = 'a';
            } else {
                stack.push(new CharWithIndex(array[i], i));
            }
        }

        MyLogger.LOGGER.debug("array = {}", Arrays.toString(array));

        int max = 0;
        int tmpSize = 0;
        for(int i=0; i<array.length; i++) {
            if(array[i] == 'a') {
                tmpSize++;
            } else {
                max = Math.max(max, tmpSize);
                tmpSize = 0;
            }
        }
        max = Math.max(max, tmpSize);
        return max;
    }

    class CharWithIndex {
        char c;
        int index;
        public CharWithIndex(char c, int index) {
            this.c = c;
            this.index = index;
        }
    }

    @Test
    public void Sandbox() {
        System.out.println(longestValidParentheses2(")()())"));
    }
}
