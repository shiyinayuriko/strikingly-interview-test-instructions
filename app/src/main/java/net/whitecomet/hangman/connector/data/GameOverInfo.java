package net.whitecomet.hangman.connector.data;

/**
 * Created by white on 2017/2/11.
 */

public class GameOverInfo {
    public String playerId;
    public String sessionId;
    public int totalWordCount;
    public int correctWordCount;
    public int totalWrongGuessCount;
    public int score;
    public String datetime;

    @Override
    public String toString() {
        //TODO
        return "score";
    }
}
