package com.match.games.lotto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class LottoMachineTest {

    public static int defaultlottoSize = 42;

    @Test
    public void generateReturnsResult() {
        LottoResult result = LottoMachine.getInstance().generate(12);
        assertTrue(result.getNumbers().size() == 6);
        assertTrue(result.getBonus() != null);
    }

    @Test
    public void generateRetrunsResultWithinRange() {
        int max = 42;
        LottoResult result = LottoMachine.getInstance().generate(max);
        List<Integer> numbers = result.getNumbers();
        for (Integer i : numbers) {
            assertTrue(i <= max);
        }
    }

    @Test
    public void generateReturnsNoDuplicates() {
        LottoResult result = LottoMachine.getInstance().generate(7);
        LinkedHashSet<Integer> lhs = new LinkedHashSet<>();
        // Duplicates won't be copied in so size should be the same
        lhs.addAll(result.getNumbers());
        assertTrue(lhs.size() == result.getNumbers().size());
    }

    @Test
    public void evaluateMatch6Wins() {
        List<Integer> selection = Arrays.asList(new Integer[] { 4, 8, 15, 16, 23, 42 });
        LottoResult lr = new LottoResult(selection, 12);

        Optional<WinType> owt = LottoMachine.getInstance().evaluate(selection, lr);
        assertEquals(WinType.MATCH6, owt.get());
    }

    @Test
    public void evaluateMatch5PlusWins() {
        List<Integer> selection = Arrays.asList(new Integer[] { 4, 8, 12, 16, 23, 42 });
        List<Integer> resultSix = Arrays.asList(new Integer[] { 4, 8, 9, 16, 23, 42 });
        LottoResult lr = new LottoResult(resultSix, 12);

        Optional<WinType> owt = LottoMachine.getInstance().evaluate(selection, lr);
        assertEquals(WinType.MATCH5PLUS, owt.get());
    }

    @Test
    public void evaluateMatch2PlusDoesntWin() {
        List<Integer> selection = Arrays.asList(new Integer[] { 4, 8, 12, 16, 23, 42 });
        List<Integer> resultSix = Arrays.asList(new Integer[] { 4, 5, 15, 19, 41, 42 });
        LottoResult lr = new LottoResult(resultSix, 12);

        Optional<WinType> owt = LottoMachine.getInstance().evaluate(selection, lr);
        assertFalse(owt.isPresent());
    }
}
