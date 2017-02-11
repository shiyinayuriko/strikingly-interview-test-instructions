package net.whitecomet.hangman.connector;

import com.google.gson.Gson;

import net.whitecomet.hangman.connector.data.GameOverInfo;
import net.whitecomet.hangman.connector.data.GetResultReponse;
import net.whitecomet.hangman.connector.data.StartGameResponse;
import net.whitecomet.hangman.connector.data.SubmitResultCallback;
import net.whitecomet.hangman.connector.data.SubmitResultResponse;
import net.whitecomet.hangman.connector.data.WordResponse;
import net.whitecomet.hangman.util.WLog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by white on 2017/2/10.
 */

public class Network {
    private static final String TAG = Network.class.getSimpleName();
    //FIXME
    private static final String PLAYER_ID = "whitecometliliwill@gmail.coms";
    private static final String URL = "https://strikingly-hangman.herokuapp.com/game/on";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();

    private String sessionId;

    public void startGame(final StartGameCallback callback){
        Map<String,String> para = new HashMap<>();
        para.put("playerId",PLAYER_ID);
        para.put("action","startGame");
        String jsonStr = gson.toJson(para);

        post(jsonStr, new PostCallback() {
            @Override
            public void onSuccess(String json) {
                WLog.i(TAG, "startGame.onSuccess: " + json);
                try{
                    StartGameResponse res = gson.fromJson(json, StartGameResponse.class);
                    sessionId = res.sessionId;
                    callback.onSucess(res.data);
                }catch (Exception e){
                    callback.onFail(e.getMessage());
                }
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }

    public void nextWord(final WordCallback callback){
        Map<String,String> para = new HashMap<>();
        para.put("sessionId",sessionId);
        para.put("action","nextWord");
        String jsonStr = gson.toJson(para);
        post(jsonStr, new PostCallback() {

            @Override
            public void onSuccess(String json) {
                WLog.i(TAG, "nextWord.onSuccess: " + json);

                try{
                    WordResponse res = gson.fromJson(json, WordResponse.class);
                    callback.onSucess(res.data);
                }catch (Exception e){
                    callback.onFail(e.getMessage());
                }
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });

    }
    public void guessWord(String ch, final WordCallback callback){
        Map<String,String> para = new HashMap<>();
        para.put("sessionId",sessionId);
        para.put("action","guessWord");
        para.put("guess","ch");
        String jsonStr = gson.toJson(para);
        post(jsonStr, new PostCallback() {

            @Override
            public void onSuccess(String json) {
                WLog.i(TAG, "guessWord.onSuccess: " + json);

                try{
                    WordResponse res = gson.fromJson(json, WordResponse.class);
                    callback.onSucess(res.data);
                }catch (Exception e){
                    callback.onFail(e.getMessage());
                }
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }
    public void getResult(final GetResultCallback callback){
        Map<String,String> para = new HashMap<>();
        para.put("sessionId",sessionId);
        para.put("action","getResult");
        String jsonStr = gson.toJson(para);
        post(jsonStr, new PostCallback() {

            @Override
            public void onSuccess(String json) {
                WLog.i(TAG, "getResult.onSuccess: " + json);

                try{
                    GetResultReponse res = gson.fromJson(json, GetResultReponse.class);
                    callback.onSucess(res.data);
                }catch (Exception e){
                    callback.onFail(e.getMessage());
                }
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }
    public void submitResult(final SubmitResultCallback callback){
        Map<String,String> para = new HashMap<>();
        para.put("sessionId",sessionId);
        para.put("action","submitResult");
        String jsonStr = gson.toJson(para);
        post(jsonStr, new PostCallback() {

            @Override
            public void onSuccess(String json) {
                WLog.i(TAG, "getResult.onSuccess: " + json);

                try{
                    SubmitResultResponse res = gson.fromJson(json, SubmitResultResponse.class);
                    callback.onSucess(res.data);
                }catch (Exception e){
                    callback.onFail(e.getMessage());
                }
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }
    private String post(String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
            .url(URL)
            .post(body)
            .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
    private interface PostCallback{
        void onSuccess(String json);
        void onFail(String msg);
    }
    private void post(String json, final PostCallback callback){
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFail(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().string());
                } else {
                    callback.onFail("Unexpected code " + response);
                }
            }
        });
    }
}
