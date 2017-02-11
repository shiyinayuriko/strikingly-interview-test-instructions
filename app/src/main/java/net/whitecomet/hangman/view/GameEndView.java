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
    private final TextView statusView;

    public GameEndView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_end, this, true);
        statusView = (TextView) findViewById(R.id.end_view_status);
    }

    public void show(GameInfo gameInfo, GameOverInfo gameOverInfo) {
            statusView.setText(gameInfo.toString() + "\n" + gameOverInfo.toString());
    }
}
