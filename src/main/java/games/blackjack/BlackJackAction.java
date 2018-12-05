package games.blackjack;

import java.util.HashMap;
import java.util.Map;

public enum BlackJackAction {
    HIT("H"), STAND("S"), DOUBLE_DOWN_HIT("Dh"), DOUBLE_DOWN_STAND("Ds"), SPLIT("SP");

    private String code;
    private static Map<String, BlackJackAction> codeMap;

    BlackJackAction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BlackJackAction fromCode(String code) {
        if (codeMap == null) {
            codeMap = new HashMap<String, BlackJackAction>();
            for(BlackJackAction action : BlackJackAction.values()) {
                codeMap.put(action.code, action);
            }
        }
        BlackJackAction action = codeMap.get(code);
        if (action == null) {
            throw new RuntimeException("unknown BlackJack action code: " + code);
        }
        return action;
    }
}
