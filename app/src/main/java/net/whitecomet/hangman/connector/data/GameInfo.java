package net.whitecomet.hangman.connector.data;

/**
 * Created by white on 2017/2/11.
 */

public class GameInfo {
    public int numberOfWordsToGuess;
    public int numberOfGuessAllowedForEachWord;

    @Override
    public String toString() {
        return "{numberOfWordsToGuess:" + numberOfWordsToGuess + ", numberOfGuessAllowedForEachWord:" + numberOfGuessAllowedForEachWord + "}";
    }
}