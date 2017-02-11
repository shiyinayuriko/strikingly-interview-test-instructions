package net.whitecomet.hangman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.whitecomet.hangman.R;
import net.whitecomet.hangman.connector.data.GameInfo;
import net.whitecomet.hangman.connector.data.WordInfo;

/**
 * Created by white on 2017/2/10.
 */

public class GuessView extends RelativeLayout {
    private static final String TAG = GuessView.class.getSimpleName();
    private final TextView wordview;
    private final TextView statusView;
    private final Button guessButton;
    private final Button resultButton;
    private final Button skipButton;

    public GuessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_guess, this, true);
        wordview = (TextView) findViewById(R.id.guess_view_current_word);
        statusView = (TextView) findViewById(R.id.guess_view_current_states);
        guessButton = (Button) findViewById(R.id.guess_view_guess_button);
        resultButton = (Button) findViewById(R.id.guess_view_get_result_button);
        skipButton = (Button) findViewById(R.id.guess_view_skip_button);
    }

    public void showInfo(GameInfo gameInfo, WordInfo wordInfo){
        guessButton.setEnabled(true);
        resultButton.setEnabled(true);
        skipButton.setEnabled(true);
        wordview.setText(wordInfo.word);
        statusView.setText(gameInfo.toString() + "\n" + wordInfo.toString());
    }

    public void disableStartButton() {
        guessButton.setEnabled(false);
        resultButton.setEnabled(false);
        skipButton.setEnabled(false);
    }
}
