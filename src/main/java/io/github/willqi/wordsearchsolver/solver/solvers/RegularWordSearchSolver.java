package io.github.willqi.wordsearchsolver.solver.solvers;

import io.github.willqi.wordsearchsolver.api.Solver;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * Solves rectangle word search puzzles
 * @author WillQi
 */
public class RegularWordSearchSolver implements Solver {

    protected final char[][] board;
    protected final int height;
    protected final int length;

    protected final Map<Character, Set<Pair<Integer, Integer>>> letterLocations = new HashMap<>();

    public RegularWordSearchSolver (char[][] input) {
        board = input;
        height = input.length;
        if (input.length > 0) {
            length = input[0].length;
        } else {
            length = 0;
        }
        storeLetterPositions();
    }

    @Override
    public Optional<Pair<Integer, Integer>> find(String word) {
        if (word.length() == 0 || !letterLocations.containsKey(word.charAt(0))) {
            return Optional.empty();
        }

        Set<Pair<Integer, Integer>> characterLocations = letterLocations.get(word.charAt(0));
        if (word.length() == 1) {
            return characterLocations.stream().findAny();
        }

        for (Pair<Integer, Integer> location : characterLocations) {
            if (
                    // horizontal
                    followThroughShiftGuess(word, location, new ImmutablePair<>(0, 1)) ||
                    followThroughShiftGuess(word, location, new ImmutablePair<>(0, -1)) ||
                    // vertical
                    followThroughShiftGuess(word, location, new ImmutablePair<>(1, 0)) ||
                    followThroughShiftGuess(word, location, new ImmutablePair<>(-1, 0)) ||
                    // diagonal
                    followThroughShiftGuess(word, location, new ImmutablePair<>(1, 1)) ||
                    followThroughShiftGuess(word, location, new ImmutablePair<>(1, -1)) ||
                    followThroughShiftGuess(word, location, new ImmutablePair<>(-1, -1)) ||
                    followThroughShiftGuess(word, location, new ImmutablePair<>(-1, 1))
            ) {
                return Optional.of(location);
            }
        }

        return Optional.empty();    // Technically, this should not be reachable. But should implementation of code change in the future, this is also to be safe.
    }

    /**
     * Stores the location of letters for an faster search compared to iterating through every element constantly when trying to find a word
     */
    protected void storeLetterPositions () {
        letterLocations.clear();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < length; col++) {
                char c = board[row][col];
                if (letterLocations.containsKey(c)) {
                    letterLocations.get(c).add(new ImmutablePair<>(row, col));
                } else {
                    letterLocations.put(c, new HashSet<>(Arrays.asList(new ImmutablePair<>(row, col))));
                }
            }
        }
    }

    protected boolean followThroughShiftGuess (String word, Pair<Integer, Integer> currentPosition, Pair<Integer, Integer> shift) {
        int currentRow = currentPosition.getLeft();
        int currentCol = currentPosition.getRight();

        if (currentRow < 0 || currentRow >= height || currentCol < 0 || currentCol >= length) {
            return false;   // Out of bounds
        }
        if (board[currentRow][currentCol] != word.charAt(0)) {
            return false;
        }
        if (word.length() == 1) {
            return true;
        }

        int newRow = currentRow + shift.getLeft();
        int newCol = currentCol + shift.getRight();
        return followThroughShiftGuess(word.substring(1), new ImmutablePair<>(newRow, newCol), shift);
    }

}
