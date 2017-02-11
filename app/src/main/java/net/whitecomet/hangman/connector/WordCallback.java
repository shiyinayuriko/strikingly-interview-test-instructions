package net.whitecomet.hangman.connector;

import net.whitecomet.hangman.connector.data.WordInfo;

/**
 * Created by white on 2017/2/11.
 */

public interface WordCallback extends BaseFailCallback{
    void onSuccess(WordInfo info);
}
