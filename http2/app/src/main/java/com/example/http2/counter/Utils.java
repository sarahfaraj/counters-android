package com.example.http2.counter;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
    public final static String BASE_URL  = "http://10.11.98.106:8080";
    //public final static String BASE_URL  = "http://192.168.1.20:8080";


    public static String sendHttp(String path) {
        Log.d("DEBUG", path);

        StringBuilder result = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();

            //connection = new URL(url + "?" + query).openConnection();
            //connection.setRequestProperty("Accept-Charset", charset);
            //InputStream response = connection.getInputStream();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");
            connection.connect();
            int codeResponse = connection.getResponseCode();
            result = new StringBuilder();
            if (200 <= codeResponse && codeResponse < 300) {
                Log.d("DEBUG", "200");
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
        Log.d("UTILS", result.toString());
        return result.toString();
    }

}
