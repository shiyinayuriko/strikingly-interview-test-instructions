package net.whitecomet.hangman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapProgressBar;

import net.whitecomet.hangman.R;
import net.whitecomet.hangman.connector.data.GameInfo;
import net.whitecomet.hangman.connector.data.WordInfo;

/**
 * Created by white on 2017/2/10.
 */

public class GuessView extends RelativeLayout {
    private static final String TAG = GuessView.class.getSimpleName();
    private final TextView wordview;
    private final Button guessButton;
    private final BootstrapButton resultButton;
    private final BootstrapButton skipButton;
    private final TextView wordCounter;
    private final BootstrapProgressBar wordCounterProgress;
    private final TextView wrongCounter;
    private final BootstrapProgressBar wrongCounterProgress;

    public GuessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_guess, this, true);
        wordview = (TextView) findViewById(R.id.guess_view_current_word);
        wordCounter = (TextView) findViewById(R.id.guess_view_word_count_text);
        wordCounterProgress = (BootstrapProgressBar) findViewById(R.id.guess_view_word_count_progress);
        wrongCounter = (TextView) findViewById(R.id.guess_view_wrong_count_text);
        wrongCounterProgress = (BootstrapProgressBar) findViewById(R.id.guess_view_wrong_count_progress);
        guessButton = (Button) findViewById(R.id.guess_view_guess_button);
        resultButton = (BootstrapButton) findViewById(R.id.guess_view_get_result_button);
        skipButton = (BootstrapButton) findViewById(R.id.guess_view_skip_button);
    }

    public void showInfo(GameInfo gameInfo, WordInfo wordInfo){
        guessButton.setEnabled(true);
        resultButton.setEnabled(true);
        skipButton.setEnabled(true);
        wordview.setText(wordInfo.word);
        String wordCounterStr = getResources().getString(R.string.view_counter_text,wordInfo.totalWordCount,gameInfo.numberOfWordsToGuess);
        wordCounter.setText(wordCounterStr);
        wordCounterProgress.setProgress(wordInfo.totalWordCount * 100 / gameInfo.numberOfWordsToGuess);
        String wrongCounterStr = getResources().getString(R.string.view_counter_text,wordInfo.wrongGuessCountOfCurrentWord,gameInfo.numberOfGuessAllowedForEachWord);
        wrongCounter.setText(wrongCounterStr);
        wrongCounterProgress.setProgress(wordInfo.wrongGuessCountOfCurrentWord * 100 / gameInfo.numberOfGuessAllowedForEachWord);

        if(wordInfo.wrongGuessCountOfCurrentWord >= gameInfo.numberOfGuessAllowedForEachWord){
            //TODO
        }
        if(wordInfo.totalWordCount >= gameInfo.numberOfWordsToGuess){
            //TODO
        }
    }

    public void disableStartButton() {
        guessButton.setEnabled(false);
        resultButton.setEnabled(false);
        skipButton.setEnabled(false);
    }
}
