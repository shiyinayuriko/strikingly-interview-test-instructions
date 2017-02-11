package net.whitecomet.hangman.connector.data;

/**
 * Created by white on 2017/2/11.
 */

public class WordInfo {
    public String word;
    public int totalWordCount;
    public int wrongGuessCountOfCurrentWord;

    @Override
    public String toString() {
        String f = "{word:%s,count:%d,wrong:%d}";
        return String.format(f, word, totalWordCount, wrongGuessCountOfCurrentWord);
    }
}