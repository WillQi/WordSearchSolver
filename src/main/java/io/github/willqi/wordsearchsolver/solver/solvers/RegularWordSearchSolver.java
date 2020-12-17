package io.github.willqi.wordsearchsolver.solver.solvers;

import io.github.willqi.wordsearchsolver.api.Solver;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

/**
 * Solves rectangle word search puzzles
 * @author WillQi
 */
public class RegularWordSearchSolver implements Solver {

    protected final char[][] board;

    public RegularWordSearchSolver (char[][] input) {
        board = input;
    }

    @Override
    public Optional<Pair<Integer, Integer>> find(String word) {
        return Optional.empty();
    }

}
