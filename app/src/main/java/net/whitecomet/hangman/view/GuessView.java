package net.whitecomet.hangman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private final BootstrapButton resultButton;
    private final BootstrapButton skipButton;
    private final TextView wordCounter;
    private final BootstrapProgressBar wordCounterProgress;
    private final TextView wrongCounter;
    private final BootstrapProgressBar wrongCounterProgress;
    private final LinearLayout guessButtons;

    public GuessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_guess, this, true);
        wordview = (TextView) findViewById(R.id.guess_view_current_word);
        wordCounter = (TextView) findViewById(R.id.guess_view_word_count_text);
        wordCounterProgress = (BootstrapProgressBar) findViewById(R.id.guess_view_word_count_progress);
        wrongCounter = (TextView) findViewById(R.id.guess_view_wrong_count_text);
        wrongCounterProgress = (BootstrapProgressBar) findViewById(R.id.guess_view_wrong_count_progress);
        guessButtons = (LinearLayout)findViewById(R.id.guess_view_guess_keys);
        resultButton = (BootstrapButton) findViewById(R.id.guess_view_get_result_button);
        skipButton = (BootstrapButton) findViewById(R.id.guess_view_skip_button);
    }

    public void showInfo(GameInfo gameInfo, WordInfo wordInfo){
        resultButton.setEnabled(true);
        skipButton.setEnabled(true);
        wordview.setText(wordInfo.word);
        String wordCounterStr = getResources().getString(R.string.view_counter_text,wordInfo.totalWordCount,gameInfo.numberOfWordsToGuess);
        wordCounter.setText(wordCounterStr);
        wordCounterProgress.setProgress(wordInfo.totalWordCount * 100 / gameInfo.numberOfWordsToGuess);
        String wrongCounterStr = getResources().getString(R.string.view_counter_text,wordInfo.wrongGuessCountOfCurrentWord,gameInfo.numberOfGuessAllowedForEachWord);
        wrongCounter.setText(wrongCounterStr);
        wrongCounterProgress.setProgress(wordInfo.wrongGuessCountOfCurrentWord * 100 / gameInfo.numberOfGuessAllowedForEachWord);

        if(wordInfo.wrongGuessCountOfCurrentWord >= gameInfo.numberOfGuessAllowedForEachWord || !wordInfo.word.contains("*")){
            setKeyboardEnabled(false);
        }else{
            setKeyboardEnabled(true);
        }
        if(wordInfo.totalWordCount >= gameInfo.numberOfWordsToGuess){
            skipButton.setEnabled(false);
            setKeyboardEnabled(false);
        }
    }

    public void disableStartButton() {
        resultButton.setEnabled(false);
        skipButton.setEnabled(false);
    }
    private void setKeyboardEnabled(boolean enabled){
        int count = guessButtons.getChildCount();
        for(int i=0; i<count; i++) {
            View row = guessButtons.getChildAt(i);
            if(!(row instanceof  LinearLayout)){
                continue;
            }
            LinearLayout rowLayout = (LinearLayout) row;
            int count2 = rowLayout.getChildCount();
            for(int j=0; j<count2; j++) {
                View v = rowLayout.getChildAt(j);
                v.setEnabled(enabled);
            }
        }
    }
}
