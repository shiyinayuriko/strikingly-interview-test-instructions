package net.whitecomet.hangman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapProgressBar;

import net.whitecomet.hangman.R;
import net.whitecomet.hangman.connector.data.GameInfo;
import net.whitecomet.hangman.connector.data.ResultInfo;

/**
 * Created by white on 2017/2/10.
 */

public class ResultView extends RelativeLayout {
    private static final String TAG = ResultView.class.getSimpleName();
    private final TextView scoreTextView;
    private final TextView otherStatusTextView;
    private final TextView answeredCounter;
    private final BootstrapProgressBar answeredCounterProgress;

    public ResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_result, this, true);
        scoreTextView = (TextView) findViewById(R.id.result_view_score_text_view);
        otherStatusTextView = (TextView) findViewById(R.id.result_view_other_status);
        answeredCounter = (TextView) findViewById(R.id.result_view_answered_text);
        answeredCounterProgress = (BootstrapProgressBar) findViewById(R.id.result_view_word_count_progress);
    }

    public void show(GameInfo gameInfo, ResultInfo resultInfo) {
        if(resultInfo != null){
            String scoreStr = getResources().getString(R.string.view_status_score, resultInfo.score);
            scoreTextView.setText(scoreStr);
            String otherStatusStr = getResources().getString(R.string.result_view_status_other_status, resultInfo.correctWordCount, resultInfo.totalWrongGuessCount);
            otherStatusTextView.setText(otherStatusStr);
            String answeredCounterStr = getResources().getString(R.string.view_counter_text, resultInfo.totalWordCount, gameInfo.numberOfWordsToGuess);
            answeredCounter.setText(answeredCounterStr);
            answeredCounterProgress.setProgress(resultInfo.totalWordCount *100 / gameInfo.numberOfWordsToGuess);
        }
    }
}
