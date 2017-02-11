package net.whitecomet.hangman.connector.data;

/**
 * Created by white on 2017/2/11.
 */

public class ResultInfo {
    public int totalWordCount;
    public int correctWordCount;
    public int totalWrongGuessCount;
    public int score;

    @Override
    public String toString() {
        String f = "{total:%d, correct:%d, wrong: %d, score: %d}";
        return String.format(f,totalWordCount, correctWordCount, totalWrongGuessCount, score);
    }
}
