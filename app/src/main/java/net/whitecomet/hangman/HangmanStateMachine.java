package net.whitecomet.hangman;

import com.android.internal.util.State;
import com.android.internal.util.StateMachine;

/**
 * Created by white on 2017/2/11.
 */

public class HangmanStateMachine extends StateMachine {
    protected HangmanStateMachine() {
        super("HangmanStateMachine");
        addState(defaultState);
        addState(guessState);
        addState(resultState);
        setInitialState(defaultState);
        start();
    }
    private State defaultState = new State(){

    };
    private State guessState = new State(){

    };
    private State resultState = new State(){

    };
    //StartView
    public void startGame(){
        //TODO
    }
    //GuessView
    public void guessWord(String ch){
        //TODO
    }
    public void checkResult(){
        //TODO
    }
    //ResultView
    public void submitResult(){
        //TODO
    }
    public void resmumeGame(){
        //TODO
    }
    public void exitStartView(){
        //TODO
    }

}
