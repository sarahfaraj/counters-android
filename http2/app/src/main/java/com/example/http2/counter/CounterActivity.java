package com.example.http2.counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.http2.R;
import com.example.http2.counter.Counter;
import com.example.http2.counter.CounterAdapter;
import com.example.http2.counter.CounterTask;

import java.util.ArrayList;

public class CounterActivity extends AppCompatActivity {
    private ListView ll;
    private static final String TAG = "counterTask";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        System.out.println("!!!!!!ONCREATE");

        ll = (ListView) findViewById(R.id.listViewCounter);

        //Create adapter
        ArrayList<Counter> counters = new ArrayList<>();
        CounterAdapter adapter = new CounterAdapter(this, counters);

        ll.setAdapter(adapter);
        //call AsyncTask
        new CounterTask(adapter, this).execute();
        System.out.println("!!!!!!TASK CREATED");



        ll.setFocusable(false);
        ll.setFocusableInTouchMode(false);
        ll.setClickable(false);
        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("!!!!CLICK ON THE BUTTON!!!!!!" + position + " " + id);
            }
        });
    }
}
