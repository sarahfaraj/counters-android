package com.example.http2.counter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.http2.counter.Counter;
import com.example.http2.counter.CounterAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CounterTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "counterTask";

    private Context context;
    private CounterAdapter counterAdapter;

    public CounterTask(CounterAdapter counterAdapter, Context context) {
        this.context = context;
        this.counterAdapter = counterAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        StringBuilder result = null;
        String path = Utils.BASE_URL + "/counters";
        return Utils.sendHttp(path);
    }

    @Override
    protected void onPostExecute(String result) {
        if (result == null || result.isEmpty()) {
            Log.d(TAG, "Result  is empty...");
            return;
        }

        try {
            //Create the type “ArrayList<counter>”  with a anonyme class
            Type listType = new TypeToken<ArrayList<Counter>>() {
            }.getType();
            //Convert result to objects
            List<Counter> list = new Gson().fromJson(result, listType);

            //Updates the listView
            counterAdapter.getcounters().addAll(list);
            counterAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            System.out.println("e " + e);
        }
    }
}
