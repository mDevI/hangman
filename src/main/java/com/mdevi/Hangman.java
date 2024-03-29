package com.mdevi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class to develop by TDD approach.
 */
public class Hangman {

    public static final int MAX_TRIALS = 10;
    public int remainingTrials;
    public int score;
    Set<String> usedWordsSet = new HashSet<>();
    List<String> wordsList = new ArrayList<>();
    MockScoreDB mockScoreDB;

    public Hangman(MockScoreDB mockScoreDB) {
        this.mockScoreDB = mockScoreDB;
    }

    public Hangman() {
    }

    public int countAlphabet(char alphabet, String word) {
        int result = 0;
        for (char c : word.toCharArray()) {
            if (c == alphabet) {
                result++;
            }
        }
        return result;
    }


    public String fetchWord(int requestedLength) {
        String result = null;
        remainingTrials = MAX_TRIALS;
        for (String word : wordsList) {
            if (word.length() != requestedLength) continue;
            else if (usedWordsSet.add(word)) {
                result = word;
                break;
            }
        }
        return result;
    }

    public void loadWords() {
        String word = null;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/WordSource.txt"))) {
            while ((word = br.readLine()) != null) {
                wordsList.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String fetchClue(String wordForClue) {

        StringBuilder clue = new StringBuilder();
        clue.append("-".repeat(wordForClue.length()));

        return clue.toString();
    }

    public String fetchClue(String wordForClue, String clue, char guessChar) {
        remainingTrials--;
        if (guessChar >= 'A' && guessChar <= 'Z') guessChar += 32;
        if (guessChar < 'a' || guessChar > 'z') throw new IllegalArgumentException("Invalid character");
        StringBuilder newClue = new StringBuilder();
        for (int i = 0; i < wordForClue.length(); i++) {
            if (guessChar == wordForClue.charAt(i) && guessChar != clue.charAt(i)) {
                newClue.append(guessChar);
                score += (double) MAX_TRIALS / wordForClue.length();
            }
            else newClue.append(clue.charAt(i));
        }

        return newClue.toString();
    }

    public boolean saveWordScore(String word, double score) {
        return mockScoreDB.writeScoreDB(word, score);
    }
}
