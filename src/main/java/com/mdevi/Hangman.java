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

    Set<String> usedWordsSet = new HashSet<>();
    List<String> wordsList = new ArrayList<>();

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
        for (String result : wordsList) {
            if (result.length() != requestedLength) continue;
            else if (usedWordsSet.add(result)) return result;
        }
        return null;
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
        if (guessChar >= 'A' && guessChar <= 'Z') guessChar += 32;
        if (guessChar < 'a' || guessChar > 'z') throw new IllegalArgumentException();
        StringBuilder newClue = new StringBuilder();
        for (int i = 0; i < wordForClue.length(); i++) {
            if (guessChar == wordForClue.charAt(i) && guessChar != clue.charAt(i)) newClue.append(guessChar);
            else newClue.append(clue.charAt(i));
        }

        return newClue.toString();
    }
}
