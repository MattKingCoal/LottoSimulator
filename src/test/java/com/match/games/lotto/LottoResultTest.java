package com.match.games.lotto;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LottoResultTest {

    List<Integer> numbers = Arrays.asList(new Integer[] { 1, 5, 6, 15, 20, 32 });

    @Test
    public void testToString() {
        LottoResult result = new LottoResult(numbers, 16);
        assertTrue(result.toString().contains("Numbers"));
        assertTrue(result.toString().contains("Bonus ball"));
    }

}
