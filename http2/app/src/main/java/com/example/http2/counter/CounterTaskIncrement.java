package com.example.http2.counter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CounterTaskIncrement extends AsyncTask<String, Void, String> {

    private static final String TAG = "CounterTaskIncrement";

    private Counter counter;

    public CounterTaskIncrement(Counter c) {
        this.counter = c;
    }

    public int getCount() {
        return this.counter.getCount();
    }

    @Override
    protected String doInBackground(String... params) {
        String result;
        String path = null;
        System.out.println(params[0]);
        path = Utils.BASE_URL + "/counter/web/exec?title=" + this.counter.getTitle() + "&action=" + params[0];
        result = Utils.sendHttp(path);
        System.out.println("RESULT1=" + result);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result == null || result.isEmpty()) {
            Log.d(TAG, "Result  is empty...");
            return;
        }
        try {
            // augmenter le counter de 1
            System.out.println("CounterTaskIncrement: " + this.counter.count);
        } catch (Exception e) {
            System.out.println("e " + e);
        }
    }
}
