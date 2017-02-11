package net.whitecomet.hangman.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.beardedhen.androidbootstrap.BootstrapButton;

import net.whitecomet.hangman.R;

/**
 * Created by white on 2017/2/11.
 */

public class GuessKey extends BootstrapButton{
    private String letter;
    public GuessKey(Context context) {
        super(context);
        initialise(null);
    }

    public GuessKey(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise(attrs);
    }

    public GuessKey(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialise(attrs);
    }
    private void initialise(AttributeSet attrs){
        if(attrs!=null){
            Context context = getContext();
            TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.GuessKey);
            letter = typeArray.getString(R.styleable.GuessKey_letter);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(letter != null){
            setText(letter);
        }
        setEnabled(letter != null);
        setVisibility(letter != null ? VISIBLE : INVISIBLE);
        setPadding(0,getPaddingTop(),0,getPaddingBottom());
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled && letter != null);
    }

    public String getLetter(){
        return letter;
    }
}
