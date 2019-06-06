package com.example.http2.album;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AlbumTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "AlbumTask";

    private Context context;
    private AlbumAdapter albumAdapter;

    public AlbumTask(AlbumAdapter albumAdapter, Context context) {
        this.context = context;
        this.albumAdapter = albumAdapter;
    }

    @Override
    protected String doInBackground(Void... params) {
        StringBuilder result = null;
        String path = "https://jsonplaceholder.typicode.com/photos";

        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");
            connection.connect();
            int codeResponse = connection.getResponseCode();
            result = new StringBuilder();
            if (200 <= codeResponse && codeResponse < 300) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        Log.d(TAG, result.toString());
        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        if(result == null || result.isEmpty()) {
            Log.d(TAG, "Result  is empty...");
            return;
        }

        try{
            //Create the type “ArrayList<Album>”  with a anonyme class
            Type listType = new TypeToken<ArrayList<Album>>() {}.getType();
            //Convert result to objects
            List<Album> list = new Gson().fromJson(result, listType);

            //Updates the listView
            albumAdapter.getAlbums().addAll(list);
            albumAdapter.notifyDataSetChanged();
        }catch(Exception e){
            System.out.println("e " + e);
        }
    }
}