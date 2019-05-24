package leetcode.first50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 *
 * Example 1:
 *
 * Input: 121
 * Output: true
 * Example 2:
 *
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 *
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 * Follow up:
 *
 * Coud you solve it without converting the integer to a string?
 */

public class PalindromeNumber {
    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        }

        List<Integer> list = new ArrayList<Integer>();

        while(x != 0) {
            list.add(x % 10);
            x = x / 10;
        }

        int mid = list.size()/2;
        int tmp = 1;
        boolean isEven = list.size() % 2 == 0 ? true : false;
        while(tmp <= mid) {
            if (list.get(mid-tmp) != list.get(mid+tmp-1*(isEven?1:0))) {
                return false;
            } else {
                tmp++;
            }
        }

        return true;
    }

    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        } else if (x < 0 || x%10 == 0) {
            return false;
        }
        int reverse = 0;
        while(reverse < x) {
            reverse = reverse*10 + (x % 10);
            x = x / 10;
        }
        System.out.println("reverse = " + reverse + ", x = " + x);
        return reverse == x || reverse/10 == x;
    }

    @Test
    public void Sandbox() {
        System.out.println(isPalindrome(100));
    }

}
