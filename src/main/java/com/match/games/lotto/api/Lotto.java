package com.match.games.lotto.api;

import java.util.List;
import java.util.Optional;

import com.match.games.lotto.model.LottoResult;
import com.match.games.lotto.model.WinType;

public interface Lotto {

    public LottoResult generate(int i);

    public Optional<WinType> evaluate(List<Integer> selection, LottoResult result);
}
