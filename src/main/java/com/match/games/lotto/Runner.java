package com.match.games.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

public class Runner {

    static Logger log = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        log.info("Starting............");
        List<Integer> usersSelection = new ArrayList<>();

        for (String s : args) {
            usersSelection.add(Integer.valueOf(s));
        }
        log.info("Lucky numbers: " + usersSelection);

        List<WinType> wins = new ArrayList<>();
        int plays = Integer.valueOf(System.getProperty("lotto.plays", "2000"));
        for (int i = 1; i <= plays; i++) {
            LottoResult result = LottoEngine.generate(LottoConstants.DEFAULTMAXVALUE);
            Optional<WinType> owt = LottoEngine.evaluate(usersSelection, result);
            if (owt.isPresent()) {
                WinType wt = owt.get();
                log.info(String.format("YES, a WIN!!, spin %d Won %s", i, wt));
                wins.add(wt);
            }
        }

        log.info("Total wins: " + wins.size());

        Map<WinType, Long> countBreakdown = wins.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        log.info("Breakdown: " + countBreakdown);

        int prizeMoney = 0;
        for (WinType wt : countBreakdown.keySet()) {
            long amount = countBreakdown.get(wt) * LottoConstants.PRIZEMAP.get(wt);
            log.info(countBreakdown.get(wt) + " * " + wt + " = €" + amount);
            prizeMoney += amount;
        }

        log.info(String.format("Total prizemoney: €%d", prizeMoney));
    }
}
