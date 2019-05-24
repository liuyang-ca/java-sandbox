package leetcode.first50;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 * Example 1:
 *
 * Input: 3
 * Output: "III"
 * Example 2:
 *
 * Input: 4
 * Output: "IV"
 * Example 3:
 *
 * Input: 9
 * Output: "IX"
 * Example 4:
 *
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * Example 5:
 *
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class IntegerToRoman {
    @Test
    public void Sandbox() {
        System.out.println(intToRoman(10));
      System.out.println(intToRoman(50));
        System.out.println(intToRoman(100));
        System.out.println(intToRoman(2300));
//        System.out.println(intToRoman(1994));
//        System.out.println(intToRoman(3999));
    }


    private static Map<Integer, String> tenMap = new HashMap<Integer, String>();
    private static Map<Integer, String> fiveMap = new HashMap<Integer, String>();
    static {
        tenMap.put(1, "I");
        tenMap.put(10, "X");
        tenMap.put(100, "C");
        tenMap.put(1000, "M");

        fiveMap.put(1, "V");
        fiveMap.put(10, "L");
        fiveMap.put(100, "D");
    }


    public String intToRoman2(int num) {
        int[] ints = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] symbs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<ints.length; i++){
            while(num >= ints[i]){
                num = num-ints[i];
                sb.append(symbs[i]);
            }
        }
        return sb.toString();
    }

    public String intToRoman(int num) {
        String result = "";
        int multiplier = 1;
        while(num != 0) {
            int lastDigit = num % 10;
            num = num / 10;

            if (lastDigit != 0) {
                result = digitToRoman(lastDigit, multiplier) + result;
            }

            multiplier = multiplier * 10;
        }
        return result;
    }

    // multiplier == 1 || 10 || 100 || 1000
    private String digitToRoman(int singleDigit, int multiplier) {
        StringBuilder sb = new StringBuilder();
        if(singleDigit > 0 && singleDigit < 4) {
            for(int i = 0; i< singleDigit; i++) {
                sb = sb.append(tenMap.get(multiplier));
            }
        } else if (singleDigit == 4) {
            sb = sb.append(tenMap.get(multiplier)).append(fiveMap.get(multiplier));
        } else if (singleDigit == 5) {
            sb = sb.append(fiveMap.get(multiplier));
        } else if (singleDigit > 5 && singleDigit < 9) {
            sb = sb.append(fiveMap.get(multiplier));
            for(int i = 5; i< singleDigit; i++) {
                sb = sb.append(tenMap.get(multiplier));
            }
        } else if (singleDigit == 9) {
            sb = sb.append(tenMap.get(multiplier)).append(tenMap.get(multiplier*10));
        } else {
            throw new RuntimeException("singleDigit should between 1 and 9");
        }

        return sb.toString();
    }
}
