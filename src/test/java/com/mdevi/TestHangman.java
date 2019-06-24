package com.mdevi;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Hangman facility methods.")
public class TestHangman {

    Random random;
    int requestedLength;
    Hangman hangman;

    @BeforeEach
    @DisplayName("Set up test conditionals.")
    public void setupTest() {
        random = new Random();
        requestedLength = random.nextInt(6) + 5;
        hangman = new Hangman();
        hangman.loadWords();
    }

    @AfterEach
    public void tearDownTest() {
        random = null;
        requestedLength = 0;
        hangman = null;
    }


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
    public void testLengthFetchedWordRandom() {

        requestedLength = random.nextInt(6) + 5;
        String word = hangman.fetchWord(requestedLength);

        assertTrue(word.length() == requestedLength);
    }

    @Test
    public void testUniquenessFetchedWord() {
        Set<String> usedWordsSet = new HashSet<>();
        int round = 0;
        String word = null;
        while (round < 4) {
            requestedLength = random.nextInt(6) + 5;
            word = hangman.fetchWord(requestedLength);
            System.out.println(requestedLength + " " + round + " " + word);
            round++;

            assertTrue(usedWordsSet.add(word));
        }
    }

    @Test
    public void testFetchClueBeforeAnyGuess() {
        String clue = hangman.fetchClue("pizza");

        assertEquals("-----", clue);
    }

    @Test
    public void testFetchClueAfterCorrectGuess() {
        // arrange
        String clue = hangman.fetchClue("pizza");
        // act
        String newClue = hangman.fetchClue("pizza", clue, 'a');
        // assert
        assertEquals("----a", newClue);
    }

    @Test
    public void testFetchClueAfterIncorrectGuess() {
        // arrange
        String clue = hangman.fetchClue("pizza");
        // act
        String newClue = hangman.fetchClue("pizza", clue, 'x');
        // assert
        assertEquals("-----", newClue);
    }

    @Test
    public void testWhenInvalidGuessThenFetchClueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> hangman.fetchClue("pizza", "-----", '1'));
    }

}
