package games.card;

import java.util.*;

public enum Ranks {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

    private int value;
    private static Map<Integer, List<Ranks>> valueMap;
    private static Random rand = new Random();

    Ranks(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Ranks fromCode(String code) {
        code = code.trim();
        if (code.equalsIgnoreCase("A")) {
            return Ranks.ACE;
        }

        return fromValue(Integer.valueOf(code));
    }
    public static Ranks fromValue(int value) {
        if (valueMap == null) {
            valueMap = new HashMap<Integer, List<Ranks>>();
            for(Ranks ranks : Ranks.values()) {
                List<Ranks> v = valueMap.get(ranks.value);
                if (v == null) {
                    valueMap.put(ranks.value, new ArrayList<Ranks>(Arrays.asList(ranks)));
                } else {
                    v.add(ranks);
                }
            }
        }
        List<Ranks> v = valueMap.get(value);
        return v.size() == 1 ? v.get(0) : v.get(rand.nextInt(v.size()));
    }

    public String getCode() {
        return this == ACE ? "A" : String.valueOf(value);
    }

    public String toString() {
        return this.name() + "(" + value + ")";
    }
}
