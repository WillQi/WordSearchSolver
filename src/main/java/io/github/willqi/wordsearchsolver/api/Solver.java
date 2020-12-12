package io.github.willqi.wordsearchsolver.api;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

/**
 * Represents an object that can be used to find words in the word search
 * @author WillQi
 */
public interface Solver {

    /**
     * Retrieve the position of a word in the word search
     * @param word The word we're looking for
     * @return the position of the word in the word search if it exists
     */
    Optional<Pair<Integer, Integer>> find (String word);

}
