package io.github.willqi.wordsearchsolver.solver.solvers;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRegularWordSearchSolver {

    private static final char[][] TEST_BOARD = {
            {'a', 'b', 'c', 'd', 'e'},
            {'f', 'g', 'h', 'i', 'j'},
            {'k', 'l', 'm', 'n', 'o'},
            {'p', 'q', 'r', 's', 't'},
            {'u', 'v', 'w', 'x', 'y'}
    };

    @Test
    public void shouldFindForwardHorizontalWord () {
        RegularWordSearchSolver solver = new RegularWordSearchSolver(TEST_BOARD);
        Optional<Pair<Integer, Integer>> test = solver.find("bcd");
        assertEquals(true, test.isPresent(), "word exists in board");
        assertEquals(new ImmutablePair<>(0, 1), test.get(), "word has correct coordinates");
    }

    @Test
    public void shouldFindBackwardsHorizontalWord () {
        RegularWordSearchSolver solver = new RegularWordSearchSolver(TEST_BOARD);
        Optional<Pair<Integer, Integer>> test = solver.find("nml");
        assertEquals(true, test.isPresent(), "word exists in board");
        assertEquals(new ImmutablePair<>(2, 3), test.get(), "word has correct coordinates");
    }

    @Test
    public void shouldFindDownwardVerticalWord () {
        RegularWordSearchSolver solver = new RegularWordSearchSolver(TEST_BOARD);
        Optional<Pair<Integer, Integer>> test = solver.find("bgl");
        assertEquals(true, test.isPresent(), "word exists in board");
        assertEquals(new ImmutablePair<>(0, 1), test.get(), "word has correct coordinates");
    }

    @Test
    public void shouldFindUpwardVerticalWord () {
        RegularWordSearchSolver solver = new RegularWordSearchSolver(TEST_BOARD);
        Optional<Pair<Integer, Integer>> test = solver.find("kfa");
        assertEquals(true, test.isPresent(), "word exists in board");
        assertEquals(new ImmutablePair<>(2, 0), test.get(), "word has correct coordinates");
    }

    @Test
    public void shouldFindTopLeftToBottomRightForwardDiagonalWord () {
        RegularWordSearchSolver solver = new RegularWordSearchSolver(TEST_BOARD);
        Optional<Pair<Integer, Integer>> test = solver.find("lrx");
        assertEquals(true, test.isPresent(), "word exists in board");
        assertEquals(new ImmutablePair<>(2, 1), test.get(), "word has correct coordinates");
    }

    @Test
    public void shouldFindTopLeftToBottomRightBackwardsDiagonalWord () {
        RegularWordSearchSolver solver = new RegularWordSearchSolver(TEST_BOARD);
        Optional<Pair<Integer, Integer>> test = solver.find("ysm");
        assertEquals(true, test.isPresent(), "word exists in board");
        assertEquals(new ImmutablePair<>(4, 4), test.get(), "word has correct coordinates");
    }

    @Test
    public void shouldFindTopRightToBottomLeftForwardDiagonalWord () {
        RegularWordSearchSolver solver = new RegularWordSearchSolver(TEST_BOARD);
        Optional<Pair<Integer, Integer>> test = solver.find("jnr");
        assertEquals(true, test.isPresent(), "word exists in board");
        assertEquals(new ImmutablePair<>(1, 4), test.get(), "word has correct coordinates");
    }

    @Test
    public void shouldFindTopRightToBottomLeftBackwardsDiagonalWord () {
        RegularWordSearchSolver solver = new RegularWordSearchSolver(TEST_BOARD);
        Optional<Pair<Integer, Integer>> test = solver.find("wso");
        assertEquals(true, test.isPresent(), "word exists in board");
        assertEquals(new ImmutablePair<>(4, 2), test.get(), "word has correct coordinates");
    }

    @Test
    public void shouldNotFindAnyWord () {
        RegularWordSearchSolver solver = new RegularWordSearchSolver(TEST_BOARD);
        Optional<Pair<Integer, Integer>> test = solver.find("egg");
        assertEquals(false, test.isPresent(), "Word should not be found on board");
    }


}
