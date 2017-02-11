package net.whitecomet.hangman.connector.data;

import net.whitecomet.hangman.connector.BaseFailCallback;

/**
 * Created by white on 2017/2/11.
 */

public interface SubmitResultCallback extends BaseFailCallback{
    void onSucess(GameOverInfo info);
}
