package leetcode.first50;

import org.junit.Test;

import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsOfAPhoneNumber {
    private static Map<String, List<String>> map;
    static {
        map = new HashMap<String, List<String>>();
        map.put("2", Arrays.asList("a", "b", "c"));
        map.put("3", Arrays.asList("d", "e", "f"));
        map.put("4", Arrays.asList("g", "h", "i"));
        map.put("5", Arrays.asList("j", "k", "l"));
        map.put("6", Arrays.asList("m", "n", "o"));
        map.put("7", Arrays.asList("p", "q", "r", "s"));
        map.put("8", Arrays.asList("t", "u", "v"));
        map.put("9", Arrays.asList("w", "x", "y", "z"));
    }


    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        String pop = String.valueOf(digits.charAt(0));
        List<String> list = letterCombinations(digits.substring(1));

        List<String> newList = new ArrayList<String>();
        for (String p : map.get(pop)) {
            if (list.size() == 0) {
                newList.add(p);
            } else {
                for (String s : list) {
                    newList.add(p + s);
                }
            }
        }

        return newList;
    }

    @Test
    public void Sandbox() {
        System.out.println(Arrays.toString(letterCombinations("233").toArray()));
    }
}
