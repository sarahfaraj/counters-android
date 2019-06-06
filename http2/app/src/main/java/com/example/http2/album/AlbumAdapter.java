package com.example.http2.album;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.http2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class AlbumAdapter  extends BaseAdapter{

    private Context context;
    private ArrayList<Album> albumsList;

    public AlbumAdapter(Context context, ArrayList<Album> albumsList) {
        this.context = context;
        this.albumsList = albumsList;
    }

    @Override
    public int getCount() {
        return albumsList.size();
    }

    @Override
    public Object getItem(int position) {
        return albumsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<Album> getAlbums() {
        return this.albumsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout linearLayout = null;
        // inflate the layout for each list row
        if (convertView != null) {
            linearLayout = (LinearLayout) convertView;
            System.out.println("!!!!! convertView no null !!!!!" + position);
        }
        else {
            linearLayout = (LinearLayout) LayoutInflater.from(context).
                    inflate(R.layout.row_album, parent, false);
            System.out.println("convertView NULL " + position);

        }

        // get current item to be displayed
        Album currentAlbum = (Album) getItem(position);

        TextView txtAlbumName = (TextView)
                linearLayout.findViewById(R.id.txtAlbumName);
        TextView txtAlbumUrl = (TextView)
                linearLayout.findViewById(R.id.txtAlbumUrl);
        ImageView img = (ImageView) linearLayout.findViewById(R.id.imageView);



        //sets the text for item name and item description from the current item object
        // txtAlbumId.setText(currentAlbum.getId());
        txtAlbumName.setText(currentAlbum.getTitle());
        txtAlbumUrl.setText(currentAlbum.getUrl());
        Picasso.with(context).load(currentAlbum.thumbnailUrl).into(img);
        // retourne la ligne
        return linearLayout;
    }
}
