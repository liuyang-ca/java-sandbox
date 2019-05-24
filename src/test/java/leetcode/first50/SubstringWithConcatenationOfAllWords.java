package leetcode.first50;

import leetcode.MyLogger;
import org.junit.Test;

import java.util.*;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words
 * exactly once and without any intervening characters.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "aabarfoothefoobarman",
 * words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 * <p>
 * Input:
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * Output: []
 */
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s.isEmpty() || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int totalLength = wordLength * words.length;
        if (s.length() < totalLength) {
            return result;
        }

        Set<String> set = new HashSet<>();
        set.addAll(findAll(Arrays.asList(words)));

        int len = s.length() - totalLength;
        for (int i = 0; i <= len; i++) {
            if(set.contains(s.substring(i, i+totalLength))) {
                result.add(i);
            }
        }
        return result;
    }

    private List<String> findAll(List<String> words) {
        List<String> result = new ArrayList<>();
        if(words.size() == 1) {
            result.add(words.get(0));
        } else {
            for(int i=0; i< words.size(); i++) {
                List<String> list = new ArrayList<>(words);
                String tmp = list.get(i);
                list.remove(i);
                List<String> finds = findAll(list);
                for(String s : finds) {
                    result.add(tmp+s);
                }
            }
        }

        return result;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s.isEmpty() || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int totalLength = 0;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            Integer tmp = map.get(word);
            map.put(word, tmp == null ? 1 : tmp + 1);
            totalLength = totalLength + word.length();
        }

        if (s.length() < totalLength) {
            return result;
        }

        int len = s.length() - totalLength;
        for (int i = 0; i <= len; i++) {
            String tmp = s.substring(i, i+wordLength);
            if (map.get(tmp) != null) {
                Map<String, Integer> tmpMap = new HashMap<>(map);
                MyLogger.LOGGER.debug("i = {}, tmp = {}", i, tmp);
                int j = i;
                while (tmpMap.get(tmp) != null) {
                    MyLogger.LOGGER.debug("j = {}, tmp = {}", j, tmp);
                    int count = tmpMap.get(tmp);
                    if (count == 1) {
                        tmpMap.remove(tmp);
                    } else {
                        tmpMap.put(tmp, count - 1);
                    }
                    j = j + wordLength;
                    if (j + wordLength <= s.length()) {
                        tmp = s.substring(j, j + wordLength);
                    } else {
                        tmp = "";
                    }
                }
                MyLogger.LOGGER.debug("");
                if (tmpMap.isEmpty()) {
                    result.add(i);
                }
            }
        }

        return result;
    }

    @Test
    public void Sandbox() {
        // a a b a r f o o t h e  f  o  o  b  a  r  m  a  n
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
        System.out.println(Arrays.toString(findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}).toArray()));
        //System.out.println(findAll(Arrays.asList("a", "b", "c", "c")));
    }
}
