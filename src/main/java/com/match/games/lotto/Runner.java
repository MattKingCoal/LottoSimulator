package com.match.games.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.match.games.lotto.api.Lotto;
import com.match.games.lotto.model.LottoResult;
import com.match.games.lotto.model.WinType;

public class Runner {

    static Logger log = Logger.getLogger(Runner.class);

    @Autowired
    private Lotto lotto;

    public void go(String[] args) {
        log.info("Starting............");
        log.info("Creating machine....");

        List<Integer> usersSelection = new ArrayList<>();

        for (String s : args) {
            usersSelection.add(Integer.valueOf(s));
        }
        log.info("Lucky numbers: " + usersSelection);

        List<WinType> wins = new ArrayList<>();
        int plays = Integer.valueOf(System.getProperty("lotto.plays", "2000"));
        for (int i = 1; i <= plays; i++) {
            LottoResult result = lotto.generate(LottoConstants.DEFAULTMAXVALUE);
            Optional<WinType> owt = lotto.evaluate(usersSelection, result);
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

    public Lotto getLotto() {
        return lotto;
    }

    public void setLotto(Lotto lotto) {
        this.lotto = lotto;
    }
}
