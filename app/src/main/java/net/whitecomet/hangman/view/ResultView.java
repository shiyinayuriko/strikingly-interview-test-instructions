package net.whitecomet.hangman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.whitecomet.hangman.R;
import net.whitecomet.hangman.connector.data.GameInfo;
import net.whitecomet.hangman.connector.data.ResultInfo;

/**
 * Created by white on 2017/2/10.
 */

public class ResultView extends RelativeLayout {
    private static final String TAG = ResultView.class.getSimpleName();
    private final TextView statusView;

    public ResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_result, this, true);
        statusView = (TextView) findViewById(R.id.result_view_status);
    }

    public void show(GameInfo gameInfo, ResultInfo resultInfo) {
        if (resultInfo !=null){
            statusView.setText(gameInfo.toString() + "\n" + resultInfo.toString());
        }else{
            statusView.setText(null);
        }
    }
}
