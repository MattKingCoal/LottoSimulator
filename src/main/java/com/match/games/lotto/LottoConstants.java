package com.match.games.lotto;

import java.util.HashMap;
import java.util.Map;

public class LottoConstants {

    public static final int DEFAULTMAXVALUE = 42;
    public static final Map<WinType, Integer> PRIZEMAP = new HashMap<WinType, Integer>();

    static {
        PRIZEMAP.put(WinType.MATCH3, 9);
        PRIZEMAP.put(WinType.MATCH3PLUS, 27);
        PRIZEMAP.put(WinType.MATCH4, 53);
        PRIZEMAP.put(WinType.MATCH4PLUS, 190);
        PRIZEMAP.put(WinType.MATCH5, 1500);
        PRIZEMAP.put(WinType.MATCH5PLUS, 71000);
        PRIZEMAP.put(WinType.MATCH6, 5000000);
    }
}
