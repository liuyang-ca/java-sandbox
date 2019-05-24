package leetcode.first50;

import org.junit.Test;

public class RomanToInteger {
    int[] intArray = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] romanArray = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public int romanToInt(String s) {
        int sum = 0;
        for(int i=0; i<romanArray.length; i++) {
            String roman = romanArray[i];
            while(s.startsWith(roman)) {
                sum = sum + intArray[i];
                s = s.substring(roman.length(), s.length());
            }
        }
        return sum;
    }

    @Test
    public void Sandbox() {
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
        System.out.println(romanToInt("I"));
    }
}
