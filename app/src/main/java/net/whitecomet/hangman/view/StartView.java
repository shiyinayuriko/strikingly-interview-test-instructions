package net.whitecomet.hangman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import net.whitecomet.hangman.R;

/**
 * Created by white on 2017/2/10.
 */

public class StartView extends RelativeLayout {
    private static final String TAG = StartView.class.getSimpleName();
    private final Button startButton;

    public StartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_start, this, true);
        startButton = (Button) findViewById(R.id.start_view_start_button);
    }
    public void show(){
        startButton.setEnabled(true);
    }
    public void disableStartButton(){
        startButton.setEnabled(false);
    }
}
