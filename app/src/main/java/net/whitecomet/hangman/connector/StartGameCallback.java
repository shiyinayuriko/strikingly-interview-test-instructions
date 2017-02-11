package net.whitecomet.hangman.connector;

import net.whitecomet.hangman.connector.data.GameInfo;

/**
 * Created by white on 2017/2/11.
 */

public interface StartGameCallback extends BaseFailCallback{
    void onSucess(GameInfo info);
}
