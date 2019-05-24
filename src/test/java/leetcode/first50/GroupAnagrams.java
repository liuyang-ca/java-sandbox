package leetcode.first50;

import org.junit.Test;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
public class GroupAnagrams {
    private Set<String> findAnagrams(String str) {
        Set<String> set = new HashSet<>();
        boolean[] isVisited = new boolean[str.length()];
        for(int i=0; i<isVisited.length; i++) {
            isVisited[i] = false;
        }
        StringBuilder sb = new StringBuilder();
        findAnagramsDSF(set, isVisited, sb, str);
        return set;
    }

    private void findAnagramsDSF(Set<String> set, boolean[] isVisited, StringBuilder sb, String str) {
        if(sb.length() == str.length()) {
            set.add(sb.toString());
        }

        for(int i=0; i<str.length(); i++) {
            if(isVisited[i]) {
                continue;
            }

            if(i>0 && str.charAt(i) == str.charAt(i-1) && !isVisited[i-1]) {
                continue;
            }

            isVisited[i] = true;
            sb.append(str.charAt(i));
            findAnagramsDSF(set, isVisited, sb, str);
            isVisited[i] = false;
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String sorted = new String(array);
            List<String> list = map.get(sorted);
            if (list == null) {
                list = new ArrayList<>();
                list.add(str);
                map.put(sorted, list);
            } else {
                list.add(str);
            }
        }
        return new ArrayList<>(map.values());
    }

    @Test
    public void Sandbox() {
        System.out.println(findAnagrams("aab"));
        //ystem.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
