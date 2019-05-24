package leetcode;



import java.util.*;

/**
 * Create a timebased key-value store class TimeMap, that supports two operations.
 *
 * 1. set(string key, string value, int timestamp)
 *
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 *
 * Example 1:
 *
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 *
 * Example 2:
 *
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 *
 *
 * Note:
 *
 * All key/value strings are lowercase.
 * All key/value strings have length in the range [1, 100]
 * The timestamps for all TimeMap.set operations are strictly increasing.
 * 1 <= timestamp <= 10^7
 * TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 */
public class TimeBasedKeyValueStore {
    private Map<String, TreeMap<Integer, String>> map = new HashMap<>();

    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore() {
    }

    public void set1(String key, String value, int timestamp) {
        if(map.get(key) == null) {
            map.put(key, new TreeMap<>());
        }

        map.get(key).put(timestamp, value);
    }

    public String get1(String key, int timestamp) {
        TreeMap<Integer, String> treeMap = map.get(key);
        if(treeMap == null) {
            return "";
        }

        Integer floorKey = treeMap.floorKey(timestamp);
        return floorKey == null ? "" : treeMap.get(floorKey);
    }

    private Map<String, List<IntStrPair>> m = new HashMap<>();

    class IntStrPair implements Comparable {
        int timestamp;
        String value;

        public IntStrPair(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            IntStrPair other = (IntStrPair)obj;
            return other.timestamp == timestamp;
        }

        @Override
        public int compareTo(Object o) {
            IntStrPair other = (IntStrPair)o;
            return Integer.compare(timestamp, other.timestamp);
        }
    }

    public void set(String key, String value, int timestamp) {
        if(m.get(key) == null) {
            m.put(key, new ArrayList<>());
        }

       m.get(key).add(new IntStrPair(timestamp, value));
    }

    public String get(String key, int timestamp) {
        List<IntStrPair> list = m.get(key);
        if (list == null) {
            return "";
        }

        List<IntStrPair> A = m.get(key);
        int i = Collections.binarySearch(A, new IntStrPair(timestamp, "*"),
                (a, b) -> Integer.compare(a.timestamp, b.timestamp));

        if (i >= 0)
            return A.get(i).value;
        else if (i == -1)
            return "";
        else
            return A.get(-i-2).value;
    }


}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

