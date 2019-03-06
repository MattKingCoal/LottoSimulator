package com.match.games.lotto.model;

import java.util.List;

public class LottoResult {

    private List<Integer> numbers;
    private Integer bonus;

    public LottoResult(List<Integer> numbers, Integer bonus) {
        this.numbers = numbers;
        this.bonus = bonus;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "LottoResult [Numbers " + numbers + " Bonus ball " + bonus + "]";
    }
}
