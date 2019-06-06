package com.example.http2.album;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.http2.R;

import java.util.ArrayList;


public class AlbumActivity extends AppCompatActivity {

    private ListView ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        ll = (ListView) findViewById(R.id.listViewAlbum);

        //Create adapter
        ArrayList<Album> albums = new ArrayList<>();
        AlbumAdapter adapter = new AlbumAdapter(this, albums);

        ll.setAdapter(adapter);
        //call AsyncTask
        new AlbumTask(adapter, this).execute();

        ll.setFocusable(false);
        ll.setFocusableInTouchMode(false);
        ll.setClickable(false);
        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("la");
            }
        });
    }
}
