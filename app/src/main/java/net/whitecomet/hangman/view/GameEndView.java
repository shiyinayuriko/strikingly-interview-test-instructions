package net.whitecomet.hangman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.whitecomet.hangman.R;
import net.whitecomet.hangman.connector.data.GameInfo;
import net.whitecomet.hangman.connector.data.GameOverInfo;

/**
 * Created by white on 2017/2/10.
 */

public class GameEndView extends RelativeLayout {
    private static final String TAG = GameEndView.class.getSimpleName();
    private final TextView scoreView;
    private final TextView playerIdTextView;
    private final TextView sessionIdTextView;
    private final TextView correctTextView;
    private final TextView wrongTextView;
    private final TextView totalTextView;
    private final TextView dateTimeTextView;

    public GameEndView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_end, this, true);
        scoreView = (TextView) findViewById(R.id.end_view_score_text_view);
        playerIdTextView = (TextView) findViewById(R.id.end_view_title_playerId_text);
        sessionIdTextView = (TextView) findViewById(R.id.end_view_title_sessionId_text);
        correctTextView = (TextView) findViewById(R.id.end_view_title_correct_words_text);
        wrongTextView = (TextView) findViewById(R.id.end_view_title_wrong_times_text);
        totalTextView = (TextView) findViewById(R.id.end_view_title_total_words_text);
        dateTimeTextView = (TextView) findViewById(R.id.end_view_title_date_time_text);
    }

    public void show(GameInfo gameInfo, GameOverInfo gameOverInfo) {
        String scoreStr = getResources().getString(R.string.view_status_score, gameOverInfo.score);
        scoreView.setText(scoreStr);
        playerIdTextView.setText(gameOverInfo.playerId);
        sessionIdTextView.setText(gameOverInfo.sessionId);
        correctTextView.setText(gameOverInfo.correctWordCount + "");
        wrongTextView.setText(gameOverInfo.totalWrongGuessCount + "");
        totalTextView.setText(gameOverInfo.totalWordCount + "");
        dateTimeTextView.setText(gameOverInfo.datetime);
    }
}
