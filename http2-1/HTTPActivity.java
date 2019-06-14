package com.example.http2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HTTPActivity extends AppCompatActivity {


    private EditText editTextUrl;
    private TextView textViewRes;

    private Button btnHttp;
    private Button btnImg;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        btnHttp = (Button) findViewById(R.id.btnHttp);
        editTextUrl = (EditText) findViewById(R.id.editTextUrl);
        textViewRes = (TextView) findViewById(R.id.textViewRes);
        btnImg = (Button) findViewById(R.id.btnImg);
        img = (ImageView) findViewById(R.id.imageView2);

        btnHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextUrl.getText().toString();
                new HttpAsyncTask().execute(url);
                // C'est équivalent à =>
                // HttpAsyncTask httpAsyncTack = new HttpAsyncTask();
                //httpAsyncTack.execute(url);
            }
        });

       btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewRes.setText("");
                Picasso.with(v.getContext()).invalidate("https://picsum.photos/200/300/?random");
                Picasso.with(v.getContext()).load("https://picsum.photos/200/300/?random").into(img);
            }
        });

    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String path = params[0];
            URL url = null;
            HttpURLConnection connection = null;
            StringBuilder result = null;

            try {
                url = new URL(path);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int codeResponse = connection.getResponseCode();
                result = new StringBuilder();
                Log.d("CODE", "" + codeResponse);
                // Je vérifie que la requete a bien eu lieu
                if( 200 <= codeResponse && codeResponse < 300 ){
                    // Je recupère la réponse de la requete
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = null;

                    while((line = br.readLine()) != null){
                        result.append(line);
                    }
                    br.close();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            if(result == null) {
                return "";
            }
            return result.toString();

        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println(result);
            textViewRes.setText(result);
        }
    }
}







