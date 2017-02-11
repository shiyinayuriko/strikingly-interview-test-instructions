package net.whitecomet.hangman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import net.whitecomet.hangman.R;

/**
 * Created by white on 2017/2/10.
 */

public class ResultView extends RelativeLayout {
    private static final String TAG = ResultView.class.getSimpleName();
    public ResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_result, this, true);
    }

}
