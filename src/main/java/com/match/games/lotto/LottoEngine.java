package com.match.games.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;

public class LottoEngine {

    static Logger log = Logger.getLogger(LottoEngine.class);

    public static LottoResult generate(int max) {
        log.debug("Simulating lotto result");
        if (max < 7)
            throw new IllegalArgumentException("Size can't be less than 7");

        List<Integer> lottoBalls = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            lottoBalls.add(new Integer(i));
        }
        List<Integer> firstSix = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, max - i);
            int lottoBall = lottoBalls.get(randomIndex);
            firstSix.add(lottoBall);
            lottoBalls.remove(randomIndex);
            log.debug(String.format("Ball %d is %s", i + 1, lottoBall));
        }
        Collections.sort(firstSix);
        // Bonus
        int randomIndex = ThreadLocalRandom.current().nextInt(0, max - 6);
        int bonusBall = lottoBalls.get(randomIndex);
        log.debug(String.format("Bonus ball is %d", bonusBall));
        LottoResult result = new LottoResult(firstSix, bonusBall);
        log.info(result);
        return result;
    }

    public static Optional<WinType> evaluate(List<Integer> selection, LottoResult result) {
        if (selection.size() != 6) {
            throw new IllegalArgumentException("Only 6 numbers can be used");
        }
        log.debug("Evaluating result...." + result);
        int matches = 0;
        boolean bonusMatched = false;
        for (Integer i : selection) {
            if (result.getNumbers().contains(i))
                matches++;
            if (i.equals(result.getBonus()))
                bonusMatched = true;
        }
        log.debug(String.format("Matches: %d, Bonus: %s", matches, bonusMatched));

        WinType wt = null;
        switch (matches) {
            case 6:
                wt = WinType.MATCH6;
                break;
            case 5:
                if (bonusMatched) {
                    wt = WinType.MATCH5PLUS;
                    break;
                } else {
                    wt = WinType.MATCH5;
                    break;
                }
            case 4:
                if (bonusMatched) {
                    wt = WinType.MATCH4PLUS;
                    break;
                } else {
                    wt = WinType.MATCH4;
                    break;
                }
            case 3:
                if (bonusMatched) {
                    wt = WinType.MATCH3PLUS;
                    break;
                } else {
                    wt = WinType.MATCH3;
                    break;
                }
        }
        log.info(String.format("Win type: %s", wt));
        Optional<WinType> owt;
        if (wt == null) {
            owt = Optional.empty();
        } else {
            owt = Optional.of(wt);
        }
        return owt;
    }
}
