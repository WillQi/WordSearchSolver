package io.github.willqi.wordsearchsolver.solver;

import io.github.willqi.wordsearchsolver.solver.solvers.RegularWordSearchSolver;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WordSearchProgram {

    public static void main (String[] args) {
        Options cliOptions = new Options()
                .addRequiredOption("ws", "wordsearch", true, "Path to the word search file")
                .addRequiredOption("wl", "wordlist", true, "Path to the word list file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine;

        try {
            cmdLine = parser.parse(cliOptions, args);
        } catch (ParseException exception) {
            new HelpFormatter()
                    .printHelp("WordSearch", cliOptions);
            return;
        }

        ArrayList<String> wordSearchFileLines;
        List<String> wordListFileLines;

        try {
            wordSearchFileLines = getFileContent(new File(cmdLine.getOptionValue("ws")));
        } catch (FileNotFoundException exception) {
            System.out.println("The word search file does not exist!");
            return;
        } catch (IOException exception) {
            System.out.println("An error has occurred while reading the word search file!");
            exception.printStackTrace();
            return;
        }

        try {
            wordListFileLines = getFileContent(new File(cmdLine.getOptionValue("wl")));
        } catch (FileNotFoundException exception) {
            System.out.println("The word list file does not exist!");
            return;
        } catch (IOException exception) {
            System.out.println("An error has occurred while reading the word list file!");
            exception.printStackTrace();
            return;
        }

        RegularWordSearchSolver solver = new RegularWordSearchSolver(parseInputList(wordSearchFileLines));
        for (String word : wordListFileLines) {
            Optional<Pair<Integer, Integer>> coordinates = solver.find(word);
            if (coordinates.isPresent()) {
                System.out.println(String.format("%s was found at (%s, %s)", word, coordinates.get().getLeft(), coordinates.get().getRight()));
            } else {
                System.out.println(String.format("%s was not found.", word));
            }
        }


    }

    private static ArrayList<String> getFileContent (File file) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader stream = null;

        try {
            stream = new BufferedReader(new FileReader(file));
            String currentLine = stream.readLine();
            while (currentLine != null) {
                lines.add(currentLine);
                currentLine = stream.readLine();
            }
        } catch (IOException exception) {
            if (stream != null) {
                stream.close();
            }
            throw exception;
        }
        stream.close();

        return lines;
    }

    private static char[][] parseInputList (ArrayList<String> lines) {

        int rows = lines.size();
        if (rows == 0) {
            return new char[0][];
        }
        int length = lines.get(0).length();

        char[][] board = new char[rows][length];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < length; col++) {
                board[row][col] = lines.get(row).charAt(col);
            }
        }

        return board;

    }

}
