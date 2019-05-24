package leetcode.first50;

import org.junit.Test;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class ZigZagConversion {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        int len = s.length();
        char[][] array = new char[numRows][len];
        int k=0;
        int rowMinus1 = numRows - 1;
        for(int i=0; i<len; i++) {
            for(int j=0; j<numRows; j++) {
                if (k < len && (i % rowMinus1 == 0 || ((j + i) % rowMinus1 == 0))) {
                    array[j][i] = s.charAt(k);
                    k++;
                }
                else {
                    array[j][i] = " ".charAt(0);
                }
            }
        }

        String result = "";
        for(int i=0; i<numRows; i++) {
            result = result + new String(array[i]).replaceAll(" ", "");
            //result = result + new String(array[i]).trim() + "\n";
        }

        return result.trim();
    }

    @Test
    public void Sandbox() {
        System.out.println(convert("PAYPALISHIRING", 8));
    }
}
