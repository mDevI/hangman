package com.mdevi;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test Hangman facility methods.")
public class TestHangman {


    @Test
    public void testAllShouldBeOk() {

        assertTrue(1 > 0);
    }

    @Test
    public void testAlphabetCountInAWord() {
        String word = "pizza";
        char alphabet = 'a';

        Hangman hangman = new Hangman();

        int count = hangman.countAlphabet(alphabet, word);

        assertEquals(1, count);

    }

    @Test
    public void testLengthFetchedWord() {
        Hangman hangman = new Hangman();
        String word = hangman.fetchWord();

        assertTrue(word.length() == 5);

    }

    @Test
    public void testLengthFetchedWordRandom() {
        Random random = new Random();
        Hangman hangman = new Hangman();
        int requestedLength = random.nextInt(6) + 5;
        String word = hangman.fetchWord(requestedLength);

        assertTrue(word.length() == requestedLength);
    }

    @Test
    public void testUniquenessFetchedWord() {
        Random random = new Random();
        int requestedLength = 0;
        Set<String> usedWordsSet = new HashSet<>();
        int round = 0;
        String word = null;
        Hangman hangman = new Hangman();

        while (round < 100) {
            requestedLength = random.nextInt(6) + 5;
            word = hangman.fetchWord(requestedLength);
            System.out.println(requestedLength + " " + round + " " + word);
            round++;

            assertTrue(usedWordsSet.add(word));
        }
    }
}
