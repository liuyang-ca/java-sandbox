package leetcode.first50;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        boolean run = true;
        while(run && strs.length > 0) {
            char c = 0;
            for (String str : strs) {
                if(index == str.length()) {
                    run = false;
                    break;
                }
                char tmp = str.charAt(index);
                if (c == 0) {
                    c = tmp;
                } else if (c != tmp){
                    run = false;
                    break;
                }
            }
            if (run) {
                sb = sb.append(c);
            }
            index++;
        }
        return sb.toString();
    }
}
