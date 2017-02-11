package net.whitecomet.hangman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import net.whitecomet.hangman.R;

/**
 * Created by white on 2017/2/10.
 */

public class StartView extends RelativeLayout {
    private static final String TAG = StartView.class.getSimpleName();
    public StartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_start, this, true);
    }
}
