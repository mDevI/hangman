package com.mdevi;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to develop by TDD approach.
 */
public class Hangman {

    Set<String> usedWordsSet = new HashSet<>();

    public int countAlphabet(char alphabet, String word) {
        int result = 0;
        for (char c : word.toCharArray()) {
            if (c == alphabet) {
                result++;
            }
        }
        return result;
    }

    public String fetchWord() {
        return "pizza";
    }

    public String fetchWord(int requestedLength) {
        String result = null;

        try (BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/WordSource.txt")))) {
            while ((result = br.readLine()) != null) {
                if (result.length() != requestedLength) continue;
                else if (usedWordsSet.add(result)) break;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
