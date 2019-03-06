package com.match.games.lotto;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.match.games.lotto.model.LottoResult;

public class LottoResultTest {

    List<Integer> numbers = Arrays.asList(new Integer[] { 1, 5, 6, 15, 20, 32 });

    @Test
    public void testToString() {
        LottoResult result = new LottoResult(numbers, 16);
        assertTrue(result.toString().contains("Numbers"));
        assertTrue(result.toString().contains("Bonus ball"));
    }

    @Test
    public void stringManip() {
        String str = "colm@hpe.com";
        str = StringUtils.remove(str, "-");
        str = StringUtils.remove(str, "@hpe.com");
        String[] splits = StringUtils.split(str, ".");
        String s = ((splits.length > 1) ? (splits[0] + " " + splits[1]) : splits[0]);
        System.out.println(s);
    }
}
