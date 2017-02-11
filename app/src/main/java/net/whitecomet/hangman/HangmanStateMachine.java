package net.whitecomet.hangman;

import android.os.Bundle;
import android.os.Message;

import com.android.internal.util.stub.State;
import com.android.internal.util.stub.StateMachine;

import net.whitecomet.hangman.connector.GetResultCallback;
import net.whitecomet.hangman.connector.Network;
import net.whitecomet.hangman.connector.StartGameCallback;
import net.whitecomet.hangman.connector.WordCallback;
import net.whitecomet.hangman.connector.data.GameInfo;
import net.whitecomet.hangman.connector.data.GameOverInfo;
import net.whitecomet.hangman.connector.data.ResultInfo;
import net.whitecomet.hangman.connector.data.SubmitResultCallback;
import net.whitecomet.hangman.connector.data.WordInfo;
import net.whitecomet.hangman.util.WLog;

/**
 * Created by white on 2017/2/11.
 */

public class HangmanStateMachine extends StateMachine {
    private static final String TAG = HangmanStateMachine.class.getSimpleName();

    public static final int EVENT_MSG_START_GAME = 1;
    public static final int EVENT_MSG_GUESS_WORD = 2;
    public static final int EVENT_MSG_CHECK_RESULT = 3;
    public static final int EVENT_MSG_SKIP_WORD = 6;
    public static final int EVENT_MSG_SUBMIT_RESULT = 4;
    public static final int EVENT_MSG_EXIT = 5;

    public static final int NETWORK_START_GAME_READY = 101;
    public static final int NETWORK_START_GAME_FAIL = 201;
    public static final int NETWORK_NEXT_WORD_READY = 102;
    public static final int NETWORK_NEXT_WORD_FAIL = 202;
    public static final int NETWORK_GUESS_WORD_READY = 103;
    public static final int NETWORK_GUESS_WORD_FAIL = 203;
    public static final int NETWORK_GET_RESULT_READY = 104;
    public static final int NETWORK_GET_RESULT_FAIL = 204;
    public static final int NETWORK_SUBMIT_RESULT_READY = 105;
    public static final int NETWORK_SUBMIT_RESULT_FAIL = 205;

    private ViewShower viewShower;
    private Network network;
    private GameInfo gameInfo;
    private WordInfo wordInfo;
    public interface ViewShower{
        void showFail(String fail);
        void showStartView();
        void showGuessView(GameInfo gameInfo, WordInfo info);
        void showResultView(GameInfo gameInfo, ResultInfo resultInfo);
        void showEndView(GameInfo gameInfo, GameOverInfo gameOverInfo);
    }
    public HangmanStateMachine(ViewShower viewShower) {
        super("HangmanStateMachine");
        addState(defaultState);
        addState(guessPaddingState);
        addState(guessState);
        setInitialState(defaultState);
        start();

        this.viewShower = viewShower;
        network = new Network();
    }
    private State defaultState = new State(){
        @Override
        public void enter() {
            WLog.i(TAG, "guessPaddingState: enter");
            viewShower.showStartView();
        }

        @Override
        public boolean processMessage(Message msg) {
            WLog.i(TAG, "defaultState: msg " + msg.what);
            switch (msg.what){
                case EVENT_MSG_START_GAME:
                    transitionTo(guessPaddingState);
                    return HANDLED;
                default:
                    WLog.w(TAG,"defaultState: Unsupported message");
                    return NOT_HANDLED;
            }
        }
    };
    private State guessPaddingState = new State(){
        @Override
        public void enter() {
            WLog.i(TAG, "guessPaddingState: enter");
            doStartGame();
        }
        @Override
        public boolean processMessage(Message msg) {
            WLog.i(TAG, "guessPaddingState: msg " + msg.what);
            switch (msg.what) {
                case NETWORK_START_GAME_READY:
                    gameInfo = (GameInfo) msg.obj;
                    doNextWord();
                    return HANDLED;
                case NETWORK_NEXT_WORD_READY:
                    wordInfo = (WordInfo) msg.obj;
                    transitionTo(guessState);
                    return HANDLED;
                case NETWORK_START_GAME_FAIL:
                case NETWORK_NEXT_WORD_FAIL:
                case EVENT_MSG_EXIT:
                    transitionTo(defaultState);
                default:
                    WLog.w(TAG,"guessPaddingState: Unsupported message");
                    return NOT_HANDLED;
            }
        }
    };
    private State guessState = new State(){
        private ResultInfo result;

        @Override
        public void enter() {
            viewShower.showGuessView(gameInfo, wordInfo);
        }

        @Override
        public boolean processMessage(Message msg) {
            switch (msg.what){
                case EVENT_MSG_GUESS_WORD:
                    String ch = msg.getData().getString("letter", "e");
                    doGuessWord(ch);
                    return HANDLED;
                case NETWORK_GUESS_WORD_READY:
                    wordInfo = (WordInfo) msg.obj;
                    viewShower.showGuessView(gameInfo, wordInfo);
                    return HANDLED;
                case NETWORK_GUESS_WORD_FAIL:
                    viewShower.showGuessView(gameInfo, wordInfo);
                    return HANDLED;
                case EVENT_MSG_SKIP_WORD:
                    doNextWord();
                    return HANDLED;
                case NETWORK_NEXT_WORD_READY:
                    wordInfo = (WordInfo) msg.obj;
                    viewShower.showGuessView(gameInfo, wordInfo);
                    return HANDLED;
                case NETWORK_NEXT_WORD_FAIL:
                    viewShower.showGuessView(gameInfo, wordInfo);
                    return HANDLED;
                case EVENT_MSG_CHECK_RESULT:
                    doGetResult();
                    return HANDLED;
                case NETWORK_GET_RESULT_READY:
                    result = (ResultInfo) msg.obj;
                    viewShower.showResultView(gameInfo, result);
                    return HANDLED;
                case NETWORK_GET_RESULT_FAIL:
                    viewShower.showResultView(gameInfo, null);
                    return HANDLED;
                case EVENT_MSG_SUBMIT_RESULT:
                    doSubmitResult();
                    return HANDLED;
                case NETWORK_SUBMIT_RESULT_READY:
                    GameOverInfo gameOverInfo = (GameOverInfo) msg.obj;
                    viewShower.showEndView(gameInfo, gameOverInfo);
                    return HANDLED;
                case NETWORK_SUBMIT_RESULT_FAIL:
                    viewShower.showResultView(gameInfo, result);
                    return HANDLED;
                case EVENT_MSG_EXIT:
                    transitionTo(defaultState);
                    return HANDLED;
                default:
                    return NOT_HANDLED;
            }
        }
    };

    private void doStartGame(){
        WLog.i(TAG, "startGame:");
        network.startGame(new StartGameCallback() {
            @Override
            public void onSuccess(GameInfo info) {
                Message msg = obtainMessage(NETWORK_START_GAME_READY);
                msg.obj = info;
                msg.sendToTarget();
            }

            @Override
            public void onFail(String msg) {
                obtainMessage(NETWORK_START_GAME_FAIL).sendToTarget();
                viewShower.showFail(msg);
            }
        });
    }
    private void doNextWord(){
        network.nextWord(new WordCallback() {
            @Override
            public void onSuccess(WordInfo info) {
                Message msg = obtainMessage(NETWORK_NEXT_WORD_READY);
                msg.obj = info;
                msg.sendToTarget();
            }

            @Override
            public void onFail(String msg) {
                obtainMessage(NETWORK_NEXT_WORD_FAIL).sendToTarget();
                viewShower.showFail(msg);
            }
        });
    }
    private void doGuessWord(String ch){
        network.guessWord(ch, new WordCallback() {
            @Override
            public void onSuccess(WordInfo info) {
                Message msg = obtainMessage(NETWORK_GUESS_WORD_READY);
                msg.obj = info;
                msg.sendToTarget();
            }

            @Override
            public void onFail(String msg) {
                obtainMessage(NETWORK_GUESS_WORD_FAIL).sendToTarget();
                viewShower.showFail(msg);
            }
        });
    }
    private void doGetResult(){
        network.getResult(new GetResultCallback() {
            @Override
            public void onSuccess(ResultInfo info) {
                Message msg = obtainMessage(NETWORK_GET_RESULT_READY);
                msg.obj = info;
                msg.sendToTarget();
            }

            @Override
            public void onFail(String msg) {
                obtainMessage(NETWORK_GET_RESULT_FAIL).sendToTarget();
                viewShower.showFail(msg);
            }
        });
    }
    private void doSubmitResult(){
        network.submitResult(new SubmitResultCallback() {
            @Override
            public void onSuccess(GameOverInfo info) {
                Message msg = obtainMessage(NETWORK_SUBMIT_RESULT_READY);
                msg.obj = info;
                msg.sendToTarget();
            }

            @Override
            public void onFail(String msg) {
                obtainMessage(NETWORK_SUBMIT_RESULT_FAIL).sendToTarget();
                viewShower.showFail(msg);
            }
        });
    }



    public void startGame(){
        obtainMessage(EVENT_MSG_START_GAME).sendToTarget();
    }
    //GuessView
    public void guessWord(String ch){
        Message msg = obtainMessage(EVENT_MSG_GUESS_WORD);
        Bundle data = new Bundle();
        data.putString("letter", ch);
        msg.setData(data);
        msg.sendToTarget();
    }
    public void checkResult(){
        obtainMessage(EVENT_MSG_CHECK_RESULT).sendToTarget();
    }
    public void skipWord(){
        obtainMessage(EVENT_MSG_SKIP_WORD).sendToTarget();
    }
    //ResultView
    public void submitResult(){
        obtainMessage(EVENT_MSG_SUBMIT_RESULT).sendToTarget();
    }
    public void resmumeGame(){
        viewShower.showGuessView(gameInfo, wordInfo);
    }
    public void exitGame(){
        obtainMessage(EVENT_MSG_EXIT).sendToTarget();
    }

}
