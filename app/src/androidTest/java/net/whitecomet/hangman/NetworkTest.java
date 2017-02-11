package net.whitecomet.hangman;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import net.whitecomet.hangman.connector.Network;
import net.whitecomet.hangman.connector.StartGameCallback;
import net.whitecomet.hangman.connector.WordCallback;
import net.whitecomet.hangman.connector.data.GameInfo;
import net.whitecomet.hangman.connector.data.WordInfo;
import net.whitecomet.hangman.util.WLog;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class NetworkTest {
    public static final String TAG = NetworkTest.class.getSimpleName();
    public static final String TEST_SESSION = "3369edfba2a6c77ddb2425928e1cc17e";

    @Test
    public void testStartGame() throws Exception {
        Network network = new Network();
        network.startGame(new StartGameCallback() {

            @Override
            public void onSuccess(GameInfo info) {
                WLog.d(TAG, info.numberOfGuessAllowedForEachWord +"");
            }

            @Override
            public void onFail(String msg) {
                WLog.d(TAG, msg);
            }
        });
        Thread.sleep(1000);
        assertEquals(1,1);
    }

    @Test
    public void testNextword() throws Exception {
        Network network = new Network();
        Field sessionField = Network.class.getDeclaredField("sessionId");
        sessionField.setAccessible(true);
        sessionField.set(network, "3369edfba2a6c77ddb2425928e1cc17e");
        network.nextWord(new WordCallback(){

            @Override
            public void onSuccess(WordInfo info) {
                WLog.d(TAG, info.word +"");
            }

            @Override
            public void onFail(String msg) {
                WLog.d(TAG, msg);
            }
        });
        Thread.sleep(2000);
        assertEquals(1,1);
    }

    @Test
    public void testGuessWord() throws Exception {
        Network network = new Network();
        Field sessionField = Network.class.getDeclaredField("sessionId");
        sessionField.setAccessible(true);
        sessionField.set(network, "3369edfba2a6c77ddb2425928e1cc17e");
        network.guessWord("e", new WordCallback(){

            @Override
            public void onSuccess(WordInfo info) {
                WLog.d(TAG, info.word +"");
            }

            @Override
            public void onFail(String msg) {
                WLog.d(TAG, msg);
            }
        });
        Thread.sleep(2000);
        assertEquals(1,1);
    }
}
