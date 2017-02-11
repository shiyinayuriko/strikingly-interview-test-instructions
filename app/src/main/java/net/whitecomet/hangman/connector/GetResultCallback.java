package net.whitecomet.hangman.connector;

import net.whitecomet.hangman.connector.data.ResultInfo;
import net.whitecomet.hangman.connector.data.WordInfo;

/**
 * Created by white on 2017/2/11.
 */

public interface GetResultCallback extends BaseFailCallback{
    void onSuccess(ResultInfo info);
}
