package net.whitecomet.hangman.util;

import android.util.Log;

/**
 * Created by white on 2017/2/11.
 */

public class WLog {
    public static int i(String tag,String msg){
        return Log.i(tag, msg);
    }
    public static int d(String tag,String msg){
        return Log.d(tag, msg);
    }
    public static int w(String tag,String msg){
        return Log.w(tag, msg);
    }
}
