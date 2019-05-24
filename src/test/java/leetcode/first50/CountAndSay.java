package leetcode.first50;

import org.junit.Test;

/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 *
 * Note: Each term of the sequence of integers will be represented as a string.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: "1"
 * Example 2:
 *
 * Input: 4
 * Output: "1211"
 */
public class CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String pre = countAndSay(n-1);

        StringBuilder sb = new StringBuilder();

        //1234*
        pre = pre + "*";
        int counter = 1;
        for(int i=1; i<pre.length(); i++) {
            if (pre.charAt(i) == pre.charAt(i-1)) {
                counter++;
            } else {
                sb = sb.append(counter).append(pre.charAt(i-1));
                counter = 1;
            }
        }

        return sb.toString();
    }

    @Test
    public void Sandbox() {
        System.out.println(countAndSay(5));
    }
}
