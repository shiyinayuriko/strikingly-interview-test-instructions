package net.whitecomet.hangman.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.whitecomet.hangman.HangmanStateMachine;
import net.whitecomet.hangman.R;
import net.whitecomet.hangman.connector.data.GameInfo;
import net.whitecomet.hangman.connector.data.GameOverInfo;
import net.whitecomet.hangman.connector.data.ResultInfo;
import net.whitecomet.hangman.connector.data.WordInfo;
import net.whitecomet.hangman.util.WLog;

public class MainActivity extends Activity implements HangmanStateMachine.ViewShower {
    private static final String TAG = MainActivity.class.getSimpleName();

    private HangmanStateMachine sm;
    private StartView startView;
    private GuessView guessView;
    private ResultView resultView;
    private GameEndView endView;
    private Handler viewHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startView = (StartView) findViewById(R.id.layout_start_view);
        guessView = (GuessView) findViewById(R.id.layout_guess_view);
        resultView = (ResultView) findViewById(R.id.layout_result_view);
        endView = (GameEndView) findViewById(R.id.layout_end_view);
        sm = new HangmanStateMachine(this);
    }

    public void startGame(View v){
        WLog.i(TAG, "click startGame");
        startView.disableStartButton();
        sm.startGame();
    }
    public void guessWord(View v){
        WLog.i(TAG, "click guessWord");
        if(v instanceof GuessKey){
            String ch = ((GuessKey) v).getLetter();
            guessView.disableStartButton();
            sm.guessWord(ch);
        }
    }
    public void checkResult(View v){
        WLog.i(TAG, "click checkResult");
        guessView.disableStartButton();
        sm.checkResult();
    }
    public void skipWord(View v){
        WLog.i(TAG, "click skipWord");
        guessView.disableStartButton();
        sm.skipWord();
    }
    public void resumeGame(View v){
        WLog.i(TAG, "click resumeGame");
        sm.resmumeGame();
    }
    public void submitResult(View v){
        WLog.i(TAG, "click submitResult");
        sm.submitResult();
    }
    public void exitGame(View v){
        WLog.i(TAG, "click exitGame");
        sm.exitGame();
    }


    @Override
    public void showFail(final String fail) {
        viewHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, fail,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void showGuessView(final GameInfo gameInfo, final WordInfo info) {
        viewHandler.post(new Runnable() {
            @Override
            public void run() {
                guessView.setVisibility(View.VISIBLE);
                startView.setVisibility(View.GONE);
                resultView.setVisibility(View.GONE);
                endView.setVisibility(View.GONE);
                guessView.showInfo(gameInfo, info);
            }
        });
    }

    @Override
    public void showStartView() {
        viewHandler.post(new Runnable() {
            @Override
            public void run() {
                guessView.setVisibility(View.GONE);
                startView.setVisibility(View.VISIBLE);
                resultView.setVisibility(View.GONE);
                endView.setVisibility(View.GONE);
                startView.show();
            }
        });
    }

    @Override
    public void showResultView(final GameInfo gameInfo, final ResultInfo resultInfo) {
        viewHandler.post(new Runnable() {
            @Override
            public void run() {
                guessView.setVisibility(View.GONE);
                startView.setVisibility(View.GONE);
                resultView.setVisibility(View.VISIBLE);
                endView.setVisibility(View.GONE);
                resultView.show(gameInfo, resultInfo);
            }
        });
    }

    @Override
    public void showEndView(final GameInfo gameInfo, final GameOverInfo gameOverInfo) {
        viewHandler.post(new Runnable() {
            @Override
            public void run() {
                guessView.setVisibility(View.GONE);
                startView.setVisibility(View.GONE);
                resultView.setVisibility(View.GONE);
                endView.setVisibility(View.VISIBLE);
                endView.show(gameInfo, gameOverInfo);
            }
        });
    }

}
