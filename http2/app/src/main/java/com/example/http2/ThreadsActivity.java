package com.example.http2;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;



public class ThreadsActivity extends AppCompatActivity {

    private TextView txtStatus;
    private ProgressBar progress;
    private Button btnGo;
    private ProgressBar loader;
    private EditText fiboN;

    private Button btnSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);

        btnSum = (Button) findViewById(R.id.btnAsyncSum);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        progress = (ProgressBar) findViewById(R.id.progressBarCircle);
        btnGo = (Button) findViewById(R.id.btnGo);
        loader = (ProgressBar) findViewById(R.id.progressBar2);
        fiboN = (EditText) findViewById(R.id.editTextFiboNum);

        // Exercice 1
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SumAsyncTask().execute("a", "b", "c", "toto");
            }
        });

        // Fibonacci
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Je récupere la valeur de mon input Text
                int n = Integer.parseInt(fiboN.getText().toString());

                new FiboAsyncTask().execute(n);
            }
        });
    }


    // Exercice 1 - Sum
    public class SumAsyncTask extends AsyncTask<String, Float, String> {
        private static final String TAG = "Test";

        public SumAsyncTask() {

        }
        @Override
        protected void onPreExecute() { // UI Thread
            super.onPreExecute();
            txtStatus.setText("Loading");
        }

        @Override
        protected String doInBackground(String... params) { // Exécuté dans un autre thread
            // params [1, 2, 3, 4]
            String result = "";
            for( int i = 0; i < params.length;  i++ ){
                result += params[i];
                // publishProgress((float)i/params.length);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            publishProgress((float)1.0);
            return result;
        }

        @Override
        protected void onProgressUpdate(Float... values) { // Exécuté dans le UIThread
            super.onProgressUpdate(values);
            Log.i(TAG, "Progress " + values[0]);
        }

        @Override
        protected void onPostExecute(String s) { // Exécuté dans le UIThread
            Log.i(TAG, "Result " + s);
            txtStatus.setText(s);
        }
    }



    // Exercice - Fibonacci
    public class FiboAsyncTask extends AsyncTask<Integer, Float, Integer> {
        private static final String TAG = "Test";
        private long tStart;

        @Override
        protected void onPreExecute() { // UI Thread
            super.onPreExecute();
            txtStatus.setText("Loading");
            loader.setVisibility(View.VISIBLE);
            // sauvergarde du temps
            tStart = System.currentTimeMillis();
        }

        private int fibo(int n) {
            if (n <= 1 ) return n;
            else return fibo(n-1) + fibo(n-2);
        }
        @Override
        protected Integer doInBackground(Integer... params) { // Exécuté dans un autre thread
            int n = params[0];
            int res = fibo(n);
            return res;
        }


        @Override
        protected void onPostExecute(Integer res) { // Exécuté dans le UIThread

            Log.i(TAG, "Result " + res);

            long tEnd = System.currentTimeMillis();
            long tDelta = tEnd - tStart;
            double elapsedSeconds = tDelta / 1000.0;

            txtStatus.setText("Finish, result = " + res + " \n Elapsed Time = " + elapsedSeconds );
            loader.setVisibility(View.GONE);

        }
    }


}
